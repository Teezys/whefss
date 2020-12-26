package com.murong.ecp.netpay.whefss.web.pub.model;

import lombok.Data;

/**
 * @author tianlp
 * @version Id: TranRecordQueryReqBO.java, v 0.1 2020/10/30 11:25 tinalp Exp $$
 */
@Data
public class TranRecordQueryReqBO {

    /**
     * 开始时间
     */
    private String startDate;

    /**
     * 结束时间
     */
    private String endDate;

    /**
     * 监管账号
     */
    private String accNum;

    /**
     * 银行流水
     */
    private String sn;

    /**
     * 交易类型
     */
    private String tranType;

    /**
     * 通知书编号
     */
    private String noiceNum;
}
