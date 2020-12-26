package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.dict.ECPDict;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;


/**
* Created by syxlw on 2018/4/28.
*/
@Data
public class WhefssAccOutSendAddReqBO {
    /**
     * 拨付通知书编号
     */
    @ECPDict(required = true,length = 30, message = "noticeNum值不合法")
    private String noticeNum;

    /**
     * 监管账户总行
     */
    @ECPDict(required = false,length = 150, message = "bankName值不合法")
    private String bankName;

    /**
     * 监管账户支行
     */
    @ECPDict(required = true,length = 150, message = "branchName值不合法")
    private String branchName;

    /**
     * 监管账户名称
     */
    @ECPDict(required = true,length = 150, message = "accountName值不合法")
    private String accountName;

    /**
     * 监管账户账号
     */
    @ECPDict(required = true,length = 60, message = "accountNum值不合法")
    private String accountNum;

    /**
     * 收款方开户总行
     */
    @ECPDict(required = true,length = 75, message = "bankTo值不合法")
    private String bankTo;

    /**
     * 收款方账户名称
     */
    @ECPDict(required = true,length = 150, message = "nameTo值不合法")
    private String nameTo;

    /**
     * 收款方账户账号
     */
    @ECPDict(required = true,length = 30, message = "accountTo值不合法")
    private String accountTo;

    /**
     * 收款方支行行号
     */
    @ECPDict(required = true,length = 18, message = "branchTo值不合法")
    private String branchTo;

    /**
     * 拨付金额（两位小数）
     */
    @ECPDict(required = true, message = "amount值不合法")
    private YGAmt amount;

    /**
     * 流水号
     */
    @ECPDict(required = true,length = 75, message = "sn值不合法")
    private String sn;

    /**
     * 拨付时间
     */
    @ECPDict(required = true,length = 30, message = "time值不合法")
    private String time;

    /**
     * 失败原因
     */
    @ECPDict(required = false,length = 150, message = "reason值不合法")
    private String reason;


    /**
     * 备用1
     */
    @ECPDict(required = false,length = 384, message = "reserve1值不合法")
    private String reserve1;

    /**
     * 备用2
     */
    @ECPDict(required = false,length = 384, message = "reserve2值不合法")
    private String reserve2;

    /**
     * 备用3
     */
    @ECPDict(required = false,length = 384, message = "reserve3值不合法")
    private String reserve3;



}
