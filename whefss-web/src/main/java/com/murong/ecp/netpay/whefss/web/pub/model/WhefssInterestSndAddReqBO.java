package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.dict.ECPDict;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;


/**
* Created by syxlw on 2018/4/28.
*/
@Data
public class WhefssInterestSndAddReqBO {
    /**
     * 流水号
     */
    @ECPDict(required = true,length = 75, message = "sn值不合法")
    private String sn;

    /**
     * 监管账户账号
     */
    @ECPDict(required = true,length = 60, message = "accountNum值不合法")
    private String accountNum;

    /**
     * 利息、年费
     */
    @ECPDict(required = true, message = "interest值不合法")
    private YGAmt interest;

    /**
     * 操作时间
     */
    @ECPDict(required = true,length = 30, message = "time值不合法")
    private String time;

    /**
     * 进账类别:1-进账;2-出账
     */
    @ECPDict(required = true,length = 2, message = "type值不合法")
    private String type;

    /**
     * 备用1
     */
    @ECPDict(required = false,length = 384, message = "reserve1值不合法")
    private String reserve1;

    /**
     * 备用2
     */
    @ECPDict(required = false,length = 384, message = "reserve2值不合法")
    private String reserve2;

    /**
     * 备用3
     */
    @ECPDict(required = false,length = 384, message = "reserve3值不合法")
    private String reserve3;

}
