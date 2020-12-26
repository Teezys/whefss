package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.dict.ECPDict;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;


/**
* Created by syxlw on 2018/4/28.
*/
@Data
public class WhefssPosLogUpdateReqBO {
    /**
    * 缴款通知书编号
    */
    @ECPDict(required = true,length = 30, message = "noticeNum值不合法")
    private String noticeNum;

    /**
    * 终端号
    */
    @ECPDict(required = true,length = 30, message = "zdhNum值不合法")
    private String zdhNum;

    /**
    * 交易日期
    */
    @ECPDict(required = true,length = 12, message = "tranDate值不合法")
    private String tranDate;

    /**
    * 交易时间
    */
    @ECPDict(required = true,length = 9, message = "tranTime值不合法")
    private String tranTime;

    /**
    * 缴款账号
    */
    @ECPDict(required = true,length = 60, message = "payAcc值不合法")
    private String payAcc;

    /**
    * 缴款通知书编号
    */
    @ECPDict(required = true,length = 2, message = "jksbh值不合法")
    private String jksbh;

    /**
    * 监管账号
    */
    @ECPDict(required = true,length = 60, message = "accountNum值不合法")
    private String accountNum;

    /**
    * 付款次数
    */
    @ECPDict(required = true,length = 2, message = "payNum值不合法")
    private String payNum;

    /**
    * 缴款书金额
    */
    @ECPDict(required = true,length = 60, message = "jksje值不合法")
    private String jksje;

    /**
    * POS手续费
    */
    @ECPDict(required = true, message = "posAmt值不合法")
    private YGAmt posAmt;

    /**
    * 交易金额
    */
    @ECPDict(required = true, message = "tranAmt值不合法")
    private YGAmt tranAmt;

    /**
    * nds对应流水号
    */
    @ECPDict(required = true,length = 75, message = "sn值不合法")
    private String sn;

    /**
    * Nds清算日期
    */
    @ECPDict(required = true,length = 12, message = "setDate值不合法")
    private String setDate;

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

    /**
    * 柜员ID
    */
    @ECPDict(required = true,length = 32, message = "userId值不合法")
    private String userId;

    /**
    * 柜员名
    */
    @ECPDict(required = true,length = 32, message = "userName值不合法")
    private String userName;

    /**
    * 批次号
    */
    @ECPDict(required = true,length = 32, message = "batchNum值不合法")
    private String batchNum;

}
