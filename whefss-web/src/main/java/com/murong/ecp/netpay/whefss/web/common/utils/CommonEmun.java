package com.murong.ecp.netpay.whefss.web.common.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * 公共方法类
 *
 * @author zhang_wei 2019-09-23
 *
 *
 */
public class CommonEmun {

    public static final String DATE_FOR = "yyyyMMddHHmmss";

    /**
     *
     */
    public static final String ACCT_SIGN = "1";
    public static final String ACCT_CANCEL = "0";


    /**
     *  核心账户状态为 A 正常
     */
    public static final String ACCT_STATUS_ACTIVITY = "A";


    /**
     * 通讯响应结果：Y-通讯成功,E-通讯异常
     */
    public final static String CMUC_FLG_SUCCESS = "Y";

    public final static String CMUC_FLG_FAIL = "E";
    /**
     * 消息接收标志：1-成功,0-失败
     */
    public static String RECEIVE_SUCCESS = "1";
    public static String RECEIVE_FAIL = "0";
}
