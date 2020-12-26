package com.murong.ecp.netpay.whefss.web.common.utils;

public class WhefssConstant {

    /**
     * 通讯方式
     */
    public static final String HTTP_METHOD_POST = "post";
    public static final String HTTP_METHOD_GET = "get";

    public static final String HTTP_STATUS = "200";
    /*
     *查询开户数据接口
     * */
    public static final String ACCOUNTS_OPEN_URL = "/api.v3/accounts/param/open/";

    /*
     *返回开户数据接口
     * */
    public static final String ACCOUNTS_OPEN_NOTIFY_URL = "/api.v3/accounts/param/open/";

    /*
     *查询销户数据接口
     * */
    public static final String ACCOUNTS_REMOVE_URL = "/api.v3/accounts/param/remove/";

    /*
     *查询缴款通知书数据接口
     * */
    public static final String CONTRACTS_INCOME_URL = "/api.v3/contracts/param/income/";

    /*
     *查询冲正数据接口
     *
     * */
    public static final String FUNDS_CORRECT_URL = "/api.v3/funds/param/correct/";
    /*
     *查询拨付数据接口
     * */
    public static final String FUNDS_APPROPRIATE_URL = "/api.v3/funds/param/appropriate/";

    /*
     *查询退款数据接口
     * */
    public static final String FUNDS_REFUND_URL = "/api.v3/funds/param/refund/";

    /*
     *查询指定日期范围进账数据接口
     * */
    public static final String FUNDS_DETAIL_URL = "/api.v3/funds/param/detail/";
    /*
     *余额及交易明细合并提交接口
     * */
    public static final String FUNDS_UPLOADINFO_URL = "/api.v3/funds/param/uploadInfo/";

    /*
     *按揭贷款信息采集接口
     * */
    public static final String CONTRACTS_LOANINFO_URL = "/api.v3/Contracts/param/LoanInfo/";

    /*
     *按揭贷款信息采集接口
     * */
    public static final String FUNDS_BALANCE_URL = "/api.v3/Funds/param/Balance/";

    /*
     * 监管状态 0-未监管 1-监管中
     * */
    public static final String ACCT_STS_0 = "0";

    /*
     * 监管状态 0-未监管 1-监管中
     * */
    public static final String ACCT_STS_1 = "1";
    public static final String FSS_FLG = "whef";

    public static final String BANK_CODE = "BEA";
    public static final String BANK_NAME = "东亚银行";

    /*
     * 发送状态 00-未发送;01-已发送对账状态待更新;02-对账成功;03-对账失败房管已接收明细;04-对账失败房管未接收明细;05-异常数据（不提交给房管）
     * */
    public static final String SEND_STATUS_00 = "00";

    public static final String SEND_STATUS_01 = "01";

    public static final String SEND_STATUS_02 = "02";

    public static final String SEND_STATUS_03 = "03";

    public static final String SEND_STATUS_04 = "04";

    public static final String SEND_STATUS_05 = "05";

    /*
     * 交易状态 S-成功 F-失败
     * */
    public static final String TXN_STS_S = "S";

    public static final String TXN_STS_F = "F";

    /*
     * 按揭贷款附言规则HTBH?([0-9]{13})?
     * */
    public static final String PATTERN_NARRATIVE = "HTBH";

    /*
     * 年费/利息  1-利息（进账）  2-年费（出账）
     * */
    public static final String INTEREST_FUNDS_TYPE_1 = "1";
    public static final String INTEREST_FUNDS_TYPE_2 = "2";

    /*
     * 借贷标志  C-贷  D-借
     * */
    public static final String DC_FLG_C = "C";
    public static final String DC_FLG_D = "D";

    /*
     * 对账总控状态  00-初始状态 01-已生成对账账户记录 02-对账完成
     * */
    public static final String CHK_TOTAL_STS_00 = "00";
    public static final String CHK_TOTAL_STS_01 = "01";
    public static final String CHK_TOTAL_STS_02 = "02";

    /*
     * 对账账户状态  00未对账；01对账失败；02对账成功；11-明细提交房管返回失败；12-明细提交房管返回成功；
     * */
    public static final String CHK_DETAIL_STS_00 = "00";
    public static final String CHK_DETAIL_STS_01 = "01";
    public static final String CHK_DETAIL_STS_02 = "02";
    public static final String CHK_DETAIL_STS_11 = "11";
    public static final String CHK_DETAIL_STS_12 = "12";

    /**
     * 序列 对账序列
     */
    public static final String SEQ_WHEFSS_CHK_TOTAL = "SEQ_WHEFSS_CHK_TOTAL";

    /**
     * 序列 对账序列
     */
    public static final String SEQ_WHEFSS_CHK_DETAIL_JNL = "SEQ_WHEFSS_CHK_DETAIL_JNL";


    /*
     * 交易类型 1-正常进账;2-专用pos刷卡;3-不明进账;4-利息;5-年费、手续费;6-司法扣划;
     * 7-拨付成功;8-拨付失败;9-冲正成功;10-退款成功;11-退款失败;12-冻结;13-解冻;14-按揭贷款
     * */
    public static final String DATA_TYPE_1 = "1";
    public static final String DATA_TYPE_2 = "2";
    public static final String DATA_TYPE_3 = "3";
    public static final String DATA_TYPE_4 = "4";
    public static final String DATA_TYPE_5 = "5";
    public static final String DATA_TYPE_6 = "6";
    public static final String DATA_TYPE_7 = "7";
    public static final String DATA_TYPE_8 = "8";
    public static final String DATA_TYPE_9 = "9";
    public static final String DATA_TYPE_10 = "10";
    public static final String DATA_TYPE_11 = "11";
    public static final String DATA_TYPE_12 = "12";
    public static final String DATA_TYPE_13 = "13";
    public static final String DATA_TYPE_14 = "14";

    /*
     * 冻结解冻 busdype  1-冻结 2-解冻 6-司法扣划
     * 利息年费   1-利息 2-年费
     * */
    public static final String BUS_TYPE_1 = "1";
    public static final String BUS_TYPE_2 = "2";
    public static final String BUS_TYPE_6 = "6";


    /*
    * 按揭贷款附言格式 HTBH([0-1][0-9]{10}[0][0-9])
    * */
    public static final String AJDK_CHK = "HTBH([0-1][0-9]{10}[0][0-9])";
    public static final String AJDK_CHK_HTBH = "HTBH";

    /*
    * UnknownType 不明入账类型(1贷款，2其 他)
    * */
    public static final String CHK_UNKNOWEN_TYPE_NAME = "放贷";
    public static final String CHK_UNKNOWEN_TYPE_1 = "1";
    public static final String CHK_UNKNOWEN_TYPE_2 = "2";

    /*
    * POS文件名称
    * */
    public static final String FILE_DT = "filedt";

    public static final String FLAG_C = "C";
    public static final String FLAG_R = "R";
}
