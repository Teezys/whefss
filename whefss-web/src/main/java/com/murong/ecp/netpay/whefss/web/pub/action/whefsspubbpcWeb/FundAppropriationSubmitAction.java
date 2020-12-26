package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;

import com.murong.ecp.netpay.whefss.web.common.utils.*;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.AcctInfoMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.WhefssAccOutSendMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.*;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccOutSendAddReqBO;
import com.murong.ecp.netpay.whefss.web.service.server.WhefssAcctInfoService;
import com.yuangou.ecp.bp.core.common.exception.YGException;
import com.yuangou.ecp.bp.core.common.message.YGBizMessage;
import com.yuangou.ecp.bp.core.common.message.YGBizMessageContext;
import com.yuangou.ecp.bp.core.common.message.YGEDB;
import com.yuangou.ecp.bp.core.common.yglog4j.Logger;
import com.yuangou.ecp.bp.core.common.yglog4j.YGLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tianlp
 * 资金拨付登记
 * @version Id: FundAppropriationSubmitAction.java, v 0.1 2020/11/2 15:00 tinalp Exp $$
 */
@Service("fundAppropriationSubmit")
@Slf4j
public class FundAppropriationSubmitAction extends BaseAction {
    /**
     * 资金拨付流水表
     */
    @Autowired
    private WhefssAccOutSendMapper whefssAccOutSendMapper;


    @Autowired
    private WhefssAcctInfoService whefssAcctInfoService;

    @Autowired
    private AcctInfoMapper acctInfoMapper;

    public ResultT<WhefssAccInSendDO> execute(YGBizMessageContext bizCtx, WhefssAccOutSendAddReqBO reqBO) throws YGException {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        Logger msgLog = YGLogger.getLogger(bizMsg);
        msgLog.info("fundAppropriationSubmit, reqBO=" + edb);
        ValidatorUtil.validateObject(reqBO);
        ResultT resultT = new ResultT();
        WhefssAccOutSendDO whefssAccOutSendDO = new WhefssAccOutSendDO();
        BeanUtils.copyProperties(reqBO, whefssAccOutSendDO);

        //账户是否已监管
        AcctInfoDO acctInfoDO = new AcctInfoDO();
        acctInfoDO.setSvsAcNo(reqBO.getAccountNum());
        AcctInfoDO acctInfoDoQry= acctInfoMapper.selectByPrimaryKey(acctInfoDO);
        //未监管
        if(acctInfoDoQry==null){
            resultT.setMsgCod(CommonMessageCode.WH_ACC_STS_NO_WATCH);
            return resultT;
        }

        WhefssAccOutSendDO whefssAccOutSendQry = whefssAccOutSendMapper.selectByPrimaryKey(whefssAccOutSendDO);
        if (whefssAccOutSendQry != null) {
            resultT.setMsgCod(CommonMessageCode.JNL_REC_DUP);
            return resultT;
        }

        //校验流水号
        TTransJnlDO transJnlDo = new TTransJnlDO();
        transJnlDo.setSvsAcNo(reqBO.getAccountNum());
        transJnlDo.setBnkNumber(reqBO.getSn());
        transJnlDo.setTranAmt(reqBO.getAmount());
        transJnlDo.setTranDate(reqBO.getTime());
        ResultT resultTNds = whefssAcctInfoService.isTransJnl(bizCtx, transJnlDo);
        if (!CommonMessageCode.SUCC.getMsgCod().equals(resultTNds.getMsgCod())) {
            resultT.setMsgCod(resultTNds.getMsgCod());
            resultT.setMsgInf(resultTNds.getMsgInf());
            return resultT;
        }
        //登记
        TTransJnlDO transJnlNds = (TTransJnlDO)resultTNds.getData();
        whefssAccOutSendDO.setBankCode(WhefssConstant.BANK_CODE);
        whefssAccOutSendDO.setBankName(WhefssConstant.BANK_NAME);
        whefssAccOutSendDO.setSendStatus(WhefssConstant.SEND_STATUS_00);
        whefssAccOutSendDO.setSysTranid(DateUtil.getTimestamp());
        whefssAccOutSendDO.setSysTime(DateUtil.getTimestamp());
        whefssAccOutSendDO.setTime(transJnlNds.getExecTime());
        BuiOperDO buiOperDO = CommonUtils.getBuiOper(bizCtx);
        whefssAccOutSendDO.setUserId(buiOperDO.getOperId());
        whefssAccOutSendDO.setUserName(buiOperDO.getOperNm());
        whefssAccOutSendDO.setFlag(WhefssConstant.TXN_STS_S);
        int i = 0;
        i = whefssAccOutSendMapper.insert(whefssAccOutSendDO);

        if (i <= 0) {
            resultT.setMsgCod(CommonMessageCode.FAIL);
            return resultT;
        }
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;

    }




}
