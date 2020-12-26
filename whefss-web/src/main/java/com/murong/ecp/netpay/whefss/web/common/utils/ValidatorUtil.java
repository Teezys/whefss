package com.murong.ecp.netpay.whefss.web.common.utils;


import com.yuangou.ecp.bp.core.common.exception.YGException;
import com.yuangou.ecp.bp.core.constants.YGMessageCode;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import java.util.List;

/**
 * 参数验证工具
 * @author XYY-SH
 * @version Id: ValidatorUtil.java, v 0.1 2016/11/9 19:58 XYY-SH Exp $$
 */
public final class ValidatorUtil {

    /**
     * 隐藏工具类构造方法
     */
    private ValidatorUtil(){}
    private static Validator validator = new Validator();


    /**
     * 请求参数非空、格式验证，请求对象
     *
     * @param object 请求校验参数
     */
    public static void validateObject(Object object) throws YGException {
        if( object == null ) {
            throw new YGException(YGMessageCode.ERR_SYSTEM);
        }
        List<ConstraintViolation> violations = validator.validate(object);

        if (violations.size() > 0) {
            throw new YGException(YGMessageCode.ERR_SYSTEM, violations.get(0).getContext() + "=" + violations.get(0).getInvalidValue()  + "," + violations.get(0).getMessage());
        }
    }
}
