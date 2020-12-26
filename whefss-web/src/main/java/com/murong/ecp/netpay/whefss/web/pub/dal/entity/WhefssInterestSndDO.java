/**
 * WhefssInterestSndDO.java
 * Copyright(C) 北京沐融信息科技有限公司
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 18:03:10.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.entity;

import com.murong.ecp.bp.common.util.BaseDO;
import net.sf.json.JSONObject;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;

import java.math.BigDecimal;
/**
 * WHEFSS_INTEREST_SND
 */
@Data
public class WhefssInterestSndDO extends BaseDO {

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
    * 利息、年费
    */
    private YGAmt interest;

    /**
    * 操作时间
    */
    private String time;

    /**
    * 进账类别:1-进账;2-出账
    */
    private String type;

    /**
    * 发送状态:00-未发送;01-已发送对账状态待更新;02 对账成功;03-对账失败房管已接收明细;04-对账失败房管未接收明细;05-异常数据（不提交给房管）
    */
    private String sendStatus;

    /**
    * 批次号
    */
    private String batchNum;

    /**
    * 柜员号
    */
    private String userId;

    /**
    * 柜员名
    */
    private String userName;

    /**
    * 系统流水号
    */
    private String sysTranid;

    /**
    * 系统时间yyyymmddhhmiss
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
}
