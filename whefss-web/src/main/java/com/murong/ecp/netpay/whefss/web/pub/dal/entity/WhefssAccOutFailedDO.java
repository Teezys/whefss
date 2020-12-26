/**
 * WhefssAccOutFailedDO.java
 * Copyright(C) 北京沐融信息科技有限公司
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 17:44:16.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.entity;

import com.murong.ecp.bp.common.util.BaseDO;
import net.sf.json.JSONObject;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;

import java.math.BigDecimal;
/**
 * WHEFSS_ACC_OUT_FAILED
 */
@Data
public class WhefssAccOutFailedDO extends BaseDO {

    /**
    * 拨付通知书编号
    */
    private String noticeNum;

    /**
    * 监管账号
    */
    private String accountNum;

    /**
    * 银行代码
    */
    private String bankCode;

    /**
    * 拨付金额(保留两位小数)
    */
    private YGAmt amount;

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
    * 失败原因
    */
    private String reason;

    /**
    * 拨付时间
    */
    private String time;

    /**
    * 流水号
    */
    private String sn;

    /**
    * 发送状态:00-未发送;01-已发送对账状态待更新;02-对账成功;03-对账失败房管已接收明细;04-对账失败房管未接收明细;05-异常数据（不提交给房管）
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
    * 柜员名
    */
    private String userName;

    /**
    * 柜员id
    */
    private String userId;

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
