package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.dict.ECPDict;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;


/**
* Created by syxlw on 2018/4/28.
*/
@Data
public class WhefssAccInSendUpdateReqBO {
    /**
    * 缴款通知书编号
    */
    @ECPDict(required = true,length = 30, message = "noticeNum值不合法")
    private String noticeNum;

    /**
    * 监管编号
    */
    @ECPDict(required = true,length = 30, message = "monitorNum值不合法")
    private String monitorNum;

    /**
    * 银行代号
    */
    @ECPDict(required = true,length = 150, message = "bankCode值不合法")
    private String bankCode;

    /**
    * 买方
    */
    @ECPDict(required = true,length = 45, message = "buyers值不合法")
    private String buyers;

    /**
    * 证件类型
    */
    @ECPDict(required = true,length = 30, message = "idType值不合法")
    private String idType;

    /**
    * 证件号码(以逗号分隔)
    */
    @ECPDict(required = true,length = 30, message = "idNum值不合法")
    private String idNum;

    /**
    * 证件号码
    */
    @ECPDict(required = true,length = 75, message = "bankName值不合法")
    private String bankName;

    /**
    * 监管账户开户总行
    */
    @ECPDict(required = true,length = 150, message = "accountName值不合法")
    private String accountName;

    /**
    * 监管账户账号
    */
    @ECPDict(required = true,length = 60, message = "accountNum值不合法")
    private String accountNum;

    /**
    * 首付金额（保留两位小数）
    */
    @ECPDict(required = true, message = "totalPrice值不合法")
    private YGAmt totalPrice;

    /**
    * 首付金额（保留两位小时）
    */
    @ECPDict(required = true, message = "firstPrice值不合法")
    private YGAmt firstPrice;

    /**
    * 实收金额
    */
    @ECPDict(required = true, message = "realIn值不合法")
    private YGAmt realIn;

    /**
    * 手续费(保留两位小数)
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
    * 发送状态
    */
    @ECPDict(required = true,length = 8, message = "sendStatus值不合法")
    private String sendStatus;

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
    * 柜员姓名
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
