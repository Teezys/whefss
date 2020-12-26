package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;

import com.murong.ecp.netpay.whefss.web.common.utils.*;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.*;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.*;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccOutSendUpdateReqBO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAjdkSndAddReqBO;
import com.murong.ecp.netpay.whefss.web.service.server.WhefssAcctInfoService;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import com.yuangou.ecp.bp.core.common.exception.YGException;
import com.yuangou.ecp.bp.core.common.message.YGBizMessage;
import com.yuangou.ecp.bp.core.common.message.YGBizMessageContext;
import com.yuangou.ecp.bp.core.common.message.YGEDB;
import com.yuangou.ecp.bp.core.common.yglog4j.Logger;
import com.yuangou.ecp.bp.core.common.yglog4j.YGLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @author tianlp
 * 按揭贷款登记
 * @version Id: MortgageLoanSubmitAction.java, v 0.1 2020/11/3 15:18 tinalp Exp $$
 */
@Service("mortgageLoanSubmit")
@Slf4j
public class MortgageLoanSubmitAction  extends BaseAction {
    /**
     * 按揭贷款流水表
     */
    @Autowired
    private WhefssAjdkSndMapper whefssAjdkSndMapper;

    @Autowired
    private WhefssAcctInfoService whefssAcctInfoService;

    @Autowired
    private AcctInfoMapper acctInfoMapper;

    @Autowired
    private WhefssAccInfoMapper whefssAccInfoMapper;


    public ResultT<WhefssAccInSendDO> execute(YGBizMessageContext bizCtx, WhefssAjdkSndAddReqBO reqBO) throws YGException {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        Logger msgLog = YGLogger.getLogger(bizMsg);
        msgLog.info("incomeContractsSubmit, reqBO=" + edb);
        ValidatorUtil.validateObject(reqBO);
        ResultT resultT = new ResultT();
        WhefssAjdkSndDO whefssAjdkSndDO = new WhefssAjdkSndDO();
        BeanUtils.copyProperties(reqBO, whefssAjdkSndDO);

        //账户是否已监管
        AcctInfoDO acctInfoDO = new AcctInfoDO();
        acctInfoDO.setSvsAcNo(reqBO.getAccountNum());
        AcctInfoDO acctInfoDoQry= acctInfoMapper.selectByPrimaryKey(acctInfoDO);
        //未监管
        if(acctInfoDoQry==null){
            resultT.setMsgCod(CommonMessageCode.WH_ACC_STS_NO_WATCH);
            return resultT;
        }

        WhefssAjdkSndDO whefssAjdkSndQry = whefssAjdkSndMapper.selectByPrimaryKey(whefssAjdkSndDO);
        if (whefssAjdkSndQry != null) {
            resultT.setMsgCod(CommonMessageCode.JNL_REC_DUP);
            return resultT;
        }

        //校验流水号
        TTransJnlDO transJnlDo = new TTransJnlDO();
        transJnlDo.setSvsAcNo(reqBO.getAccountNum());
        transJnlDo.setBnkNumber(reqBO.getSn());
        transJnlDo.setTranDate(reqBO.getTime());
        ResultT resultTNds = whefssAcctInfoService.isTransJnl(bizCtx, transJnlDo);
        if (!CommonMessageCode.SUCC.getMsgCod().equals(resultTNds.getMsgCod())) {
            resultT.setMsgCod(resultTNds.getMsgCod());
            resultT.setMsgInf(resultTNds.getMsgInf());
            return resultT;
        }

        TTransJnlDO transJnlNds = (TTransJnlDO)resultTNds.getData();
        //判断附言格式:HTBH[缴款通知书编号]（如：HTBH012123456701），缴款通知书编号长度为13位
        String text = transJnlNds.getNarrative();
        //Pattern pattern= compile(WhefssConstant.PATTERN_NARRATIVE);
        if(text==null){
            resultT.setMsgCod(CommonMessageCode.NDS_JNL_NARRATIVE_ERROR);
            return resultT;
        }
      /*  Matcher match=pattern.matcher(text);
        boolean flag = match.matches();*/
      int a = text.indexOf(WhefssConstant.PATTERN_NARRATIVE);
        if(!(text.indexOf(WhefssConstant.PATTERN_NARRATIVE)!=-1)){
            resultT.setMsgCod(CommonMessageCode.NDS_JNL_NARRATIVE_ERROR);
            return resultT;
        }

        whefssAjdkSndDO.setBankCode(WhefssConstant.BANK_CODE);

        whefssAjdkSndDO.setSendStatus(WhefssConstant.SEND_STATUS_00);
        whefssAjdkSndDO.setRealIn(transJnlNds.getTranAmt());
        whefssAjdkSndDO.setSysTranid(DateUtil.getTimestamp());
        whefssAjdkSndDO.setTime(transJnlNds.getExecTime());
        whefssAjdkSndDO.setSysTime(DateUtil.getTimestamp());
        BuiOperDO buiOperDO = CommonUtils.getBuiOper(bizCtx);
        whefssAjdkSndDO.setUserId(buiOperDO.getOperId());
        whefssAjdkSndDO.setUserName(buiOperDO.getOperNm());
        WhefssAccInfoDO whefssAccInfoDo = new WhefssAccInfoDO();
        whefssAccInfoDo.setAccountNum(reqBO.getAccountNum());
        WhefssAccInfoDO whefssAccInfoQry = whefssAccInfoMapper.selectByPrimaryKey(whefssAccInfoDo);
        if(whefssAccInfoQry==null){
            resultT.setMsgCod(CommonMessageCode.FAIL);
            return resultT;
        }
        whefssAjdkSndDO.setMonitorNum(whefssAccInfoQry.getMonitorNum());
        //登记
        int i = 0;
        i = whefssAjdkSndMapper.insert(whefssAjdkSndDO);

        if (i <= 0) {
            resultT.setMsgCod(CommonMessageCode.FAIL);
            return resultT;
        }
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }

}
