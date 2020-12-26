package com.murong.ecp.netpay.whefss.web.service.server.bo;

import lombok.Data;

@Data
public class AccountsOpen extends RespondHead{
    private String noticeNum;
    private String monitorNum;
    private String bankName;
    private String branchName;
    private String branchNum;
    private String corpName;
    private String accountName;
    private String projectName;
    private String monitorRange;
}
