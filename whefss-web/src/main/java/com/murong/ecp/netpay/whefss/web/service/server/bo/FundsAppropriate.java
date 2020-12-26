package com.murong.ecp.netpay.whefss.web.service.server.bo;

import lombok.Data;

@Data
public class FundsAppropriate extends RespondHead{
    private String noticeNum;
    private String bankName;
    private String branchName;
    private String accountName;
    private String accountNum;
    private String bankTo;
    private String nameTo;
    private String accountTo;
    private String branchTo;
    private String amount;
}
