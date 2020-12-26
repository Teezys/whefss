package com.murong.ecp.netpay.whefss.web.common.utils;

import com.murong.ecp.bp.common.util.MrSpringContextHolder;
import com.yuangou.ecp.biz.transengine.AbstractTransaction;
import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;
import com.yuangou.ecp.biz.transengine.util.EDBUtil;
import com.yuangou.ecp.bp.comp.expr.YGExpUtil;
import com.yuangou.ecp.bp.comp.pubatc.YGPUBATCUtil;
import com.yuangou.ecp.bp.core.common.exception.YGException;
import com.yuangou.ecp.bp.core.common.message.YGBizMessage;
import com.yuangou.ecp.bp.core.common.message.YGBizMessageContext;
import com.yuangou.ecp.bp.core.common.message.YGEDB;
import com.yuangou.ecp.bp.core.common.yglog4j.Logger;
import com.yuangou.ecp.bp.core.common.yglog4j.YGLogger;
import org.apache.commons.lang.reflect.MethodUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;

/**
 * 支持请求交易码映射到方法
 * xxx.do?_action=query
 */
public abstract class BaseAction extends AbstractTransaction {

    @Override
    public void doProcess(YGBizMessageContext bizCtx) throws YGException {
        YGBizMessage msg = bizCtx.getCurrentMsg();
        String txnCod = msg.getHeadItem(YGBizMessage.STC);
        YGEDB root = msg.getEDBBody();
        Logger msgLog = YGLogger.getLogger(msg);

        Object bean = MrSpringContextHolder.getBean(txnCod);
        String methodName = root.getData("_action");
        if( methodName == null ) {
            methodName = "execute";
        }

        try {
            Method method = BeanUtils.findMethodWithMinimalParameters(bean.getClass(),methodName);
            if( method == null ) {
                throw new IllegalArgumentException("not found method:[" + methodName + "]");
            }
            Type[] types = method.getGenericParameterTypes();
            Object obj = null;
            ResultT resultT = null;
            if( types.length < 1 ) {
                throw new IllegalArgumentException("method:[" + methodName + "]'s argument number less than 1");
            }
            if( types.length == 1 ) {
                resultT = (ResultT) MethodUtils.invokeMethod(bean, methodName, new Object[] {bizCtx});
            } else {

                List params = new ArrayList();
                params.add(bizCtx);
                Annotation[][] parameterAnnotations = method.getParameterAnnotations();
                int i = 1;
                for( int j  = 0; parameterAnnotations != null && j < parameterAnnotations.length; j++ ) {
                    Annotation[]  parameterAnnotation = parameterAnnotations[j];
                    for (Annotation annotation : parameterAnnotation) {
                        if (annotation instanceof RequestParam) {
                            RequestParam param = (RequestParam) annotation;
                            String value1 = root.getData(param.value());
                            Object value = null;
                            Class type = ((Class)types[j]);
                            System.out.println((type.getName()));

                            if( "java.lang.Long".equals(type.getName()) ) {
                                value = Long.valueOf(value1);
                            } else {
                                value = value1;
                            }
                            if( value == null && param.required() ){
                                throw new IllegalArgumentException(param.name() + " is required");
                            }
                            params.add(value);
                        } else if( annotation instanceof RequestBody ) {
                            obj = ((Class) types[1]).newInstance();
                            EDBUtil.edb2objNam(root, obj);
                            params.add(obj);
                        }
                    }
                }

                if( params.size() == 1 ) {
                    obj = ((Class) types[1]).newInstance();
                    EDBUtil.edb2objNam(root, obj);
                    params.add(obj);
                }

                resultT = (ResultT) MethodUtils.invokeMethod(bean, methodName, params.toArray());
            }

            root.removeChildNode("gda");
            root.setData("gda.msg_cd", resultT.getMsgCod());
            root.setData("gda.msg_inf", resultT.getMsgInf());

            if( resultT instanceof PageResultT ) {
                YGPageEntity pageEntity = ((PageResultT)resultT).getPageEntity();
                if( pageEntity != null ) {
                    EDBUtil.obj2edb(((PageResultT) resultT).getPageEntity(), root);
                }
            }

            if(resultT.getData() != null ) {
                if( resultT.getData() instanceof YGEDB) {
                    root.combineAll((YGEDB)resultT.getData());
                } else if( resultT.getData() instanceof List) {
                    EDBUtil.list2edbNam((List) resultT.getData(), "data", root);
                } else{
                    YGEDB recNod = root.addNode("data");
                    EDBUtil.obj2edbNam(resultT.getData(), recNod);
                }
            }
        } catch (Throwable t) {
            if( t instanceof InvocationTargetException) {
                t = ((InvocationTargetException)t).getTargetException();
            }

            if( t instanceof UndeclaredThrowableException) {
                t = ((UndeclaredThrowableException)t).getUndeclaredThrowable();
            }


            root.setData("gda.msg_cd", "SCM99999");
            root.setData("gda.msg_inf", "系统错误");

            msgLog.info(t, t);
            logger.info("action:[" + txnCod + "] execute failure", t);

        }

        String msgCd = root.getData("gda.msg_cd");
        if (!YGExpUtil.is_succ(msgCd)) {
            YGPUBATCUtil.rollbackWork(bizCtx);
        }
    }

    public boolean isFail(YGEDB root) {
        String msgCd = root.getData("gda.msg_cd");
        return msgCd == null || msgCd.length() < 8 || !"00000".equals(msgCd.substring(3));
    }

    public boolean isSucc(YGEDB root) {
        return !isFail(root);
    }
}
