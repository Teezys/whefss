/**
 * WhefssAccRefundFailedDO.java
 * Copyright(C) 北京沐融信息科技有限公司
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 17:51:16.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.entity;

import com.murong.ecp.bp.common.util.BaseDO;
import net.sf.json.JSONObject;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;

import java.math.BigDecimal;
/**
 * WHEFSS_ACC_REFUND_FAILED
 */
@Data
public class WhefssAccRefundFailedDO extends BaseDO {

    /**
    * 退款通知书编号
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
    * 收款方帐户名称
    */
    private String corpAccountName;

    /**
    * 收款方帐号
    */
    private String corpAccountNum;

    /**
    * 收款方开户行
    */
    private String corpAccountBank;

    /**
    * 退款金额
    */
    private YGAmt refundPrice;

    /**
    * 失败原因
    */
    private String reason;

    /**
    * 失败时间
    */
    private String time;

    /**
    * 流水号
    */
    private String sn;

    /**
    * 发送状态:00-未发送;01-已发送对账状态待更新;02 对账成功;03-对账失败房管已接收明细;04-对账失败房管未接收明细;05-异常数据（不提交给房管）
    */
    private String sendStatus;

    /**
    * 返回码
    */
    private String respCode;

    /**
    * 结果信息
    */
    private String respMsg;

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
    * 柜员名
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
