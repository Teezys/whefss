/**
 * WhefssAjdkSndDO.java
 * Copyright(C) 北京沐融信息科技有限公司
 * ------------------------------------------------
 * Created by xu_lw on 2020-11-16 16:24:45.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.entity;

import com.murong.ecp.bp.common.util.BaseDO;
import net.sf.json.JSONObject;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;

import java.math.BigDecimal;
/**
 * WHEFSS_AJDK_SND
 */
@Data
public class WhefssAjdkSndDO extends BaseDO {

    /**
    * 缴款通知书编号:含带格式附言的他行转账HTBH01123456701
    */
    private String noticeNum;

    /**
    * 监管编号
    */
    private String monitorNum;

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
    * 实收金额
    */
    private YGAmt realIn;

    /**
    * 手续费
    */
    private YGAmt commission;

    /**
    * 进账时间
    */
    private String time;

    /**
    * 流水号
    */
    private String sn;

    /**
    * 购房人姓名
    */
    private String buyer;

    /**
    * 购房人证件类型
    */
    private String idType;

    /**
    * 购房人证件号
    */
    private String idNum;

    /**
    * 总房款
    */
    private YGAmt priceSum;

    /**
    * 首付款
    */
    private YGAmt firstMoney;

    /**
    * 首付是否缴齐
    */
    private String firstPaied;

    /**
    * 面积
    */
    private YGAmt area;

    /**
    * 贷方账户名称
    */
    private String senderName;

    /**
    * 货方账号
    */
    private String senderNum;

    /**
    * 备注:失败原因等
    */
    private String note;

    /**
    * 发送状态:00-未发送;01-已发送对账状态待更新;02 对账成功;03-对账失败房管已接收明细;04-对账失败房管未接收明细;05-异常数据（不提交给房管）
    */
    private String sendStatus;

    /**
    * 系统流水号
    */
    private String sysTranid;

    /**
    * 系统时间
    */
    private String sysTime;

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
