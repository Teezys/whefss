package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.dict.ECPDict;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;


/**
* Created by syxlw on 2018/4/28.
*/
@Data
public class WhefssAjdkSndAddReqBO {
    /**
     * 缴款通知书编号:含带格式附言的他行转账HTBH01123456701
     */
    @ECPDict(required = true,length = 30, message = "noticeNum值不合法")
    private String noticeNum;

    /**
     * 监管编号
     */
    @ECPDict(required = false,length = 30, message = "monitorNum值不合法")
    private String monitorNum;

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
    @ECPDict(required = false, message = "realIn值不合法")
    private YGAmt realIn;

    /**
     * 手续费
     */
    @ECPDict(required = false, message = "commission值不合法")
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
     * 购房人姓名
     */
    @ECPDict(required = false,length = 150, message = "buyer值不合法")
    private String buyer;

    /**
     * 购房人证件类型
     */
    @ECPDict(required = false,length = 100, message = "idType值不合法")
    private String idType;

    /**
     * 购房人证件号
     */
    @ECPDict(required = false,length = 50, message = "idNum值不合法")
    private String idNum;

    /**
     * 总房款
     */
    @ECPDict(required = false, message = "priceSum值不合法")
    private YGAmt priceSum;

    /**
     * 首付款
     */
    @ECPDict(required = false, message = "firstMoney值不合法")
    private YGAmt firstMoney;

    /**
     * 首付是否缴齐
     */
    @ECPDict(required = false,length = 2, message = "firstPaied值不合法")
    private String firstPaied;

    /**
     * 面积
     */
    @ECPDict(required = false, message = "area值不合法")
    private YGAmt area;

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
