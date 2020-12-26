package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.dict.ECPDict;
import lombok.Data;

/**
 * @author tianlp
 * @version Id: WhefssAccCancelInfoQueryReqBO.java, v 0.1 2020/11/3 19:23 tinalp Exp $$
 */
@Data
public class WhefssAccCancelInfoSubmitReqBO {

    /**
     * 销户通知书编号
     */
    @ECPDict(required = true, length = 20, message = "noticeNum值不合法")
    private String noticeNum;
    /**
     * 监管账户账号
     */
    @ECPDict(required = true, length = 60, message = "accountNum值不合法")
    private String accountNum;
    /**
     * 监管编号
     */
    @ECPDict(required = true, length = 30, message = "monitorNum值不合法")
    private String monitorNum;

}
