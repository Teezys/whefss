package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.dict.ECPDict;
import lombok.Data;

/**
 * Created by syxlw on 2018/4/28.
 */
@Data
public class WhefssAccInfoDetailReqBO {
    /**
     * 开户预审结果单编号
     */
    @ECPDict(required = true, length = 32, message = "accountNum值不合法")
    private String accountNum;


}
