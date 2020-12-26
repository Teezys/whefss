package com.murong.ecp.netpay.whefss.web.service.server.bo;

import lombok.Data;

@Data
public class FundsBalance extends RespondHead {

    private String monitorNum;
    private String accountName;
    private String accountNum;
    private String balanceBank;
    private String balanceSystem;
    private String balanceTime;
    private String accountState;

}
