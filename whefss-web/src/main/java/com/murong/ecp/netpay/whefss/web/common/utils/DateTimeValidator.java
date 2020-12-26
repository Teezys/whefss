package com.murong.ecp.netpay.whefss.web.common.utils;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;
import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeValidator extends AbstractAnnotationCheck<DateTime> {
    private static final long serialVersionUID = 1L;
    private String format;

    @Override
    public void configure(final DateTime constraintAnnotation) {
        super.configure(constraintAnnotation);
        setMessage(constraintAnnotation.message());
        setFormat(constraintAnnotation.format());
    }

    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate, OValContext context, Validator validator) throws OValException {
        if (valueToValidate == null) {
            return true;
        }
        String value = valueToValidate.toString();

        if (value.length() != format.length()) {
            return false;
        }

        Date date;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            date = DateUtils.parseDate(value, format);
            String dateResult = simpleDateFormat.format(date);
            if (!value.equals(dateResult)) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void setMessage(final String message) {
        super.setMessage(message);
    }

    public void setFormat(final String format) {
        this.format = format;
    }
}
