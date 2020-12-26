/**
 * WhefssAccInfoDO.java
 * Copyright(C) 北京沐融信息科技有限公司
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 18:05:50.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.entity;

import com.murong.ecp.bp.common.util.BaseDO;
import net.sf.json.JSONObject;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;

import java.math.BigDecimal;
/**
 * WHEFSS_ACC_INFO
 */
@Data
public class WhefssAccInfoDO extends BaseDO {

    /**
    * 监管账户账号
    */
    private String accountNum;

    /**
     * 开户预审单编号
     */
    private String noticeNum;

    /**
    * 监管账户名称
    */
    private String accountName;

    /**
    * 监管编号
    */
    private String monitorNum;

    /**
    * 总行名称
    */
    private String bankName;

    /**
    * 支行名称
    */
    private String branchName;

    /**
    * 支行行号
    */
    private String branchNum;

    /**
    * 企业名称
    */
    private String corpName;

    /**
    * 项目名称
    */
    private String projectName;

    /**
    * 监管范围
    */
    private String monitorRange;

    /**
    * 日终余额
    */
    private YGAmt dayendAmt;

    /**
    * 操作时间Yyyymmddhhmiss
    */
    private String monitorTime;

    /**
    * 销户通知书编号
    */
    private String cloNtcNo;

    /**
    * 变更时间Yyyymmddhhmiss
    */
    private String changeTime;

    /**
    * 备用1
    */
    private String reserve1;

    /**
    * 备用2
    */
    private String reserve2;

    /**
    * 备用3
    */
    private String reserve3;

    /**
    * 柜员ID
    */
    private String userId;

    /**
    * 柜员姓名
    */
    private String userName;
    /**
     * 拟开监管账户名
     */
    private String planAccName;
}
