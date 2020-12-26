package com.murong.ecp.netpay.whefss.web.common.utils;

import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class ReturnCode {
    HashMap<String,String> map = new HashMap<>();
    ReturnCode(){
        map.put("00","交易成功");
        map.put("01","购房款缴款凭证编号不存在");
        map.put("02","缴款通知书首付款已完成缴款");
        map.put("03","缴款通知书首付款未完成缴款");
        map.put("04","缴款通知书贷款已完成缴款");
        map.put("05","资金划转凭证编号不存在");
        map.put("06","划转凭证已经完成支付");
        map.put("07","报文的包长错误");
        map.put("08","交易代码错误");
        map.put("09","银行代码错误");
        map.put("10","对账文件不存在");
        map.put("11","对账文件数据为空");
        map.put("12","对账文件数据格式错误");
        map.put("13","对账失败，存在未完成对账的交易");
        map.put("14","接口服务发生异常");
        map.put("15","数据异常，如格式不正确");
        map.put("16","IP未授权");
        map.put("17","贷款下放清单文件不存在");
        map.put("18","贷款下放清单文件数据为空");
        map.put("19","贷款下放清单文件数据格式错误");
        map.put("20","贷款下放清单文件数据错误");
        map.put("21","申请书编号不存在");
        map.put("22","监管账户账号不存在(已注销、已停用、未申请)");
        map.put("23","流水号相同，交易金额、交易日期等数据不同");
        map.put("24","未与银行签订监管协议、或者协议已注销");
        map.put("25","应缴金额已缴满");
        map.put("26","银行流水号已存在");
        map.put("27","当前缴交金额加上已缴交金额超出应缴资金");
        map.put("28","资金划转凭证已失效");
        map.put("29","资金划转凭证未审批");
        map.put("30","资金已全部划转");
        map.put("31","收款方账号不一致与划转查询的收款方不一致");
        map.put("32","划转的监管账号与划转查询的监管账号不一致");
        map.put("33","当前支付金额加上已支付金额超出应支付总资金");
        map.put("34","缴款清单编号不存在，无效");
        map.put("35","缴款清单已完成缴款");
        map.put("36","缴款清单明细不存在");
        map.put("37","开发行代码、银行网点号不能为空");
        map.put("38","该银行网点不是监管银行");
        map.put("39","缴款金额与业务实际缴款金额不一致");
    };
    public String getMsg(String code){
        return map.get(code);
    }
}
