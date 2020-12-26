package com.murong.ecp.netpay.whefss.web.service.server.bo;

import lombok.Data;

@Data
public class FundsUploadDetail {
    private String dataType;
    private String amount;
    private String commission;
    private String noticeNum;
    private String sn;
    private String time;
    private String senderName;
    private String senderNum;
    private String note;
    private String unknownType;
}
