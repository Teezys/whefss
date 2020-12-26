package com.murong.ecp.netpay.whefss.web.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 东亚核心交易类型常量类
 *
 * @author zhang_wei
 * @date 2019-09-26
 */
public class BEATransTypeContants {

    private static Map<String, String> TRANS_TYPES = new HashMap<String, String>();

    static {
        TRANS_TYPES.put("SVC_FR_09086", "NDSEBillingBox");// 企业电子回单箱
        TRANS_TYPES.put("SVC_FR_09005", "NDSCurrentDepositAccBalanceInq");// 账户余额查询-活期
        TRANS_TYPES.put("SVC_FR_09052", "NDSAccInq");// 账户信息查询
    }

    /**
     *
     * @param key 服务码
     * @return 获取到的交易类型
     */
    public static String getBeandsTrantype(String key) {
        return TRANS_TYPES.get(key);
    }
}
