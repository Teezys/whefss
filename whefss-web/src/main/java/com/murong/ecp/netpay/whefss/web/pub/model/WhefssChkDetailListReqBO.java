package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.netpay.whefss.web.common.utils.PageRequest;
import lombok.Data;

/**
* Created by syxlw on 2018/4/28.
*/
@Data
public class WhefssChkDetailListReqBO extends PageRequest {
    /**
     * 开始日期
     */
    private String startDate;
    /**
     * 结束日期
     */
    private String endDate;
    /**
     * 监管账户
     */
    private String accountNum;
}
