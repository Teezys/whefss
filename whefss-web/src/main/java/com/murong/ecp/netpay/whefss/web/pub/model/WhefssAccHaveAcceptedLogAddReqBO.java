package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.dict.ECPDict;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;


/**
* Created by syxlw on 2018/4/28.
*/
@Data
public class WhefssAccHaveAcceptedLogAddReqBO {
    /**
     * 缴款通知书编号
     */
    @ECPDict(required = true,length = 60, message = "noticeNum值不合法")
    private String noticeNum;

    /**
     * 流水号
     */
    @ECPDict(required = true,length = 75, message = "sn值不合法")
    private String sn;

    /**
     * 监管账号
     */
    @ECPDict(required = true,length = 60, message = "accountNum值不合法")
    private String accountNum;

    /**
     * 下载时间:yyyymmddhhmiss
     */
    @ECPDict(required = true,length = 21, message = "loadTime值不合法")
    private String loadTime;

    /**
     * 发生交易金额：两位小数
     */
    @ECPDict(required = true, message = "amount值不合法")
    private YGAmt amount;

    /**
     * 时间
     */
    @ECPDict(required = true,length = 30, message = "time值不合法")
    private String time;

    /**
     * 类型描述:正常进账;专用POS刷卡;不明进账;利息;年费、手续费;司法扣划;拨付成功;拨付失败;冲正成功;退款成功;退款失败;冻结;解冻;按揭贷款;
     */
    @ECPDict(required = true,length = 20, message = "typeDescriptions值不合法")
    private String typeDescriptions;

    /**
     * 进账类型：1-正常进账;2-专用POS刷卡;3-不明进账;4-利息;5-年费、手续费;6-司法扣划;7-拨付成功;8-拨付失败;9-冲正成功;10-退款成功;11-退款失败;12-冻结;13-解冻;14-按揭贷款;
     */
    @ECPDict(required = true,length = 75, message = "type值不合法")
    private String type;

    /**
     * 状态：正常；正常_划分中；正常_已划分完成；冲正_申请中；正常_冲正审核未通过；冲正_审核通过；冲正_银行操作成功；作废；
     */
    @ECPDict(required = true,length = 75, message = "state值不合法")
    private String state;

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
