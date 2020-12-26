/**
 * WhefssAmpTranidDO.java
 * Copyright(C) 北京沐融信息科技有限公司
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 17:54:12.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.entity;

import com.murong.ecp.bp.common.util.BaseDO;
import net.sf.json.JSONObject;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;

import java.math.BigDecimal;
/**
 * 司法冻结/解冻登记簿
 */
@Data
public class WhefssAmpTranidDO extends BaseDO {

    /**
    * 交易流水号
    */
    private String ampTranid;

    /**
    * 交易时间
    */
    private String ampTime;

    /**
    * 交易码
    */
    private String trancode;

    /**
    * 业务类型:1-冻结;2-解冻
    */
    private String bustype;

    /**
    * 交易类型
    */
    private String transtype;

    /**
    * HTTP请求方式:POST,GET方式 
    */
    private String method;

    /**
    * KEY
    */
    private String apikey;

    /**
    * 监管账号
    */
    private String accountNum;

    /**
    * 冻结时间
    */
    private String time;

    /**
    * 冻结金额
    */
    private YGAmt amount;

    /**
    * 冻结原因
    */
    private String reason;

    /**
    * 交易类型:1-为冻结;2-为查封;3-为扣划
    */
    private String fgType;

    /**
    * 冻结执行文号
    */
    private String noticeNum;

    /**
    * 冻结期限(单位:月)
    */
    private String freezeMonth;

    /**
    * 流水号
    */
    private String sn;

    /**
    * 发送状态:00-未发送;01-已发送对账状态待更新;02 对账成功;03-对账失败房管已接收明细;04-对账失败房管未接收明细;05-异常数据（不提交给房管）
    */
    private String sendStatus;

    /**
    * 柜员名
    */
    private String userName;

    /**
    * 柜员ID
    */
    private String userId;

    /**
    * 批次号
    */
    private String batchNum;

    /**
    * 授权ID
    */
    private String licenseNum;

    /**
    * 授权姓名
    */
    private String licenseName;

    /**
    * 结果信息
    */
    private String respMsg;

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
     * 开始日期
     */
    private String startDate;

    /**
     * 结束日期
     */
    private String endDate;

}
