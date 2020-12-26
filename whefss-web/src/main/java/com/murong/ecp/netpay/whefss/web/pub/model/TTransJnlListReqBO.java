package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.netpay.whefss.web.common.utils.PageRequest;
import lombok.Data;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.Size;

/**
* Created by syxlw on 2018/4/28.
*/
@Data
public class TTransJnlListReqBO extends PageRequest {
    /**
     * search
     */
    private String search;

    /**
     * 交易日期
     */
    @Size(max = 8, message = "txnDt值不合法")
    private String tranDate;

    @Size(max = 64, message = "svsAcNo值不合法")
    private String svsAcNo;

}
