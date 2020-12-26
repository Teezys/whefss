/**
 * WhefssChkDetailJnlDO.java
 * Copyright(C) 北京沐融信息科技有限公司
 * ------------------------------------------------
 * Created by xu_lw on 2020-12-15 15:46:44.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.entity;

import com.murong.ecp.bp.common.util.BaseDO;
import net.sf.json.JSONObject;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;

import java.math.BigDecimal;
/**
 * tabledesc
 */
@Data
public class WhefssChkDetailJnlDO extends BaseDO {

    /**
    * PK_ID
    */
    private String pkId;

    /**
    * FK_TOTAL_ID
    */
    private String fkTotalId;

    /**
    * FK_DETAIL_ID
    */
    private String fkDetailId;

    /**
    * 对账日期
    */
    private String chkDt;

    /**
    * 监管账户
    */
    private String accountNum;

    /**
    * 交易类型 1-正常进账;2-专用pos刷卡;3-不明进账;4-利息;5-年费、手续费;6-司法扣划;7-拨付成功;8-拨付失败;9-冲正成功;10-退款成功;11-退款失败;12-冻结;13-解冻;14-按揭贷款
    */
    private String dataType;

    /**
    * 交易金额
    */
    private YGAmt amount;

    /**
    * 手续费
    */
    private YGAmt commission;

    /**
    * 办件号
    */
    private String noticeNum;

    /**
    * 核心流水号
    */
    private String sn;

    /**
    * 交易时间
    */
    private String time;

    /**
    * 贷方账户名称
    */
    private String senderName;

    /**
    * 贷方账号
    */
    private String senderNum;

    /**
    * 备注（失败原因等）
    */
    private String node;

    /**
    * 不明入账类型 1-贷款;2-其他
    */
    private String unknownType;

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
