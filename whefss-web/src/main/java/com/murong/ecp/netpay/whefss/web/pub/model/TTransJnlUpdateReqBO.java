package com.murong.ecp.netpay.whefss.web.pub.model;

import com.murong.ecp.bp.common.dict.ECPDict;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import lombok.Data;


/**
* Created by syxlw on 2018/4/28.
*/
@Data
public class TTransJnlUpdateReqBO {
    /**
    * 数据库主键
    */
    @ECPDict(required = true,length = 32, message = "pkId值不合法")
    private String pkId;

    /**
    * 监管账号
    */
    @ECPDict(required = true,length = 64, message = "svsAcNo值不合法")
    private String svsAcNo;

    /**
    * blef-博罗县房管,xyef-仙游房管，ptef-莆田房管
    */
    @ECPDict(required = true,length = 4, message = "fssFlg值不合法")
    private String fssFlg;

    /**
    * 借贷标志：C-贷记，D-借记
    */
    @ECPDict(required = true,length = 1, message = "dcFlg值不合法")
    private String dcFlg;

    /**
    * 主机交易日期
    */
    @ECPDict(required = true,length = 8, message = "tranDate值不合法")
    private String tranDate;

    /**
    * 交易金额
    */
    @ECPDict(required = true, message = "tranAmt值不合法")
    private YGAmt tranAmt;

    /**
    * 币种
    */
    @ECPDict(required = true,length = 16, message = "ccy值不合法")
    private String ccy;

    /**
    * 交易描述
    */
    @ECPDict(required = true,length = 150, message = "tranDesc值不合法")
    private String tranDesc;

    /**
    * 上次余额
    */
    @ECPDict(required = true, message = "previousBalAmt值不合法")
    private YGAmt previousBalAmt;

    /**
    * 交易操作员
    */
    @ECPDict(required = true,length = 32, message = "officerId值不合法")
    private String officerId;

    /**
    * 参考行
    */
    @ECPDict(required = true,length = 16, message = "referenceBank值不合法")
    private String referenceBank;

    /**
    * 参考分行
    */
    @ECPDict(required = true,length = 16, message = "referenceBranch值不合法")
    private String referenceBranch;

    /**
    * 描述信息
    */
    @ECPDict(required = true,length = 256, message = "narrative值不合法")
    private String narrative;

    /**
    * 场次号
    */
    @ECPDict(required = true,length = 16, message = "sessionId值不合法")
    private String sessionId;

    /**
    * 他行号
    */
    @ECPDict(required = true,length = 16, message = "othBankCode值不合法")
    private String othBankCode;

    /**
    * 柜员号
    */
    @ECPDict(required = true,length = 32, message = "btOfficerId值不合法")
    private String btOfficerId;

    /**
    * 对方账户户名
    */
    @ECPDict(required = true,length = 100, message = "tfrAcctName值不合法")
    private String tfrAcctName;

    /**
    * 对方账户号码
    */
    @ECPDict(required = true,length = 100, message = "oopAcctNo值不合法")
    private String oopAcctNo;

    /**
    * 对方开户行
    */
    @ECPDict(required = true,length = 100, message = "oopOpnAcctBnkNm值不合法")
    private String oopOpnAcctBnkNm;

    /**
    * 交易后余额
    */
    @ECPDict(required = true, message = "actualBalAmt值不合法")
    private YGAmt actualBalAmt;

    /**
    * 交易后余额借贷标志
    */
    @ECPDict(required = true,length = 4, message = "actualBalAmtCrDrInt值不合法")
    private String actualBalAmtCrDrInt;

    /**
    * 余额类型
    */
    @ECPDict(required = true,length = 4, message = "balType值不合法")
    private String balType;

    /**
    * 可用余额=账户余额-冻结金额，实时计算出来的
    */
    @ECPDict(required = true, message = "avaAmt值不合法")
    private YGAmt avaAmt;

    /**
    * 交易类型
    */
    @ECPDict(required = true,length = 4, message = "tranType值不合法")
    private String tranType;

    /**
    * 冲正标识，R：冲正
    */
    @ECPDict(required = true,length = 1, message = "reverse值不合法")
    private String reverse;

    /**
    * 冻结金额，同步核心流水之前查询账户的冻结金额
    */
    @ECPDict(required = true, message = "frzAmt值不合法")
    private YGAmt frzAmt;

    /**
    * 银行流水号
    */
    @ECPDict(required = true,length = 64, message = "bnkNumber值不合法")
    private String bnkNumber;

    /**
    * 附记备注
    */
    @ECPDict(required = true,length = 1024, message = "summary值不合法")
    private String summary;

    /**
    * 记账时间
    */
    @ECPDict(required = true,length = 14, message = "execTime值不合法")
    private String execTime;

    /**
    * 备注
    */
    @ECPDict(required = true,length = 300, message = "rmk值不合法")
    private String rmk;

    /**
    * 备用字段1
    */
    @ECPDict(required = true,length = 100, message = "rsv1值不合法")
    private String rsv1;

    /**
    * 备用字段2
    */
    @ECPDict(required = true,length = 100, message = "rsv2值不合法")
    private String rsv2;

    /**
    * 备用字段3
    */
    @ECPDict(required = true,length = 100, message = "rsv3值不合法")
    private String rsv3;

    /**
    * 备用字段4
    */
    @ECPDict(required = true,length = 100, message = "rsv4值不合法")
    private String rsv4;

    /**
    * 备用字段5
    */
    @ECPDict(required = true,length = 100, message = "rsv5值不合法")
    private String rsv5;

}
