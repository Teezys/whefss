package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;

import com.murong.ecp.netpay.whefss.web.common.utils.*;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAccInfoDO;
import com.murong.ecp.netpay.whefss.web.pub.model.ChkSummitReqBO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccInfoQueryReqBO;
import com.murong.ecp.netpay.whefss.web.service.server.WhefssService;
import com.murong.ecp.netpay.whefss.web.service.server.bo.AccountsOpen;
import com.murong.ecp.netpay.whefss.web.service.server.chk.ChkTotalService;
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
@Service("chkSummit")
@Slf4j
public class ChkSummitAction extends BaseAction {

     @Autowired
     private ChkTotalService chkTotalService;
    /**
     *查询监管账户信息
     * @param bizCtx 上下文
     * @return
     * @throws YGException
     */
    public ResultT<WhefssAccInfoDO> execute(YGBizMessageContext bizCtx, ChkSummitReqBO reqBO) throws YGException {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        Logger msgLog = YGLogger.getLogger(bizMsg);
        msgLog.info("query, reqBO=" + reqBO);
        ValidatorUtil.validateObject(reqBO);
        ResultT  resultT = chkTotalService.chk(bizCtx,reqBO.getChkDt(),false);
        return resultT;
    }

}
