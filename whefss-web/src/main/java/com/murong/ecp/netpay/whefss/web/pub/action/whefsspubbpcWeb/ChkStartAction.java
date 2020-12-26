package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;

import com.murong.ecp.netpay.whefss.web.common.utils.*;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.AcctInfoMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.WhefssAccRefundMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.AcctInfoDO;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.BuiOperDO;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.TTransJnlDO;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAccRefundDO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccRefundAddReqBO;
import com.murong.ecp.netpay.whefss.web.service.server.WhefssAcctInfoService;
import com.murong.ecp.netpay.whefss.web.service.server.chk.ChkTotalService;
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
 * @version Id: RefundFundsSubmitAction.java, v 0.1 2020/11/3 9:56 tinalp Exp $$
 */
@Service("chkStart")
@Slf4j
public class ChkStartAction extends BaseAction {


    @Autowired
    private ChkTotalService chkTotalService;

    public ResultT<WhefssAccRefundDO> execute(YGBizMessageContext bizCtx) throws Exception {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        Logger msgLog = YGLogger.getLogger(bizMsg);
        ResultT  resultT = chkTotalService.chk(bizCtx,DateUtil.getDay(DateUtil.getDate(),-1),true);
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }
}
