package com.murong.ecp.netpay.whefss.web.service.server.bo;

import lombok.Data;

import java.util.List;

@Data
public class FundsUploadInfo{
    private String balance;
    private String time;
    private String account_num;
    private List<FundsUploadDetail> detail;
}
