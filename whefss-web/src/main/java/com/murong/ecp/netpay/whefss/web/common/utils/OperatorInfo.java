package com.murong.ecp.netpay.whefss.web.common.utils;

import com.yuangou.ecp.bp.core.common.message.YGEDB;

/**
 * Created by syxlw on 2018/8/6.
 */
public class OperatorInfo {
	/**
	 * ??????
	 */
	private String orgNo;
	/**
	 * ????
	 */
	private String operNo;
	/**
	 * ????
	 */
	private String operNm;

	/**
	 * ?????
	 */
	private String imgPath;
	/**
	 * ????????Χ
	 */
	private String oprQryDatScp;

	/**
	 * ?
	 */
	private String operProv;
	/**
	 * ????
	 */
	private String operCity;
	/**
	 * ?????
	 */
	private String operProvLvl;

	/**
	 * ????
	 */
	private String operDept;


	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
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

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getOprQryDatScp() {
		return oprQryDatScp;
	}

	public void setOprQryDatScp(String oprQryDatScp) {
		this.oprQryDatScp = oprQryDatScp;
	}

	public String getOperProv() {
		return operProv;
	}

	public void setOperProv(String operProv) {
		this.operProv = operProv;
	}

	public String getOperCity() {
		return operCity;
	}

	public void setOperCity(String operCity) {
		this.operCity = operCity;
	}

	public String getOperProvLvl() {
		return operProvLvl;
	}

	public void setOperProvLvl(String operProvLvl) {
		this.operProvLvl = operProvLvl;
	}

	public String getOperDept() {
		return operDept;
	}

	public void setOperDept(String operDept) {
		this.operDept = operDept;
	}


	public static OperatorInfo create(YGEDB root) {
		OperatorInfo operatorInfo = new OperatorInfo();
		operatorInfo.setOrgNo(root.getData("cur_org_no"));
		operatorInfo.setOperNo(root.getData("cur_oper_no"));
		operatorInfo.setOperNm(root.getData("cur_oper_nm"));
		operatorInfo.setOperCity(root.getData("cur_oper_city"));
		operatorInfo.setOperProvLvl(root.getData("cur_oper_prov_lvl"));
		operatorInfo.setImgPath(root.getData("img_path"));
		operatorInfo.setOprQryDatScp(root.getData("cur_qry_dat_scp"));
		operatorInfo.setOperProv(root.getData("cur_oper_prov"));
		operatorInfo.setOperDept(root.getData("cur_oper_dept"));
		return operatorInfo;
	}
}
