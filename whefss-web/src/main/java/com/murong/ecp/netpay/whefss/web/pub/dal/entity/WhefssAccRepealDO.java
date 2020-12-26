/**
 * WhefssAccRepealDO.java
 * Copyright(C) 北京沐融信息科技有限公司
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 17:46:05.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.entity;

import com.murong.ecp.bp.common.util.BaseDO;
import net.sf.json.JSONObject;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;

import java.math.BigDecimal;
/**
 * WHEFSS_ACC_REPEAL
 */
@Data
public class WhefssAccRepealDO extends BaseDO {

    /**
    * 冲正申请书编号
    */
    private String noticeNum;

    /**
    * 购房合同编号
    */
    private String contractNum;

    /**
    * 银行代码
    */
    private String bankCode;

    /**
    * 监管账户开户总行
    */
    private String bankName;

    /**
    * 监管账户开户支行
    */
    private String branchName;

    /**
    * 监管账户名称
    */
    private String accountName;

    /**
    * 监管账户账号
    */
    private String accountNum;

    /**
    * 冲正金额
    */
    private YGAmt amount;

    /**
    * 资金流水号
    */
    private String orgSn;

    /**
    * 进帐时间
    */
    private String orgTime;

    /**
    * 流水号
    */
    private String sn;

    /**
    * 操作时间
    */
    private String time;

    /**
    * 发送状态:00-未发送;01-已发送对账状态待更新;02 对账成功;03-对账失败房管已接收明细;04-对账失败房管未接收明细;05-异常数据（不提交给房管）
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
    * 系统时间
    */
    private String sysTime;

    /**
    * 柜员id
    */
    private String userId;

    /**
    * 柜员名
    */
    private String userName;

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
