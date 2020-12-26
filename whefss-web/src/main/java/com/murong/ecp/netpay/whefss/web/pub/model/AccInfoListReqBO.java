package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.netpay.whefss.web.common.utils.PageRequest;
import lombok.Data;

/**
 * @author tianlp
 * @version Id: AccInfoListReqBO.java, v 0.1 2020/11/4 19:03 tinalp Exp $$
 */
@Data
public class AccInfoListReqBO extends PageRequest {

    /**
     * 监管账号
     */
    private String svsAcNo;

}
