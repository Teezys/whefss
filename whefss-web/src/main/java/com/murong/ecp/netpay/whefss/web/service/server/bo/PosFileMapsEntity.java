package com.murong.ecp.netpay.whefss.web.service.server.bo;

import lombok.Data;

@Data
public class PosFileMapsEntity {
    /*
    * 2-系统跟踪号，3-日期，9-时间，11-终端号，20-付款方账号，23-原交易跟踪号
    *
    * 若原流水号不为空，则为撤销交易，删除原流水，根据监管账户，JYRQ and XTGZH
    * 否增登记 XTGZH,ZDH,JYRQ,JYSJ,JKZH,JKSBH,JGZH,FKQC,JKSJE
    * 最后一个字段长度不为243或者293，报文异常
    * xfg_wh_pos_log
    *
    * */

    /*
    * 系统跟踪号 缴存通知书编号 2
    * */
    private String tranSeq;

    /*
    * 终端号 11
    **/
    private String termId;

    /*
    * 日期 3
    * */
    private String tranDt;

    /*
    * 时间 9
    * */
    private String tranTm;

    /*
    * 付款方账号 20
    * */
    private String paccNo;

    /*
    * 最后一个字段 7+18
    * 缴存通知书编号
    * */
    private String conNo;

    /*
     * 最后一个字段 43+40
     * 监管账号
     * */
    private String accNo;

    /*
     * 最后一个字段 83+1
     * 付款次数
     * */
    private String fkqc;

    /*
    * 最后一个字段 174+14
    * 缴款书金额
    * */
    private String stdAmt;

    /*
    * 原交易跟踪号 23
    * */
    private String  ortrnSeq;

    @Override
    public String toString() {
        return "PosFileMapsEntity{" +
                "tranSeq='" + tranSeq + '\'' +
                ", termId='" + termId + '\'' +
                ", tranDt='" + tranDt + '\'' +
                ", tranTm='" + tranTm + '\'' +
                ", paccNo='" + paccNo + '\'' +
                ", conNo='" + conNo + '\'' +
                ", accNo='" + accNo + '\'' +
                ", fkqc='" + fkqc + '\'' +
                ", stdAmt='" + stdAmt + '\'' +
                ", ortrnSeq='" + ortrnSeq + '\'' +
                '}';
    }
}
