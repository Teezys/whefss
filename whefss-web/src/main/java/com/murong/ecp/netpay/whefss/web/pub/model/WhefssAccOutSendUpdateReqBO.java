package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.dict.ECPDict;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;


/**
* Created by syxlw on 2018/4/28.
*/
@Data
public class WhefssAccOutSendUpdateReqBO {
    /**
    * 拨付通知书编号
    */
    @ECPDict(required = true,length = 30, message = "noticeNum值不合法")
    private String noticeNum;

    /**
    * 银行代码
    */
    @ECPDict(required = false,length = 75, message = "bankCode值不合法")
    private String bankCode;

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
    @ECPDict(required = true,length = 18, message = "bracnchTo值不合法")
    private String bracnchTo;

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
//
//    /**
//    * 发送状态:00-未发送;01-已发送对账状态待更新;02 对账成功;03-对账失败房管已接收明细;04-对账失败房管未接收明细;05-异常数据（不提交给房管）
//    */
//    @ECPDict(required = true,length = 3, message = "sendStatus值不合法")
//    private String sendStatus;
//
//    /**
//    * 系统流水号
//    */
//    @ECPDict(required = false,length = 60, message = "sysTranid值不合法")
//    private String sysTranid;
//
//    /**
//    * 系统时间
//    */
//    @ECPDict(required = true,length = 21, message = "sysTime值不合法")
//    private String sysTime;
//
//    /**
//    * 用户ID
//    */
//    @ECPDict(required = true,length = 30, message = "userId值不合法")
//    private String userId;
//
//    /**
//    * 用户名
//    */
//    @ECPDict(required = true,length = 30, message = "userName值不合法")
//    private String userName;
//
//    /**
//    * 批次号
//    */
//    @ECPDict(required = true,length = 32, message = "batchNum值不合法")
//    private String batchNum;
//
//    /**
//    * 备用1
//    */
//    @ECPDict(required = true,length = 384, message = "reserve1值不合法")
//    private String reserve1;
//
//    /**
//    * 备用2
//    */
//    @ECPDict(required = true,length = 384, message = "reserve2值不合法")
//    private String reserve2;
//
//    /**
//    * 备用3
//    */
//    @ECPDict(required = false,length = 384, message = "reserve3值不合法")
//    private String reserve3;

}
