package com.murong.ecp.netpay.whefss.web.service.server;

import com.murong.ecp.netpay.whefss.web.common.utils.ResultT;
import com.murong.ecp.netpay.whefss.web.service.server.bo.AccountOpenNotify;
import com.murong.ecp.netpay.whefss.web.service.server.bo.FundsUploadInfo;
import com.yuangou.ecp.bp.core.common.message.YGBizMessageContext;
import org.springframework.stereotype.Service;


public interface WhefssService {
    /*
     * 查询开户数据
     * */
    ResultT accountsOpen(YGBizMessageContext bizCtx, String noticeNum);

    /*
     *通知开户数据
     * */
    ResultT accountsOpenNotify(YGBizMessageContext bizCtx, AccountOpenNotify accountOpenNotify);

    /*
     * 查询销户数据
     * */
    ResultT accountsRemove(YGBizMessageContext bizCtx, String noticeNum);

    /*    *
     * 查询缴款通知书数据
     * */
    ResultT contractsIncome(YGBizMessageContext bizCtx, String noticeNum);

    /*
     * 查询冲正数据
     * */
    ResultT fundsCorrect(YGBizMessageContext bizCtx, String noticeNum);

    /*
     * 查询拨付数据
     * */
    ResultT fundsAppropriate(YGBizMessageContext bizCtx, String noticeNum);

    /*
     * 查询退款数据
     * */
    ResultT fundsRefund(YGBizMessageContext bizCtx, String noticeNum);

    /*
     * 查询指定日期范围进账数据
     * */
    ResultT fundsDetail(YGBizMessageContext bizCtx, String accountNum, String startTime, String endTime, int pageIndex, int pageSize);

    /*
     * 余额及交易明细合并提交
     * */
    ResultT fundsUploadInfo(YGBizMessageContext bizCtx, FundsUploadInfo fundsUploadInfo);

    /*
     * 按揭贷款信息采集
     * */
    ResultT contractsLoanInfo(YGBizMessageContext bizCtx, String noticeNum);

    /*
     * 监管系统账户余额查询
     * */
    ResultT fundsBalance(YGBizMessageContext bizCtx, String accountNum);


}
