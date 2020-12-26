/**
 * WhefssDayEndAccountLogDO.java
 * Copyright(C) 北京沐融信息科技有限公司
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 17:58:53.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.entity;

import com.murong.ecp.bp.common.util.BaseDO;
import net.sf.json.JSONObject;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;

import java.math.BigDecimal;
/**
 * 对账结果信息查询
 */
@Data
public class WhefssDayEndAccountLogDO extends BaseDO {

    /**
    * 日期:Yyyymmdd
    */
    private String trandate;

    /**
    * 监管账号
    */
    private String accountnum;

    /**
    * 城市名
    */
    private String citycode;

    /**
    * 日终状态:01 –本地数据预处理失败；02 –成功；11-明细提交房管返回失败；12-明细提交房管返回成功；21-下载房管明细失败；22-下载房管明细成功；31-本地对账失败；
    */
    private String accstatus;

    /**
    * 日终错误信息
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
}
