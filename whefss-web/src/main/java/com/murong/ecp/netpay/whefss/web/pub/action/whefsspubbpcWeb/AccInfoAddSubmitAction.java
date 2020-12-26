package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;

import com.murong.ecp.netpay.whefss.web.common.utils.*;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.AcctInfoMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.WhefssAccInfoMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.*;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccInfoAddReqBO;
import com.murong.ecp.netpay.whefss.web.service.server.WhefssAcctInfoService;
import com.murong.ecp.netpay.whefss.web.service.server.WhefssService;
import com.murong.ecp.netpay.whefss.web.service.server.bo.AccountOpenNotify;
import com.murong.ecp.netpay.whefss.web.service.server.bo.RespondHead;
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
import org.springframework.stereotype.Service;

/**
 * @author tianlp
 * 监管账户信息录入
 * @version Id: AccInfoAddSubmitAction.java, v 0.1 2020/11/3 16:19 tinalp Exp $$
 */
@Service("accInfoSubmit")
@Slf4j
public class AccInfoAddSubmitAction extends BaseAction {

    @Autowired
    private WhefssAccInfoMapper whefssAccInfoMapper;

    @Autowired
    private WhefssAcctInfoService whefssAcctInfoService;

    @Autowired
    private AcctInfoMapper acctInfoMapper;

    @Autowired
    private WhefssService whefssService;


    public ResultT<WhefssAccInfoDO> execute(YGBizMessageContext bizCtx, WhefssAccInfoAddReqBO reqBO) throws YGException {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        Logger msgLog = YGLogger.getLogger(bizMsg);
        msgLog.info("submit, reqBO=" + edb);
        ResultT resultT = new ResultT();
        ValidatorUtil.validateObject(reqBO);

        //账户是否已监管
        AcctInfoDO acctInfoDO = new AcctInfoDO();
        acctInfoDO.setSvsAcNo(reqBO.getAccountNum());
        AcctInfoDO acctInfoDoQry= acctInfoMapper.selectByPrimaryKey(acctInfoDO);
        //未监管
        if(acctInfoDoQry==null){
            //查询账户信息
            AcctInfoNds acctInfoNds = whefssAcctInfoService.getAcctInfoByNds(bizCtx,acctInfoDO.getSvsAcNo());
            if(!acctInfoNds.isResult()){
                resultT.setMsgCod(CommonMessageCode.NDS_ACC_NOT_EXIST);
                return resultT;
            }
            if(reqBO.getAccountName().equals(acctInfoNds.getAcctCnam())){
                resultT.setMsgCod(CommonMessageCode.NDS_ACC_NAME_ERROR);
                return resultT;
            }
            AcctRemainderMoney remainderMoney = whefssAcctInfoService.getAcctRemainderMoney(bizCtx,acctInfoDO.getSvsAcNo());
            if(!remainderMoney.isResult()){
                resultT.setMsgCod(CommonMessageCode.FAIL);
                return resultT;
            }

            //登记账户信息表
            acctInfoDO.setSvsAcNme(reqBO.getAccountName());//监管账户名称
            acctInfoDO.setFssFlg(WhefssConstant.FSS_FLG);//房管标志
            acctInfoDO.setAccSts(WhefssConstant.ACCT_STS_1);//账户状态
            acctInfoDO.setOpnDte(DateUtil.getDate());//设立监管日期
            BuiOperDO buiOperDO = CommonUtils.getBuiOper(bizCtx);
            acctInfoDO.setUpdOpr(buiOperDO.getOperId());
            acctInfoDO.setOprFlg(buiOperDO.getOrgId());
            acctInfoDO.setOprTlr(buiOperDO.getOperNm());
            int i = 0;
            i= acctInfoMapper.insert(acctInfoDO);
            if(i<=0){
                resultT.setMsgCod(CommonMessageCode.FAIL);
                return resultT;
            }
            WhefssAccInfoDO whefssAccInfoDO = new WhefssAccInfoDO();
            BeanUtils.copyProperties(reqBO, whefssAccInfoDO);
            whefssAccInfoDO.setNoticeNum(reqBO.getNoticeNum());
            whefssAccInfoDO.setPlanAccName(reqBO.getPlanAccName());
            whefssAccInfoDO.setMonitorTime(DateUtil.getTimestamp());
            whefssAccInfoDO.setChangeTime(DateUtil.getTimestamp());
            whefssAccInfoDO.setUserId(buiOperDO.getOperId());
            whefssAccInfoDO.setUserName(buiOperDO.getOperNm());
            whefssAccInfoDO.setBankName(WhefssConstant.BANK_NAME);
            //交易余额
            whefssAccInfoDO.setDayendAmt(new YGAmt(remainderMoney.getActualBal()));

            i = whefssAccInfoMapper.insertSelective(whefssAccInfoDO);
            if(i<=0){
                resultT.setMsgCod(CommonMessageCode.FAIL);
                return resultT;
            }
        }else if(WhefssConstant.ACCT_STS_0.equals(acctInfoDoQry.getAccSts())){
            resultT.setMsgCod(CommonMessageCode.WH_ACC_STS_ERROR);
            return resultT;
        }
        //通知
        AccountOpenNotify accountOpenNotify = new AccountOpenNotify();
        BeanUtils.copyProperties(reqBO, accountOpenNotify);
        accountOpenNotify.setSn(DateUtil.getTimestamp());
        accountOpenNotify.setTime(DateUtil.getTimestamp());
        ResultT resultTNotify = whefssService.accountsOpenNotify(bizCtx,accountOpenNotify);
        RespondHead respondHead = (RespondHead)resultTNotify.getData();
        if(CommonMessageCode.SUCC.getMsgCod().equals(resultTNotify.getMsgCod())&&WhefssConstant.HTTP_STATUS.equals(respondHead.getStatus())){
            resultT.setMsgCod(CommonMessageCode.SUCC);
        }else if(CommonMessageCode.SUCC.getMsgCod().equals(resultTNotify.getMsgCod())&&!WhefssConstant.HTTP_STATUS.equals(respondHead.getStatus())){
            resultT.setMsgCod(CommonMessageCode.SUCC);
            resultT.setMsgInf(respondHead.getError());
        }else{
            resultT.setMsgCod(CommonMessageCode.FAIL);
            if(respondHead.getError()!=null){
                resultT.setMsgInf(respondHead.getError());
            }
        }
        return resultT;
    }
}


