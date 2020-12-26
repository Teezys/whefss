/**
 * TTransJnlDO.java
 * Copyright(C) 北京沐融信息科技有限公司
 * ------------------------------------------------
 * Created by xu_lw on 2020-09-03 15:20:33.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.entity;

import com.murong.ecp.bp.common.util.BaseDO;
import lombok.Data;

/**
 * 房管交易流水查询结果
 */
@Data
public class TTransJnlResult extends BaseDO {

    private String serialNumber;
    private String banknumber;
    private String bankNm;
    private String svsAcNo;
    private String svsAcNme;
    private String borrowFlag;
    private String execTime;
    private String tranAmt;
    private String actualBalAmt;
    private String tranDesc;
    private String tfrAcctName;
    private String oopAcctNo;
    private String approveNumber;
}
