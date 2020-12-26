package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.dict.ECPDict;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;


/**
 * Created by syxlw on 2018/4/28.
 */
@Data
public class WhefssAccInfoAddReqBO {

    /**
     * 开户预审结果单编号
     */
    @ECPDict(required = true, length = 20, message = "noticeNum值不合法")
    private String noticeNum;
    /**
     * 监管账户账号
     */
    @ECPDict(required = true, length = 60, message = "accountNum值不合法")
    private String accountNum;

    /**
     * 监管账户名称
     */
    @ECPDict(required = true, length = 150, message = "accountName值不合法")
    private String accountName;

    /**
     * 监管编号
     */
    @ECPDict(required = true, length = 30, message = "monitorNum值不合法")
    private String monitorNum;

    /**
     * 总行名称
     */
    @ECPDict(required = false, length = 75, message = "bankName值不合法")
    private String bankName;

    /**
     * 支行名称
     */
    @ECPDict(required = true, length = 150, message = "branchName值不合法")
    private String branchName;

    /**
     * 支行行号
     */
    @ECPDict(required = true, length = 30, message = "branchNum值不合法")
    private String branchNum;

    /**
     * 企业名称
     */
    @ECPDict(required = false, length = 150, message = "corpName值不合法")
    private String corpName;

    /**
     * 项目名称
     */
    @ECPDict(required = true, length = 300, message = "projectName值不合法")
    private String projectName;

    /**
     * 监管范围
     */
    @ECPDict(required = true, length = 300, message = "monitorRange值不合法")
    private String monitorRange;

    /**
     * 日终余额
     */
    @ECPDict(required = false, message = "dayendAmt值不合法")
    private YGAmt dayendAmt;

    /**
     * 操作时间Yyyymmddhhmiss
     */
    @ECPDict(required = false, length = 21, message = "monitorTime值不合法")
    private String monitorTime;

    /**
     * 销户通知书编号
     */
    @ECPDict(required = false, length = 16, message = "cloNtcNo值不合法")
    private String cloNtcNo;

    /**
     * 变更时间Yyyymmddhhmiss
     */
    @ECPDict(required = false, length = 21, message = "changeTime值不合法")
    private String changeTime;

    /**
     * 备用1
     */
    @ECPDict(required = false, length = 384, message = "reserve1值不合法")
    private String reserve1;

    /**
     * 备用2
     */
    @ECPDict(required = false, length = 384, message = "reserve2值不合法")
    private String reserve2;

    /**
     * 备用3
     */
    @ECPDict(required = false, length = 384, message = "reserve3值不合法")
    private String reserve3;

    /**
     * 柜员ID
     */
    @ECPDict(required = false, length = 30, message = "userId值不合法")
    private String userId;

    /**
     * 柜员姓名
     */
    @ECPDict(required = false, length = 30, message = "userName值不合法")
    private String userName;

    /**
     * 拟开监管账户名
     */
    private String planAccName;


}
