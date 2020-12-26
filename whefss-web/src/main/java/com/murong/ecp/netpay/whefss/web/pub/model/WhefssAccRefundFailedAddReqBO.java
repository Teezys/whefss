package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.dict.ECPDict;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;


/**
* Created by syxlw on 2018/4/28.
*/
@Data
public class WhefssAccRefundFailedAddReqBO {
    /**
     * 退款通知书编号
     */
    @ECPDict(required = true,length = 30, message = "noticeNum值不合法")
    private String noticeNum;

    /**
     * 监管账号
     */
    @ECPDict(required = true,length = 60, message = "accountNum值不合法")
    private String accountNum;

    /**
     * 银行代码
     */
    @ECPDict(required = true,length = 150, message = "bankCode值不合法")
    private String bankCode;

    /**
     * 收款方帐户名称
     */
    @ECPDict(required = true,length = 300, message = "corpAccountName值不合法")
    private String corpAccountName;

    /**
     * 收款方帐号
     */
    @ECPDict(required = true,length = 60, message = "corpAccountNum值不合法")
    private String corpAccountNum;

    /**
     * 收款方开户行
     */
    @ECPDict(required = true,length = 75, message = "corpAccountBank值不合法")
    private String corpAccountBank;

    /**
     * 退款金额
     */
    @ECPDict(required = true, message = "refundPrice值不合法")
    private YGAmt refundPrice;

    /**
     * 失败原因
     */
    @ECPDict(required = true,length = 150, message = "reason值不合法")
    private String reason;

    /**
     * 失败时间
     */
    @ECPDict(required = true,length = 21, message = "time值不合法")
    private String time;

    /**
     * 流水号
     */
    @ECPDict(required = true,length = 75, message = "sn值不合法")
    private String sn;

    /**
     * 发送状态:00-未发送;01-已发送对账状态待更新;02 对账成功;03-对账失败房管已接收明细;04-对账失败房管未接收明细;05-异常数据（不提交给房管）
     */
    @ECPDict(required = true,length = 3, message = "sendStatus值不合法")
    private String sendStatus;

    /**
     * 返回码
     */
    @ECPDict(required = true,length = 9, message = "respCode值不合法")
    private String respCode;

    /**
     * 结果信息
     */
    @ECPDict(required = true,length = 384, message = "respMsg值不合法")
    private String respMsg;

    /**
     * 系统流水号
     */
    @ECPDict(required = true,length = 60, message = "sysTranid值不合法")
    private String sysTranid;

    /**
     * 系统时间
     */
    @ECPDict(required = true,length = 21, message = "sysTime值不合法")
    private String sysTime;

    /**
     * 柜员ID
     */
    @ECPDict(required = true,length = 30, message = "userId值不合法")
    private String userId;

    /**
     * 柜员名
     */
    @ECPDict(required = true,length = 30, message = "userName值不合法")
    private String userName;

    /**
     * 批次号
     */
    @ECPDict(required = true,length = 32, message = "batchNum值不合法")
    private String batchNum;

    /**
     * 备用1
     */
    @ECPDict(required = true,length = 384, message = "reserve1值不合法")
    private String reserve1;

    /**
     * 备用2
     */
    @ECPDict(required = true,length = 384, message = "reserve2值不合法")
    private String reserve2;

    /**
     * 备用3
     */
    @ECPDict(required = true,length = 384, message = "reserve3值不合法")
    private String reserve3;



}
