package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.netpay.whefss.web.common.utils.PageRequest;
import lombok.Data;

/**
* Created by syxlw on 2018/4/28.
*/
@Data
public class WhefssAmpTranidListReqBO extends PageRequest {


    /**
     * 开始日期
     */
    private String startDate;

    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 交易类型
     */
    private String bustype;

    /**
     * 操作类型
     */
    private String fgType;

    /**
     * 交易状态
     */
    private String sendStatus;
}
