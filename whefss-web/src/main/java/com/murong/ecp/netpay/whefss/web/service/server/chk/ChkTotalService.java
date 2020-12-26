package com.murong.ecp.netpay.whefss.web.service.server.chk;


import com.murong.ecp.netpay.whefss.web.common.utils.CommonMessageCode;
import com.murong.ecp.netpay.whefss.web.common.utils.DateUtil;
import com.murong.ecp.netpay.whefss.web.common.utils.ResultT;
import com.murong.ecp.netpay.whefss.web.common.utils.WhefssConstant;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.*;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.*;
import com.murong.ecp.netpay.whefss.web.service.server.WhefssService;
import com.murong.ecp.netpay.whefss.web.service.server.bo.FundsUploadDetail;
import com.murong.ecp.netpay.whefss.web.service.server.bo.FundsUploadInfo;
import com.yuangou.ecp.bp.comp.pubatc.YGPUBATCUtil;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import com.yuangou.ecp.bp.core.common.exception.YGException;
import com.yuangou.ecp.bp.core.common.message.YGBizMessageContext;
import com.yuangou.ecp.bp.pub.atc.YGPubUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

/**
 * 生成对账记录
 *
 * @author wanglei
 */
@Component("ChkTotalService")
public class ChkTotalService {

    @Autowired
    private WhefssChkTotalMapper chkTotalMapper;
    @Autowired
    private WhefssChkDetailMapper chkDetailMapper;
    @Autowired
    private AcctInfoMapper acctInfoMapper;
    @Autowired
    private TTransJnlMapper transJnlMapper;
    @Autowired
    private WhefssAccInfoMapper whefssAccInfoMapper;
    @Autowired
    private WhefssAccInSendMapper accInSendMapper;
    @Autowired
    private WhefssChkDetailJnlMapper chkDetailJnlMapper;
    @Autowired
    private WhefssAccPosInSendMapper posInSendMapper;
    @Autowired
    private WhefssAccOutSendMapper accOutSendMapper;
    @Autowired
    private WhefssAccRefundMapper accRefundMapper;
    @Autowired
    private WhefssAccRepealMapper accRepealMapper;
    @Autowired
    private WhefssAmpTranidMapper ampTranidMapper;
    @Autowired
    private WhefssInterestSndMapper interestSndMapper;
    @Autowired
    private WhefssAjdkSndMapper ajdkSndMapper;
    @Autowired
    private WhefssOtherInSndMapper otherInSndMapper;
    @Autowired
    private WhefssService whefssService;

    public ResultT chk(YGBizMessageContext bizCtx, String chkDt, boolean flag) {
        ResultT resultT = new ResultT();
        try {
            //检查对账总控表
            WhefssChkTotalDO whefssChkTotalDO = new WhefssChkTotalDO();
            whefssChkTotalDO.setChkDt(chkDt);
            WhefssChkTotalDO chkTotalQry = chkTotalMapper.selectByChkDt(whefssChkTotalDO);
            if (chkTotalQry == null) {
                //检查上一日对账是否完成，未完成退出,
                if (flag) {
                    String yesterday = DateUtil.getDay(chkDt, -1);
                    whefssChkTotalDO.setChkDt(yesterday);
                    WhefssChkTotalDO chkTotalQry2 = chkTotalMapper.selectByChkDt(whefssChkTotalDO);
                    if (chkTotalQry2 == null || !WhefssConstant.CHK_TOTAL_STS_02.equals(chkTotalQry2.getChkSts())) {
                        resultT.setMsgCod(CommonMessageCode.LAST_CHK_ERROR);
                        return resultT;
                    }
                }
                chkTotalQry = new WhefssChkTotalDO();
                //生成对账总控表
                chkTotalQry.setChkDt(chkDt);
                String dateStr = DateUtil.getTimestamp();
                String seqNo = dateStr + YGPubUtil.getUniqueNo(bizCtx, WhefssConstant.SEQ_WHEFSS_CHK_TOTAL, 8);
                chkTotalQry.setPkId(seqNo);
                chkTotalQry.setChkTm(dateStr);
                chkTotalQry.setChkSts(WhefssConstant.CHK_TOTAL_STS_00);
                int i = chkTotalMapper.insertSelective(chkTotalQry);
                if (i <= 0) {
                    resultT.setMsgCod(CommonMessageCode.CHK_TOTAL_INSERT_ERROR);
                    return resultT;
                }
                //生成对账账户记录
                ResultT resultTDetail = createChkDetail(bizCtx, chkTotalQry);
                if (!CommonMessageCode.SUCC.getMsgCod().equals(resultTDetail.getMsgCod())) {
                    resultT.setMsgCod(resultTDetail.getMsgCod());
                    resultT.setMsgInf(resultTDetail.getMsgInf());
                    return resultT;
                }
            } else if (WhefssConstant.CHK_TOTAL_STS_02.equals(chkTotalQry.getChkSts())) {
                resultT.setMsgCod(CommonMessageCode.CHK_FINISH_ERROR);
                return resultT;
            } else if (WhefssConstant.CHK_TOTAL_STS_00.equals(chkTotalQry.getChkSts())) {
                //生成对账账户记录
                ResultT resultTDetail = createChkDetail(bizCtx, chkTotalQry);
                if (!CommonMessageCode.SUCC.getMsgCod().equals(resultTDetail.getMsgCod())) {
                    resultT.setMsgCod(resultTDetail.getMsgCod());
                    resultT.setMsgInf(resultTDetail.getMsgInf());
                    return resultT;
                }
            }
            whefssChkTotalDO.setPkId(chkTotalQry.getPkId());
            whefssChkTotalDO.setChkSts(WhefssConstant.CHK_TOTAL_STS_01);
            chkTotalMapper.updateByPrimaryKey(whefssChkTotalDO);
            YGPUBATCUtil.commitWork(bizCtx);

            //开始循环对账账户明细
            resultT = chkDetail(bizCtx, chkTotalQry);

            //统计是否所有账户都已对账完成
            whefssChkTotalDO.setChkSts(WhefssConstant.CHK_TOTAL_STS_02);
            return sumTotal(bizCtx, chkTotalQry.getPkId());
        } catch (Exception e) {
            e.printStackTrace();
            resultT.setMsgCod(CommonMessageCode.FAIL);
            return resultT;
        }
    }

    //生成对账账户明细
    public ResultT createChkDetail(YGBizMessageContext bizCtx, WhefssChkTotalDO chkTotalQry) throws Exception {
        ResultT resultT = new ResultT();
        //查询需要对账的账户
        AcctInfoDO acctInfoDO = new AcctInfoDO();
        acctInfoDO.setFssFlg(WhefssConstant.FSS_FLG);
        acctInfoDO.setAccSts(WhefssConstant.ACCT_STS_1);
        List<AcctInfoDO> list = acctInfoMapper.selectListBySts(acctInfoDO);
        String dateStr = DateUtil.getTimestamp();
        String seqNo = "";
        for (int i = 0; i < list.size(); i++) {
            //登记账户明细
            WhefssChkDetailDO chkDetailDO = new WhefssChkDetailDO();
            seqNo = dateStr + YGPubUtil.getUniqueNo(bizCtx, WhefssConstant.SEQ_WHEFSS_CHK_TOTAL, 8);
            chkDetailDO.setPkId(seqNo);
            chkDetailDO.setFkId(chkTotalQry.getPkId());
            chkDetailDO.setAccountNum(list.get(0).getSvsAcNo());
            chkDetailDO.setChkDt(chkTotalQry.getChkDt());
            chkDetailDO.setChkStatus(WhefssConstant.CHK_DETAIL_STS_00);
            //余额 查询公共流水表交易余额
            TTransJnlDO tTransJnlDO = new TTransJnlDO();
            tTransJnlDO.setFssFlg(WhefssConstant.FSS_FLG);
            tTransJnlDO.setTranDate(chkTotalQry.getChkDt());
            tTransJnlDO.setSvsAcNo(chkDetailDO.getAccountNum());
            String balance = transJnlMapper.selectBalance(tTransJnlDO);
            WhefssAccInfoDO whefssAccInfoDO = new WhefssAccInfoDO();
            whefssAccInfoDO.setAccountNum(chkDetailDO.getAccountNum());
            if (StringUtils.isBlank(balance)) {
                //查询监管账户中的余额
                WhefssAccInfoDO whefssAccInfoqry = whefssAccInfoMapper.selectByPrimaryKey(whefssAccInfoDO);
                if (whefssAccInfoqry == null) {
                    resultT.setMsgCod(CommonMessageCode.FAIL);
                    return resultT;
                }
                chkDetailDO.setBalanceBank(whefssAccInfoqry.getDayendAmt());
            } else {
                chkDetailDO.setBalanceBank(new YGAmt(balance));
                whefssAccInfoDO.setDayendAmt(new YGAmt(balance));
                int k = whefssAccInfoMapper.updateByPrimaryKey(whefssAccInfoDO);
                if (k <= 0) {
                    resultT.setMsgCod(CommonMessageCode.CHK_DETAIL_BALANCE_ERROR);
                    return resultT;
                }
            }

            int j = chkDetailMapper.insertSelective(chkDetailDO);
            if (j <= 0) {
                resultT.setMsgCod(CommonMessageCode.CHK_DETAIL_INSERT_ERROR);
                return resultT;
            }
        }
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }


    //对账
    public ResultT chkDetail(YGBizMessageContext bizCtx, WhefssChkTotalDO chkTotalQry) throws Exception {
        ResultT resultT = new ResultT();
        //查询需要对账的账户
        WhefssChkDetailDO whefssChkDetailDO = new WhefssChkDetailDO();
        whefssChkDetailDO.setFkId(chkTotalQry.getPkId());
        List<WhefssChkDetailDO> chkList = chkDetailMapper.selectListByFkid(whefssChkDetailDO);

        for (int i = 0; i < chkList.size(); i++) {
            WhefssChkDetailDO chkDetailDO = chkList.get(i);
            if (WhefssConstant.CHK_DETAIL_STS_02.equals(chkDetailDO.getChkStatus())) {
                continue;
            }
            if (WhefssConstant.CHK_DETAIL_STS_00.equals(chkDetailDO.getChkStatus()) ||
                    WhefssConstant.CHK_DETAIL_STS_01.equals(chkDetailDO.getChkStatus()) ||
                    WhefssConstant.CHK_DETAIL_STS_11.equals(chkDetailDO.getChkStatus())) {
                //开始对账
                //删除该账户对账交易流水表记录
                WhefssChkDetailJnlDO entity = new WhefssChkDetailJnlDO();
                entity.setFkTotalId(chkTotalQry.getPkId());
                entity.setFkDetailId(chkList.get(i).getPkId());
                chkDetailJnlMapper.deleteChk(entity);
                //缴存
                resultT = chkContractsIncome(bizCtx, chkDetailDO);
                if (!CommonMessageCode.SUCC.getMsgCod().equals(resultT.getMsgCod())) {
                    //更新状态
                    whefssChkDetailDO.setPkId(chkDetailDO.getPkId());
                    whefssChkDetailDO.setMsg(resultT.getMsgInf());
                    whefssChkDetailDO.setChkStatus(WhefssConstant.CHK_DETAIL_STS_01);
                    chkDetailMapper.updateByPrimaryKey(whefssChkDetailDO);
                    YGPUBATCUtil.commitWork(bizCtx);
                    continue;
                }
                //拨付
                resultT = chkAccOut(bizCtx, chkDetailDO);
                if (!CommonMessageCode.SUCC.getMsgCod().equals(resultT.getMsgCod())) {
                    //更新状态
                    whefssChkDetailDO.setPkId(chkDetailDO.getPkId());
                    whefssChkDetailDO.setMsg(resultT.getMsgInf());
                    whefssChkDetailDO.setChkStatus(WhefssConstant.CHK_DETAIL_STS_01);
                    chkDetailMapper.updateByPrimaryKey(whefssChkDetailDO);
                    YGPUBATCUtil.commitWork(bizCtx);
                    continue;
                }
                //退款
                resultT = chkAccRefund(bizCtx, chkDetailDO);
                if (!CommonMessageCode.SUCC.getMsgCod().equals(resultT.getMsgCod())) {
                    //更新状态
                    whefssChkDetailDO.setPkId(chkDetailDO.getPkId());
                    whefssChkDetailDO.setMsg(resultT.getMsgInf());
                    whefssChkDetailDO.setChkStatus(WhefssConstant.CHK_DETAIL_STS_01);
                    chkDetailMapper.updateByPrimaryKey(whefssChkDetailDO);
                    YGPUBATCUtil.commitWork(bizCtx);
                    continue;
                }
                //冲正
                resultT = chkAccRepeal(bizCtx, chkDetailDO);
                if (!CommonMessageCode.SUCC.getMsgCod().equals(resultT.getMsgCod())) {
                    //更新状态
                    whefssChkDetailDO.setPkId(chkDetailDO.getPkId());
                    whefssChkDetailDO.setMsg(resultT.getMsgInf());
                    whefssChkDetailDO.setChkStatus(WhefssConstant.CHK_DETAIL_STS_01);
                    chkDetailMapper.updateByPrimaryKey(whefssChkDetailDO);
                    YGPUBATCUtil.commitWork(bizCtx);
                    continue;
                }
                //按揭贷款
                resultT = chkAjdk(bizCtx, chkDetailDO);
                if (!CommonMessageCode.SUCC.getMsgCod().equals(resultT.getMsgCod())) {
                    //更新状态
                    whefssChkDetailDO.setPkId(chkDetailDO.getPkId());
                    whefssChkDetailDO.setMsg(resultT.getMsgInf());
                    whefssChkDetailDO.setChkStatus(WhefssConstant.CHK_DETAIL_STS_01);
                    chkDetailMapper.updateByPrimaryKey(whefssChkDetailDO);
                    YGPUBATCUtil.commitWork(bizCtx);
                    continue;
                }

                //pos
                resultT = chkPos(bizCtx, chkDetailDO);
                if (!CommonMessageCode.SUCC.getMsgCod().equals(resultT.getMsgCod())) {
                    //更新状态
                    whefssChkDetailDO.setPkId(chkDetailDO.getPkId());
                    whefssChkDetailDO.setMsg(resultT.getMsgInf());
                    whefssChkDetailDO.setChkStatus(WhefssConstant.CHK_DETAIL_STS_01);
                    chkDetailMapper.updateByPrimaryKey(whefssChkDetailDO);
                    YGPUBATCUtil.commitWork(bizCtx);
                    continue;
                }

                //冻结解冻
                resultT = chkFundsInfo(bizCtx, chkDetailDO);
                if (!CommonMessageCode.SUCC.getMsgCod().equals(resultT.getMsgCod())) {
                    //更新状态
                    whefssChkDetailDO.setPkId(chkDetailDO.getPkId());
                    whefssChkDetailDO.setMsg(resultT.getMsgInf());
                    whefssChkDetailDO.setChkStatus(WhefssConstant.CHK_DETAIL_STS_01);
                    chkDetailMapper.updateByPrimaryKey(whefssChkDetailDO);
                    YGPUBATCUtil.commitWork(bizCtx);
                    continue;
                }
                //年费、利息
                resultT = chkInterest(bizCtx, chkDetailDO);
                if (!CommonMessageCode.SUCC.getMsgCod().equals(resultT.getMsgCod())) {
                    //更新状态
                    whefssChkDetailDO.setPkId(chkDetailDO.getPkId());
                    whefssChkDetailDO.setMsg(resultT.getMsgInf());
                    whefssChkDetailDO.setChkStatus(WhefssConstant.CHK_DETAIL_STS_01);
                    chkDetailMapper.updateByPrimaryKey(whefssChkDetailDO);
                    YGPUBATCUtil.commitWork(bizCtx);
                    continue;
                }
                //不明入账
                resultT = chkOtherIn(bizCtx, chkDetailDO);
                if (!CommonMessageCode.SUCC.getMsgCod().equals(resultT.getMsgCod())) {
                    //更新状态
                    whefssChkDetailDO.setPkId(chkDetailDO.getPkId());
                    whefssChkDetailDO.setMsg(resultT.getMsgInf());
                    whefssChkDetailDO.setChkStatus(WhefssConstant.CHK_DETAIL_STS_01);
                    chkDetailMapper.updateByPrimaryKey(whefssChkDetailDO);
                    YGPUBATCUtil.commitWork(bizCtx);
                    continue;
                }
                //报送数据
                resultT = sendDetail(bizCtx, chkDetailDO);
                if (!CommonMessageCode.SUCC.getMsgCod().equals(resultT.getMsgCod())) {
                    //更新状态
                    whefssChkDetailDO.setPkId(chkDetailDO.getPkId());
                    whefssChkDetailDO.setMsg(resultT.getMsgInf());
                    whefssChkDetailDO.setChkStatus(WhefssConstant.CHK_DETAIL_STS_11);
                    chkDetailMapper.updateByPrimaryKey(whefssChkDetailDO);
                    updateJnlSts(bizCtx, chkDetailDO.getPkId(), WhefssConstant.SEND_STATUS_04);
                    YGPUBATCUtil.commitWork(bizCtx);
                    continue;
                }
            }
            //统计信息
            resultT = sumDetail(bizCtx, chkDetailDO);
            if (!CommonMessageCode.SUCC.getMsgCod().equals(resultT.getMsgCod())) {
                //更新状态
                whefssChkDetailDO.setPkId(chkDetailDO.getPkId());
                whefssChkDetailDO.setMsg(resultT.getMsgInf());
                whefssChkDetailDO.setChkStatus(WhefssConstant.CHK_DETAIL_STS_12);
                chkDetailMapper.updateByPrimaryKey(whefssChkDetailDO);
                updateJnlSts(bizCtx, chkDetailDO.getPkId(), WhefssConstant.SEND_STATUS_03);
                YGPUBATCUtil.commitWork(bizCtx);
                continue;
            }
            whefssChkDetailDO.setPkId(chkDetailDO.getPkId());
            whefssChkDetailDO.setMsg(resultT.getMsgInf());
            whefssChkDetailDO.setChkStatus(WhefssConstant.CHK_DETAIL_STS_02);
            updateJnlSts(bizCtx, chkDetailDO.getPkId(), WhefssConstant.SEND_STATUS_02);
            chkDetailMapper.updateByPrimaryKey(whefssChkDetailDO);

            YGPUBATCUtil.commitWork(bizCtx);
        }

        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }

    //按揭贷款处理
    public ResultT chkAjdk(YGBizMessageContext bizCtx, WhefssChkDetailDO chkDetailDO) throws Exception {
        ResultT resultT = new ResultT();
        TTransJnlDO tTransJnlDO = new TTransJnlDO();
        tTransJnlDO.setSvsAcNo(chkDetailDO.getAccountNum());
        tTransJnlDO.setTranDate(chkDetailDO.getChkDt());
        List<TTransJnlDO> list = transJnlMapper.selectChkAjdk(tTransJnlDO);
        for (int i = 0; i < list.size(); i++) {
            //校验按揭贷款格式,匹配不通过直接跳过
            WhefssAjdkSndDO ajdkSndDO = new WhefssAjdkSndDO();
            if (Pattern.matches(WhefssConstant.AJDK_CHK, list.get(i).getNarrative().trim())) {
                ajdkSndDO.setNoticeNum(list.get(i).getNarrative().trim().replace(WhefssConstant.AJDK_CHK_HTBH, ""));
            } else {
                continue;
            }
            //查询是否登记过
            ajdkSndDO.setSn(list.get(i).getBnkNumber());
            WhefssAjdkSndDO result = ajdkSndMapper.selectByEntity(ajdkSndDO);
            ajdkSndDO.setSendStatus(WhefssConstant.SEND_STATUS_00);
            int j = 0;
            if (result != null) {
                j = ajdkSndMapper.updateSts(ajdkSndDO);
            } else {
                ajdkSndDO.setTime(list.get(i).getExecTime());
                ajdkSndDO.setAccountName(chkDetailDO.getAccountNum());
                ajdkSndDO.setBankCode(WhefssConstant.BANK_CODE);
                ajdkSndDO.setRealIn(list.get(i).getTranAmt());
                ajdkSndDO.setSendStatus(WhefssConstant.SEND_STATUS_00);
                //查询监管编号
                WhefssAccInfoDO whefssAccInfoDO = new WhefssAccInfoDO();
                whefssAccInfoDO.setAccountNum(chkDetailDO.getAccountNum());
                WhefssAccInfoDO whefssAccInfoqry = whefssAccInfoMapper.selectByPrimaryKey(whefssAccInfoDO);
                if (whefssAccInfoqry == null) {
                    resultT.setMsgCod(CommonMessageCode.FAIL);
                    return resultT;
                }
                ajdkSndDO.setMonitorNum(whefssAccInfoqry.getMonitorNum());
                j = ajdkSndMapper.insertSelective(ajdkSndDO);
            }
            if (j <= 0) {
                resultT.setMsgCod(CommonMessageCode.CHK_INSERT_AJDK_ERROR);
                return resultT;
            }
        }

        //登记账户交易流水表
        WhefssAjdkSndDO accInSendDO = new WhefssAjdkSndDO();
        accInSendDO.setTime(chkDetailDO.getChkDt());
        accInSendDO.setAccountNum(chkDetailDO.getAccountNum());
        List<WhefssAjdkSndDO> listAjdk = ajdkSndMapper.selectChk(accInSendDO);
        String dateStr = DateUtil.getTimestamp();
        for (int i = 0; i < listAjdk.size(); i++) {
            WhefssChkDetailJnlDO chkDetailJnlDO = new WhefssChkDetailJnlDO();
            chkDetailJnlDO.setPkId(dateStr + YGPubUtil.getUniqueNo(bizCtx, WhefssConstant.SEQ_WHEFSS_CHK_DETAIL_JNL, 8));
            chkDetailJnlDO.setFkDetailId(chkDetailDO.getPkId());
            chkDetailJnlDO.setFkTotalId(chkDetailDO.getFkId());
            chkDetailJnlDO.setChkDt(chkDetailDO.getChkDt());
            chkDetailJnlDO.setAccountNum(chkDetailDO.getAccountNum());
            chkDetailJnlDO.setDataType(WhefssConstant.DATA_TYPE_14);
            chkDetailJnlDO.setAmount(listAjdk.get(i).getRealIn());
            chkDetailJnlDO.setCommission(listAjdk.get(i).getCommission());
            chkDetailJnlDO.setNoticeNum(listAjdk.get(i).getNoticeNum());
            chkDetailJnlDO.setSn(chkDetailDO.getChkDt() + listAjdk.get(i).getSn());
            chkDetailJnlDO.setTime(DateUtil.formatSdfToSdt(listAjdk.get(i).getTime()));
            if (!insertDetailJnl(bizCtx, chkDetailJnlDO)) {
                resultT.setMsgCod(CommonMessageCode.CHK_INSERT_DETAIL_JNL_ERROR);
                return resultT;
            }
            WhefssAjdkSndDO chk = new WhefssAjdkSndDO();
            chk.setBatchNum(chkDetailDO.getPkId());
            chk.setNoticeNum(listAjdk.get(i).getNoticeNum());
            int a = ajdkSndMapper.updateByPrimaryKey(chk);
            if (a <= 0) {
                resultT.setMsgCod(CommonMessageCode.CHK_UPDATE_BATCH_NUM_ERROR);
                return resultT;
            }
        }
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }

    //正常入账 柜面缴存
    public ResultT chkContractsIncome(YGBizMessageContext bizCtx, WhefssChkDetailDO chkDetailDO) throws Exception {
        ResultT resultT = new ResultT();
        //查询需要对账的账户
        WhefssAccInSendDO accInSendDO = new WhefssAccInSendDO();
        accInSendDO.setTime(chkDetailDO.getChkDt());
        accInSendDO.setAccountNum(chkDetailDO.getAccountNum());
        List<WhefssAccInSendDO> list = accInSendMapper.selectChk(accInSendDO);
        String dateStr = DateUtil.getTimestamp();
        for (int i = 0; i < list.size(); i++) {
            WhefssChkDetailJnlDO chkDetailJnlDO = new WhefssChkDetailJnlDO();
            chkDetailJnlDO.setPkId(dateStr + YGPubUtil.getUniqueNo(bizCtx, WhefssConstant.SEQ_WHEFSS_CHK_DETAIL_JNL, 8));
            chkDetailJnlDO.setFkDetailId(chkDetailDO.getPkId());
            chkDetailJnlDO.setFkTotalId(chkDetailDO.getFkId());
            chkDetailJnlDO.setChkDt(chkDetailDO.getChkDt());
            chkDetailJnlDO.setAccountNum(chkDetailDO.getAccountNum());
            chkDetailJnlDO.setDataType(WhefssConstant.DATA_TYPE_1);
            chkDetailJnlDO.setAmount(list.get(i).getRealIn());
            chkDetailJnlDO.setCommission(list.get(i).getCommission());
            chkDetailJnlDO.setNoticeNum(list.get(i).getNoticeNum());
            chkDetailJnlDO.setSn(chkDetailDO.getChkDt() + list.get(i).getSn());
            chkDetailJnlDO.setTime(DateUtil.formatSdfToSdt(list.get(i).getTime()));
            if (!insertDetailJnl(bizCtx, chkDetailJnlDO)) {
                resultT.setMsgCod(CommonMessageCode.CHK_INSERT_DETAIL_JNL_ERROR);
                return resultT;
            }
            WhefssAccInSendDO chk = new WhefssAccInSendDO();
            chk.setBatchNum(chkDetailDO.getPkId());
            chk.setNoticeNum(list.get(i).getNoticeNum());
            int a = accInSendMapper.updateByPrimaryKey(chk);
            if (a <= 0) {
                resultT.setMsgCod(CommonMessageCode.CHK_UPDATE_BATCH_NUM_ERROR);
                return resultT;
            }
        }

        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }

    //pos
    public ResultT chkPos(YGBizMessageContext bizCtx, WhefssChkDetailDO chkDetailDO) throws Exception {
        ResultT resultT = new ResultT();
        //查询需要对账的账户
        WhefssAccPosInSendDO entity = new WhefssAccPosInSendDO();
        entity.setTime(chkDetailDO.getChkDt());
        entity.setAccountNum(chkDetailDO.getAccountNum());
        List<WhefssAccPosInSendDO> list = posInSendMapper.selectChk(entity);
        String dateStr = DateUtil.getTimestamp();
        for (int i = 0; i < list.size(); i++) {
            WhefssChkDetailJnlDO chkDetailJnlDO = new WhefssChkDetailJnlDO();
            chkDetailJnlDO.setPkId(dateStr + YGPubUtil.getUniqueNo(bizCtx, WhefssConstant.SEQ_WHEFSS_CHK_DETAIL_JNL, 8));
            chkDetailJnlDO.setFkDetailId(chkDetailDO.getPkId());
            chkDetailJnlDO.setFkTotalId(chkDetailDO.getFkId());
            chkDetailJnlDO.setChkDt(chkDetailDO.getChkDt());
            chkDetailJnlDO.setAccountNum(chkDetailDO.getAccountNum());
            chkDetailJnlDO.setDataType(WhefssConstant.DATA_TYPE_2);
            chkDetailJnlDO.setAmount(list.get(i).getRealIn());
            chkDetailJnlDO.setCommission(list.get(i).getCommission());
            chkDetailJnlDO.setNoticeNum(list.get(i).getNoticeNum());
            chkDetailJnlDO.setSn(chkDetailDO.getChkDt() + list.get(i).getSn() + YGPubUtil.getUniqueNo(bizCtx, WhefssConstant.SEQ_WHEFSS_CHK_DETAIL_JNL, 6));
            chkDetailJnlDO.setTime(DateUtil.formatSdfToSdt(list.get(i).getTime()));
            if (!insertDetailJnl(bizCtx, chkDetailJnlDO)) {
                resultT.setMsgCod(CommonMessageCode.CHK_INSERT_DETAIL_JNL_ERROR);
                return resultT;
            }
            WhefssAccPosInSendDO chk = new WhefssAccPosInSendDO();
            chk.setBatchNum(chkDetailDO.getPkId());
            chk.setNoticeNum(list.get(i).getNoticeNum());
            int a = posInSendMapper.updateByPrimaryKey(chk);
            if (a <= 0) {
                resultT.setMsgCod(CommonMessageCode.CHK_UPDATE_BATCH_NUM_ERROR);
                return resultT;
            }
        }

        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }

    //拨付
    public ResultT chkAccOut(YGBizMessageContext bizCtx, WhefssChkDetailDO chkDetailDO) throws Exception {
        ResultT resultT = new ResultT();
        //查询需要对账的账户
        WhefssAccOutSendDO entity = new WhefssAccOutSendDO();
        entity.setTime(chkDetailDO.getChkDt());
        entity.setAccountNum(chkDetailDO.getAccountNum());
        List<WhefssAccOutSendDO> list = accOutSendMapper.selectChk(entity);
        String dateStr = DateUtil.getTimestamp();
        for (int i = 0; i < list.size(); i++) {
            WhefssChkDetailJnlDO chkDetailJnlDO = new WhefssChkDetailJnlDO();
            chkDetailJnlDO.setPkId(dateStr + YGPubUtil.getUniqueNo(bizCtx, WhefssConstant.SEQ_WHEFSS_CHK_DETAIL_JNL, 8));
            chkDetailJnlDO.setFkDetailId(chkDetailDO.getPkId());
            chkDetailJnlDO.setFkTotalId(chkDetailDO.getFkId());
            chkDetailJnlDO.setChkDt(chkDetailDO.getChkDt());
            chkDetailJnlDO.setAccountNum(chkDetailDO.getAccountNum());
            if (WhefssConstant.TXN_STS_S.equals(list.get(i).getFlag())) {
                chkDetailJnlDO.setDataType(WhefssConstant.DATA_TYPE_7);
            } else if (WhefssConstant.TXN_STS_F.equals(list.get(i).getFlag())) {
                chkDetailJnlDO.setDataType(WhefssConstant.DATA_TYPE_8);
            } else {
                resultT.setMsgCod(CommonMessageCode.FAIL);
                return resultT;
            }
            chkDetailJnlDO.setAmount(list.get(i).getAmount());
            chkDetailJnlDO.setNoticeNum(list.get(i).getNoticeNum());
            chkDetailJnlDO.setSn(chkDetailDO.getChkDt() + list.get(i).getSn());
            chkDetailJnlDO.setTime(DateUtil.formatSdfToSdt(list.get(i).getTime()));
            if (!insertDetailJnl(bizCtx, chkDetailJnlDO)) {
                resultT.setMsgCod(CommonMessageCode.CHK_INSERT_DETAIL_JNL_ERROR);
                return resultT;
            }
            WhefssAccOutSendDO chk = new WhefssAccOutSendDO();
            chk.setBatchNum(chkDetailDO.getPkId());
            chk.setNoticeNum(list.get(i).getNoticeNum());
            int a = accOutSendMapper.updateByPrimaryKey(chk);
            if (a <= 0) {
                resultT.setMsgCod(CommonMessageCode.CHK_UPDATE_BATCH_NUM_ERROR);
                return resultT;
            }
        }
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }

    //退款
    public ResultT chkAccRefund(YGBizMessageContext bizCtx, WhefssChkDetailDO chkDetailDO) throws Exception {
        ResultT resultT = new ResultT();
        //查询需要对账的账户
        WhefssAccRefundDO entity = new WhefssAccRefundDO();
        entity.setTime(chkDetailDO.getChkDt());
        entity.setAccountNum(chkDetailDO.getAccountNum());
        List<WhefssAccRefundDO> list = accRefundMapper.selectChk(entity);
        String dateStr = DateUtil.getTimestamp();
        for (int i = 0; i < list.size(); i++) {
            WhefssChkDetailJnlDO chkDetailJnlDO = new WhefssChkDetailJnlDO();
            chkDetailJnlDO.setPkId(dateStr + YGPubUtil.getUniqueNo(bizCtx, WhefssConstant.SEQ_WHEFSS_CHK_DETAIL_JNL, 8));
            chkDetailJnlDO.setFkDetailId(chkDetailDO.getPkId());
            chkDetailJnlDO.setFkTotalId(chkDetailDO.getFkId());
            chkDetailJnlDO.setChkDt(chkDetailDO.getChkDt());
            chkDetailJnlDO.setAccountNum(chkDetailDO.getAccountNum());
            if (WhefssConstant.TXN_STS_S.equals(list.get(i).getFlag())) {
                chkDetailJnlDO.setDataType(WhefssConstant.DATA_TYPE_10);
            } else if (WhefssConstant.TXN_STS_F.equals(list.get(i).getFlag())) {
                chkDetailJnlDO.setDataType(WhefssConstant.DATA_TYPE_11);
            } else {
                resultT.setMsgCod(CommonMessageCode.FAIL);
                return resultT;
            }
            chkDetailJnlDO.setAmount(list.get(i).getRefundPrice());
            chkDetailJnlDO.setNoticeNum(list.get(i).getNoticeNum());
            chkDetailJnlDO.setSn(chkDetailDO.getChkDt() + list.get(i).getSn());
            chkDetailJnlDO.setTime(DateUtil.formatSdfToSdt(list.get(i).getTime()));
            if (!insertDetailJnl(bizCtx, chkDetailJnlDO)) {
                resultT.setMsgCod(CommonMessageCode.CHK_INSERT_DETAIL_JNL_ERROR);
                return resultT;
            }
            WhefssAccRefundDO chk = new WhefssAccRefundDO();
            chk.setBatchNum(chkDetailDO.getPkId());
            chk.setNoticeNum(list.get(i).getNoticeNum());
            int a = accRefundMapper.updateByPrimaryKey(chk);
            if (a <= 0) {
                resultT.setMsgCod(CommonMessageCode.CHK_UPDATE_BATCH_NUM_ERROR);
                return resultT;
            }
        }

        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }

    //冲正
    public ResultT chkAccRepeal(YGBizMessageContext bizCtx, WhefssChkDetailDO chkDetailDO) throws Exception {
        ResultT resultT = new ResultT();
        //查询需要对账的账户
        WhefssAccRepealDO entity = new WhefssAccRepealDO();
        entity.setTime(chkDetailDO.getChkDt());
        entity.setAccountNum(chkDetailDO.getAccountNum());
        List<WhefssAccRepealDO> list = accRepealMapper.selectChk(entity);
        String dateStr = DateUtil.getTimestamp();
        for (int i = 0; i < list.size(); i++) {
            WhefssChkDetailJnlDO chkDetailJnlDO = new WhefssChkDetailJnlDO();
            chkDetailJnlDO.setPkId(dateStr + YGPubUtil.getUniqueNo(bizCtx, WhefssConstant.SEQ_WHEFSS_CHK_DETAIL_JNL, 8));
            chkDetailJnlDO.setFkDetailId(chkDetailDO.getPkId());
            chkDetailJnlDO.setFkTotalId(chkDetailDO.getFkId());
            chkDetailJnlDO.setChkDt(chkDetailDO.getChkDt());
            chkDetailJnlDO.setAccountNum(chkDetailDO.getAccountNum());
            chkDetailJnlDO.setDataType(WhefssConstant.DATA_TYPE_9);
            chkDetailJnlDO.setAmount(list.get(i).getAmount());
            chkDetailJnlDO.setNoticeNum(list.get(i).getNoticeNum());
            chkDetailJnlDO.setSn(chkDetailDO.getChkDt() + list.get(i).getSn());
            chkDetailJnlDO.setTime(DateUtil.formatSdfToSdt(list.get(i).getTime()));
            if (!insertDetailJnl(bizCtx, chkDetailJnlDO)) {
                resultT.setMsgCod(CommonMessageCode.CHK_INSERT_DETAIL_JNL_ERROR);
                return resultT;
            }
            WhefssAccRepealDO chk = new WhefssAccRepealDO();
            chk.setBatchNum(chkDetailDO.getPkId());
            chk.setNoticeNum(list.get(i).getNoticeNum());
            int a = accRepealMapper.updateByPrimaryKey(chk);
            if (a <= 0) {
                resultT.setMsgCod(CommonMessageCode.CHK_UPDATE_BATCH_NUM_ERROR);
                return resultT;
            }
        }

        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }

    //冻结解冻
    public ResultT chkFundsInfo(YGBizMessageContext bizCtx, WhefssChkDetailDO chkDetailDO) throws Exception {
        ResultT resultT = new ResultT();
        //查询需要对账的账户
        WhefssAmpTranidDO entity = new WhefssAmpTranidDO();
        entity.setTime(chkDetailDO.getChkDt());
        entity.setAccountNum(chkDetailDO.getAccountNum());
        List<WhefssAmpTranidDO> list = ampTranidMapper.selectChk(entity);
        String dateStr = DateUtil.getTimestamp();
        for (int i = 0; i < list.size(); i++) {
            WhefssChkDetailJnlDO chkDetailJnlDO = new WhefssChkDetailJnlDO();
            chkDetailJnlDO.setPkId(dateStr + YGPubUtil.getUniqueNo(bizCtx, WhefssConstant.SEQ_WHEFSS_CHK_DETAIL_JNL, 8));
            chkDetailJnlDO.setFkDetailId(chkDetailDO.getPkId());
            chkDetailJnlDO.setFkTotalId(chkDetailDO.getFkId());
            chkDetailJnlDO.setChkDt(chkDetailDO.getChkDt());
            chkDetailJnlDO.setAccountNum(chkDetailDO.getAccountNum());

            if (WhefssConstant.BUS_TYPE_6.equals(list.get(i).getFgType())) {
                chkDetailJnlDO.setDataType(WhefssConstant.DATA_TYPE_6);
            } else if (WhefssConstant.BUS_TYPE_1.equals(list.get(i).getBustype())) {
                chkDetailJnlDO.setDataType(WhefssConstant.DATA_TYPE_12);
            } else if (WhefssConstant.BUS_TYPE_2.equals(list.get(i).getBustype())) {
                chkDetailJnlDO.setDataType(WhefssConstant.DATA_TYPE_13);
            } else {
                resultT.setMsgCod(CommonMessageCode.FAIL);
                return resultT;
            }
            chkDetailJnlDO.setAmount(list.get(i).getAmount());
            chkDetailJnlDO.setNoticeNum(list.get(i).getNoticeNum());
            chkDetailJnlDO.setSn(chkDetailDO.getChkDt() + list.get(i).getSn());
            chkDetailJnlDO.setTime(DateUtil.formatSdfToSdt(list.get(i).getTime()));
            if (!insertDetailJnl(bizCtx, chkDetailJnlDO)) {
                resultT.setMsgCod(CommonMessageCode.CHK_INSERT_DETAIL_JNL_ERROR);
                return resultT;
            }
            WhefssAmpTranidDO chk = new WhefssAmpTranidDO();
            chk.setBatchNum(chkDetailDO.getPkId());
            chk.setAmpTranid(list.get(i).getAmpTranid());
            int a = ampTranidMapper.updateByPrimaryKey(chk);
            if (a <= 0) {
                resultT.setMsgCod(CommonMessageCode.CHK_UPDATE_BATCH_NUM_ERROR);
                return resultT;
            }
        }

        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }

    //年费、利息
    public ResultT chkInterest(YGBizMessageContext bizCtx, WhefssChkDetailDO chkDetailDO) throws Exception {
        ResultT resultT = new ResultT();
        TTransJnlDO tTransJnlDO = new TTransJnlDO();
        tTransJnlDO.setSvsAcNo(chkDetailDO.getAccountNum());
        tTransJnlDO.setTranDate(chkDetailDO.getChkDt());
        List<TTransJnlDO> listTrans = transJnlMapper.selectChkInterest(tTransJnlDO);
        for (int i = 0; i < listTrans.size(); i++) {
            WhefssInterestSndDO interestSndDO = new WhefssInterestSndDO();
            //查询是否登记过
            interestSndDO.setSn(listTrans.get(i).getBnkNumber());
            interestSndDO.setAccountNum(chkDetailDO.getAccountNum());
            WhefssInterestSndDO result = interestSndMapper.selectByEntity(interestSndDO);
            interestSndDO.setSendStatus(WhefssConstant.SEND_STATUS_00);
            int j = 0;
            if (result != null) {
                j = interestSndMapper.updateSts(interestSndDO);
            } else {
                interestSndDO.setTime(listTrans.get(i).getExecTime());
                interestSndDO.setBankCode(WhefssConstant.BANK_CODE);
                interestSndDO.setInterest(listTrans.get(i).getTranAmt());
                interestSndDO.setSendStatus(WhefssConstant.SEND_STATUS_00);
                interestSndDO.setType(WhefssConstant.BUS_TYPE_1);
                j = interestSndMapper.insertSelective(interestSndDO);
            }
            if (j <= 0) {
                resultT.setMsgCod(CommonMessageCode.CHK_INSERT_AJDK_ERROR);
                return resultT;
            }
        }

        //查询需要对账的账户
        WhefssInterestSndDO entity = new WhefssInterestSndDO();
        entity.setTime(chkDetailDO.getChkDt());
        entity.setAccountNum(chkDetailDO.getAccountNum());
        List<WhefssInterestSndDO> list = interestSndMapper.selectChk(entity);
        String dateStr = DateUtil.getTimestamp();
        for (int i = 0; i < list.size(); i++) {
            WhefssChkDetailJnlDO chkDetailJnlDO = new WhefssChkDetailJnlDO();
            chkDetailJnlDO.setPkId(dateStr + YGPubUtil.getUniqueNo(bizCtx, WhefssConstant.SEQ_WHEFSS_CHK_DETAIL_JNL, 8));
            chkDetailJnlDO.setFkDetailId(chkDetailDO.getPkId());
            chkDetailJnlDO.setFkTotalId(chkDetailDO.getFkId());
            chkDetailJnlDO.setChkDt(chkDetailDO.getChkDt());
            chkDetailJnlDO.setAccountNum(chkDetailDO.getAccountNum());

            if (WhefssConstant.BUS_TYPE_1.equals(list.get(i).getType())) {
                chkDetailJnlDO.setDataType(WhefssConstant.DATA_TYPE_4);
            } else if (WhefssConstant.BUS_TYPE_2.equals(list.get(i).getType())) {
                chkDetailJnlDO.setDataType(WhefssConstant.DATA_TYPE_5);
            } else {
                resultT.setMsgCod(CommonMessageCode.FAIL);
                return resultT;
            }
            chkDetailJnlDO.setSn(chkDetailDO.getChkDt() + list.get(i).getSn());
            chkDetailJnlDO.setTime(DateUtil.formatSdfToSdt(list.get(i).getTime()));
            if (!insertDetailJnl(bizCtx, chkDetailJnlDO)) {
                resultT.setMsgCod(CommonMessageCode.CHK_INSERT_DETAIL_JNL_ERROR);
                return resultT;
            }
            WhefssInterestSndDO chk = new WhefssInterestSndDO();
            chk.setBatchNum(chkDetailDO.getPkId());
            chk.setSn(list.get(i).getSn());
            int a = interestSndMapper.updateByPrimaryKey(chk);
            if (a <= 0) {
                resultT.setMsgCod(CommonMessageCode.CHK_UPDATE_BATCH_NUM_ERROR);
                return resultT;
            }
        }
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }

    //不明入账
    public ResultT chkOtherIn(YGBizMessageContext bizCtx, WhefssChkDetailDO chkDetailDO) throws Exception {
        ResultT resultT = new ResultT();
        TTransJnlDO tTransJnlDO = new TTransJnlDO();
        tTransJnlDO.setSvsAcNo(chkDetailDO.getAccountNum());
        tTransJnlDO.setTranDate(chkDetailDO.getChkDt());
        tTransJnlDO.setRsv1(chkDetailDO.getPkId());//fk_detail_id
        tTransJnlDO.setRsv2(chkDetailDO.getFkId());//fk_total_id
        List<TTransJnlDO> listTrans = transJnlMapper.selectChkOtherIn(tTransJnlDO);
        for (int i = 0; i < listTrans.size(); i++) {
            WhefssOtherInSndDO otherInSndDO = new WhefssOtherInSndDO();
            //查询是否登记过
            otherInSndDO.setSn(listTrans.get(i).getBnkNumber());
            otherInSndDO.setAccountNum(chkDetailDO.getAccountNum());
            WhefssOtherInSndDO result = otherInSndMapper.selectByEntity(otherInSndDO);
            otherInSndDO.setSendStatus(WhefssConstant.SEND_STATUS_00);
            int j = 0;
            if (result != null) {
                j = otherInSndMapper.updateSts(otherInSndDO);
            } else {
                otherInSndDO.setTime(listTrans.get(i).getExecTime());
                otherInSndDO.setReserve1(listTrans.get(i).getNarrative());//附言是否包含放贷
                otherInSndDO.setBankCode(WhefssConstant.BANK_CODE);
                otherInSndDO.setAmount(listTrans.get(i).getTranAmt());
                otherInSndDO.setSenderName(listTrans.get(i).getOopAcctNo());
                otherInSndDO.setSenderNum(listTrans.get(i).getTfrAcctName());
                j = otherInSndMapper.insertSelective(otherInSndDO);
            }
            if (j <= 0) {
                resultT.setMsgCod(CommonMessageCode.CHK_INSERT_AJDK_ERROR);
                return resultT;
            }
        }

        WhefssOtherInSndDO entity = new WhefssOtherInSndDO();
        entity.setTime(chkDetailDO.getChkDt());
        entity.setAccountNum(chkDetailDO.getAccountNum());
        List<WhefssOtherInSndDO> list = otherInSndMapper.selectChk(entity);
        String dateStr = DateUtil.getTimestamp();
        for (int i = 0; i < list.size(); i++) {
            WhefssChkDetailJnlDO chkDetailJnlDO = new WhefssChkDetailJnlDO();
            //判断附言是否包含房贷
            if (list.get(i).getReserve1().contains(WhefssConstant.CHK_UNKNOWEN_TYPE_NAME) || list.get(i).getReserve1().contains(WhefssConstant.AJDK_CHK_HTBH)) {
                chkDetailJnlDO.setUnknownType(WhefssConstant.CHK_UNKNOWEN_TYPE_1);
            } else {
                chkDetailJnlDO.setUnknownType(WhefssConstant.CHK_UNKNOWEN_TYPE_2);
            }
            chkDetailJnlDO.setPkId(dateStr + YGPubUtil.getUniqueNo(bizCtx, WhefssConstant.SEQ_WHEFSS_CHK_DETAIL_JNL, 8));
            chkDetailJnlDO.setFkDetailId(chkDetailDO.getPkId());
            chkDetailJnlDO.setFkTotalId(chkDetailDO.getFkId());
            chkDetailJnlDO.setChkDt(chkDetailDO.getChkDt());
            chkDetailJnlDO.setAccountNum(chkDetailDO.getAccountNum());
            chkDetailJnlDO.setDataType(WhefssConstant.DATA_TYPE_3);
            chkDetailJnlDO.setSn(chkDetailDO.getChkDt() + list.get(i).getSn());
            chkDetailJnlDO.setSenderName(list.get(i).getSenderName());
            chkDetailJnlDO.setSenderNum(list.get(i).getSenderNum());
            chkDetailJnlDO.setTime(DateUtil.formatSdfToSdt(list.get(i).getTime()));
            if (!insertDetailJnl(bizCtx, chkDetailJnlDO)) {
                resultT.setMsgCod(CommonMessageCode.CHK_INSERT_DETAIL_JNL_ERROR);
                return resultT;
            }
            WhefssOtherInSndDO chk = new WhefssOtherInSndDO();
            chk.setBatchNum(chkDetailDO.getPkId());
            chk.setSn(list.get(i).getSn());
            int a = otherInSndMapper.updateByPrimaryKey(chk);
            if (a <= 0) {
                resultT.setMsgCod(CommonMessageCode.CHK_UPDATE_BATCH_NUM_ERROR);
                return resultT;
            }
        }
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }


    public boolean insertDetailJnl(YGBizMessageContext bizCtx, WhefssChkDetailJnlDO chkDetailJnlDO) {
        boolean flag = false;
        int i = chkDetailJnlMapper.insert(chkDetailJnlDO);
        if (i <= 0) {
            return flag;
        }
        flag = true;
        return flag;
    }

    //统计汇总信息
    public ResultT sumDetail(YGBizMessageContext bizCtx, WhefssChkDetailDO chkDetailJnlDO) {
        ResultT resultT = new ResultT();

        WhefssChkDetailDO qry = chkDetailJnlMapper.sumCheckDetail(chkDetailJnlDO);
        if (qry == null) {
            resultT.setMsgCod(CommonMessageCode.CHK_INSERT_DETAIL_JNL_ERROR);
            return resultT;
        }
        chkDetailJnlDO.setRzAmt(qry.getRzAmt());
        chkDetailJnlDO.setRzNum(qry.getRzNum());
        chkDetailJnlDO.setCzAmt(qry.getCzAmt());
        chkDetailJnlDO.setCzNum(qry.getCzNum());
        chkDetailJnlDO.setChkNum(qry.getChkNum());
        chkDetailJnlDO.setChkStatus(null);
        int i = chkDetailMapper.updateByPrimaryKey(chkDetailJnlDO);
        if (i <= 0) {
            resultT.setMsgCod(CommonMessageCode.CHK_INSERT_DETAIL_JNL_ERROR);
            return resultT;
        }
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }

    public ResultT sendDetail(YGBizMessageContext bizCtx, WhefssChkDetailDO chkDetailDO) {
        try {
            FundsUploadInfo fundsUploadInfo = new FundsUploadInfo();
            fundsUploadInfo.setAccount_num(chkDetailDO.getAccountNum());
            fundsUploadInfo.setBalance(chkDetailDO.getBalanceBank().toString());
            fundsUploadInfo.setTime(DateUtil.formatSdfToSdt(chkDetailDO.getChkDt() + "235959"));
            List<FundsUploadDetail> detail = chkDetailJnlMapper.selectChk(chkDetailDO);
            fundsUploadInfo.setDetail(detail);
            return whefssService.fundsUploadInfo(bizCtx, fundsUploadInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ResultT resultT = new ResultT();
            resultT.setMsgCod(CommonMessageCode.FAIL);
            return resultT;
        }
    }

    //
    public ResultT sumTotal(YGBizMessageContext bizCtx, String pkId) throws YGException {
        ResultT resultT = new ResultT();
        WhefssChkTotalDO chkTotalDO = new WhefssChkTotalDO();
        chkTotalDO.setPkId(pkId);
        WhefssChkTotalDO chkTotalSum = chkDetailMapper.selectChk(chkTotalDO);
        if (chkTotalSum == null) {
            resultT.setMsgCod(CommonMessageCode.FAIL);
        }
        chkTotalSum.setPkId(pkId);
        if (chkTotalSum.getSucChkNum().equals(chkTotalSum.getChkNum())) {
            chkTotalSum.setChkSts(WhefssConstant.CHK_TOTAL_STS_02);
            int i = chkTotalMapper.updateByPrimaryKey(chkTotalSum);
            if (i <= 0) {
                resultT.setMsgCod(CommonMessageCode.FAIL);
                return resultT;
            } else {
                YGPUBATCUtil.commitWork(bizCtx);
                resultT.setMsgCod(CommonMessageCode.SUCC);
                return resultT;
            }
        }
        resultT.setMsgCod(CommonMessageCode.CHK_FAIL);
        return resultT;
    }

    public ResultT updateJnlSts(YGBizMessageContext bizCtx, String pkId, String sts) throws YGException {
        ResultT resultT = new ResultT();
        int i = 0;
        //更新缴存信息表accInSendMapper
        WhefssAccInSendDO accInSendDO = new WhefssAccInSendDO();
        accInSendDO.setBatchNum(pkId);
        accInSendDO.setSendStatus(sts);
        i = accInSendMapper.updateByBatchNum(accInSendDO);
        if (i < 0) {
            resultT.setMsgCod(CommonMessageCode.CHK_UPDATE_SEND_STS_ERROR);
            return resultT;
        }

        //更新资金拨付表
        WhefssAccOutSendDO accOutSendDO = new WhefssAccOutSendDO();
        accOutSendDO.setBatchNum(pkId);
        accOutSendDO.setSendStatus(sts);
        i = accOutSendMapper.updateByBatchNum(accOutSendDO);
        if (i < 0) {
            resultT.setMsgCod(CommonMessageCode.CHK_UPDATE_SEND_STS_ERROR);
            return resultT;
        }
        //更新冲正流水表
        WhefssAccRepealDO accRepealDO = new WhefssAccRepealDO();
        accRepealDO.setBatchNum(pkId);
        accRepealDO.setSendStatus(sts);
        i = accRepealMapper.updateByBatchNum(accRepealDO);
        if (i < 0) {
            resultT.setMsgCod(CommonMessageCode.CHK_UPDATE_SEND_STS_ERROR);
            return resultT;
        }
        //更新退款流水表 accRefundMapper
        WhefssAccRefundDO accRefundDO = new WhefssAccRefundDO();
        accRefundDO.setBatchNum(pkId);
        accRefundDO.setSendStatus(sts);
        i = accRefundMapper.updateByBatchNum(accRefundDO);
        if (i < 0) {
            resultT.setMsgCod(CommonMessageCode.CHK_UPDATE_SEND_STS_ERROR);
            return resultT;
        }
        //更新pos入账表 posInSendMapper
        WhefssAccPosInSendDO accPosInSendDO = new WhefssAccPosInSendDO();
        accPosInSendDO.setBatchNum(pkId);
        accPosInSendDO.setSendStatus(sts);
        i = posInSendMapper.updateByBatchNum(accPosInSendDO);
        if (i < 0) {
            resultT.setMsgCod(CommonMessageCode.CHK_UPDATE_SEND_STS_ERROR);
            return resultT;
        }
        //更新按揭贷款流水表 ajdkSndMapper
        WhefssAjdkSndDO ajdkSndDO = new WhefssAjdkSndDO();
        ajdkSndDO.setBatchNum(pkId);
        ajdkSndDO.setSendStatus(sts);
        i = ajdkSndMapper.updateByBatchNum(ajdkSndDO);
        if (i < 0) {
            resultT.setMsgCod(CommonMessageCode.CHK_UPDATE_SEND_STS_ERROR);
            return resultT;
        }
        //更新年费、利息流水表 interestSndMapper
        WhefssInterestSndDO interestSndDO = new WhefssInterestSndDO();
        interestSndDO.setBatchNum(pkId);
        interestSndDO.setSendStatus(sts);
        i = interestSndMapper.updateByBatchNum(interestSndDO);
        if (i < 0) {
            resultT.setMsgCod(CommonMessageCode.CHK_UPDATE_SEND_STS_ERROR);
            return resultT;
        }
        //更新冻结解冻流水表 ampTranidMapper
        WhefssAmpTranidDO ampTranidDO = new WhefssAmpTranidDO();
        ampTranidDO.setBatchNum(pkId);
        ampTranidDO.setSendStatus(sts);
        i = ampTranidMapper.updateByBatchNum(ampTranidDO);
        if (i < 0) {
            resultT.setMsgCod(CommonMessageCode.CHK_UPDATE_SEND_STS_ERROR);
            return resultT;
        }
        //更新不明入账流水表 otherInSndMapper
        WhefssOtherInSndDO otherInSndDO = new WhefssOtherInSndDO();
        otherInSndDO.setBatchNum(pkId);
        otherInSndDO.setSendStatus(sts);
        i = otherInSndMapper.updateByBatchNum(otherInSndDO);
        if (i < 0) {
            resultT.setMsgCod(CommonMessageCode.CHK_UPDATE_SEND_STS_ERROR);
            return resultT;
        }
        YGPUBATCUtil.commitWork(bizCtx);
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }
}
