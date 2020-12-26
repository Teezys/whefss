package com.murong.ecp.netpay.whefss.web.common.utils;

import com.murong.ecp.bp.common.dict.MrEnumValue;

public enum CreditLevel implements MrEnumValue {
    CREDIT_LEVEL_A("A","A"),
    CREDIT_LEVEL_B("B","B"),
    CREDIT_LEVEL_C("C","C"),
    CREDIT_LEVEL_D("D","D");
    ;

    private String value;
    private String desc;

    CreditLevel(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
