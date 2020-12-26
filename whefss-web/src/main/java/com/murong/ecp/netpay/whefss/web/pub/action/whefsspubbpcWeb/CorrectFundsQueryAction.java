package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;


import com.murong.ecp.netpay.whefss.web.common.utils.*;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.AcctInfoMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.AcctInfoDO;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAccRepealDO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccRepealQueryReqBO;
import com.murong.ecp.netpay.whefss.web.service.server.WhefssService;
import com.murong.ecp.netpay.whefss.web.service.server.bo.FundsAppropriate;
import com.murong.ecp.netpay.whefss.web.service.server.bo.FundsCorrect;
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
 * @author wanglei
 * 资金冲正
 * @version Id: CorrectFundsAction.java, v 0.1 2020/11/14 17:17 wanglei Exp $$
 */
@Service("correctFundsQuery")
@Slf4j
public class CorrectFundsQueryAction extends BaseAction {


    @Autowired
    private WhefssService whefssService;

    @Autowired
    private AcctInfoMapper acctInfoMapper;

    /**
     *资金冲正查询
     * @param bizCtx 上下文
     * @return
     * @throws YGException
     */
    public ResultT<WhefssAccRepealDO> execute(YGBizMessageContext bizCtx, WhefssAccRepealQueryReqBO reqBO) throws YGException {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        Logger msgLog = YGLogger.getLogger(bizMsg);
        msgLog.info("query, reqBO=" + reqBO);
        ValidatorUtil.validateObject(reqBO);
        ResultT resultT = whefssService.fundsCorrect(bizCtx, reqBO.getNoticeNum());
        FundsCorrect fundsCorrect = (FundsCorrect) resultT.getData();
        if (!CommonMessageCode.SUCC.getMsgCod().equals(resultT.getMsgCod())) {
            resultT.setMsgCod(CommonMessageCode.FAIL);
            return resultT;
        }
        if (!WhefssConstant.HTTP_STATUS.equals(fundsCorrect.getStatus())) {
            resultT.setMsgCod(CommonMessageCode.FAIL);
            resultT.setMsgInf(fundsCorrect.getError());
        }
        AcctInfoDO acctInfoDO = new AcctInfoDO();
        acctInfoDO.setSvsAcNo(fundsCorrect.getAccountNum());
        AcctInfoDO acctInfoqry = acctInfoMapper.selectByPrimaryKey(acctInfoDO);
        if (acctInfoqry == null) {
            resultT.setMsgCod(CommonMessageCode.WH_ACC_NOT_EXIST);
            return resultT;
        }
        if (!WhefssConstant.ACCT_STS_1.equals(acctInfoqry.getAccSts())) {
            resultT.setMsgCod(CommonMessageCode.WH_ACC_STS_NO_WATCH);
            return resultT;
        }
        fundsCorrect.setAccountName(acctInfoqry.getSvsAcNme());
        resultT.setData(fundsCorrect);
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }
}
