package com.murong.ecp.netpay.whefss.web.service.server.bo;

import lombok.Data;

@Data
public class AccountOpenNotify {
    private String noticeNum;    ;
    private String monitorNum;
    private String accountName;
    private String accountNum;
    private String sn;
    private String time;
}
