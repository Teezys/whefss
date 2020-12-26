package com.murong.ecp.netpay.whefss.web.common.utils;

import com.yuangou.ecp.bp.comp.pubatc.IMessageCode;

/**
 * Created by syxlw on 2018/4/29.
 */
public enum FLWMessageCode implements IMessageCode {
	SUCC("SCM00000", "�ɹ�"),
	FAIL("SCM99999", "ʧ��"),
	REC_NFND("FLWBUI001", "��¼û���ҵ�"),
	REC_DUP("FLWBUI002", "��¼�ظ�"),
	ACTIVED("FLWBUI004", "�Ѿ�����"),
	SUSPENDED("FLWBUI005", "�Ѿ�����"),
	;

	private String msgCod;
	private String msgInf;

	FLWMessageCode(String msgCod, String msgInf) {
		this.msgCod = msgCod;
		this.msgInf = msgInf;
	}

	@Override
	public String getMsgCod() {
		return msgCod;
	}

	@Override
	public String getMsgInf() {
		return msgInf;
	}
}
