package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;

import com.murong.ecp.netpay.whefss.web.common.utils.*;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.AcctInfoMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.TTransJnlMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.WhefssAccRefundMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.*;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccInSendAddReqBO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccOutSendQueryReqBO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccRefundQueryReqBO;
import com.murong.ecp.netpay.whefss.web.service.server.WhefssService;
import com.murong.ecp.netpay.whefss.web.service.server.bo.FundsCorrect;
import com.murong.ecp.netpay.whefss.web.service.server.bo.FundsRefund;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import com.yuangou.ecp.bp.core.common.exception.YGException;
import com.yuangou.ecp.bp.core.common.message.YGBizMessage;
import com.yuangou.ecp.bp.core.common.message.YGBizMessageContext;
import com.yuangou.ecp.bp.core.common.message.YGEDB;
import com.yuangou.ecp.bp.core.common.yglog4j.Logger;
import com.yuangou.ecp.bp.core.common.yglog4j.YGLogger;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author tianlp
 * 退款信息
 * @version Id: refundFundsAction.java, v 0.1 2020/11/1 16:49 tinalp Exp $$
 */
@Service("refundFundsQuery")
@Slf4j
public class RefundFundsQueryAction extends BaseAction {



    @Autowired
    private WhefssService whefssService;

    @Autowired
    private AcctInfoMapper acctInfoMapper;

    /**
     *资金退款查询
     * @param bizCtx 上下文
     * @return
     * @throws YGException
     */
    public ResultT<WhefssAccRefundDO> execute(YGBizMessageContext bizCtx, WhefssAccRefundQueryReqBO reqBO) throws YGException {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        Logger msgLog = YGLogger.getLogger(bizMsg);
        msgLog.info("query, reqBO=" + reqBO);
        ValidatorUtil.validateObject(reqBO);
        ResultT resultT = whefssService.fundsRefund(bizCtx, reqBO.getNoticeNum());
        FundsRefund fundsRefund = (FundsRefund) resultT.getData();
        if (!CommonMessageCode.SUCC.getMsgCod().equals(resultT.getMsgCod())) {
            resultT.setMsgCod(CommonMessageCode.FAIL);
            return resultT;
        }
        if (!WhefssConstant.HTTP_STATUS.equals(fundsRefund.getStatus())) {
            resultT.setMsgCod(CommonMessageCode.FAIL);
            resultT.setMsgInf(fundsRefund.getError());
        }
        AcctInfoDO acctInfoDO = new AcctInfoDO();
        acctInfoDO.setSvsAcNo(fundsRefund.getAccountNum());
        AcctInfoDO acctInfoqry = acctInfoMapper.selectByPrimaryKey(acctInfoDO);
        if (acctInfoqry == null) {
            resultT.setMsgCod(CommonMessageCode.WH_ACC_NOT_EXIST);
            return resultT;
        }
        if (!WhefssConstant.ACCT_STS_1.equals(acctInfoqry.getAccSts())) {
            resultT.setMsgCod(CommonMessageCode.WH_ACC_STS_NO_WATCH);
            return resultT;
        }
        fundsRefund.setAccountName(acctInfoqry.getSvsAcNme());
        resultT.setData(fundsRefund);
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }





}
