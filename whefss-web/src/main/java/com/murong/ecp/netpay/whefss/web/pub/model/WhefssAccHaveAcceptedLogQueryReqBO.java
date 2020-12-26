package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.dict.ECPDict;
import lombok.Data;
import net.sf.oval.constraint.Size;

/**
* Created by syxlw on 2018/4/28.
*/
@Data
public class WhefssAccHaveAcceptedLogQueryReqBO {
    /**
     * 交易日期
     */
    @Size(max = 8, message = "交易日期不合法")
    @ECPDict(required = true, message = "时间不能为空")
    private String tranDate;

    @Size(max = 64, message = "监管账户不合法")
    @ECPDict(required = true, message = "监管账户值不合法")
    private String svsAcNo;


}
