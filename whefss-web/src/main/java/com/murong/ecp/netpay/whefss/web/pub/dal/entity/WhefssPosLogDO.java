/**
 * WhefssPosLogDO.java
 * Copyright(C) 北京沐融信息科技有限公司
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 18:01:55.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.entity;

import com.murong.ecp.bp.common.util.BaseDO;
import net.sf.json.JSONObject;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;

import java.math.BigDecimal;
/**
 * WHEFSS_POS_LOG
 */
@Data
public class WhefssPosLogDO extends BaseDO {

    /**
    * 缴款通知书编号
    */
    private String noticeNum;

    /**
    * 终端号
    */
    private String zdhNum;

    /**
    * 交易日期
    */
    private String tranDate;

    /**
    * 交易时间
    */
    private String tranTime;

    /**
    * 缴款账号
    */
    private String payAcc;

    /**
    * 缴款通知书编号
    */
    private String jksbh;

    /**
    * 监管账号
    */
    private String accountNum;

    /**
    * 付款次数
    */
    private String payNum;

    /**
    * 缴款书金额
    */
    private String jksje;

    /**
    * POS手续费
    */
    private YGAmt posAmt;

    /**
    * 交易金额
    */
    private YGAmt tranAmt;

    /**
    * nds对应流水号
    */
    private String sn;

    /**
    * Nds清算日期
    */
    private String setDate;

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
    * 柜员名
    */
    private String userName;

    /**
    * 批次号
    */
    private String batchNum;
}
