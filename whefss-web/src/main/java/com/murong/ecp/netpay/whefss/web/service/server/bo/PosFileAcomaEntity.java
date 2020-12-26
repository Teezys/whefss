package com.murong.ecp.netpay.whefss.web.service.server.bo;

import lombok.Data;

@Data
public class PosFileAcomaEntity {
    /*
    * [24]+6-系统跟踪号，[42]+19-主账号，[118]+8-终端号，[392]+8-商户手续费，[62]+12-交易金额
    * posAmt0-[391] czba-[324] cxbz-[326]
    * czbz!=R&&cxbz!=C&&posAmt0!=C 时执行 更新语句
    * xfg_wh_pos_log set POSSXF=posAmt,JYJE=payAmt where JYRQ=wkDate and XTGZH=tranSeq and ZDH=termId and JKZH=paccNo
    *
    * delete where JYRQ=wkDate and JYJE is null
    * */

    /*
    * 系统跟踪号 缴存通知书编号
    * */
    private String tranSeq;


    /*
    * 主账号
    * */
    private String paccNo;

    /*
    * 终端号
    * */
    private String termId;

    /*
    * 交易金额
    * */
    private String payAmt;

    /*
    * 商户手续费
    * */
    private String posAmt;

    /*
    * posAmt0  391
    * */
    private String Amtflag;

    /*
     * czba  324
     * */
    private String czbz;

    /*
     * cxbz  326
     * */
    private String cxbz;

    @Override
    public String toString() {
        return "PosFileAcomaEntity{" +
                "tranSeq='" + tranSeq + '\'' +
                ", paccNo='" + paccNo + '\'' +
                ", termId='" + termId + '\'' +
                ", payAmt='" + payAmt + '\'' +
                ", posAmt='" + posAmt + '\'' +
                ", Amtflag='" + Amtflag + '\'' +
                ", czba='" + czbz + '\'' +
                ", cxbz='" + cxbz + '\'' +
                '}';
    }
}
