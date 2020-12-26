package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.dict.ECPDict;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;


/**
* Created by syxlw on 2018/4/28.
*/
@Data
public class WhefssChkDetailUpdateReqBO {
    /**
    * pkId
    */
    @ECPDict(required = true,length = 60, message = "pkId值不合法")
    private String pkId;

    /**
    * fkId
    */
    @ECPDict(required = true,length = 60, message = "fkId值不合法")
    private String fkId;

    /**
    * 对账日期
    */
    @ECPDict(required = true,length = 8, message = "chkDay值不合法")
    private String chkDay;

    /**
    * 监管账户账号
    */
    @ECPDict(required = true,length = 60, message = "accountNum值不合法")
    private String accountNum;

    /**
    * 当日对账总额（两位小数）
    */
    @ECPDict(required = true, message = "chkAmt值不合法")
    private YGAmt chkAmt;

    /**
    * 对账的条数
    */
    @ECPDict(required = true,length = 32, message = "chkNum值不合法")
    private String chkNum;

    /**
    * 对账人员
    */
    @ECPDict(required = true,length = 60, message = "userId值不合法")
    private String userId;

    /**
    * 对账时间YYYYMMDDHHMISS
    */
    @ECPDict(required = true,length = 14, message = "chkTime值不合法")
    private String chkTime;

    /**
    * 对账状态：00未对账；01对账失败；02对账成功；11-明细提交房管返回失败；12-明细提交房管返回成功；
    */
    @ECPDict(required = true,length = 2, message = "chkStatus值不合法")
    private String chkStatus;

    /**
    * 错误信息
    */
    @ECPDict(required = true,length = 256, message = "msg值不合法")
    private String msg;

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

    /**
    * 最近银行余额
    */
    @ECPDict(required = true, message = "balanceBank值不合法")
    private YGAmt balanceBank;

    /**
    * 最近系统余额
    */
    @ECPDict(required = true, message = "balanceSystem值不合法")
    private YGAmt balanceSystem;

    /**
    * 最近对账日期
    */
    @ECPDict(required = true,length = 19, message = "balanceTime值不合法")
    private String balanceTime;

}
