package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.util.BaseDO;
import lombok.Data;

@Data
public class TranRecordRspBO extends BaseDO {
    private String tranTime;
    private String sn;
    private String accountNum;
    private String toAccount;
    private String toAccountName;
    private String noticeNum;
    private String sendStatus;
    private String tranAmt;
    private String commission;
}
