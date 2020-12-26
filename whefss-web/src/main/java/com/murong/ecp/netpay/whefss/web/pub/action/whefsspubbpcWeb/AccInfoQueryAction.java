package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;

import com.murong.ecp.netpay.whefss.web.common.utils.*;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAccInSendDO;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAccInfoDO;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAccOutSendDO;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssChkDetailDO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccInfoQueryReqBO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccOutSendQueryReqBO;
import com.murong.ecp.netpay.whefss.web.service.server.WhefssService;
import com.murong.ecp.netpay.whefss.web.service.server.bo.AccountsOpen;
import com.murong.ecp.netpay.whefss.web.service.server.chk.ChkTotalService;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
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
 * 监管账户登记
 * @version Id: AccInfoAddAction.java, v 0.1 2020/11/3 15:43 tinalp Exp $$
 */
@Service("accInfoQuery")
@Slf4j
public class AccInfoQueryAction extends BaseAction {


     @Autowired
     private WhefssService whefssService;
     @Autowired
     private ChkTotalService chkTotalService;
    /**
     *查询监管账户信息
     * @param bizCtx 上下文
     * @return
     * @throws YGException
     */
    public ResultT<WhefssAccInfoDO> execute(YGBizMessageContext bizCtx, WhefssAccInfoQueryReqBO reqBO) throws YGException {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        Logger msgLog = YGLogger.getLogger(bizMsg);
        msgLog.info("query, reqBO=" + reqBO);
        ValidatorUtil.validateObject(reqBO);
        //调房管资金拨付信息查询接口
        ResultT resultT = whefssService.accountsOpen(bizCtx,reqBO.getNoticeNum());
        AccountsOpen accountsOpen = (AccountsOpen)resultT.getData();
        if(!CommonMessageCode.SUCC.getMsgCod().equals(resultT.getMsgCod())){
            resultT.setMsgCod(CommonMessageCode.FAIL);
            return resultT;
        }
        if(!WhefssConstant.HTTP_STATUS.equals(accountsOpen.getStatus())){
            resultT.setMsgCod(CommonMessageCode.FAIL);
            resultT.setMsgInf(accountsOpen.getError());
        }
        resultT.setData(accountsOpen);
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }

}
