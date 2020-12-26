package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.dict.ECPDict;
import lombok.Data;


/**
* Created by syxlw on 2018/4/28.
*/
@Data
public class WhefssAccOpenSndUpdateReqBO {
    /**
    * 监管编号
    */
    @ECPDict(required = true,length = 30, message = "monitorNum值不合法")
    private String monitorNum;

    /**
    * 开户预审结果单编号
    */
    @ECPDict(required = true,length = 30, message = "noticeNum值不合法")
    private String noticeNum;

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
    * 银行内部流水号
    */
    @ECPDict(required = true,length = 75, message = "sn值不合法")
    private String sn;

    /**
    * 操作时间
    */
    @ECPDict(required = true,length = 30, message = "time值不合法")
    private String time;

    /**
    * 发送状态00:未发送；01:发送成功；02: 发送失败；
    */
    @ECPDict(required = true,length = 2, message = "sendStatus值不合法")
    private String sendStatus;

    /**
    * 返回码
    */
    @ECPDict(required = true,length = 30, message = "respCode值不合法")
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
    * 系统时间Yyyymmddhhmiss
    */
    @ECPDict(required = true,length = 14, message = "sysTime值不合法")
    private String sysTime;

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
