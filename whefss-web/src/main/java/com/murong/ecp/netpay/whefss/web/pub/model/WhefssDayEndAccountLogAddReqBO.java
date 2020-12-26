package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.dict.ECPDict;
import lombok.Data;


/**
* Created by syxlw on 2018/4/28.
*/
@Data
public class WhefssDayEndAccountLogAddReqBO {
    /**
     * 日期:Yyyymmdd
     */
    @ECPDict(required = true,length = 8, message = "trandate值不合法")
    private String trandate;

    /**
     * 监管账号
     */
    @ECPDict(required = true,length = 60, message = "accountnum值不合法")
    private String accountnum;

    /**
     * 城市名
     */
    @ECPDict(required = true,length = 384, message = "citycode值不合法")
    private String citycode;

    /**
     * 日终状态:01 –本地数据预处理失败；02 –成功；11-明细提交房管返回失败；12-明细提交房管返回成功；21-下载房管明细失败；22-下载房管明细成功；31-本地对账失败；
     */
    @ECPDict(required = true,length = 2, message = "accstatus值不合法")
    private String accstatus;

    /**
     * 日终错误信息
     */
    @ECPDict(required = true,length = 384, message = "msg值不合法")
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



}
