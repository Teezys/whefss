/**
 * WhefssBalanceDO.java
 * Copyright(C) 北京沐融信息科技有限公司
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 18:04:34.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.entity;

import com.murong.ecp.bp.common.util.BaseDO;
import net.sf.json.JSONObject;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;

import java.math.BigDecimal;
/**
 * 余额流水表（原IBS余额查询页面）
 */
@Data
public class WhefssBalanceDO extends BaseDO {

    /**
    * 监管账号
    */
    private String accountNum;

    /**
    * 银行代码
    */
    private String bankCode;

    /**
    * 对账时间
    */
    private String time;

    /**
    * 当前余额
    */
    private YGAmt remainingSum;

    /**
    * 发送状态：00-未发送;01-已发送对账状态待更新;02-对账成功;03-对账失败;
    */
    private String sendStatus;

    /**
    * 返回码：0000 –成功;0001-失败
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
    * 系统时间：yyyymmddhhmiss
    */
    private String sysTime;

    /**
    * 日终账面余额
    */
    private YGAmt zmye;

    /**
    * 日终可用余额
    */
    private YGAmt kyye;

    /**
    * 日终冻结金额
    */
    private YGAmt djje;

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
