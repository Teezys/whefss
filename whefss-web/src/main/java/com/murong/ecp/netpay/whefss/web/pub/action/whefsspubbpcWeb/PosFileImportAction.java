package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;

import com.alibaba.fastjson.JSONObject;
import com.murong.ecp.netpay.whefss.web.common.utils.*;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.WhefssPosLogMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssPosLogDO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssPosFileImportReqBo;
import com.murong.ecp.netpay.whefss.web.service.server.InsertPosFile;
import com.murong.ecp.netpay.whefss.web.service.server.bo.PosFileAcomaEntity;
import com.murong.ecp.netpay.whefss.web.service.server.bo.PosFileMapsEntity;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import com.yuangou.ecp.bp.core.common.exception.YGException;
import com.yuangou.ecp.bp.core.common.message.YGBizMessageContext;
import com.yuangou.ecp.bp.core.common.yglog4j.Logger;
import com.yuangou.ecp.bp.core.common.yglog4j.YGLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xu_lw on 2020-05-08 17:23:57.
 */
@Service("posFileImport")
public class PosFileImportAction extends BaseAction {


    @Value("${MAPSFILE}")
    private String fileMaps;
    //币种
    @Value("${ACOMAFILE}")
    private String fileAcoma;
    @Autowired
    private WhefssPosLogMapper posLogMapper;

    public ResultT execute(YGBizMessageContext bizCtx, WhefssPosFileImportReqBo reqBO) throws YGException {
        Logger logger = YGLogger.getLogger(bizCtx.getCurrentMsg());
        logger.info("export, reqBo=" + JSONObject.toJSON(reqBO));
        ValidatorUtil.validateObject(reqBO);

        ResultT resultT = new ResultT();
        List<PosFileMapsEntity> records = new ArrayList<>();
        List<PosFileAcomaEntity> acomas = new ArrayList<>();
        List<WhefssPosLogDO> posLogList = new ArrayList<>();
        String fileDt = reqBO.getFileDt().substring(2,8);
        String filePathMaps = fileMaps.replace(WhefssConstant.FILE_DT,fileDt);
        String filePathAcoma = fileAcoma.replace(WhefssConstant.FILE_DT,fileDt);
        try {
            //解析Maps文件
            InputStream isMaps = new FileInputStream(filePathMaps);
            InputStream isAcoma = new FileInputStream(filePathAcoma);
            InsertPosFile.readMaps(isMaps,records);
            InsertPosFile.readAcoma(isAcoma,acomas);
        }catch (Exception e){
            resultT.setMsgCod(CommonMessageCode.FILE_NOT_EXIST);
            return resultT;
        }
        if(records.size()<=0 && acomas.size()<=0){
            resultT.setMsgCod(CommonMessageCode.FILE_NOT_EXIST);
            return resultT;
        }
        int k = 0;
        posLogMapper.deleteByTranDate(reqBO.getFileDt());
        for (int i = 0;i<records.size();i++){
            WhefssPosLogDO posLogDO = new WhefssPosLogDO();
            posLogDO.setNoticeNum(records.get(i).getTranSeq());
            posLogDO.setZdhNum(records.get(i).getTermId());
            posLogDO.setTranDate(records.get(i).getTranDt());
            posLogDO.setTranTime(records.get(i).getTranTm());
            posLogDO.setPayAcc(records.get(i).getPaccNo());
            posLogDO.setJksbh(records.get(i).getConNo());
            posLogDO.setAccountNum(records.get(i).getAccNo());
            posLogDO.setPayNum(records.get(i).getFkqc());
            posLogDO.setJksje(records.get(i).getStdAmt());
            if(records.get(i).getOrtrnSeq().length()==6){
                posLogDO.setNoticeNum(records.get(i).getOrtrnSeq());
                k = posLogMapper.deleteByEntity(posLogDO);
            }else{
                k = posLogMapper.insertSelective(posLogDO);
            }
            if(k<=0){
                resultT.setMsgCod(CommonMessageCode.FILE_NOT_EXIST);
                return resultT;
            }
        }

        for (int i = 0;i<acomas.size();i++){
            if((!WhefssConstant.FLAG_R.equals(acomas.get(i).getCzbz()))
                    &&(!WhefssConstant.FLAG_C.equals(acomas.get(i).getCxbz()))
                    &&(!WhefssConstant.FLAG_C.equals(acomas.get(i).getAmtflag()))){
                WhefssPosLogDO posLogDO = new WhefssPosLogDO();
                posLogDO.setPosAmt(new YGAmt(acomas.get(i).getPosAmt()));
                posLogDO.setTranAmt(new YGAmt(acomas.get(i).getPayAmt()));
                posLogDO.setTranDate(reqBO.getFileDt());
                posLogDO.setNoticeNum(acomas.get(i).getTranSeq());
                posLogDO.setZdhNum(acomas.get(i).getTermId());
                posLogDO.setPayAcc(acomas.get(i).getPaccNo());
                posLogMapper.updateAcoma(posLogDO);
            }
        }

        //删除冲正报文中没有记录金额的数据,注意：此为冲正报文，请在coma文件中查找冲正
        posLogMapper.deleteByTranAmt(reqBO.getFileDt());
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }

}
