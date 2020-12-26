/**
 * WhefssAccInSendDO.java
 * Copyright(C) 北京沐融信息科技有限公司
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 17:42:39.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.entity;

import com.murong.ecp.bp.common.util.BaseDO;
import net.sf.json.JSONObject;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;

import java.math.BigDecimal;
/**
 * 监管账户名称
 */
@Data
public class WhefssAccInSendDO extends BaseDO {

    /**
    * 缴款通知书编号
    */
    private String noticeNum;

    /**
    * 监管编号
    */
    private String monitorNum;

    /**
    * 银行代号
    */
    private String bankCode;

    /**
    * 买方
    */
    private String buyers;

    /**
    * 证件类型
    */
    private String idType;

    /**
    * 证件号码(以逗号分隔)
    */
    private String idNum;

    /**
    * 证件号码
    */
    private String bankName;

    /**
    * 监管账户开户总行
    */
    private String accountName;

    /**
    * 监管账户账号
    */
    private String accountNum;

    /**
    * 首付金额（保留两位小数）
    */
    private YGAmt totalPrice;

    /**
    * 首付金额（保留两位小时）
    */
    private YGAmt firstPrice;

    /**
    * 实收金额
    */
    private YGAmt realIn;

    /**
    * 手续费(保留两位小数)
    */
    private YGAmt commission;

    /**
    * 进账时间
    */
    private String time;

    /**
    * 流水号
    */
    private String sn;

    /**
    * 发送状态
    */
    private String sendStatus;

    /**
    * 系统流水号
    */
    private String sysTranid;

    /**
    * 系统时间
    */
    private String sysTime;

    /**
    * 柜员ID
    */
    private String userId;

    /**
    * 柜员姓名
    */
    private String userName;

    /**
    * 批次号
    */
    private String batchNum;

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
}
