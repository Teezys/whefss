package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;

import com.murong.ecp.netpay.whefss.web.common.utils.*;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.AcctInfoMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.AcctInfoDO;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAjdkSndDO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAjdkSndQueryReqBO;
import com.murong.ecp.netpay.whefss.web.service.server.WhefssService;
import com.murong.ecp.netpay.whefss.web.service.server.bo.ContractsLoanInfo;
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
 * 按揭贷款流水信息
 * @version Id: MortgageLoanAction.java, v 0.1 2020/11/2 10:00 tinalp Exp $$
 */
@Service("mortgageLoanQuery")
@Slf4j
public class MortgageLoanQueryAction extends BaseAction {

    @Autowired
    private WhefssService whefssService;

    @Autowired
    private AcctInfoMapper acctInfoMapper;

    /**
     *按揭贷款查询
     * @param bizCtx 上下文
     * @return
     * @throws YGException
     */
    public ResultT<WhefssAjdkSndDO> execute(YGBizMessageContext bizCtx, WhefssAjdkSndQueryReqBO reqBO) throws YGException {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        Logger msgLog = YGLogger.getLogger(bizMsg);
        msgLog.info("query, reqBO=" + reqBO);
        ValidatorUtil.validateObject(reqBO);
        ResultT resultT = whefssService.contractsLoanInfo(bizCtx, reqBO.getNoticeNum());
        ContractsLoanInfo contractsLoanInfo = (ContractsLoanInfo) resultT.getData();
        if (!CommonMessageCode.SUCC.getMsgCod().equals(resultT.getMsgCod())) {
            resultT.setMsgCod(CommonMessageCode.FAIL);
            return resultT;
        }
        if (!WhefssConstant.HTTP_STATUS.equals(contractsLoanInfo.getStatus())) {
            resultT.setMsgCod(CommonMessageCode.FAIL);
            resultT.setMsgInf(contractsLoanInfo.getError());
        }
        AcctInfoDO acctInfoDO = new AcctInfoDO();
        acctInfoDO.setSvsAcNo(contractsLoanInfo.getAccountNum());
        AcctInfoDO acctInfoqry = acctInfoMapper.selectByPrimaryKey(acctInfoDO);
        if (acctInfoqry == null) {
            resultT.setMsgCod(CommonMessageCode.WH_ACC_NOT_EXIST);
            return resultT;
        }
        if (!WhefssConstant.ACCT_STS_1.equals(acctInfoqry.getAccSts())) {
            resultT.setMsgCod(CommonMessageCode.WH_ACC_STS_NO_WATCH);
            return resultT;
        }
        contractsLoanInfo.setAccountName(acctInfoqry.getSvsAcNme());
        resultT.setData(contractsLoanInfo);
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }





}
