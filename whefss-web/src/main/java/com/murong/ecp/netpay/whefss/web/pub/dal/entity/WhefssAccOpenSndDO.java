/**
 * WhefssAccOpenSndDO.java
 * Copyright(C) 北京沐融信息科技有限公司
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 18:07:10.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.entity;

import com.murong.ecp.bp.common.util.BaseDO;
import net.sf.json.JSONObject;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;

import java.math.BigDecimal;
/**
 * WHEFSS_ACC_OPEN_SND
 */
@Data
public class WhefssAccOpenSndDO extends BaseDO {

    /**
    * 监管编号
    */
    private String monitorNum;

    /**
    * 开户预审结果单编号
    */
    private String noticeNum;

    /**
    * 银行代码
    */
    private String bankCode;

    /**
    * 监管账户名称
    */
    private String accountName;

    /**
    * 监管账户账号
    */
    private String accountNum;

    /**
    * 银行内部流水号
    */
    private String sn;

    /**
    * 操作时间
    */
    private String time;

    /**
    * 发送状态00:未发送；01:发送成功；02: 发送失败；
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
    * 系统时间Yyyymmddhhmiss
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
