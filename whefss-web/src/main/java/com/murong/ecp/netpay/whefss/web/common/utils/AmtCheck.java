package com.murong.ecp.netpay.whefss.web.common.utils;

import net.sf.oval.configuration.annotation.Constraint;

import java.lang.annotation.*;


/**
 * 日期格式校验
 * Created by wanglei on 2020-09-03 15:20:33.
 * @author wanglei
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Constraint(checkWith = AmtCheckValidator.class)
public @interface AmtCheck {
    String message() default "金额格式错误";
}
