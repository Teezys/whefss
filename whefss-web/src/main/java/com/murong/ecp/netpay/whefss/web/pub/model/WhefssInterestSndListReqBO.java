package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.netpay.whefss.web.common.utils.PageRequest;
import lombok.Data;

/**
* Created by syxlw on 2018/4/28.
*/
@Data
public class WhefssInterestSndListReqBO extends PageRequest {
    /**
     * search
     */
    private String search;
}
