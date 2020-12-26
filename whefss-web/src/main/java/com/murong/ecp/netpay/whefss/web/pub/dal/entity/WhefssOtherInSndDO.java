/**
 * WhefssOtherInSndDO.java
 * Copyright(C) 北京沐融信息科技有限公司
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 17:57:29.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.entity;

import com.murong.ecp.bp.common.util.BaseDO;
import net.sf.json.JSONObject;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;

import java.math.BigDecimal;
/**
 * 不明进账流水表
 */
@Data
public class WhefssOtherInSndDO extends BaseDO {

    /**
    * 流水号
    */
    private String sn;

    /**
    * 监管账户账号
    */
    private String accountNum;

    /**
    * 银行代码
    */
    private String bankCode;

    /**
    * 付款金额
    */
    private YGAmt amount;

    /**
    * 汇款人姓名
    */
    private String senderName;

    /**
    * 汇款人卡号
    */
    private String senderNum;

    /**
    * 进账时间
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
    * 系统时间：yyyymmddhhmiss
    */
    private String sysTime;

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
     * 批次号
     */
    private String batchNum;

}
