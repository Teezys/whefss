package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;

import com.murong.ecp.netpay.whefss.web.common.utils.*;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.AcctInfoMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.TTransJnlMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.WhefssAccInSendMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.*;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccInSendAddReqBO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccOutSendQueryReqBO;
import com.murong.ecp.netpay.whefss.web.service.server.WhefssAcctInfoService;
import com.murong.ecp.netpay.whefss.web.service.server.WhefssService;
import com.sun.xml.internal.rngom.parse.host.Base;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import com.yuangou.ecp.bp.core.common.exception.YGException;
import com.yuangou.ecp.bp.core.common.message.YGBizMessage;
import com.yuangou.ecp.bp.core.common.message.YGBizMessageContext;
import com.yuangou.ecp.bp.core.common.message.YGEDB;
import com.yuangou.ecp.bp.core.common.yglog4j.Logger;
import com.yuangou.ecp.bp.core.common.yglog4j.YGLogger;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tianlp
 * @version Id: IncomeContractsSubmitActIon.java, v 0.1 2020/11/2 20:37 tinalp Exp $$
 */
@Service("incomeContractsSubmit")
@Slf4j
public class IncomeContractsSubmitAction extends BaseAction{

    @Autowired
    private WhefssAccInSendMapper whefssAccInSendMapper;

    @Autowired
    private WhefssAcctInfoService whefssAcctInfoService;

    @Autowired
    private AcctInfoMapper acctInfoMapper;

    /**
     * @param bizCtx 上下文
     * @return
     * @throws YGException
     */
    public ResultT execute(YGBizMessageContext bizCtx, WhefssAccInSendAddReqBO reqBO) throws YGException {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        Logger msgLog = YGLogger.getLogger(bizMsg);
        msgLog.info("incomeContractsSubmit, reqBO=" + edb);
        ValidatorUtil.validateObject(reqBO);
        ResultT resultT = new ResultT();
        WhefssAccInSendDO whefssAccInSendDO = new WhefssAccInSendDO();
        BeanUtils.copyProperties(reqBO, whefssAccInSendDO);

        //账户是否已监管
        AcctInfoDO acctInfoDO = new AcctInfoDO();
        acctInfoDO.setSvsAcNo(reqBO.getAccountNum());
        AcctInfoDO acctInfoDoQry= acctInfoMapper.selectByPrimaryKey(acctInfoDO);
        //未监管
        if(acctInfoDoQry==null){
            resultT.setMsgCod(CommonMessageCode.WH_ACC_STS_NO_WATCH);
            return resultT;
        }

        WhefssAccInSendDO whefssAccInSendQry = whefssAccInSendMapper.selectByPrimaryKey(whefssAccInSendDO);
        if (whefssAccInSendQry != null) {
            resultT.setMsgCod(CommonMessageCode.JNL_REC_DUP);
            return resultT;
        }

        //校验流水号
        TTransJnlDO transJnlDo = new TTransJnlDO();
        transJnlDo.setSvsAcNo(reqBO.getAccountNum());
        transJnlDo.setBnkNumber(reqBO.getSn());
        transJnlDo.setTranAmt(reqBO.getRealIn());
        transJnlDo.setTranDate(reqBO.getTime());
        ResultT resultTNds = whefssAcctInfoService.isTransJnl(bizCtx, transJnlDo);
        if (!CommonMessageCode.SUCC.getMsgCod().equals(resultTNds.getMsgCod())) {
            resultT.setMsgCod(resultTNds.getMsgCod());
            resultT.setMsgInf(resultTNds.getMsgInf());
            return resultT;
        }
        //登记
        TTransJnlDO transJnlNds = (TTransJnlDO)resultTNds.getData();
        whefssAccInSendDO.setBankCode(WhefssConstant.BANK_CODE);
        whefssAccInSendDO.setBankName(WhefssConstant.BANK_NAME);
        whefssAccInSendDO.setSendStatus(WhefssConstant.SEND_STATUS_00);
        whefssAccInSendDO.setSysTranid(DateUtil.getTimestamp());
        whefssAccInSendDO.setTime(transJnlNds.getExecTime());
        whefssAccInSendDO.setSysTime(DateUtil.getTimestamp());
        BuiOperDO buiOperDO = CommonUtils.getBuiOper(bizCtx);
        whefssAccInSendDO.setUserId(buiOperDO.getOperId());
        whefssAccInSendDO.setUserName(buiOperDO.getOperNm());
        int i = 0;
        i = whefssAccInSendMapper.insert(whefssAccInSendDO);

        if (i <= 0) {
            resultT.setMsgCod(CommonMessageCode.FAIL);
            return resultT;
        }
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }
}
