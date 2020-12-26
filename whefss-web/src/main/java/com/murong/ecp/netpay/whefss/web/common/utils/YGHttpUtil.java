package com.murong.ecp.netpay.whefss.web.common.utils;

import com.yuangou.ecp.bp.core.common.message.YGBizMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by syxlw on 2018/8/5.
 */
public class YGHttpUtil {
	public static HttpServletRequest getHttpRequest(YGBizMessage msg) {
		return (HttpServletRequest) msg.getObjectHeadItem("_WEB_REQUEST");
	}


	public static HttpServletResponse getHttpResponse(YGBizMessage msg) {
		return (HttpServletResponse) msg.getObjectHeadItem("_WEB_RESPONSE");
	}


	public static HttpSession getHttpSession(YGBizMessage msg) {
		HttpSession httpSession = (HttpSession) msg.getObjectHeadItem("_WEB_SESSION");
		if (httpSession == null) {
			return getHttpRequest(msg).getSession();
		} else {
			return httpSession;
		}
	}
}
