package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.dict.ECPDict;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;


/**
* Created by syxlw on 2018/4/28.
*/
@Data
public class WhefssYeInfoLogUpdateReqBO {
    /**
    * 监管账号
    */
    @ECPDict(required = true,length = 75, message = "accountNum值不合法")
    private String accountNum;

    /**
    * 监管编号
    */
    @ECPDict(required = true,length = 30, message = "monitorNum值不合法")
    private String monitorNum;

    /**
    * 账户名称
    */
    @ECPDict(required = true,length = 150, message = "accountName值不合法")
    private String accountName;

    /**
    * 监管状态01 ：开户  02： 销户
    */
    @ECPDict(required = true,length = 3, message = "accountStatus值不合法")
    private String accountStatus;

    /**
    * 最近银行余额
    */
    @ECPDict(required = true, message = "balanceBank值不合法")
    private YGAmt balanceBank;

    /**
    * 最近系统余额
    */
    @ECPDict(required = true, message = "balanceSystem值不合法")
    private YGAmt balanceSystem;

    /**
    * 最近对账日期
    */
    @ECPDict(required = true,length = 19, message = "balanceTime值不合法")
    private String balanceTime;

    /**
    * 返回码:0000-查询成功;0001-查询失败;
    */
    @ECPDict(required = true,length = 6, message = "retcode值不合法")
    private String retcode;

    /**
    * 返回信息
    */
    @ECPDict(required = true,length = 256, message = "retmsg值不合法")
    private String retmsg;

    /**
    * 查询时间yyyymmddhhmmss
    */
    @ECPDict(required = true,length = 14, message = "trandate值不合法")
    private String trandate;

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
