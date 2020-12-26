package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;

import com.murong.ecp.netpay.whefss.web.common.utils.*;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.AcctInfoMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.TTransJnlMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.WhefssAccInfoMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.WhefssAccRepealMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.*;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccCancelInfoSubmitReqBO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccInfoUpdateReqBO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccRepealAddReqBO;
import com.yuangou.ecp.bp.core.common.exception.YGException;
import com.yuangou.ecp.bp.core.common.message.YGBizMessage;
import com.yuangou.ecp.bp.core.common.message.YGBizMessageContext;
import com.yuangou.ecp.bp.core.common.message.YGEDB;
import com.yuangou.ecp.bp.core.common.yglog4j.Logger;
import com.yuangou.ecp.bp.core.common.yglog4j.YGLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tianlp
 * @version Id: AccInfoCancelSubmit.java, v 0.1 2020/11/3 19:15 tinalp Exp $$
 */
@Service("accInfoCancelSubmit")
@Slf4j
public class AccInfoCancelSubmitAction extends BaseAction {
    /**
     * 武汉监管账户信息表
     */
    @Autowired
    private WhefssAccInfoMapper whefssAccInfoMapper;

    /**
     * 公共监管账户信息表
     */
    @Autowired
    private AcctInfoMapper acctInfoMapper;

    public ResultT execute(YGBizMessageContext bizCtx, WhefssAccCancelInfoSubmitReqBO reqBO) throws YGException {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        Logger msgLog = YGLogger.getLogger(bizMsg);
        msgLog.info("submit, reqBO=" + edb);
        ResultT resultT = new ResultT();
        ValidatorUtil.validateObject(reqBO);
        AcctInfoDO acctInfoDO = new AcctInfoDO();
        acctInfoDO.setSvsAcNo(reqBO.getAccountNum());
        AcctInfoDO acctInfoDOQry = acctInfoMapper.selectByPrimaryKey(acctInfoDO);
        if(acctInfoDOQry==null){
            resultT.setMsgCod(CommonMessageCode.WH_ACC_NOT_EXIST);
            return resultT;
        }
        if(!WhefssConstant.ACCT_STS_1.equals(acctInfoDOQry.getAccSts())){
            resultT.setMsgCod(CommonMessageCode.WH_ACC_STS_NO_WATCH_CANCEL);
            return resultT;
        }
        acctInfoDO.setAccSts(WhefssConstant.ACCT_STS_0);
        acctInfoDO.setCalDte(DateUtil.getDate());
        BuiOperDO buiOperDO = CommonUtils.getBuiOper(bizCtx);
        acctInfoDO.setUpdOpr(buiOperDO.getOperId());
        acctInfoDO.setOprFlg(buiOperDO.getOrgId());
        acctInfoDO.setOprTlr(buiOperDO.getOperNm());
        int i = 0;
        i = acctInfoMapper.updateByPrimaryKey(acctInfoDO);
        if(i<=0){
            resultT.setMsgCod(CommonMessageCode.FAIL);
            return resultT;
        }
        WhefssAccInfoDO whefssAccInfoDO = new WhefssAccInfoDO();
        whefssAccInfoDO.setAccountNum(reqBO.getAccountNum());
        whefssAccInfoDO.setMonitorNum(reqBO.getMonitorNum());
        whefssAccInfoDO.setCloNtcNo(reqBO.getNoticeNum());
        whefssAccInfoDO.setChangeTime(DateUtil.getTimestamp());
        i = whefssAccInfoMapper.updateByPrimaryKey(whefssAccInfoDO);
        if(i<=0){
            resultT.setMsgCod(CommonMessageCode.FAIL);
            return resultT;
        }
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }


}
