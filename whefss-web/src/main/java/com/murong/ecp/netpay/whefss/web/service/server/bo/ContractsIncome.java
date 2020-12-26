package com.murong.ecp.netpay.whefss.web.service.server.bo;

import lombok.Data;

@Data
public class ContractsIncome extends RespondHead{
    private String noticeNum;
    private String monitor_num;
    private String buyers;
    private String idType;
    private String idNum;
    private String bankName;
    private String accountName;
    private String accountNum;
    private String totalPrice;
    private String firstPrice;
}
