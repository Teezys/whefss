package com.murong.ecp.netpay.whefss.web.common.utils;

import com.yuangou.ecp.bp.comp.pubatc.IMessageCode;

/**
 * Created by syxlw on 2018/4/29.
 */
public enum CommonMessageCode implements IMessageCode {
    /**
     * 成功
     */
    SUCC("SCM00000", "交易成功"),
    FAIL("SCM99999", "交易失败"),
    REC_NFND("SCM00002", "记录没有找到"),
    REC_DUP("SCM00003", "记录重复"),
    NO_DETAIL("SCM00005","无需生成无明细数据"),
    NO_DETAIL_SUCC("SCM00006","生成无明细数据成功"),
    OPER_ERROR("SCM00007","授权员或密码错误"),
    OPER_DUP_ERROR("SCM00008","授权员与操作员不能相同"),
    NDS_ACC_NOT_EXIST("NDS00001","账户不存在"),
    NDS_ACC_NAME_ERROR("NDS00002","账户名称有误"),
    NDS_JNL_SEQ_NO_ERROR("NDS00003","核心流水号不存在"),
    NDS_JNL_AMT_ERROR("NDS00004","交易金额不一致"),
    NDS_JNL_DATE_ERROR("NDS00005","交易日期不一致"),
    NDS_JNL_DC_FLG_ERROR("NDS00006","借贷标志不一致"),
    NDS_JNL_ACC_NO_ERROR("NDS00007","监管账户不一致"),
    JNL_REC_DUP("NDS00008","交易已提交，请勿重复提交"),
    NDS_JNL_NARRATIVE_ERROR("NDS00009","NDS附言（或备注）格式不正确"),

    /**
     *武汉房管接口返回信息
     */
    WH_ACC_NOT_EXIST("WHE00001","监管账户不存在"),
    WH_ACC_NAME_ERROR("WHE00002","监管账户名不存在"),
    WH_ACC_STS_ERROR("WHE00003","监管账户已撤销监管，无法重新监管"),
    WH_ACC_STS_NO_WATCH("WHE00004","该账户未处于监管中"),
    WH_ACC_STS_NO_WATCH_CANCEL("WHE00005","该账户未处于监管中,无需取消监管"),
    TRAN_TYPE_ERROR("WHE00006","交易类型有误"),
    LAST_CHK_ERROR("CHK00001","上一日对账未完成"),
    CHK_TOTAL_INSERT_ERROR("CHK00002","登记对账总控失败"),
    CHK_FINISH_ERROR("CHK00003","对账已完成，无法重复对账"),
    CHK_DETAIL_INSERT_ERROR("CHK00004","登记对账账户失败"),
    CHK_DETAIL_BALANCE_ERROR("CHK00005","更新账户余额失败"),
    CHK_INSERT_DETAIL_JNL_ERROR("CHK00005","登记对账交易流水表失败"),
    CHK_INSERT_AJDK_ERROR("CHK00006","登记按揭贷款信息失败"),
    CHK_FAIL("CHK00007","对账未完成"),
    CHK_UPDATE_BATCH_NUM_ERROR("CHK00008","更新对账批次号失败"),
    CHK_UPDATE_SEND_STS_ERROR("CHK00009","更新交易发送状态失败"),
    FILE_NOT_EXIST("CHK00010","请确认文件是否上传"),

    ;
    private String msgCod;
    private String msgInf;

    CommonMessageCode(String msgCod, String msgInf) {
        this.msgCod = msgCod;
        this.msgInf = msgInf;
    }
    @Override
    public String getMsgCod() {
        return msgCod;
    }

    @Override
    public String getMsgInf() {
        return msgInf;
    }
}
