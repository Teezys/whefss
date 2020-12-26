package com.murong.ecp.netpay.whefss.web.common.utils;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class AmtCheckValidator extends AbstractAnnotationCheck<AmtCheck> {
    private static final long serialVersionUID = 1L;

    @Override
    public void configure(final AmtCheck constraintAnnotation) {
        super.configure(constraintAnnotation);
        setMessage(constraintAnnotation.message());
    }

    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate, OValContext context, Validator validator) throws OValException {
        if (valueToValidate == null) {
            return true;
        }
        String value = valueToValidate.toString();
        try {
            Pattern pattern= compile("(^[1-9]([0-9]+)?(\\.[0-9]{1,2})?$)|(^(0){1})(\\.[0-9]{1,2}?$)");
            Matcher match=pattern.matcher(value);
            boolean flag = match.matches();
            if(!flag){
                return false;
            }
            if(!".".equals(value.substring(value.length()-3,value.length()-2))){
                return false;
            }
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public void setMessage(final String message) {
        super.setMessage(message);
    }

    public static void main(String[] args) {
        String value ="1.11";
        Pattern pattern= compile("(^[1-9]([0-9]+)?(\\.[0-9]{1,2})?$)|(^(0){1})(\\.[0-9]{1,2})?$");
        Matcher match=pattern.matcher(value);
        System.out.println(match.matches());
    }

}
