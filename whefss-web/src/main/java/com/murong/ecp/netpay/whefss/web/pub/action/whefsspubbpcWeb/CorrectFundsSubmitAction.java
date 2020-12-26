package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;

import com.murong.ecp.netpay.whefss.web.common.utils.*;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.*;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.*;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccOutSendQueryReqBO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccOutSendUpdateReqBO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccRepealAddReqBO;
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

/**
 * @author tianlp
 * 资金冲正登记
 * @version Id: CorrectFundsSubmitAction.java, v 0.1 2020/11/2 20:39 tinalp Exp $$
 */
@Service("correctFundsSubmit")
@Slf4j
public class CorrectFundsSubmitAction extends BaseAction {

    @Autowired
    private WhefssAccRepealMapper whefssAccRepealMapper;

    @Autowired
    private WhefssAcctInfoService whefssAcctInfoService;

    @Autowired
    private AcctInfoMapper acctInfoMapper;


    public ResultT<WhefssAccRepealDO> execute(YGBizMessageContext bizCtx, WhefssAccRepealAddReqBO reqBO) throws YGException {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        Logger msgLog = YGLogger.getLogger(bizMsg);
        msgLog.info("fundAppropriationSubmit, reqBO=" + edb);
        ValidatorUtil.validateObject(reqBO);
        ResultT resultT = new ResultT();
        WhefssAccRepealDO whefssAccRepealDO = new WhefssAccRepealDO();
        BeanUtils.copyProperties(reqBO, whefssAccRepealDO);

        //账户是否已监管
        AcctInfoDO acctInfoDO = new AcctInfoDO();
        acctInfoDO.setSvsAcNo(reqBO.getAccountNum());
        AcctInfoDO acctInfoDoQry= acctInfoMapper.selectByPrimaryKey(acctInfoDO);
        //未监管
        if(acctInfoDoQry==null){
            resultT.setMsgCod(CommonMessageCode.WH_ACC_STS_NO_WATCH);
            return resultT;
        }

        WhefssAccRepealDO whefssAccRepealQry = whefssAccRepealMapper.selectByPrimaryKey(whefssAccRepealDO);
        if (whefssAccRepealQry != null) {
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
        whefssAccRepealDO.setBankCode(WhefssConstant.BANK_CODE);
        whefssAccRepealDO.setBankName(WhefssConstant.BANK_NAME);
        whefssAccRepealDO.setSendStatus(WhefssConstant.SEND_STATUS_00);
        whefssAccRepealDO.setSysTranid(DateUtil.getTimestamp());
        whefssAccRepealDO.setSysTime(DateUtil.getTimestamp());
        whefssAccRepealDO.setTime(transJnlNds.getExecTime());
        BuiOperDO buiOperDO = CommonUtils.getBuiOper(bizCtx);
        whefssAccRepealDO.setUserId(buiOperDO.getOperId());
        whefssAccRepealDO.setUserName(buiOperDO.getOperNm());

        int i = 0;
        i = whefssAccRepealMapper.insert(whefssAccRepealDO);

        if (i <= 0) {
            resultT.setMsgCod(CommonMessageCode.FAIL);
            return resultT;
        }
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }





}
