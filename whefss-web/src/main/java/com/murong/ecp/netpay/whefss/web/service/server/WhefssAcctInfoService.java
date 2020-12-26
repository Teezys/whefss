package com.murong.ecp.netpay.whefss.web.service.server;


import com.murong.ecp.netpay.whefss.web.common.utils.CommonMessageCode;
import com.murong.ecp.netpay.whefss.web.common.utils.ResultT;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.AcctInfoMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.TTransJnlMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.AcctInfoNds;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.AcctRemainderMoney;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.TTransJnlDO;
import com.yuangou.ecp.biz.transengine.util.GDAUtil;
import com.yuangou.ecp.bp.comp.pubatc.YGPUBATCUtil;
import com.yuangou.ecp.bp.core.common.exception.YGException;
import com.yuangou.ecp.bp.core.common.message.YGBizMessage;
import com.yuangou.ecp.bp.core.common.message.YGBizMessageContext;
import com.yuangou.ecp.bp.core.common.message.YGEDB;
import com.yuangou.ecp.bp.core.common.yglog4j.Logger;
import com.yuangou.ecp.bp.core.common.yglog4j.YGLogger;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 监管账户信息处理
 *
 * @author wanglei
 */
@Component("WhefssAcctInfoService")
public class WhefssAcctInfoService {
    //子产品码
    @Value("${nds.acctSubt}")
    private String acctSubt;
    //币种
    @Value("${nds.ccy}")
    private String ccy;
    //币种
    @Value("${zhefss.iswatch.flag}")
    private boolean isWatchFlag;

    @Autowired
    private AcctInfoMapper acctInfoMapper;

    @Autowired
    private TTransJnlMapper transJnlMapper;

    //查询NDS监管账户信息
    public AcctInfoNds getAcctInfoByNds(YGBizMessageContext bizCtx, String acctNo) throws YGException {
        Logger logger = YGLogger.getLogger(bizCtx.getCurrentMsg());
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        AcctInfoNds acctInfoNds = new AcctInfoNds();
        if (StringUtils.isBlank(acctNo)) {
            return acctInfoNds;
        }
        edb.setData("acct_no", acctNo);
        int ii = YGPUBATCUtil.callThirdService(bizCtx, "bibspubbpc2", "beaNdsEsbAccInq", "bibspub_region");
        if (ii == 0) {
            logger.info("msgCd=" + GDAUtil.getMsg_cd(bizMsg));
            String msgCd = GDAUtil.getMsg_cd(bizMsg);
            if ("SCM00000".equals(msgCd)) {
                acctInfoNds.setFlxFlag(edb.getData("flx_flag"));
                acctInfoNds.setAcctPreFlag(edb.getData("acct_pre_flag"));
                acctInfoNds.setAcctFtaFlag(edb.getData("acct_fta_flag"));
                acctInfoNds.setAcctEnam(edb.getData("acct_enam"));
                acctInfoNds.setAcctCnam(edb.getData("acct_cnam"));
                acctInfoNds.setAcctAddr(edb.getData("acct_addr"));
                acctInfoNds.setDepositType(edb.getData("deposit_type"));
                acctInfoNds.setBranch(edb.getData("branch"));
                acctInfoNds.setSubBranch(edb.getData("sub_branch"));
                acctInfoNds.setCcy(edb.getData("ccy"));

                acctInfoNds.setAcctFlag(edb.getData("acct_flag"));
                acctInfoNds.setAcctStatus(edb.getData("acct_status"));
                acctInfoNds.setAcctAttr1(edb.getData("acct_attr1"));
                acctInfoNds.setAcctAttr2(edb.getData("acct_attr2"));
                acctInfoNds.setAcctAttr3(edb.getData("acct_attr3"));
                acctInfoNds.setCustomNo(edb.getData("custom_no"));
                acctInfoNds.setAcctProdtype(edb.getData("acct_prodtype"));
                acctInfoNds.setAcctCliType(edb.getData("acct_clitype"));
                acctInfoNds.setAcctAtn(edb.getData("acct_atn"));
                acctInfoNds.setAcctCiType(edb.getData("acct_citype"));

                acctInfoNds.setAcctRegf(edb.getData("acct_regf"));
                acctInfoNds.setAcctJoinFlag(edb.getData("acct_join_flag"));
                acctInfoNds.setPbNo(edb.getData("PB_NO"));
                acctInfoNds.setPrefix(edb.getData("prefix"));
                acctInfoNds.setDocType(edb.getData("doc_type"));
                acctInfoNds.setLostStatus(edb.getData("lost_status"));
                acctInfoNds.setCrIntType(edb.getData("cr_int_type"));
                acctInfoNds.setBalType(edb.getData("bal_type"));
            } else {
                logger.info("MsgId:[" + bizMsg.getRequestId() + "],账户查询调用NDS失败：[msgCd=" + msgCd + "]");
                return acctInfoNds;
            }
        } else {
            logger.info("MsgId:[" + bizMsg.getRequestId() + "],账户查询调用NDS失败：[调用接出的返回结果为" + ii + "]");
            return acctInfoNds;
        }
        acctInfoNds.setResult(true);
        return acctInfoNds;
    }


    //查询NDS监管账户余额
    public AcctRemainderMoney getAcctRemainderMoney(YGBizMessageContext bizCtx, String acctNo) throws YGException {
        Logger logger = YGLogger.getLogger(bizCtx.getCurrentMsg());
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        AcctRemainderMoney acctRemainderMoney = new AcctRemainderMoney();
        if (StringUtils.isBlank(acctNo)) {
            return acctRemainderMoney;
        }
        edb.setData("acct_no", acctNo);
        acctRemainderMoney.setAcctNo(acctNo);
        edb.setData("acct_subt", acctSubt);
        edb.setData("ccy", ccy);
        edb.removeChildNode("kma");
        int ii = YGPUBATCUtil.callThirdService(bizCtx, "bibspubbpc2", "queryNdsEsbAcctBal", "bibspub_region");
        if (ii == 0) {
            logger.info("msgCd=" + GDAUtil.getMsg_cd(bizMsg));
            String msgCd = GDAUtil.getMsg_cd(bizMsg);
            if ("SCM00000".equals(msgCd)) {
                YGEDB ygedbs = edb.getChildNode("kma");
                if (null == ygedbs) {
                    logger.info("MsgId:[" + bizMsg.getRequestId() + "],余额查询调用NDS失败：[余额为空]");
                    return acctRemainderMoney;
                }
                YGEDB ygedb = (YGEDB) edb.getChildNode("kma").getChildNodes()
                        .get(0);
                acctRemainderMoney.setLedgerBal(ygedb.getData("ledger_bal"));
                acctRemainderMoney.setActualBal(ygedb.getData("actual_bal"));
                acctRemainderMoney.setCcy(ygedb.getData("ccy"));
                acctRemainderMoney.setCashBal(ygedb.getData("cash_bal"));
                acctRemainderMoney.setTtBal(ygedb.getData("tt_bal"));
                acctRemainderMoney.setCaActualBal(ygedb.getData("ca_actual_bal"));
                acctRemainderMoney.setTtActualBal(ygedb.getData("tt_actual_bal"));
            } else {
                acctRemainderMoney.setMsgCd(msgCd);
                logger.info("MsgId:[" + bizMsg.getRequestId() + "],余额查询调用NDS失败：[msgCd=" + msgCd + "]");
                return acctRemainderMoney;
            }
        } else {
            logger.info("MsgId:[" + bizMsg.getRequestId() + "],余额查询调用NDS失败：[调用接出的返回结果为" + ii + "]");
            return acctRemainderMoney;
        }
        acctRemainderMoney.setResult(true);
        return acctRemainderMoney;
    }

    //查询ESB监管账户信息
    public AcctInfoNds getAcctInfoByEsb(YGBizMessageContext bizCtx, String acctNo) throws YGException {
        Logger logger = YGLogger.getLogger(bizCtx.getCurrentMsg());
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        AcctInfoNds acctInfoNds = new AcctInfoNds();
        if (StringUtils.isBlank(acctNo)) {
            return acctInfoNds;
        }
        edb.setData("acct_no", acctNo);
        int ii = YGPUBATCUtil.callThirdService(bizCtx, "bibspubbpc2", "beaNdsEsbAccInq2", "bibspub_region");
        if (ii == 0) {
            logger.info("msgCd=" + GDAUtil.getMsg_cd(bizMsg));
            String msgCd = GDAUtil.getMsg_cd(bizMsg);
            if ("SCM00000".equals(msgCd)) {
                YGEDB ygedbs = edb.getChildNode("kma");
                if (null == ygedbs) {
                    logger.info("MsgId:[" + bizMsg.getRequestId() + "],余额查询调用NDS失败：[余额为空]");
                    return acctInfoNds;
                }

                YGEDB ygedb = (YGEDB) edb.getChildNode("kma").getChildNodes()
                        .get(0);
                //卡状态
                acctInfoNds.setAcctStatus(ygedb.getData("acct_status"));
            } else {
                logger.info("MsgId:[" + bizMsg.getRequestId() + "],账户查询调用NDS失败：[msgCd=" + msgCd + "]");
                return acctInfoNds;
            }
        } else {
            logger.info("MsgId:[" + bizMsg.getRequestId() + "],账户查询调用NDS失败：[调用接出的返回结果为" + ii + "]");
            return acctInfoNds;
        }
        acctInfoNds.setResult(true);
        return acctInfoNds;
    }


    //校验记账流水
    public ResultT isTransJnl(YGBizMessageContext bizCtx, TTransJnlDO transJnlDo) throws YGException {
        ResultT resultT = new ResultT();
        TTransJnlDO transJnlNds = transJnlMapper.selectSelectByBnkNumber(transJnlDo);
        //判断核心流水号是否一致
        if(transJnlNds==null){
            resultT.setMsgCod(CommonMessageCode.NDS_JNL_SEQ_NO_ERROR);
            return resultT;
        }
        resultT.setData(transJnlNds);
        //判断监管账户是否一致
        if(transJnlDo.getSvsAcNo()!=null){
            if(!transJnlDo.getSvsAcNo().equals(transJnlNds.getSvsAcNo())){
                resultT.setMsgCod(CommonMessageCode.NDS_JNL_ACC_NO_ERROR);
                return resultT;
            }
        }
        //判断交易金额是否一致
        if(transJnlDo.getTranAmt()!=null){
            if(transJnlDo.getTranAmt().compareTo(transJnlNds.getTranAmt())!=0){
                resultT.setMsgCod(CommonMessageCode.NDS_JNL_AMT_ERROR);
                return resultT;
            }
        }
        //判断交易日期是否一致
        if(transJnlDo.getTranDate()!=null){
            if(!transJnlDo.getTranDate().equals(transJnlNds.getTranDate())){
                resultT.setMsgCod(CommonMessageCode.NDS_JNL_DATE_ERROR);
                return resultT;
            }
        }
        //判断借贷标志是否一致
        if(transJnlDo.getDcFlg()!=null){
            if(!transJnlDo.getDcFlg().equals(transJnlNds.getDcFlg())){
                resultT.setMsgCod(CommonMessageCode.NDS_JNL_DC_FLG_ERROR);
                return resultT;
            }
        }
        resultT.setData(transJnlNds);
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }

}
