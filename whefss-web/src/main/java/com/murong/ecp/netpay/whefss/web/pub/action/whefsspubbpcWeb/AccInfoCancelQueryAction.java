package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;

import com.murong.ecp.netpay.whefss.web.common.utils.*;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.AcctInfoMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.AcctInfoDO;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAccInfoDO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccCancelInfoQueryReqBO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccInfoQueryReqBO;
import com.murong.ecp.netpay.whefss.web.service.server.WhefssService;
import com.murong.ecp.netpay.whefss.web.service.server.bo.AccountsOpen;
import com.murong.ecp.netpay.whefss.web.service.server.bo.AccountsRemove;
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
 * @version Id: accinfocancel.java, v 0.1 2020/11/3 19:13 tinalp Exp $$
 */
@Service("accInfoCancelQuery")
@Slf4j
public class AccInfoCancelQueryAction extends BaseAction {


    @Autowired
    private WhefssService whefssService;

    @Autowired
    private AcctInfoMapper acctInfoMapper;
    /**
     *查询监管账户销户信息
     * @param bizCtx 上下文
     * @return
     * @throws YGException
     */
    public ResultT<WhefssAccInfoDO> execute(YGBizMessageContext bizCtx, WhefssAccCancelInfoQueryReqBO reqBO) throws YGException {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        Logger msgLog = YGLogger.getLogger(bizMsg);
        msgLog.info("query, reqBO=" + reqBO);
        ValidatorUtil.validateObject(reqBO);
        ResultT resultT = whefssService.accountsRemove(bizCtx,reqBO.getNoticeNum());

        AccountsRemove accountsRemove = (AccountsRemove)resultT.getData();
        if(!CommonMessageCode.SUCC.getMsgCod().equals(resultT.getMsgCod())){
            resultT.setMsgCod(CommonMessageCode.FAIL);
            return resultT;
        }
        if(!WhefssConstant.HTTP_STATUS.equals(accountsRemove.getStatus())){
            resultT.setMsgCod(CommonMessageCode.FAIL);
            resultT.setMsgInf(accountsRemove.getError());
        }
        AcctInfoDO acctInfoDO = new AcctInfoDO();
        acctInfoDO.setSvsAcNo(accountsRemove.getAccountNum());
        AcctInfoDO acctInfoqry = acctInfoMapper.selectByPrimaryKey(acctInfoDO);
        if(acctInfoqry==null){
            resultT.setMsgCod(CommonMessageCode.WH_ACC_NOT_EXIST);
            return resultT;
        }
        if(!WhefssConstant.ACCT_STS_1.equals(acctInfoqry.getAccSts())){
            resultT.setMsgCod(CommonMessageCode.WH_ACC_STS_NO_WATCH);
            return resultT;
        }
        accountsRemove.setAccountName(acctInfoqry.getSvsAcNme());
        resultT.setData(accountsRemove);
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }


}
