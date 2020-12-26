package com.murong.ecp.netpay.whefss.web.pub.dal.entity;

import lombok.Data;


public class BaseHead {
    private boolean result = false;
    private String msgCd;
    private String msgInf;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsgCd() {
        return msgCd;
    }

    public void setMsgCd(String msgCd) {
        this.msgCd = msgCd;
    }

    public String getMsgInf() {
        return msgInf;
    }

    public void setMsgInf(String msgInf) {
        this.msgInf = msgInf;
    }
}
