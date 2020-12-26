package com.murong.ecp.netpay.whefss.web.service.server.bo;

import lombok.Data;

@Data
public class AccountsRemove extends RespondHead{
    private String noticeNum;
    private String monitorNum;
    private String bankName;
    private String branchName;
    private String accountNum;
    private String accountName;
}
