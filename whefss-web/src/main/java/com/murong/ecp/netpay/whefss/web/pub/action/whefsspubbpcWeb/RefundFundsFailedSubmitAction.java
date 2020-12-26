package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;

import com.murong.ecp.netpay.whefss.web.common.utils.*;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.AcctInfoMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.WhefssAccRefundMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.AcctInfoDO;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.BuiOperDO;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAccRefundDO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccRefundAddReqBO;
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
 * @version Id: RefundFundsSubmitAction.java, v 0.1 2020/11/3 9:56 tinalp Exp $$
 */
@Service("refundFundsFailedSubmit")
@Slf4j
public class RefundFundsFailedSubmitAction extends BaseAction {

    @Autowired
    private WhefssAccRefundMapper whefssAccRefundMapper;

    @Autowired
    private AcctInfoMapper acctInfoMapper;


    public ResultT<WhefssAccRefundDO> execute(YGBizMessageContext bizCtx, WhefssAccRefundAddReqBO reqBO) throws YGException {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        Logger msgLog = YGLogger.getLogger(bizMsg);
        msgLog.info("incomeContractsSubmit, reqBO=" + edb);
        ValidatorUtil.validateObject(reqBO);
        ResultT resultT = new ResultT();
        WhefssAccRefundDO whefssAccRefundDO = new WhefssAccRefundDO();
        BeanUtils.copyProperties(reqBO, whefssAccRefundDO);

        //账户是否已监管
        AcctInfoDO acctInfoDO = new AcctInfoDO();
        acctInfoDO.setSvsAcNo(reqBO.getAccountNum());
        AcctInfoDO acctInfoDoQry = acctInfoMapper.selectByPrimaryKey(acctInfoDO);
        //未监管
        if (acctInfoDoQry == null) {
            resultT.setMsgCod(CommonMessageCode.WH_ACC_STS_NO_WATCH);
            return resultT;
        }

        WhefssAccRefundDO whefssAccRefundQry = whefssAccRefundMapper.selectByPrimaryKey(whefssAccRefundDO);
        if (whefssAccRefundQry != null) {
            resultT.setMsgCod(CommonMessageCode.JNL_REC_DUP);
            return resultT;
        }


        whefssAccRefundDO.setBankCode(WhefssConstant.BANK_CODE);
        whefssAccRefundDO.setBankName(WhefssConstant.BANK_NAME);
        whefssAccRefundDO.setSendStatus(WhefssConstant.SEND_STATUS_00);
        whefssAccRefundDO.setSysTranid(DateUtil.getTimestamp());
        whefssAccRefundDO.setTime(reqBO.getTime()+"000000");
        whefssAccRefundDO.setSysTime(DateUtil.getTimestamp());
        BuiOperDO buiOperDO = CommonUtils.getBuiOper(bizCtx);
        whefssAccRefundDO.setUserId(buiOperDO.getOperId());
        whefssAccRefundDO.setUserName(buiOperDO.getOperNm());
        whefssAccRefundDO.setFlag(WhefssConstant.TXN_STS_F);
        int i = 0;
        i = whefssAccRefundMapper.insert(whefssAccRefundDO);

        if (i <= 0) {
            resultT.setMsgCod(CommonMessageCode.FAIL);
            return resultT;
        }
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }
}
