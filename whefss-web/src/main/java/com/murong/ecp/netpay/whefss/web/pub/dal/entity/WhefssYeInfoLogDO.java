/**
 * WhefssYeInfoLogDO.java
 * Copyright(C) 北京沐融信息科技有限公司
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 18:00:31.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.entity;

import com.murong.ecp.bp.common.util.BaseDO;
import net.sf.json.JSONObject;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;

import java.math.BigDecimal;
/**
 * 房管最近对账成功余额信息表(ibs监管账户对账调度及对账提交前余额自行校验使用）
 */
@Data
public class WhefssYeInfoLogDO extends BaseDO {

    /**
    * 监管账号
    */
    private String accountNum;

    /**
    * 监管编号
    */
    private String monitorNum;

    /**
    * 账户名称
    */
    private String accountName;

    /**
    * 监管状态01 ：开户  02： 销户
    */
    private String accountStatus;

    /**
    * 最近银行余额
    */
    private YGAmt balanceBank;

    /**
    * 最近系统余额
    */
    private YGAmt balanceSystem;

    /**
    * 最近对账日期
    */
    private String balanceTime;

    /**
    * 返回码:0000-查询成功;0001-查询失败;
    */
    private String retcode;

    /**
    * 返回信息
    */
    private String retmsg;

    /**
    * 查询时间yyyymmddhhmmss
    */
    private String trandate;

    /**
     * 柜员ID
     */
    private String userId;

    /**
     * 柜员名
     */
    private String userName;

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
