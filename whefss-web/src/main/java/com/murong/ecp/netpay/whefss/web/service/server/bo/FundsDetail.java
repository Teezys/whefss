package com.murong.ecp.netpay.whefss.web.service.server.bo;

import lombok.Data;

@Data
public class FundsDetail{
    private String sn;
    private String noticeNum;
    private String amount;
    private String time;
    private String type;
    private String state;
}

