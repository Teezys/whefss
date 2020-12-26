package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.dict.ECPDict;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;


/**
* Created by syxlw on 2018/4/28.
*/
@Data
public class WhefssAmpTranidAddReqBO {

    /**
     * 业务类型:1-冻结;2-解冻
     */
    @ECPDict(required = true,length = 30, message = "bustype值不合法")
    private String bustype;

    /**
     * 监管账号
     */
    @ECPDict(required = true,length = 60, message = "accountNum值不合法")
    private String accountNum;

    /**
     * 冻结金额
     */
    @ECPDict(required = false, message = "amount值不合法")
    private YGAmt amount;

    /**
     * 冻结原因
     */
    @ECPDict(required = false,length = 300, message = "reason值不合法")
    private String reason;

    /**
     * 交易类型:1-为冻结;2-为查封;3-为扣划
     */
    @ECPDict(required = true,length = 3, message = "fgType值不合法")
    private String fgType;

    /**
     * 冻结执行文号
     */
    @ECPDict(required = true,length = 75, message = "noticeNum值不合法")
    private String noticeNum;

    /**
     * 冻结期限(单位:月)
     */
    @ECPDict(required = false,length = 6, message = "freezeMonth值不合法")
    private String freezeMonth;

    /**
     * 流水号
     */
    @ECPDict(required = true,length = 75, message = "sn值不合法")
    private String sn;

    /**
     * 授权ID
     */
    @ECPDict(required = true,length = 32, message = "licenseNo值不合法")
    private String licenseNo;

    /**
     * 授权姓名
     */
    @ECPDict(required = true,length = 32, message = "授权密码不合法")
    private String licensePwd;

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
