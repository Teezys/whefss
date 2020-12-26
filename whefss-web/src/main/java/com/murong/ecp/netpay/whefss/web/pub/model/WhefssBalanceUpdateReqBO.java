package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.dict.ECPDict;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;


/**
* Created by syxlw on 2018/4/28.
*/
@Data
public class WhefssBalanceUpdateReqBO {
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
    * 对账时间
    */
    @ECPDict(required = true,length = 30, message = "time值不合法")
    private String time;

    /**
    * 当前余额
    */
    @ECPDict(required = true, message = "remainingSum值不合法")
    private YGAmt remainingSum;

    /**
    * 发送状态：00-未发送;01-已发送对账状态待更新;02-对账成功;03-对账失败;
    */
    @ECPDict(required = true,length = 3, message = "sendStatus值不合法")
    private String sendStatus;

    /**
    * 返回码：0000 –成功;0001-失败
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
    * 系统时间：yyyymmddhhmiss
    */
    @ECPDict(required = true,length = 21, message = "sysTime值不合法")
    private String sysTime;

    /**
    * 日终账面余额
    */
    @ECPDict(required = true, message = "zmye值不合法")
    private YGAmt zmye;

    /**
    * 日终可用余额
    */
    @ECPDict(required = true, message = "kyye值不合法")
    private YGAmt kyye;

    /**
    * 日终冻结金额
    */
    @ECPDict(required = true, message = "djje值不合法")
    private YGAmt djje;

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
