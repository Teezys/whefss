/** 
 * TransJnlDO.java
 * Copyright(C) 北京沐融信息科技有限公司
 * All rights reserved
 * ------------------------------------------------
 * 2020-05-10 Created
 */ 
package com.murong.ecp.netpay.whefss.web.pub.dal.entity;

import com.murong.ecp.bp.common.util.BaseDO;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import net.sf.json.JSONObject;

/**
 * 房管交易流水公共表
 */ 
public class TTransJnlDO extends BaseDO {
   /** 
    * 交易后余额
    */ 
    private YGAmt actualBalAmt;
   /** 
    * 交易后余额借贷标志
    */ 
    private String actualBalAmtCrDrInt;
   /** 
    * 可用余额=账户余额-冻结金额，实时计算出来的
    */ 
    private YGAmt avaAmt;
   /** 
    * 余额类型
    */ 
    private String balType;
   /** 
    * 银行流水号
    */ 
    private String bnkNumber;
   /** 
    * 柜员号
    */ 
    private String btOfficerId;
   /** 
    * 币种
    */ 
    private String ccy;
   /** 
    * 借贷标志：C-贷记，D-借记
    */ 
    private String dcFlg;
   /** 
    * 记账时间
    */ 
    private String execTime;
   /** 
    * 冻结金额，同步核心流水之前查询账户的冻结金额
    */ 
    private YGAmt frzAmt;
   /** 
    * blef-博罗县房管,xyef-仙游房管，ptef-莆田房管
    */ 
    private String fssFlg;
   /** 
    * 描述信息
    */ 
    private String narrative;
   /** 
    * 交易操作员
    */ 
    private String officerId;
   /** 
    * 对方账户号码
    */ 
    private String oopAcctNo;
   /** 
    * 对方开户行
    */ 
    private String oopOpnAcctBnkNm;
   /** 
    * 他行号
    */ 
    private String othBankCode;
   /** 
    * 数据库主键
    */ 
    private String pkId;
   /** 
    * 上次余额
    */ 
    private YGAmt previousBalAmt;
   /** 
    * 参考行
    */ 
    private String referenceBank;
   /** 
    * 参考分行
    */ 
    private String referenceBranch;
   /** 
    * 冲正标识，R：冲正
    */ 
    private String reverse;
    /**
     *不动户标志
     */
    private String fixFlag;
    /**
     * 账户数量
     */
    private String histNum;
    /**
     * 生效日期
     */
    private String effectDate;
    /**
     * 冲正交易类型
     */
    private String reversalTranType;
    /**
     * 等值金额
     */
    private YGAmt equivAmt;
    /**
     * 参考信息
     */
    private String reference;
    /**
     * 已做日期
     */
    private String postDate;
    /**
     * 浮动天数
     */
    private String floatDays;
    /**
     * 分行
     */
    private String  branch;
    /**
     * 源类型
     */
    private String sourceType;
    /**
     * 跟踪号
     */
    private String traceId;
    /**
     * 主序列号
     */
    private String primarySeqNo;
    /**
     * 对方账户类型
     */
    private String tfrAcctType;
    /**
     * 账户前缀标识
     */
    private String acctPreFlag;
    /**
     * 账户标识
     */
    private String acctFlag;
    /**
     * 套汇
     */
    private String crossRate;
    /**
     * 上次余额借贷标识
     */
    private String crDrPrevious;
    /**
     * 状态描述
     */
    private String status;
    /**
     * 状态
     */
    private String field;
   /** 
    * 备注
    */ 
    private String rmk = "";
   /** 
    * 备用字段1
    */ 
    private String rsv1 = "";
   /** 
    * 备用字段2
    */ 
    private String rsv2 = "";
   /** 
    * 备用字段3
    */ 
    private String rsv3 = "";
   /** 
    * 备用字段4
    */ 
    private String rsv4 = "";
   /** 
    * 备用字段5
    */ 
    private String rsv5 = "";
   /** 
    * 场次号
    */ 
    private String sessionId;
   /** 
    * 附记备注
    */ 
    private String summary;
   /** 
    * 监管账号
    */ 
    private String svsAcNo;
   /** 
    * 对方账户户名
    */ 
    private String tfrAcctName;
   /** 
    * 交易金额
    */ 
    private YGAmt tranAmt;
   /** 
    * 主机交易日期
    */ 
    private String tranDate;
   /** 
    * 交易描述
    */ 
    private String tranDesc;
   /** 
    * 交易类型
    */ 
    private String tranType = "";

    /**
     * 选择日期
     */
    private String beginDt;
    /**
     * 结束日期
     */
    private String endDt;


    public YGAmt getActualBalAmt() {
       return actualBalAmt;
    }
  
    public void setActualBalAmt(YGAmt actualBalAmt) {
       this.actualBalAmt = actualBalAmt;
    }
  
    public String getActualBalAmtCrDrInt() {
       return actualBalAmtCrDrInt;
    }
  
    public void setActualBalAmtCrDrInt(String actualBalAmtCrDrInt) {
       this.actualBalAmtCrDrInt = actualBalAmtCrDrInt==null?"":actualBalAmtCrDrInt.trim();
    }
  
    public YGAmt getAvaAmt() {
       return avaAmt;
    }
  
    public void setAvaAmt(YGAmt avaAmt) {
       this.avaAmt = avaAmt;
    }
  
    public String getBalType() {
       return balType;
    }
  
    public void setBalType(String balType) {
       this.balType = balType==null?"":balType.trim();
    }
  
    public String getBnkNumber() {
       return bnkNumber;
    }
  
    public void setBnkNumber(String bnkNumber) {
       this.bnkNumber = bnkNumber==null?"":bnkNumber.trim();
    }
  
    public String getBtOfficerId() {
       return btOfficerId;
    }
  
    public void setBtOfficerId(String btOfficerId) {
       this.btOfficerId = btOfficerId==null?"":btOfficerId.trim();
    }
  
    public String getCcy() {
       return ccy;
    }
  
    public void setCcy(String ccy) {
       this.ccy = ccy==null?"":ccy.trim();
    }
  
    public String getDcFlg() {
       return dcFlg;
    }
  
    public void setDcFlg(String dcFlg) {
       this.dcFlg = dcFlg==null?"":dcFlg.trim();
    }
  
    public String getExecTime() {
       return execTime;
    }
  
    public void setExecTime(String execTime) {
       this.execTime = execTime==null?"":execTime.trim();
    }
  
    public YGAmt getFrzAmt() {
       return frzAmt;
    }
  
    public void setFrzAmt(YGAmt frzAmt) {
       this.frzAmt = frzAmt;
    }
  
    public String getFssFlg() {
       return fssFlg;
    }
  
    public void setFssFlg(String fssFlg) {
       this.fssFlg = fssFlg==null?"":fssFlg.trim();
    }
  
    public String getNarrative() {
       return narrative;
    }
  
    public void setNarrative(String narrative) {
       this.narrative = narrative==null?"":narrative.trim();
    }
  
    public String getOfficerId() {
       return officerId;
    }
  
    public void setOfficerId(String officerId) {
       this.officerId = officerId==null?"":officerId.trim();
    }
  
    public String getOopAcctNo() {
       return oopAcctNo;
    }
  
    public void setOopAcctNo(String oopAcctNo) {
       this.oopAcctNo = oopAcctNo==null?"":oopAcctNo.trim();
    }
  
    public String getOopOpnAcctBnkNm() {
       return oopOpnAcctBnkNm;
    }
  
    public void setOopOpnAcctBnkNm(String oopOpnAcctBnkNm) {
       this.oopOpnAcctBnkNm = oopOpnAcctBnkNm==null?"":oopOpnAcctBnkNm.trim();
    }
  
    public String getOthBankCode() {
       return othBankCode;
    }
  
    public void setOthBankCode(String othBankCode) {
       this.othBankCode = othBankCode==null?"":othBankCode.trim();
    }
  
    public String getPkId() {
       return pkId;
    }
  
    public void setPkId(String pkId) {
       this.pkId = pkId==null?"":pkId.trim();
    }
  
    public YGAmt getPreviousBalAmt() {
       return previousBalAmt;
    }
  
    public void setPreviousBalAmt(YGAmt previousBalAmt) {
       this.previousBalAmt = previousBalAmt;
    }
  
    public String getReferenceBank() {
       return referenceBank;
    }
  
    public void setReferenceBank(String referenceBank) {
       this.referenceBank = referenceBank==null?"":referenceBank.trim();
    }
  
    public String getReferenceBranch() {
       return referenceBranch;
    }
  
    public void setReferenceBranch(String referenceBranch) {
       this.referenceBranch = referenceBranch==null?"":referenceBranch.trim();
    }
  
    public String getReverse() {
       return reverse;
    }
  
    public void setReverse(String reverse) {
       this.reverse = reverse==null?"":reverse.trim();
    }
  
    public String getRmk() {
       return rmk;
    }
  
    public void setRmk(String rmk) {
       this.rmk = rmk==null?"":rmk.trim();
    }
  
    public String getRsv1() {
       return rsv1;
    }
  
    public void setRsv1(String rsv1) {
       this.rsv1 = rsv1==null?"":rsv1.trim();
    }
  
    public String getRsv2() {
       return rsv2;
    }
  
    public void setRsv2(String rsv2) {
       this.rsv2 = rsv2==null?"":rsv2.trim();
    }
  
    public String getRsv3() {
       return rsv3;
    }
  
    public void setRsv3(String rsv3) {
       this.rsv3 = rsv3==null?"":rsv3.trim();
    }
  
    public String getRsv4() {
       return rsv4;
    }
  
    public void setRsv4(String rsv4) {
       this.rsv4 = rsv4==null?"":rsv4.trim();
    }
  
    public String getRsv5() {
       return rsv5;
    }
  
    public void setRsv5(String rsv5) {
       this.rsv5 = rsv5==null?"":rsv5.trim();
    }
  
    public String getSessionId() {
       return sessionId;
    }
  
    public void setSessionId(String sessionId) {
       this.sessionId = sessionId==null?"":sessionId.trim();
    }
  
    public String getSummary() {
       return summary;
    }
  
    public void setSummary(String summary) {
       this.summary = summary==null?"":summary.trim();
    }
  
    public String getSvsAcNo() {
       return svsAcNo;
    }
  
    public void setSvsAcNo(String svsAcNo) {
       this.svsAcNo = svsAcNo==null?"":svsAcNo.trim();
    }
  
    public String getTfrAcctName() {
       return tfrAcctName;
    }
  
    public void setTfrAcctName(String tfrAcctName) {
       this.tfrAcctName = tfrAcctName==null?"":tfrAcctName.trim();
    }
  
    public YGAmt getTranAmt() {
       return tranAmt;
    }
  
    public void setTranAmt(YGAmt tranAmt) {
       this.tranAmt = tranAmt;
    }
  
    public String getTranDate() {
       return tranDate;
    }
  
    public void setTranDate(String tranDate) {
       this.tranDate = tranDate==null?"":tranDate.trim();
    }
  
    public String getTranDesc() {
       return tranDesc;
    }
  
    public void setTranDesc(String tranDesc) {
       this.tranDesc = tranDesc==null?"":tranDesc.trim();
    }
  
    public String getTranType() {
       return tranType;
    }
  
    public void setTranType(String tranType) {
       this.tranType = tranType==null?"":tranType.trim();
    }


    public String getBeginDt() {
        return beginDt;
    }

    public void setBeginDt(String beginDt) {
        this.beginDt = beginDt;
    }

    public String getEndDt() {
        return endDt;
    }

    public void setEndDt(String endDt) {
        this.endDt = endDt;
    }

    public String getFixFlag() {
        return fixFlag;
    }

    public void setFixFlag(String fixFlag) {
        this.fixFlag = fixFlag;
    }

    public String getHistNum() {
        return histNum;
    }

    public void setHistNum(String histNum) {
        this.histNum = histNum;
    }

    public String getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(String effectDate) {
        this.effectDate = effectDate;
    }

    public String getReversalTranType() {
        return reversalTranType;
    }

    public void setReversalTranType(String reversalTranType) {
        this.reversalTranType = reversalTranType;
    }

    public YGAmt getEquivAmt() {
        return equivAmt;
    }

    public void setEquivAmt(YGAmt equivAmt) {
        this.equivAmt = equivAmt;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getFloatDays() {
        return floatDays;
    }

    public void setFloatDays(String floatDays) {
        this.floatDays = floatDays;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getPrimarySeqNo() {
        return primarySeqNo;
    }

    public void setPrimarySeqNo(String primarySeqNo) {
        this.primarySeqNo = primarySeqNo;
    }

    public String getTfrAcctType() {
        return tfrAcctType;
    }

    public void setTfrAcctType(String tfrAcctType) {
        this.tfrAcctType = tfrAcctType;
    }

    public String getAcctPreFlag() {
        return acctPreFlag;
    }

    public void setAcctPreFlag(String acctPreFlag) {
        this.acctPreFlag = acctPreFlag;
    }

    public String getAcctFlag() {
        return acctFlag;
    }

    public void setAcctFlag(String acctFlag) {
        this.acctFlag = acctFlag;
    }

    public String getCrossRate() {
        return crossRate;
    }

    public void setCrossRate(String crossRate) {
        this.crossRate = crossRate;
    }

    public String getCrDrPrevious() {
        return crDrPrevious;
    }

    public void setCrDrPrevious(String crDrPrevious) {
        this.crDrPrevious = crDrPrevious;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
       return JSONObject.fromObject(this).toString();
    }
    
    @Override
    public void clean() {
        super.clean();
        setActualBalAmt(null);
        setActualBalAmtCrDrInt(null);
        setAvaAmt(null);
        setBalType(null);
        setBnkNumber(null);
        setBtOfficerId(null);
        setCcy(null);
        setDcFlg(null);
        setExecTime(null);
        setFrzAmt(null);
        setFssFlg(null);
        setNarrative(null);
        setOfficerId(null);
        setOopAcctNo(null);
        setOopOpnAcctBnkNm(null);
        setOthBankCode(null);
        setPkId(null);
        setPreviousBalAmt(null);
        setReferenceBank(null);
        setReferenceBranch(null);
        setReverse(null);
        setRmk(null);
        setRsv1(null);
        setRsv2(null);
        setRsv3(null);
        setRsv4(null);
        setRsv5(null);
        setSessionId(null);
        setSummary(null);
        setSvsAcNo(null);
        setTfrAcctName(null);
        setTranAmt(null);
        setTranDate(null);
        setTranDesc(null);
        setTranType(null);
    }
}
