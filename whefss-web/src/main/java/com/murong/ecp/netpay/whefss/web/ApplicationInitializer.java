package com.murong.ecp.netpay.whefss.web;

import com.yuangou.ecp.bp.core.common.message.YGBizContext;
import com.yuangou.ecp.bp.core.common.util.YGECPProperty;
import com.yuangou.ecp.bp.core.common.yglog4j.YGLogger;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author Alawn
 */
public class ApplicationInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        System.out.println("HWorkDir=" + YGECPProperty.getWorkDir());
        YGBizContext.setCurrentContext(YGBizContext.getRootContext());
        YGBizContext.getCurrentContext().setProperty("SVR.log", YGLogger.getLogger("container.trc"));
        System.setProperty("java.net.preferIPv4Stack", "true");


        return builder.sources(Application.class);
    }
}