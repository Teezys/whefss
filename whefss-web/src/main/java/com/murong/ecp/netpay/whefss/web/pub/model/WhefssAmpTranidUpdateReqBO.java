package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.dict.ECPDict;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;


/**
* Created by syxlw on 2018/4/28.
*/
@Data
public class WhefssAmpTranidUpdateReqBO {
    /**
    * 交易流水号
    */
    @ECPDict(required = true,length = 60, message = "ampTranid值不合法")
    private String ampTranid;

    /**
    * 交易时间
    */
    @ECPDict(required = true,length = 21, message = "ampTime值不合法")
    private String ampTime;

    /**
    * 交易码
    */
    @ECPDict(required = true,length = 15, message = "trancode值不合法")
    private String trancode;

    /**
    * 业务类型:1-冻结;2-解冻
    */
    @ECPDict(required = true,length = 30, message = "bustype值不合法")
    private String bustype;

    /**
    * 交易类型
    */
    @ECPDict(required = true,length = 30, message = "transtype值不合法")
    private String transtype;

    /**
    * HTTP请求方式:POST,GET方式 
    */
    @ECPDict(required = true,length = 30, message = "method值不合法")
    private String method;

    /**
    * KEY
    */
    @ECPDict(required = true,length = 30, message = "apikey值不合法")
    private String apikey;

    /**
    * 监管账号
    */
    @ECPDict(required = true,length = 60, message = "accountNum值不合法")
    private String accountNum;

    /**
    * 冻结时间
    */
    @ECPDict(required = true,length = 30, message = "time值不合法")
    private String time;

    /**
    * 冻结金额
    */
    @ECPDict(required = true, message = "amount值不合法")
    private YGAmt amount;

    /**
    * 冻结原因
    */
    @ECPDict(required = true,length = 300, message = "reason值不合法")
    private String reason;

    /**
    * 交易类型:1-为冻结;2-为查封;3-为扣划
    */
    @ECPDict(required = true,length = 3, message = "fgType值不合法")
    private String fgType;

    /**
    * 冻结执行文号
    */
    @ECPDict(required = true,length = 75, message = "noticeNum值不合法")
    private String noticeNum;

    /**
    * 冻结期限(单位:月)
    */
    @ECPDict(required = true,length = 6, message = "freezeMonth值不合法")
    private String freezeMonth;

    /**
    * 流水号
    */
    @ECPDict(required = true,length = 3, message = "sn值不合法")
    private String sn;

    /**
    * 发送状态:00-未发送;01-已发送对账状态待更新;02 对账成功;03-对账失败房管已接收明细;04-对账失败房管未接收明细;05-异常数据（不提交给房管）
    */
    @ECPDict(required = true,length = 3, message = "sendStatus值不合法")
    private String sendStatus;

    /**
    * 柜员名
    */
    @ECPDict(required = true,length = 30, message = "userName值不合法")
    private String userName;

    /**
    * 柜员ID
    */
    @ECPDict(required = true,length = 30, message = "userId值不合法")
    private String userId;

    /**
    * 批次号
    */
    @ECPDict(required = true,length = 32, message = "batchNum值不合法")
    private String batchNum;

    /**
    * 授权ID
    */
    @ECPDict(required = true,length = 32, message = "licenseNum值不合法")
    private String licenseNum;

    /**
    * 授权姓名
    */
    @ECPDict(required = true,length = 32, message = "licenseName值不合法")
    private String licenseName;

    /**
    * 结果信息
    */
    @ECPDict(required = true,length = 384, message = "respMsg值不合法")
    private String respMsg;

    /**
    * 备用1
    */
    @ECPDict(required = true,length = 384, message = "reserve1值不合法")
    private String reserve1;

    /**
    * 备用2
    */
    @ECPDict(required = true,length = 384, message = "reserve2值不合法")
    private String reserve2;

    /**
    * 备用3
    */
    @ECPDict(required = true,length = 384, message = "reserve3值不合法")
    private String reserve3;

}
