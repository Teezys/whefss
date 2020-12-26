package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;

import com.murong.ecp.netpay.whefss.web.common.utils.*;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.AcctInfoMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.WhefssYeInfoLogMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.AcctInfoDO;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.BuiOperDO;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAccInfoDO;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssYeInfoLogDO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccCancelInfoQueryReqBO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssBalanceQueryReqBO;
import com.murong.ecp.netpay.whefss.web.service.server.WhefssService;
import com.murong.ecp.netpay.whefss.web.service.server.bo.AccountsRemove;
import com.murong.ecp.netpay.whefss.web.service.server.bo.FundsBalance;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import com.yuangou.ecp.bp.core.common.exception.YGException;
import com.yuangou.ecp.bp.core.common.message.YGBizMessage;
import com.yuangou.ecp.bp.core.common.message.YGBizMessageContext;
import com.yuangou.ecp.bp.core.common.message.YGEDB;
import com.yuangou.ecp.bp.core.common.yglog4j.Logger;
import com.yuangou.ecp.bp.core.common.yglog4j.YGLogger;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tianlp
 * @version Id: accinfocancel.java, v 0.1 2020/11/3 19:13 tinalp Exp $$
 */
@Service("balanceQuery")
@Slf4j
public class BalanceQueryAction extends BaseAction {


    @Autowired
    private WhefssService whefssService;

    @Autowired
    private AcctInfoMapper acctInfoMapper;

    @Autowired
    private WhefssYeInfoLogMapper whefssYeInfoLogMapper;
    /**
     *查询监管账户销户信息
     * @param bizCtx 上下文
     * @return
     * @throws YGException
     */
    public ResultT<WhefssAccInfoDO> execute(YGBizMessageContext bizCtx, WhefssBalanceQueryReqBO reqBO) throws YGException {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        Logger msgLog = YGLogger.getLogger(bizMsg);
        msgLog.info("query, reqBO=" + reqBO);
        ValidatorUtil.validateObject(reqBO);
        ResultT resultT = new ResultT();
        AcctInfoDO acctInfoDO = new AcctInfoDO();
        acctInfoDO.setSvsAcNo(reqBO.getAccountNum());
        AcctInfoDO acctInfoqry = acctInfoMapper.selectByPrimaryKey(acctInfoDO);
        if(acctInfoqry==null){
            resultT.setMsgCod(CommonMessageCode.WH_ACC_NOT_EXIST);
            return resultT;
        }
        if(!WhefssConstant.ACCT_STS_1.equals(acctInfoqry.getAccSts())){
            resultT.setMsgCod(CommonMessageCode.WH_ACC_STS_NO_WATCH);
            return resultT;
        }
        resultT = whefssService.fundsBalance(bizCtx,reqBO.getAccountNum());

        FundsBalance fundsBalance = (FundsBalance)resultT.getData();
        if(!CommonMessageCode.SUCC.getMsgCod().equals(resultT.getMsgCod())){
            resultT.setMsgCod(CommonMessageCode.FAIL);
            return resultT;
        }
        if(!WhefssConstant.HTTP_STATUS.equals(fundsBalance.getStatus())){
            resultT.setMsgCod(CommonMessageCode.FAIL);
            if(!StringUtils.isBlank(fundsBalance.getError())){
                resultT.setMsgInf(fundsBalance.getError());
            }
            return resultT;
        }
        resultT.setData(fundsBalance);
        //登记房管余额信息表
        WhefssYeInfoLogDO whefssYeInfoLogDO = new WhefssYeInfoLogDO();
        BeanUtils.copyProperties(fundsBalance, whefssYeInfoLogDO);
        whefssYeInfoLogDO.setRetcode(fundsBalance.getStatus());
        whefssYeInfoLogDO.setRetmsg(fundsBalance.getError());
        whefssYeInfoLogDO.setTrandate(DateUtil.getTimestamp());
        whefssYeInfoLogDO.setAccountStatus(fundsBalance.getStatus());
        whefssYeInfoLogDO.setBalanceBank(new YGAmt(fundsBalance.getBalanceBank()));
        whefssYeInfoLogDO.setBalanceSystem(new YGAmt(fundsBalance.getBalanceSystem()));
        BuiOperDO buiOperDO = CommonUtils.getBuiOper(bizCtx);
        whefssYeInfoLogDO.setUserId(buiOperDO.getOperId());
        whefssYeInfoLogDO.setUserName(buiOperDO.getOperNm());
        int i = 0;
        WhefssYeInfoLogDO whefssYeInfoLogQry = whefssYeInfoLogMapper.selectByPrimaryKey(whefssYeInfoLogDO);
        if(whefssYeInfoLogQry==null){
            i = whefssYeInfoLogMapper.insert(whefssYeInfoLogDO);
        }else{
            whefssYeInfoLogMapper.updateByPrimaryKey(whefssYeInfoLogDO);
        }

        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }

}
