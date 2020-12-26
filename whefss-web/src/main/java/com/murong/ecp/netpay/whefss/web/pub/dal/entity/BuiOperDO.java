/**
 * BuiOperDO.java
 * Copyright(C) 北京沐融信息科技股份有限公司
 * ------------------------------------------------
 * Created by xu_lw on 2019-08-24 17:26:27.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.entity;

import com.murong.ecp.bp.common.util.BaseDO;
import lombok.ToString;

/**
 * 操作员信息表
 */
@ToString
public class BuiOperDO extends BaseDO {

	/**
	 * id
	 */
	private String operId;

	/**
	 * 编号
	 */
	private String operNo;

	/**
	 * 姓名
	 */
	private String operNm;

	/**
	 * 密码
	 */
	private String operPwd;

	/**
	 * 邮箱
	 */
	private String operEmail;

	/**
	 * 操作员类型(1:系统管理员,2:数字证书+密码,3:USBKey+密码)
	 */
	private String operTyp;

	/**
	 * 授权级别
	 */
	private String authLvl;

	/**
	 * 操作员类型
	 */
	private String mblNo;

	/**
	 * 操作员类型
	 */
	private String telNo;

	/**
	 * 密码失效时间
	 */
	private String pwdExpPeriod;

	/**
	 * 操作员类型
	 */
	private String pwdUpdDt;

	/**
	 * 生效时间
	 */
	private String valDt;

	/**
	 * 失效时间
	 */
	private String expDt;

	/**
	 * 状态(0:正常,1:无效,2:过期,3:密码超过最大次数)
	 */
	private String sts;

	/**
	 * 登录时间
	 */
	private String loginTm;

	/**
	 * 最近一次登录时间
	 */
	private String lastLogTm;

	/**
	 * 登录次数
	 */
	private Long loginTms;

	/**
	 * 登录方式(1:密码,2:数字证书+密码,3:USBKey+密码)
	 */
	private String logMod;

	/**
	 * usbkey id
	 */
	private String usbKeyId;

	/**
	 * 失败次数
	 */
	private Long pswFailCnt;

	/**
	 * 图像地址
	 */
	private String imgPath;

	/**
	 * 密码salt
	 */
	private String pwdSalt;

	/**
	 * 机构id
	 */
	private String orgId;

	/**
	 * 机构名称
	 */
	private String orgNm;

	/**
	 * reqId
	 */
	private String reqId;

	/**
	 * 本机构及下属机构org_id列表
	 */
	private String orgIds;

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public String getOperNo() {
		return operNo;
	}

	public void setOperNo(String operNo) {
		this.operNo = operNo;
	}

	public String getOperNm() {
		return operNm;
	}

	public void setOperNm(String operNm) {
		this.operNm = operNm;
	}

	public String getOperPwd() {
		return operPwd;
	}

	public void setOperPwd(String operPwd) {
		this.operPwd = operPwd;
	}

	public String getOperEmail() {
		return operEmail;
	}

	public void setOperEmail(String operEmail) {
		this.operEmail = operEmail;
	}

	public String getOperTyp() {
		return operTyp;
	}

	public void setOperTyp(String operTyp) {
		this.operTyp = operTyp;
	}

	public String getAuthLvl() {
		return authLvl;
	}

	public void setAuthLvl(String authLvl) {
		this.authLvl = authLvl;
	}

	public String getMblNo() {
		return mblNo;
	}

	public void setMblNo(String mblNo) {
		this.mblNo = mblNo;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getPwdExpPeriod() {
		return pwdExpPeriod;
	}

	public void setPwdExpPeriod(String pwdExpPeriod) {
		this.pwdExpPeriod = pwdExpPeriod;
	}

	public String getPwdUpdDt() {
		return pwdUpdDt;
	}

	public void setPwdUpdDt(String pwdUpdDt) {
		this.pwdUpdDt = pwdUpdDt;
	}

	public String getValDt() {
		return valDt;
	}

	public void setValDt(String valDt) {
		this.valDt = valDt;
	}

	public String getExpDt() {
		return expDt;
	}

	public void setExpDt(String expDt) {
		this.expDt = expDt;
	}

	public String getSts() {
		return sts;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}

	public String getLoginTm() {
		return loginTm;
	}

	public void setLoginTm(String loginTm) {
		this.loginTm = loginTm;
	}

	public String getLastLogTm() {
		return lastLogTm;
	}

	public void setLastLogTm(String lastLogTm) {
		this.lastLogTm = lastLogTm;
	}

	public Long getLoginTms() {
		return loginTms;
	}

	public void setLoginTms(Long loginTms) {
		this.loginTms = loginTms;
	}

	public String getLogMod() {
		return logMod;
	}

	public void setLogMod(String logMod) {
		this.logMod = logMod;
	}

	public String getUsbKeyId() {
		return usbKeyId;
	}

	public void setUsbKeyId(String usbKeyId) {
		this.usbKeyId = usbKeyId;
	}

	public Long getPswFailCnt() {
		return pswFailCnt;
	}

	public void setPswFailCnt(Long pswFailCnt) {
		this.pswFailCnt = pswFailCnt;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getPwdSalt() {
		return pwdSalt;
	}

	public void setPwdSalt(String pwdSalt) {
		this.pwdSalt = pwdSalt;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgNm() {
		return orgNm;
	}

	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	@Override
	public void clean() {
		super.clean();
		setOperId(null);
		setOperNo(null);
		setOperNm(null);
		setOperPwd(null);
		setOperEmail(null);
		setOperTyp(null);
		setAuthLvl(null);
		setMblNo(null);
		setTelNo(null);
		setPwdExpPeriod(null);
		setPwdUpdDt(null);
		setValDt(null);
		setExpDt(null);
		setSts(null);
		setLoginTm(null);
		setLastLogTm(null);
		setLoginTms(null);
		setLogMod(null);
		setUsbKeyId(null);
		setPswFailCnt(null);
		setImgPath(null);
		setPwdSalt(null);
		setOrgId(null);
		setReqId(null);
		setOrgIds(null);
	}
}
