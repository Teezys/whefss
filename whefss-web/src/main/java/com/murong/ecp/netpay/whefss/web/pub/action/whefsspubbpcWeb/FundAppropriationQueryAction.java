package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;

import com.murong.ecp.netpay.whefss.web.common.utils.*;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.AcctInfoMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.TTransJnlMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.WhefssAccInSendMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.WhefssAccOutSendMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.AcctInfoDO;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.TTransJnlDO;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAccInSendDO;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAccOutSendDO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccInSendAddReqBO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccInSendQueryReqBO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccOutSendQueryReqBO;
import com.murong.ecp.netpay.whefss.web.service.server.WhefssService;
import com.murong.ecp.netpay.whefss.web.service.server.bo.ContractsIncome;
import com.murong.ecp.netpay.whefss.web.service.server.bo.FundsAppropriate;
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

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tianlp
 * 资金拨付录入
 * @version Id: fundAppropriationAction.java, v 0.1 2020/11/1 16:18 tinalp Exp $$
 */
@Service("fundAppropriationQuery")
@Slf4j
public class FundAppropriationQueryAction extends BaseAction {


    @Autowired
    private WhefssService whefssService;

    @Autowired
    private AcctInfoMapper acctInfoMapper;

    /**
     *资金拨付查询
     * @param bizCtx 上下文
     * @return
     * @throws YGException
     */
    public ResultT<WhefssAccInSendDO> execute(YGBizMessageContext bizCtx, WhefssAccOutSendQueryReqBO reqBO) throws YGException {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        Logger msgLog = YGLogger.getLogger(bizMsg);
        msgLog.info("query, reqBO=" + reqBO);
        ValidatorUtil.validateObject(reqBO);
        ResultT resultT = whefssService.fundsAppropriate(bizCtx, reqBO.getNoticeNum());
        FundsAppropriate fundsAppropriate = (FundsAppropriate) resultT.getData();
        if (!CommonMessageCode.SUCC.getMsgCod().equals(resultT.getMsgCod())) {
            resultT.setMsgCod(CommonMessageCode.FAIL);
            return resultT;
        }
        if (!WhefssConstant.HTTP_STATUS.equals(fundsAppropriate.getStatus())) {
            resultT.setMsgCod(CommonMessageCode.FAIL);
            resultT.setMsgInf(fundsAppropriate.getError());
        }
        AcctInfoDO acctInfoDO = new AcctInfoDO();
        acctInfoDO.setSvsAcNo(fundsAppropriate.getAccountNum());
        AcctInfoDO acctInfoqry = acctInfoMapper.selectByPrimaryKey(acctInfoDO);
        if (acctInfoqry == null) {
            resultT.setMsgCod(CommonMessageCode.WH_ACC_NOT_EXIST);
            return resultT;
        }
        if (!WhefssConstant.ACCT_STS_1.equals(acctInfoqry.getAccSts())) {
            resultT.setMsgCod(CommonMessageCode.WH_ACC_STS_NO_WATCH);
            return resultT;
        }
        fundsAppropriate.setAccountName(acctInfoqry.getSvsAcNme());
        resultT.setData(fundsAppropriate);
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }

}
