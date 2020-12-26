package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.dict.ECPDict;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;


/**
* Created by syxlw on 2018/4/28.
*/
@Data
public class WhefssInterestSndUpdateReqBO {
    /**
    * 流水号
    */
    @ECPDict(required = true,length = 75, message = "sn值不合法")
    private String sn;

    /**
    * 监管账户账号
    */
    @ECPDict(required = true,length = 60, message = "accountNum值不合法")
    private String accountNum;

    /**
    * 银行代码
    */
    @ECPDict(required = true,length = 150, message = "bankCode值不合法")
    private String bankCode;

    /**
    * 利息、年费
    */
    @ECPDict(required = true, message = "interest值不合法")
    private YGAmt interest;

    /**
    * 操作时间
    */
    @ECPDict(required = true,length = 30, message = "time值不合法")
    private String time;

    /**
    * 进账类别:1-进账;2-出账
    */
    @ECPDict(required = true,length = 2, message = "type值不合法")
    private String type;

    /**
    * 发送状态:00-未发送;01-已发送对账状态待更新;02 对账成功;03-对账失败房管已接收明细;04-对账失败房管未接收明细;05-异常数据（不提交给房管）
    */
    @ECPDict(required = true,length = 3, message = "sendStatus值不合法")
    private String sendStatus;

    /**
    * 批次号
    */
    @ECPDict(required = true,length = 32, message = "batchNum值不合法")
    private String batchNum;

    /**
    * 柜员号
    */
    @ECPDict(required = true,length = 32, message = "userId值不合法")
    private String userId;

    /**
    * 柜员名
    */
    @ECPDict(required = true,length = 32, message = "userName值不合法")
    private String userName;

    /**
    * 系统流水号
    */
    @ECPDict(required = true,length = 60, message = "sysTranid值不合法")
    private String sysTranid;

    /**
    * 系统时间yyyymmddhhmiss
    */
    @ECPDict(required = true,length = 21, message = "sysTime值不合法")
    private String sysTime;

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
