package com.murong.ecp.netpay.whefss.web.service.server.bo;

import lombok.Data;

@Data
public class FundsRefund extends RespondHead{
    private String noticeNum;
    private String contractNum;
    private String snoticeNum;
    private String buyers;
    private String idType;
    private String idNum;
    private String bankName;
    private String accountName;
    private String accountNum;
    private String totalPrice;
    private String refundPrice;
    private String corpAccountName;
    private String corpAccountNum;
    private String corpAccountBank;
    private String corpAccountBranchNum;

}
