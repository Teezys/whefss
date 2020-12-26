package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;

import com.murong.ecp.netpay.whefss.web.common.utils.*;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.AcctInfoMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.TTransJnlMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.WhefssAmpTranidMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.WhefssInterestSndMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.*;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccInSendAddReqBO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssInterestSndAddReqBO;
import com.murong.ecp.netpay.whefss.web.service.server.WhefssAcctInfoService;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import com.yuangou.ecp.bp.core.common.exception.YGException;
import com.yuangou.ecp.bp.core.common.message.YGBizMessage;
import com.yuangou.ecp.bp.core.common.message.YGBizMessageContext;
import com.yuangou.ecp.bp.core.common.message.YGEDB;
import com.yuangou.ecp.bp.core.common.yglog4j.Logger;
import com.yuangou.ecp.bp.core.common.yglog4j.YGLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tianlp
 * @version Id: InterestFunds.java, v 0.1 2020/11/5 20:02 tinalp Exp $$
 */
@Service("accInterestFunds")
@Slf4j
public class InterestFundsAction extends BaseAction {

    @Autowired
    private WhefssInterestSndMapper whefssInterestSndMapper;

    @Autowired
    private WhefssAcctInfoService whefssAcctInfoService;

    @Autowired
    private AcctInfoMapper acctInfoMapper;

    /**
     * 将消息录入到年费/利息表
     * @param bizCtx
     * @return
     * @throws YGException
     */
    public ResultT execute(YGBizMessageContext bizCtx, WhefssInterestSndAddReqBO reqBO) throws YGException {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        Logger msgLog = YGLogger.getLogger(bizMsg);
        msgLog.info("interestFundsSubmit, reqBO=" + edb);
        ValidatorUtil.validateObject(reqBO);
        ResultT resultT = new ResultT();
        WhefssInterestSndDO whefssInterestSndDO = new WhefssInterestSndDO();
        BeanUtils.copyProperties(reqBO, whefssInterestSndDO);

        //账户是否已监管
        AcctInfoDO acctInfoDO = new AcctInfoDO();
        acctInfoDO.setSvsAcNo(reqBO.getAccountNum());
        AcctInfoDO acctInfoDoQry= acctInfoMapper.selectByPrimaryKey(acctInfoDO);
        //未监管
        if(acctInfoDoQry==null){
            resultT.setMsgCod(CommonMessageCode.WH_ACC_STS_NO_WATCH);
            return resultT;
        }

        WhefssInterestSndDO whefssInterestSndQry = whefssInterestSndMapper.selectByPrimaryKey(whefssInterestSndDO);
        if (whefssInterestSndQry != null) {
            resultT.setMsgCod(CommonMessageCode.JNL_REC_DUP);
            return resultT;
        }

        //校验流水号
        TTransJnlDO transJnlDo = new TTransJnlDO();
        transJnlDo.setSvsAcNo(reqBO.getAccountNum());
        transJnlDo.setBnkNumber(reqBO.getSn());
        transJnlDo.setTranAmt(reqBO.getInterest());
        transJnlDo.setTranDate(reqBO.getTime());
        if(reqBO.getType().equals("1")){
            transJnlDo.setDcFlg(WhefssConstant.DC_FLG_D);
        }else{
            transJnlDo.setDcFlg(WhefssConstant.DC_FLG_C);
        }
        ResultT resultTNds = whefssAcctInfoService.isTransJnl(bizCtx, transJnlDo);
        if (!CommonMessageCode.SUCC.getMsgCod().equals(resultTNds.getMsgCod())) {
            resultT.setMsgCod(resultTNds.getMsgCod());
            resultT.setMsgInf(resultTNds.getMsgInf());
            return resultT;
        }
        //登记
        TTransJnlDO transJnlNds = (TTransJnlDO)resultTNds.getData();
        whefssInterestSndDO.setBankCode(WhefssConstant.BANK_CODE);
        whefssInterestSndDO.setSendStatus(WhefssConstant.SEND_STATUS_00);
        whefssInterestSndDO.setSysTranid(DateUtil.getTimestamp());
        whefssInterestSndDO.setSysTime(DateUtil.getTimestamp());
        whefssInterestSndDO.setTime(transJnlNds.getExecTime());
        BuiOperDO buiOperDO = CommonUtils.getBuiOper(bizCtx);
        whefssInterestSndDO.setUserId(buiOperDO.getOperId());
        whefssInterestSndDO.setUserName(buiOperDO.getOperNm());

        int i = 0;
        i = whefssInterestSndMapper.insert(whefssInterestSndDO);

        if (i <= 0) {
            resultT.setMsgCod(CommonMessageCode.FAIL);
            return resultT;
        }
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;


    }

}
