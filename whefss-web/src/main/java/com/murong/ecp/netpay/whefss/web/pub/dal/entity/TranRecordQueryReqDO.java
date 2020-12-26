package com.murong.ecp.netpay.whefss.web.pub.dal.entity;

import com.murong.ecp.bp.common.util.BaseDO;
import lombok.Data;

/**
 * @author tianlp
 * @version Id: TranRecordQueryReqDO.java, v 0.1 2020/10/30 13:47 tinalp Exp $$
 */
@Data
public class TranRecordQueryReqDO extends BaseDO {

    /**
     * 交易时间
     */
    private String tranTime ;

    /**
     * 交易流水
     */
    private String sn;

    /**
     * 监管账号
     */
    private String accNum;

    /**
     * 对方账户
     */
    private String toAccNum;

    /**
     * 对方户名
     */
    private String accName;

    /**
     * 通知书编号
     */
    private String noticeNum;

    /**
     * 发送状态
     */
    private String sendStatus;

    /**
     * 交易金额
     */
    private String  tranAmt;

    /**
     * 手续费
     */
    private String commissionAmt;


}
