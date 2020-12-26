package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.dict.ECPDict;
import lombok.Data;

/**
 * Created by syxlw on 2018/4/28.
 */
@Data
public class ChkSummitReqBO {
    /**
     * 对账日期
     */
    @ECPDict(required = true, length = 8, message = "对账日期值不合法")
    private String chkDt;


}
