package com.murong.ecp.netpay.whefss.web.service.server.bo;

import lombok.Data;

@Data
public class FundsCorrect extends RespondHead{
    private String noticeNum;
    private String contractNum;
    private String bankName;
    private String branchName;
    private String accountName;
    private String accountNum;
    private String amount;
    private String orgSn;
    private String orgTime;
}
