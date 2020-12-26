package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;

import com.murong.ecp.netpay.whefss.web.common.utils.*;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.AcctInfoMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.WhefssAccHaveAcceptedLogMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.AcctInfoDO;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAccHaveAcceptedLogDO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccHaveAcceptedLogListReqBO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccHaveAcceptedLogQueryReqBO;
import com.murong.ecp.netpay.whefss.web.service.server.WhefssService;
import com.murong.ecp.netpay.whefss.web.service.server.bo.FundsDetail;
import com.murong.ecp.netpay.whefss.web.service.server.bo.FundsDetailList;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import com.yuangou.ecp.bp.core.common.exception.YGException;
import com.yuangou.ecp.bp.core.common.message.YGBizMessage;
import com.yuangou.ecp.bp.core.common.message.YGBizMessageContext;
import com.yuangou.ecp.bp.core.common.message.YGEDB;
import com.yuangou.ecp.bp.core.common.yglog4j.Logger;
import com.yuangou.ecp.bp.core.common.yglog4j.YGLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 同步房管已接收流水
 * Created by xu_lw on 2020-09-03 15:20:33.
 */
@Service("haveAcceptedLogSync")
public class HaveAcceptedLogSyncAction extends BaseAction {
    @Autowired
    private WhefssAccHaveAcceptedLogMapper whefssAccHaveAcceptedLogMapper;

    @Autowired
    private WhefssService whefssService;

    @Autowired
    private AcctInfoMapper acctInfoMapper;

    /**
     * 同步房管已接收流水
     *
     * @param bizCtx 上下文
     * @param reqBO  请求对象
     * @return
     * @throws YGException
     */
    public ResultT execute(YGBizMessageContext bizCtx, WhefssAccHaveAcceptedLogQueryReqBO reqBO) throws YGException {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        Logger msgLog = YGLogger.getLogger(bizMsg);
        msgLog.info("query, reqBO=" + reqBO);
        ValidatorUtil.validateObject(reqBO);

        int pageIndex = 0;
        int pageSize = 20;
        boolean flag = true;
        String startTime = reqBO.getTranDate() + "000000";
        String endTime = reqBO.getTranDate() + "235959";
        ResultT resultT = new ResultT();
        AcctInfoDO acctInfoDO = new AcctInfoDO();
        acctInfoDO.setSvsAcNo(reqBO.getSvsAcNo());
        AcctInfoDO acctInfoqry = acctInfoMapper.selectByPrimaryKey(acctInfoDO);
        if (acctInfoqry == null) {
            resultT.setMsgCod(CommonMessageCode.WH_ACC_NOT_EXIST);
            return resultT;
        }
        if (!WhefssConstant.ACCT_STS_1.equals(acctInfoqry.getAccSts())) {
            resultT.setMsgCod(CommonMessageCode.WH_ACC_STS_NO_WATCH);
            return resultT;
        }
        int total = 0;
        List<FundsDetail> list = null;
        while (true) {
            resultT = whefssService.fundsDetail(bizCtx, reqBO.getSvsAcNo(), startTime, endTime, pageIndex, pageSize);
            if (!CommonMessageCode.SUCC.getMsgCod().equals(resultT.getMsgCod())) {
                resultT.setMsgCod(CommonMessageCode.FAIL);
                return resultT;
            }
            FundsDetailList fundsDetailList = (FundsDetailList)resultT.getData();
            total = fundsDetailList.getTotal();
            if (!WhefssConstant.HTTP_STATUS.equals(fundsDetailList.getStatus())) {
                resultT.setMsgCod(CommonMessageCode.FAIL);
                resultT.setMsgInf(fundsDetailList.getError());
                return resultT;
            }
            //登记流水
            list=fundsDetailList.getFundsDetail();

            for (int i =0;i<list.size();i++){
                WhefssAccHaveAcceptedLogDO whefssAccHaveAcceptedLogDO = new WhefssAccHaveAcceptedLogDO();
                whefssAccHaveAcceptedLogDO.setTime(list.get(i).getTime());
                whefssAccHaveAcceptedLogDO.setNoticeNum(list.get(i).getNoticeNum());
                whefssAccHaveAcceptedLogDO.setAccountNum(reqBO.getSvsAcNo());
                whefssAccHaveAcceptedLogDO.setAmount(new YGAmt(list.get(i).getAmount()));
                whefssAccHaveAcceptedLogDO.setSn(list.get(i).getSn());
                whefssAccHaveAcceptedLogDO.setType(list.get(i).getType());
                whefssAccHaveAcceptedLogDO.setState(list.get(i).getState());
                whefssAccHaveAcceptedLogDO.setTypeDescriptions(list.get(i).getType());
                whefssAccHaveAcceptedLogDO.setLoadTime(DateUtil.getTimestamp());
                WhefssAccHaveAcceptedLogDO qry = whefssAccHaveAcceptedLogMapper.selectByPrimaryKey(whefssAccHaveAcceptedLogDO);
                if(qry==null){
                    whefssAccHaveAcceptedLogMapper.insert(whefssAccHaveAcceptedLogDO);
                }

            }
            pageIndex ++;
            if(pageIndex*pageSize >=total){
                break;
            }
        }

        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }
}
