package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.netpay.whefss.web.common.utils.PageRequest;
import lombok.Data;

/**
* Created by syxlw on 2018/4/28.
*/
@Data
public class WhefssPosListReqBO extends PageRequest {
    /**
     * 交易日期
     */
    private String tranDate;
    /**
     * 监管账户
     */
    private String accountNum;
}
