/**
 * WhefssAccHaveAcceptedLogDO.java
 * Copyright(C) 北京沐融信息科技有限公司
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 17:56:07.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.entity;

import com.murong.ecp.bp.common.util.BaseDO;
import net.sf.json.JSONObject;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;

import java.math.BigDecimal;
/**
 * 已入账流水表房管流水查询时使用，但不知道源数据怎么来的2.8接口
 */
@Data
public class WhefssAccHaveAcceptedLogDO extends BaseDO {

    /**
    * 缴款通知书编号
    */
    private String noticeNum;

    /**
    * 流水号
    */
    private String sn;

    /**
    * 监管账号
    */
    private String accountNum;

    /**
    * 下载时间:yyyymmddhhmiss
    */
    private String loadTime;

    /**
    * 发生交易金额：两位小数
    */
    private YGAmt amount;

    /**
    * 时间
    */
    private String time;

    /**
    * 类型描述:正常进账;专用POS刷卡;不明进账;利息;年费、手续费;司法扣划;拨付成功;拨付失败;冲正成功;退款成功;退款失败;冻结;解冻;按揭贷款;
    */
    private String typeDescriptions;

    /**
    * 进账类型：1-正常进账;2-专用POS刷卡;3-不明进账;4-利息;5-年费、手续费;6-司法扣划;7-拨付成功;8-拨付失败;9-冲正成功;10-退款成功;11-退款失败;12-冻结;13-解冻;14-按揭贷款;
    */
    private String type;

    /**
    * 状态：正常；正常_划分中；正常_已划分完成；冲正_申请中；正常_冲正审核未通过；冲正_审核通过；冲正_银行操作成功；作废；
    */
    private String state;

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
