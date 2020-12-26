package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.dict.ECPDict;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;


/**
* Created by syxlw on 2018/4/28.
*/
@Data
public class WhefssAccRepealAddReqBO {
    /**
     * 冲正申请书编号
     */
    @ECPDict(required = true,length = 30, message = "noticeNum值不合法")
    private String noticeNum;

    /**
     * 购房合同编号
     */
    @ECPDict(required = true,length = 30, message = "contractNum值不合法")
    private String contractNum;

    /**
     * 银行代码
     */
    @ECPDict(required = false,length = 150, message = "bankCode值不合法")
    private String bankCode;

    /**
     * 监管账户开户总行
     */
    @ECPDict(required = false,length = 75, message = "bankName值不合法")
    private String bankName;

    /**
     * 监管账户开户支行
     */
    @ECPDict(required = true,length = 150, message = "branchName值不合法")
    private String branchName;

    /**
     * 监管账户名称
     */
    @ECPDict(required = true,length = 60, message = "accountName值不合法")
    private String accountName;

    /**
     * 监管账户账号
     */
    @ECPDict(required = true,length = 75, message = "accountNum值不合法")
    private String accountNum;

    /**
     * 冲正金额
     */
    @ECPDict(required = true, message = "amount值不合法")
    private YGAmt amount;

    /**
     * 资金流水号
     */
    @ECPDict(required = true,length = 75, message = "orgSn值不合法")
    private String orgSn;

    /**
     * 进帐时间
     */
    @ECPDict(required = true,length = 30, message = "orgTime值不合法")
    private String orgTime;

    /**
     * 流水号
     */
    @ECPDict(required = true,length = 60, message = "sn值不合法")
    private String sn;

    /**
     * 操作时间
     */
    @ECPDict(required = true,length = 30, message = "time值不合法")
    private String time;


    /**
     * 备用1
     */
    @ECPDict(required = false,length = 384, message = "reserve1值不合法")
    private String reserve1;

    /**
     * 备用2
     */
    @ECPDict(required = false,length = 384, message = "reserve2值不合法")
    private String reserve2;

    /**s
     * 备用3
     */
    @ECPDict(required = false,length = 384, message = "reserve3值不合法")
    private String reserve3;

}
