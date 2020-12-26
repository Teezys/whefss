/**
 * WhefssChkDetailDO.java
 * Copyright(C) 北京沐融信息科技有限公司
 * ------------------------------------------------
 * Created by xu_lw on 2020-12-14 14:49:46.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.entity;

import com.murong.ecp.bp.common.util.BaseDO;
import net.sf.json.JSONObject;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;

import java.math.BigDecimal;
/**
 * WHEFSS_CHK_DETAIL
 */
@Data
public class WhefssChkDetailDO extends BaseDO {

    /**
    * pkId
    */
    private String pkId;

    /**
    * fkId
    */
    private String fkId;

    /**
    * 对账日期
    */
    private String chkDt;

    /**
    * 监管账户账号
    */
    private String accountNum;

    /**
    * 当日对账总额（两位小数）
    */
    private YGAmt chkAmt;

    /**
    * 对账的条数
    */
    private String chkNum;

    /**
    * 对账人员
    */
    private String userId;

    /**
    * 对账时间YYYYMMDDHHMISS
    */
    private String chkTm;

    /**
    * 对账状态：00未对账；01对账失败；02对账成功；11-明细提交房管返回失败；12-明细提交房管返回成功；
    */
    private String chkStatus;

    /**
    * 错误信息
    */
    private String msg;

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
    * 余额
    */
    private YGAmt balanceBank;

    /**
     * 入账总金额
     */
    private YGAmt rzAmt;

    /**
     * 入账总笔数
     */
    private YGAmt rzNum;

    /**
     * 出账总金额
     */
    private YGAmt czAmt;

    /**
     * 余额
     */
    private YGAmt czNum;

    /**
     * 开始日期
     */
    private String startDate;
    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 本地对账结果
     */
    private String chkStsDy;
    /**
     * 本地对账描述
     */
    private String chkMsgDy;
    /**
     * 房管对账结果
     */
    private String chkStsWh;
    /**
     * 房管对账描述
     */
    private String chkMsgWh;
}
