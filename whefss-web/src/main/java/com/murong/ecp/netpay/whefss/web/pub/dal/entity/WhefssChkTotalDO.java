/**
 * WhefssChkTotalDO.java
 * Copyright(C) 北京沐融信息科技有限公司
 * ------------------------------------------------
 * Created by xu_lw on 2020-12-11 18:24:00.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.entity;

import com.murong.ecp.bp.common.util.BaseDO;
import net.sf.json.JSONObject;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;

import java.math.BigDecimal;
/**
 * WHEFSS_CHK_TOTAL
 */
@Data
public class WhefssChkTotalDO extends BaseDO {

    /**
    * 主键
    */
    private String pkId;

    /**
    * 对账日期yyyymmdd
    */
    private String chkDt;

    /**
    * 对账状态:00未完成;01已完成;
    */
    private String chkSts;

    /**
    * 对账时间yyyymmddhhmiss
    */
    private String chkTm;

    /**
    * 对账总笔数
    */
    private String chkNum;

    /**
    * 对账总金额
    */
    private YGAmt chkAmt;

    /**
    * 对账成功笔数
    */
    private String sucChkNum;

    /**
    * 对账成功金额
    */
    private YGAmt sucChkAmt;

    /**
    * 柜员ID
    */
    private String userId;

    /**
    * 柜员姓名
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
