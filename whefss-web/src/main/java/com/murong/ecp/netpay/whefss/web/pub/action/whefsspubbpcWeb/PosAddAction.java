package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;

import com.alibaba.fastjson.JSONObject;
import com.murong.ecp.netpay.whefss.web.common.utils.*;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.WhefssAccPosInSendMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.WhefssPosLogMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.TTransJnlDO;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAccPosInSendDO;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssPosLogDO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssPosFileImportReqBo;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssPosLogAddReqBO;
import com.murong.ecp.netpay.whefss.web.service.server.InsertPosFile;
import com.murong.ecp.netpay.whefss.web.service.server.WhefssAcctInfoService;
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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xu_lw on 2020-05-08 17:23:57.
 */
@Service("posAdd")
public class PosAddAction extends BaseAction {

    @Autowired
    private WhefssPosLogMapper posLogMapper;
    @Autowired
    private WhefssAccPosInSendMapper posInSendMapper;

    @Autowired
    private WhefssAcctInfoService whefssAcctInfoService;

    public ResultT execute(YGBizMessageContext bizCtx, WhefssPosLogAddReqBO reqBO) throws YGException {
        Logger logger = YGLogger.getLogger(bizCtx.getCurrentMsg());
        logger.info("export, reqBo=" + JSONObject.toJSON(reqBO));
        ValidatorUtil.validateObject(reqBO);
        ResultT resultT = new ResultT();
        //校验流水号
        TTransJnlDO transJnlDo = new TTransJnlDO();
        transJnlDo.setSvsAcNo(reqBO.getAccountNum());
        transJnlDo.setBnkNumber(reqBO.getSn());
        transJnlDo.setTranAmt(reqBO.getTranAmt());
        transJnlDo.setTranDate(reqBO.getTranDate());

        ResultT resultTNds = whefssAcctInfoService.isTransJnl(bizCtx, transJnlDo);
        if (!CommonMessageCode.SUCC.getMsgCod().equals(resultTNds.getMsgCod())) {
            resultT.setMsgCod(resultTNds.getMsgCod());
            resultT.setMsgInf(resultTNds.getMsgInf());
            return resultT;
        }
        TTransJnlDO transJnlNds = (TTransJnlDO)resultTNds.getData();
        //登记POS
        WhefssPosLogDO posLogDO = new WhefssPosLogDO();
        posLogDO.setTranDate(reqBO.getFileDate());
        posLogDO.setSn(reqBO.getSn());
        posLogDO.setAccountNum(reqBO.getAccountNum());
        int i = posLogMapper.updateSn(posLogDO);
        if(i<=0){
            resultT.setMsgCod(CommonMessageCode.FAIL);
            return resultT;
        }
        List<WhefssPosLogDO> list = posLogMapper.selectByEntity(posLogDO);
        for(int j = 0;j<list.size();j++){
            WhefssAccPosInSendDO accPosInSendDO = new WhefssAccPosInSendDO();
            accPosInSendDO.setNoticeNum(list.get(j).getJksbh());
            accPosInSendDO.setAccountNum(reqBO.getAccountNum());
            accPosInSendDO.setRealIn(list.get(j).getTranAmt());
            accPosInSendDO.setCommission(list.get(j).getPosAmt());
            accPosInSendDO.setSn(reqBO.getSn());
            accPosInSendDO.setTime(transJnlNds.getExecTime());
            accPosInSendDO.setSendStatus(WhefssConstant.SEND_STATUS_00);
            accPosInSendDO.setBankCode(WhefssConstant.BANK_CODE);
            WhefssAccPosInSendDO qry = posInSendMapper.selectByPrimaryKey(accPosInSendDO);
            if(qry == null){
                i =posInSendMapper.insert(accPosInSendDO);
            }else if(WhefssConstant.SEND_STATUS_00.equals(qry.getSendStatus())||WhefssConstant.SEND_STATUS_04.equals(qry.getSendStatus())){
                i = posInSendMapper.updateByPrimaryKey(accPosInSendDO);
            }else{
                continue;
            }
            if(i<=0){
                resultT.setMsgCod(CommonMessageCode.FAIL);
                return resultT;
            }
        }
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }

}
