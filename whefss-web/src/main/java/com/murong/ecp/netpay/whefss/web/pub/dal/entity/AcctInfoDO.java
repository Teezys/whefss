/**
 * AcctInfoDO.java
 * Copyright(C) 北京沐融信息科技有限公司
 * ------------------------------------------------
 * Created by xu_lw on 2020-09-06 16:49:51.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.entity;

import com.murong.ecp.bp.common.util.BaseDO;
import lombok.Data;
/**
 * 房管监管账户公共签约表
 */
@Data
public class AcctInfoDO extends BaseDO {

    /**
    * 监管账户
    */
    private String svsAcNo;

    /**
    * 监管账户名称
    */
    private String svsAcNme;

    /**
    * blef-博罗县房管，xyef-仙游房管，ptef-莆田房管,zhef-珠海房管 whef-武汉房管
    */
    private String fssFlg;

    /**
    * 设立监管日期
    */
    private String opnDte;

    /**
    * 取消监管日期
    */
    private String calDte;

    /**
    * 账户状态:0-不监管,1-监管中
    */
    private String accSts;

    /**
    * 操作网点
    */
    private String oprBr;

    /**
    * 操作分行
    */
    private String oprBk;

    /**
    * 操作柜员
    */
    private String oprTlr;

    /**
    * 柜员机构号
    */
    private String oprFlg;

    /**
    * 备注
    */
    private String rmk;

    /**
    * 备用字段1
    */
    private String rsv1;

    /**
    * 备用字段2
    */
    private String rsv2;

    /**
    * 备用字段3
    */
    private String rsv3;

    /**
    * 备用字段4
    */
    private String rsv4;

    /**
    * 备用字段5
    */
    private String rsv5;
}
