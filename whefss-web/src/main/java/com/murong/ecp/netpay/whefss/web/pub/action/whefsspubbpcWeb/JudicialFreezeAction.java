package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;

import com.murong.ecp.netpay.whefss.web.common.utils.*;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.*;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.*;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccRepealAddReqBO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAmpTranidAddReqBO;
import com.yuangou.ecp.bp.comp.expr.YGExpUtil;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tianlp
 * 司法操作登记
 * @version Id: AccJudicialFreeze.java, v 0.1 2020/11/5 15:11 tinalp Exp $$
 */
@Service("accJudicialFreeze")
@Slf4j
public class JudicialFreezeAction<main> extends BaseAction {
    /**
     * 司法信息表
     */
    @Autowired
    private WhefssAmpTranidMapper whefssAmpTranidMapper;

    /**
     * 查看账户在监管装态
     */
    @Autowired
    private AcctInfoMapper acctInfoMapper;

    @Autowired
    private BuiOperMapper buiOperMapper;


    /**
     * 将消息录入到监管账户信息表中
     *
     * @param bizCtx
     * @return
     * @throws YGException
     */
    public ResultT<WhefssAccRepealDO> execute(YGBizMessageContext bizCtx, WhefssAmpTranidAddReqBO reqBO) throws YGException {
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

        //判断授权柜员用户名密码
        BuiOperDO buiOperDO = CommonUtils.getBuiOper(bizCtx);
        BuiOperDO buiOper = new BuiOperDO();
        buiOper.setOperNo(reqBO.getLicenseNo());
        buiOper.setOrgId(buiOperDO.getOrgId());
        BuiOperDO buiOperQry = buiOperMapper.selectByOperNo(buiOper);
        if(buiOperQry==null){
            resultT.setMsgCod(CommonMessageCode.OPER_ERROR);
            return resultT;
        }
        String encryptPwd = YGExpUtil.sha256(AES.decryptStr(reqBO.getLicensePwd()) + buiOperQry.getPwdSalt());
        if(!buiOperQry.getOperPwd().equals(encryptPwd)){
            resultT.setMsgCod(CommonMessageCode.OPER_ERROR);
            return resultT;
        }
        //判断授权柜员与当前柜员是否一致
        if(buiOperDO.getOperId().equals(buiOperQry.getOperId())){
            resultT.setMsgCod(CommonMessageCode.OPER_DUP_ERROR);
            return resultT;
        }
        //重复性校验


        //登记
        WhefssAmpTranidDO whefssAmpTranidDO = new WhefssAmpTranidDO();

        BeanUtils.copyProperties(reqBO, whefssAmpTranidDO);
        whefssAmpTranidDO.setAmpTranid(DateUtil.getTimestamp());
        whefssAmpTranidDO.setAmpTime(DateUtil.getTimestamp());
        whefssAmpTranidDO.setTime(DateUtil.getTimestamp());
        whefssAmpTranidDO.setSendStatus(WhefssConstant.SEND_STATUS_00);
        whefssAmpTranidDO.setUserId(buiOperDO.getOperId());
        whefssAmpTranidDO.setUserName(buiOperDO.getOperNm());
        if(reqBO.getAmount()==null||StringUtils.isBlank(reqBO.getAmount().toString())){
            whefssAmpTranidDO.setAmount(new YGAmt("0"));
        }
        whefssAmpTranidDO.setLicenseNum(buiOperQry.getOperId());
        whefssAmpTranidDO.setLicenseName(buiOperQry.getOperNm());
        int i = 0;
        i =  whefssAmpTranidMapper.insert(whefssAmpTranidDO);
        if(i<=0){
            resultT.setMsgCod(CommonMessageCode.FAIL);
            return resultT;
        }
        //报送时间
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }

    public static void main(String[] args) {
        String password = "";
        String pwdSalt = "13695";
        password = AES.decryptStr("3c79799c37183c15f48f6131a63965e4");
        System.out.println("password:" + password);

        String encryptPwd = null;
        try {
            encryptPwd = YGExpUtil.sha256(password + pwdSalt);
        } catch (YGException e) {
            e.printStackTrace();
        }
        System.out.println(encryptPwd);
    }
}
