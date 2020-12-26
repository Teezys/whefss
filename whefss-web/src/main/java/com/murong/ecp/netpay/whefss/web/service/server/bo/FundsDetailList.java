package com.murong.ecp.netpay.whefss.web.service.server.bo;

import lombok.Data;

import java.util.List;

@Data
public class FundsDetailList extends RespondHead{
    private int total;
    private List<FundsDetail> FundsDetail;
}
