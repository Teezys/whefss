package com.murong.ecp.netpay.whefss;

import com.murong.ecp.bp.common.junit.YGTestCase03;
import com.murong.ecp.netpay.whefss.web.service.server.WhefssService;
import com.yuangou.ecp.bp.core.common.message.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class WhefssServiceTest extends YGTestCase03 {
    @Autowired
    private WhefssService whefssService;

    @Test
    public void test() throws  Exception{
        YGBizMessageContext ctx =createCtx("whefsspubbpcWeb");
        YGBizMessage bizMsg = ctx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        whefssService.accountsOpen(ctx,"12345678");


    }

    public static YGBizMessageContext createCtx(String containerName) throws  Exception{
        YGBizMessage msg = new YGBizMessage(containerName,"PLTLNO");
        msg.setHeadItem("ETC","text/etf");
        msg.setHeadItem("SCH","rq");
        msg.setHeadItem("STF","INFO");
        YGBizContext.pushCurrentContext(YGBizContext.getRootContext());
        YGBizMessageContext ctx = new YGBizMessageContext();
        ctx.pushParent(YGBizContext.getRootContext());
        YGBizMessageContext.setCurrentMessageContext(ctx);
        ctx.setCurrentMsg(msg);
        YGEDB root = YGEDBFactory.createEDB();

        msg.setBody(root);
        msg.setHeadItem("STM",System.currentTimeMillis());
        msg.setHeadItem("SDT",containerName);
        return ctx;
    }
}
