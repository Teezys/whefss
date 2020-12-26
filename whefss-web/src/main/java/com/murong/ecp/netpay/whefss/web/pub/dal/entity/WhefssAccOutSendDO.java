/**
 * WhefssAccOutSendDO.java
 * Copyright(C) 北京沐融信息科技有限公司
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 17:37:28.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.entity;

import com.murong.ecp.bp.common.util.BaseDO;
import net.sf.json.JSONObject;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;

import java.math.BigDecimal;
/**
 * WHEFSS_ACC_OUT_SEND
 */
@Data
public class WhefssAccOutSendDO extends BaseDO {

    /**
    * 拨付通知书编号
    */
    private String noticeNum;

    /**
    * 银行代码
    */
    private String bankCode;

    /**
    * 监管账户总行
    */
    private String bankName;

    /**
    * 监管账户支行
    */
    private String branchName;

    /**
    * 监管账户名称
    */
    private String accountName;

    /**
    * 监管账户账号
    */
    private String accountNum;

    /**
    * 收款方开户总行
    */
    private String bankTo;

    /**
    * 收款方账户名称
    */
    private String nameTo;

    /**
    * 收款方账户账号
    */
    private String accountTo;

    /**
    * 收款方支行行号
    */
    private String branchTo;

    /**
    * 拨付金额（两位小数）
    */
    private YGAmt amount;

    /**
    * 流水号
    */
    private String sn;

    /**
    * 拨付时间
    */
    private String time;

    /**
    * 发送状态:00-未发送;01-已发送对账状态待更新;02 对账成功;03-对账失败房管已接收明细;04-对账失败房管未接收明细;05-异常数据（不提交给房管）
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
    * 用户ID
    */
    private String userId;

    /**
    * 用户名
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
    /**
     * 失败原因
     */
    private String  reason;
    /**
     * 失败标志位:0-成功；1-失败
     */
    private String flag;
}
