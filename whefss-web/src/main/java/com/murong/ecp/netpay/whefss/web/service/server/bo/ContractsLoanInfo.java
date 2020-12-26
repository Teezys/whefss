package com.murong.ecp.netpay.whefss.web.service.server.bo;

import lombok.Data;

@Data
public class ContractsLoanInfo extends RespondHead{
    private String noticeNum;
    private String buyer;
    private String idType;
    private String idNum;
    private String priceSum;
    private String firstMoney;
    private String firstPaied;
    private String accountName;
    private String accountNum;
    private String area;
}
