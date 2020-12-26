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
 * 查询NDS账户余额返回结果
 */
@Data
public class AcctRemainderMoney extends BaseHead{
   /**
     * 账号
     */
    private String acctNo;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 账面余额
     */
    private String ledgerBal;

    /**
     * 可用余额
     */
    private String actualBal;

    /**
     * 现钞余额
     */
    private String cashBal;

    /**
     * 现汇余额
     */
    private String ttBal;

    /**
     * 现钞实际余额
     */
    private String caActualBal;

    /**
     * 现汇实际余额
     */
    private String ttActualBal;

}
