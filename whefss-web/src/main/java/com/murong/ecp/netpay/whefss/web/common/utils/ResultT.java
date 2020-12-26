package com.murong.ecp.netpay.whefss.web.common.utils;

import com.alibaba.fastjson.annotation.JSONField;
import com.yuangou.ecp.bp.comp.pubatc.IMessageCode;

/**
 * Created by syxlw on 2018/4/28.
 */

public class ResultT<T> {
    public static final ResultT FAIL = new ResultT(CommonMessageCode.FAIL);
    public static final ResultT SUCC = new ResultT(CommonMessageCode.SUCC);
    public static final String FAIL_CODE = CommonMessageCode.FAIL.getMsgCod();
    public static final String SUCC_CODE = CommonMessageCode.SUCC.getMsgCod();

    private T data;
    private String msgCod;
    private String msgInf;


    public ResultT() {
        setMsgCod(CommonMessageCode.FAIL);
    }

    public ResultT(T data) {
        setMsgCod(CommonMessageCode.SUCC);
        this.data = data;
    }

    public ResultT(String msgCod, String msgInf) {
        this.msgCod = msgCod;
        this.msgInf = msgInf;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsgCod() {
        return msgCod;
    }

    public void setMsgCod(IMessageCode msg) {
        this.msgCod = msg.getMsgCod();
        this.msgInf = msg.getMsgInf();
    }

    public void setMsgCod(String msgCod) {
        this.msgCod = msgCod;
    }

    public String getMsgInf() {
        return msgInf;
    }

    public void setMsgInf(String msgInf) {
        this.msgInf = msgInf;
    }

    @JSONField(deserialize = false)
    public boolean isSucc() {
        return this.msgCod != null && "00000".equals(this.msgCod.substring(3));
    }

    @JSONField(deserialize = false)
    public boolean isFail() {
        return !isSucc();
    }
}
