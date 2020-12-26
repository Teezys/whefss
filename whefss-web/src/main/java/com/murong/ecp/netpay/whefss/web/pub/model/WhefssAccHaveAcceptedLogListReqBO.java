package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.dict.ECPDict;
import com.murong.ecp.netpay.whefss.web.common.utils.PageRequest;
import lombok.Data;
import net.sf.oval.constraint.Size;

/**
* Created by syxlw on 2018/4/28.
*/
@Data
public class WhefssAccHaveAcceptedLogListReqBO extends PageRequest {
    /**
     * search
     */
    private String search;

    /**
     * 交易日期
     */
    @Size(max = 8, message = "交易日期不合法")
    private String tranDate;

    @Size(max = 64, message = "监管账户不合法")
    private String svsAcNo;
}
