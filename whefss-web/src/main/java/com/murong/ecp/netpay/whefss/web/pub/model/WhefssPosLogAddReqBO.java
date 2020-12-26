package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.dict.ECPDict;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;


/**
* Created by syxlw on 2018/4/28.
*/
@Data
public class WhefssPosLogAddReqBO {


    /**
     * 交易日期
     */
    @ECPDict(required = true,length = 12, message = "fileDate值不合法")
    private String fileDate;

    /**
     * 交易日期
     */
    @ECPDict(required = true,length = 12, message = "tranDate值不合法")
    private String tranDate;


    /**
     * 监管账号
     */
    @ECPDict(required = true,length = 60, message = "accountNum值不合法")
    private String accountNum;

    /**
     * 交易金额
     */
    @ECPDict(required = true, message = "tranAmt值不合法")
    private YGAmt tranAmt;

    /**
     * nds对应流水号
     */
    @ECPDict(required = true,length = 75, message = "sn值不合法")
    private String sn;

}
