package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.dict.ECPDict;
import com.murong.ecp.netpay.whefss.web.common.utils.PageRequest;
import lombok.Data;

@Data
public class TranRecordReqBO extends PageRequest {

    private String startDate;

    private String endDate;

    private String sn;

    private String accountNum;
    @ECPDict(required = true, message = "tranType值不合法")
    private String tranType;

    private String noticeNum;

    private String flag;
}
