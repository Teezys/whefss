package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;

import com.murong.ecp.netpay.whefss.web.common.utils.*;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.AcctInfoMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.AcctInfoDO;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.BuiOperDO;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAccInSendDO;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAccInfoDO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccInSendQueryReqBO;
import com.murong.ecp.netpay.whefss.web.service.server.WhefssService;
import com.murong.ecp.netpay.whefss.web.service.server.bo.AccountsRemove;
import com.murong.ecp.netpay.whefss.web.service.server.bo.ContractsIncome;
import com.yuangou.ecp.bp.core.common.exception.YGException;
import com.yuangou.ecp.bp.core.common.message.YGBizMessage;
import com.yuangou.ecp.bp.core.common.message.YGBizMessageContext;
import com.yuangou.ecp.bp.core.common.message.YGEDB;
import com.yuangou.ecp.bp.core.common.yglog4j.Logger;
import com.yuangou.ecp.bp.core.common.yglog4j.YGLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tianlp
 * 收取房款（进账）
 * @version Id: incomeContractsActIon.java, v 0.1 2020/11/1 9:57 tinalp Exp $$
 */

@Service("incomeContractsQuery")
@Slf4j
public class IncomeContractsQueryAction extends BaseAction {

    @Autowired
    private WhefssService whefssService;

    @Autowired
    private AcctInfoMapper acctInfoMapper;

    /**
     * 收取房款提交查询
     *
     * @param bizCtx 上下文
     * @return
     * @throws YGException
     */
    public ResultT<WhefssAccInSendDO> execute(YGBizMessageContext bizCtx, WhefssAccInSendQueryReqBO reqBO) throws YGException {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        Logger msgLog = YGLogger.getLogger(bizMsg);
        msgLog.info("query, reqBO=" + reqBO);
        ValidatorUtil.validateObject(reqBO);
        ResultT resultT = whefssService.contractsIncome(bizCtx, reqBO.getNoticeNum());
        ContractsIncome contractsIncome = (ContractsIncome) resultT.getData();
        if (!CommonMessageCode.SUCC.getMsgCod().equals(resultT.getMsgCod())) {
            resultT.setMsgCod(CommonMessageCode.FAIL);
            return resultT;
        }
        if (!WhefssConstant.HTTP_STATUS.equals(contractsIncome.getStatus())) {
            resultT.setMsgCod(CommonMessageCode.FAIL);
            resultT.setMsgInf(contractsIncome.getError());
        }
        AcctInfoDO acctInfoDO = new AcctInfoDO();
        acctInfoDO.setSvsAcNo(contractsIncome.getAccountNum());
        AcctInfoDO acctInfoqry = acctInfoMapper.selectByPrimaryKey(acctInfoDO);
        if (acctInfoqry == null) {
            resultT.setMsgCod(CommonMessageCode.WH_ACC_NOT_EXIST);
            return resultT;
        }
        if (!WhefssConstant.ACCT_STS_1.equals(acctInfoqry.getAccSts())) {
            resultT.setMsgCod(CommonMessageCode.WH_ACC_STS_NO_WATCH);
            return resultT;
        }
        contractsIncome.setAccountName(acctInfoqry.getSvsAcNme());
        resultT.setData(contractsIncome);
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }


}
