package com.murong.ecp.netpay.whefss.web.interceptor;

import com.murong.ecp.bp.common.annotation.ECPInterceptorMapping;
import com.murong.ecp.netpay.whefss.web.common.utils.CommonMessageCode;
import com.yuangou.ecp.biz.transengine.YGAbstractBizIntercetpor;
import com.yuangou.ecp.biz.transengine.util.GDAUtil;
import com.yuangou.ecp.bp.comp.expr.YGExpUtil;
import com.yuangou.ecp.bp.comp.pubatc.YGPUBATCUtil;
import com.yuangou.ecp.bp.core.common.exception.YGException;
import com.yuangou.ecp.bp.core.common.message.YGBizMessage;
import com.yuangou.ecp.bp.core.common.message.YGBizMessageContext;
import com.yuangou.ecp.bp.core.common.message.YGEDB;
import com.yuangou.ecp.bp.core.common.yglog4j.Logger;
import com.yuangou.ecp.bp.core.common.yglog4j.YGLogger;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

/**
 *
 * 珠海房管的公共拦截器
 *
 * @author wanglei
 *
 */
@ECPInterceptorMapping({"whefsspubbpcWeb"})
public class WhEfssCommonInterceptor extends YGAbstractBizIntercetpor {


    @Override
    public void onError(YGBizMessageContext ygBizMessageContext, Throwable throwable) throws YGException {
        YGBizMessage msg = ygBizMessageContext.getCurrentMsg();
        YGEDB edb = msg.getEDBBody();
        ygBizMessageContext.setProperty("request_data", edb.toString());
        ygBizMessageContext.getProperty("request_data");
        Logger logger = YGLogger.getLogger(msg);

        if (logger.isInfoEnabled()) {
            logger.info("执行异常处理:[" + this.getClass().getSimpleName() + ".onError]");
            logger.info("运行时异常，回滚数据库事务");
        }

        YGPUBATCUtil.rollbackWork(ygBizMessageContext);
        GDAUtil.setMsg_cd(msg, CommonMessageCode.FAIL.getMsgCod());
        GDAUtil.setMsg_inf(msg, CommonMessageCode.FAIL.getMsgInf());

        if (logger.isErrorEnabled()) {
            logger.error(ExceptionUtils.getFullStackTrace(throwable));
        }

        if (logger.isInfoEnabled()) {
            logger.info("异常处理结束:[" + this.getClass().getSimpleName() + ".onError]");
        }
    }

    @Override
    public boolean onRequest(YGBizMessageContext bizCtx) throws YGException {
        // 拦截器公共前处理
        YGBizMessage msg = bizCtx.getCurrentMsg();
        YGEDB edb = msg.getEDBBody();
        bizCtx.setProperty("request_data", edb.toString());
        logger.info("EDBBody Begin---->" + edb.getData("EDBBody"));
        Logger logger = YGLogger.getLogger(msg);
        if (logger.isInfoEnabled()) {
            logger.info("执行前处理:[" + this.getClass().getSimpleName() + ".onRequest]");
        }
        return true;
    }

    @Override
    public void onResponse(YGBizMessageContext bizCtx) throws YGException {
        // 拦截器公共后处理
        YGBizMessage msg = bizCtx.getCurrentMsg();
        YGEDB edb = msg.getEDBBody();
        Logger logger = YGLogger.getLogger(msg);
        logger.info("执行后处理:[" + this.getClass().getSimpleName() + ".onResponse]");


        String msgCod = GDAUtil.getMsg_cd(msg);
        if (StringUtils.isBlank(msgCod)) {
            if (logger.isInfoEnabled()) {
                logger.info("消息编码为空");
            }
            GDAUtil.setMsg_cd(msg, CommonMessageCode.FAIL.getMsgCod());
            GDAUtil.setMsg_inf(msg, CommonMessageCode.FAIL.getMsgInf());
            YGPUBATCUtil.rollbackWork(bizCtx);
        }
        msgCod = GDAUtil.getMsg_cd(msg);

        if (YGExpUtil.is_succ(msgCod)) {
            if (logger.isInfoEnabled()) {
                logger.info("业务处理成功,提交数据库事务");
                logger.info("msgCod----->" + msgCod);
            }
            edb.setData("gda.msg_typ", "N");
            YGPUBATCUtil.commitWork(bizCtx);
        } else {
            if (logger.isInfoEnabled()) {
                logger.info("业务处理失败,回滚数据库事务");
            }
            edb.setData("gda.msg_typ", "E");
            YGPUBATCUtil.rollbackWork(bizCtx);
        }

        logger.info("后处理结束:[" + this.getClass().getSimpleName() + ".onResponse]");
        logger.info("后处理结束后的数据总线为：" + edb);
    }
}
