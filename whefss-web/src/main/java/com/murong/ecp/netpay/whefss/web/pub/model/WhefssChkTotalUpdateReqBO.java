package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.dict.ECPDict;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;


/**
* Created by syxlw on 2018/4/28.
*/
@Data
public class WhefssChkTotalUpdateReqBO {
    /**
    * 主键
    */
    @ECPDict(required = true,length = 60, message = "pkId值不合法")
    private String pkId;

    /**
    * 对账日期yyyymmdd
    */
    @ECPDict(required = true,length = 8, message = "chkTime值不合法")
    private String chkTime;

    /**
    * 对账状态:00未完成;01已完成;
    */
    @ECPDict(required = true,length = 2, message = "chkStatus值不合法")
    private String chkStatus;

    /**
    * 对账操作时间yyyymmddhhmiss
    */
    @ECPDict(required = true,length = 14, message = "time值不合法")
    private String time;

    /**
    * 柜员ID
    */
    @ECPDict(required = true,length = 60, message = "userId值不合法")
    private String userId;

    /**
    * 柜员姓名
    */
    @ECPDict(required = true,length = 32, message = "userName值不合法")
    private String userName;

    /**
    * 对账账户总数
    */
    @ECPDict(required = true,length = 32, message = "accTotal值不合法")
    private String accTotal;

    /**
    * 对账成功笔数
    */
    @ECPDict(required = true,length = 32, message = "successNum值不合法")
    private String successNum;

    /**
    * 对账总金额
    */
    @ECPDict(required = true, message = "chkAmt值不合法")
    private YGAmt chkAmt;

    /**
    * 对平笔数
    */
    @ECPDict(required = true,length = 10, message = "balancNum值不合法")
    private String balancNum;

    /**
    * 对平金额
    */
    @ECPDict(required = true, message = "balancAmt值不合法")
    private YGAmt balancAmt;

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
