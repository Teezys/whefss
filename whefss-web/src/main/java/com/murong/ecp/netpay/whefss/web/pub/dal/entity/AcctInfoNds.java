/**
 * AcctInfoDO.java
 * Copyright(C) 北京沐融信息科技有限公司
 * All rights reserved
 * ------------------------------------------------
 * 2020-04-28 Created
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.entity;

import lombok.Data;

/**
 * 查询NDS账户信息表
 */
@Data
public class AcctInfoNds extends BaseHead {
    /**
     * 不动户标志
     */
    private String flxFlag;

    /**
     * 账户前缀标识
     */
    private String acctPreFlag;

    /**
     * 账户标志
     */
    private String acctFtaFlag;
    /**
     * 账户英文户名
     */
    private String acctEnam;
    /**
     * 账号中文名
     */
    private String acctCnam;
    /**
     * 账户地址
     */
    private String acctAddr;
    /**
     * 存款类型
     */
    private String depositType;
    /**
     * 分行号
     */
    private String branch;
    /**
     * 支行编号
     */
    private String subBranch;
    /**
     * 币种
     */
    private String ccy;
    /**
     * 账户合法标志
     */
    private String acctFlag;
    /**
     * 账户状态
     */
    private String acctStatus;
    /**
     * 账号属性1
     */
    private String acctAttr1;
    /**
     * 账号属性2
     */
    private String acctAttr2;
    /**
     * 账号属性3
     */
    private String acctAttr3;
    /**
     * 客户号
     */
    private String customNo;
    /**
     * 账户产品类型
     */
    private String acctProdtype;
    /**
     * 客户类型
     */
    private String acctCliType;
    /**
     * 账户属性
     */
    private String acctAtn;
    /**
     * 客户种类
     */
    private String acctCiType;
    /**
     * 区内区外标志
     */
    private String acctRegf;
    /**
     * 联名标识
     */
    private String acctJoinFlag;
    /**
     * 存折号码
     */
    private String pbNo;
    /**
     * 前缀
     */
    private String prefix;
    /**
     * 存折类型
     */
    private String docType;
    /**
     * 挂失状态
     */
    private String lostStatus;
    /**
     * 利率类型
     */
    private String crIntType;
    /**
     * 钞汇标识
     */
    private String balType;
}
