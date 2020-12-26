package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.dict.ECPDict;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;


/**
* Created by syxlw on 2018/4/28.
*/
@Data
public class WhefssAjdkSndUpdateReqBO {
    /**
    * 缴款通知书编号:含带格式附言的他行转账HTBH01123456701
    */
    @ECPDict(required = true,length = 30, message = "noticeNum值不合法")
    private String noticeNum;

    /**
    * 监管编号
    */
    @ECPDict(required = true,length = 30, message = "monitorNum值不合法")
    private String monitorNum;

    /**
    * 银行代码
    */
    @ECPDict(required = true,length = 150, message = "bankCode值不合法")
    private String bankCode;

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
    * 实收金额
    */
    @ECPDict(required = true, message = "realIn值不合法")
    private YGAmt realIn;

    /**
    * 手续费
    */
    @ECPDict(required = true, message = "commission值不合法")
    private YGAmt commission;

    /**
    * 进账时间
    */
    @ECPDict(required = true,length = 30, message = "time值不合法")
    private String time;

    /**
    * 流水号
    */
    @ECPDict(required = true,length = 75, message = "sn值不合法")
    private String sn;

    /**
    * 贷方账户名称
    */
    @ECPDict(required = true,length = 150, message = "senderName值不合法")
    private String senderName;

    /**
    * 货方账号
    */
    @ECPDict(required = true,length = 60, message = "senderNum值不合法")
    private String senderNum;

    /**
    * 备注:失败原因等
    */
    @ECPDict(required = true,length = 75, message = "note值不合法")
    private String note;

    /**
    * 发送状态:00-未发送;01-已发送对账状态待更新;02 对账成功;03-对账失败房管已接收明细;04-对账失败房管未接收明细;05-异常数据（不提交给房管）
    */
    @ECPDict(required = true,length = 3, message = "sendStatus值不合法")
    private String sendStatus;

    /**
    * 系统流水号
    */
    @ECPDict(required = true,length = 60, message = "sysTranid值不合法")
    private String sysTranid;

    /**
    * 系统时间
    */
    @ECPDict(required = true,length = 3, message = "sysTime值不合法")
    private String sysTime;

    /**
    * 柜员名
    */
    @ECPDict(required = true,length = 30, message = "userName值不合法")
    private String userName;

    /**
    * 柜员ID
    */
    @ECPDict(required = true,length = 30, message = "userId值不合法")
    private String userId;

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
