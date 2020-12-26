package com.murong.ecp.netpay.whefss.web.service.server;


import com.murong.ecp.netpay.whefss.web.common.utils.CommonMessageCode;
import com.murong.ecp.netpay.whefss.web.common.utils.DateUtil;
import com.murong.ecp.netpay.whefss.web.common.utils.ResultT;
import com.murong.ecp.netpay.whefss.web.common.utils.WhefssConstant;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.WhefssAccOpenSndMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAccOpenSndDO;
import com.murong.ecp.netpay.whefss.web.service.server.bo.*;
import com.yuangou.ecp.bp.comp.pubatc.YGPUBATCUtil;
import com.yuangou.ecp.bp.core.common.message.YGBizMessage;
import com.yuangou.ecp.bp.core.common.message.YGBizMessageContext;
import com.yuangou.ecp.bp.core.common.message.YGEDB;
import com.yuangou.ecp.bp.core.common.message.YGEDBUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("whefssService")
public class WhefssServiceImpl implements WhefssService {
    private String whefssService = "whefsstpc1";

    @Autowired
    private WhefssAccOpenSndMapper whefssAccOpenSndMapper;

    @Override
    public ResultT accountsOpen(YGBizMessageContext bizCtx, String noticeNum) {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        ResultT resultT = new ResultT();
        edb.setData("method", WhefssConstant.HTTP_METHOD_GET);
        edb.setData("url", WhefssConstant.ACCOUNTS_OPEN_URL);
        edb.setData("param", noticeNum);
        AccountsOpen accountsOpen = new AccountsOpen();
        try {
            int i = YGPUBATCUtil.callThirdComm(bizCtx, "accountsOpen", whefssService);
            if (i != 0) {
                resultT.setMsgCod(CommonMessageCode.FAIL);
                return resultT;
            }
            accountsOpen.setStatus(edb.getData("status"));
            accountsOpen.setError(edb.getData("error"));
            accountsOpen.setNoticeNum(edb.getData("notice_num"));
            accountsOpen.setMonitorNum(edb.getData("monitor_num"));
            accountsOpen.setBankName(edb.getData("bank_name"));
            accountsOpen.setBranchName(edb.getData("branch_name"));
            accountsOpen.setBranchNum(edb.getData("branch_num"));
            accountsOpen.setCorpName(edb.getData("corp_name"));
            accountsOpen.setAccountName(edb.getData("account_name"));
            accountsOpen.setProjectName(edb.getData("project_name"));
            accountsOpen.setMonitorRange(edb.getData("monitor_range"));
        } catch (Exception e) {
            resultT.setMsgCod(CommonMessageCode.FAIL);
            e.printStackTrace();
            return resultT;
        }
        resultT.setData(accountsOpen);
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }

    @Override
    public ResultT accountsOpenNotify(YGBizMessageContext bizCtx, AccountOpenNotify accountOpenNotify) {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        ResultT resultT = new ResultT();
        edb.setData("method", WhefssConstant.HTTP_METHOD_POST);
        edb.setData("url", WhefssConstant.ACCOUNTS_OPEN_NOTIFY_URL);
        edb.setData("param", accountOpenNotify.getNoticeNum());
/*        edb.setData("notice_num", accountOpenNotify.getNoticeNum());
        edb.setData("monitor_num", accountOpenNotify.getMonitorNum());
        edb.setData("account_name", accountOpenNotify.getAccountName());
        edb.setData("account_num", accountOpenNotify.getAccountNum());
        edb.setData("sn", accountOpenNotify.getSn());*/
        JSONObject json = new JSONObject();
        json.put("notice_num", accountOpenNotify.getNoticeNum());
        json.put("monitor_num", accountOpenNotify.getMonitorNum());
        json.put("account_name", accountOpenNotify.getAccountName());
        json.put("account_num", accountOpenNotify.getAccountNum());
        json.put("sn", accountOpenNotify.getSn());

        RespondHead respondHead = new RespondHead();
        try {
            edb.setData("", DateUtil.formatSdfToSdt(accountOpenNotify.getTime()));
            json.put("time", DateUtil.formatSdfToSdt(accountOpenNotify.getTime()));
            edb.setData("data", json.toString());
            WhefssAccOpenSndDO whefssAccOpenSndDO = new WhefssAccOpenSndDO();
            BeanUtils.copyProperties(accountOpenNotify, whefssAccOpenSndDO);
            whefssAccOpenSndDO.setSendStatus("00");
            whefssAccOpenSndDO.setBankCode(WhefssConstant.BANK_CODE);
            WhefssAccOpenSndDO whefssAccOpenSndQry = whefssAccOpenSndMapper.selectByPrimaryKey(whefssAccOpenSndDO);
            int j = 0;
            if (whefssAccOpenSndQry != null) {
                j = whefssAccOpenSndMapper.updateByPrimaryKey(whefssAccOpenSndDO);
            } else {
                j = whefssAccOpenSndMapper.insert(whefssAccOpenSndDO);
            }

            if (j <= 0) {
                resultT.setMsgCod(CommonMessageCode.FAIL);
                return resultT;
            }
            int i = YGPUBATCUtil.callThirdComm(bizCtx, "accountsOpenNotify", whefssService);
            if (i != 0) {
                resultT.setMsgCod(CommonMessageCode.FAIL);
                return resultT;
            }
            WhefssAccOpenSndDO whefssAccOpenSndResult = new WhefssAccOpenSndDO();
            whefssAccOpenSndResult.setMonitorNum(whefssAccOpenSndDO.getMonitorNum());
            respondHead.setStatus(edb.getData("status"));
            respondHead.setError(edb.getData("error"));
            if (WhefssConstant.HTTP_STATUS.equals(respondHead.getStatus())) {
                whefssAccOpenSndResult.setSendStatus("01");
                whefssAccOpenSndResult.setRespMsg("交易成功");
            } else {
                whefssAccOpenSndResult.setSendStatus("02");
                whefssAccOpenSndResult.setRespMsg(respondHead.getError());
            }
            whefssAccOpenSndResult.setRespCode(respondHead.getStatus());
            j = whefssAccOpenSndMapper.updateByPrimaryKey(whefssAccOpenSndResult);
        } catch (Exception e) {
            resultT.setMsgCod(CommonMessageCode.FAIL);
            e.printStackTrace();
            return resultT;
        }
        resultT.setData(respondHead);
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }

    @Override
    public ResultT accountsRemove(YGBizMessageContext bizCtx, String noticeNum) {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        ResultT resultT = new ResultT();
        edb.setData("method", WhefssConstant.HTTP_METHOD_GET);
        edb.setData("url", WhefssConstant.ACCOUNTS_REMOVE_URL);
        edb.setData("param", noticeNum);
        AccountsRemove accountsRemove = new AccountsRemove();
        try {
            int i = YGPUBATCUtil.callThirdComm(bizCtx, "accountsRemove", whefssService);
            if (i != 0) {
                resultT.setMsgCod(CommonMessageCode.FAIL);
                return resultT;
            }
            accountsRemove.setStatus(edb.getData("status"));
            accountsRemove.setError(edb.getData("error"));
            accountsRemove.setNoticeNum(edb.getData("notice_num"));
            accountsRemove.setMonitorNum(edb.getData("monitor_num"));
            accountsRemove.setBankName(edb.getData("bank_name"));
            accountsRemove.setBranchName(edb.getData("branch_name"));
            accountsRemove.setAccountNum(edb.getData("account_num"));
        } catch (Exception e) {
            resultT.setMsgCod(CommonMessageCode.FAIL);
            e.printStackTrace();
            return resultT;
        }
        resultT.setData(accountsRemove);
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }

    @Override
    public ResultT contractsIncome(YGBizMessageContext bizCtx, String noticeNum) {
        ContractsIncome contractsIncome = new ContractsIncome();
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        ResultT resultT = new ResultT();
        edb.setData("method", WhefssConstant.HTTP_METHOD_GET);
        edb.setData("url", WhefssConstant.CONTRACTS_INCOME_URL);
        edb.setData("param", noticeNum);
        try {
            int i = YGPUBATCUtil.callThirdComm(bizCtx, "contractsIncome", whefssService);
            if (i != 0) {
                resultT.setMsgCod(CommonMessageCode.FAIL);
                return resultT;
            }
            contractsIncome.setStatus(edb.getData("status"));
            contractsIncome.setError(edb.getData("error"));
            contractsIncome.setNoticeNum(edb.getData("notice_num"));
            contractsIncome.setMonitor_num(edb.getData("monitor_num"));
            contractsIncome.setBuyers(edb.getData("buyers"));
            contractsIncome.setIdType(edb.getData("id_type"));
            contractsIncome.setIdNum(edb.getData("id_num"));
            contractsIncome.setBankName(edb.getData("bank_name"));
            contractsIncome.setAccountName(edb.getData("account_name"));
            contractsIncome.setAccountNum(edb.getData("account_num"));
            contractsIncome.setTotalPrice(edb.getData("total_price"));
            contractsIncome.setFirstPrice(edb.getData("first_price"));
        } catch (Exception e) {
            resultT.setMsgCod(CommonMessageCode.FAIL);
            e.printStackTrace();
            return resultT;
        }
        resultT.setData(contractsIncome);
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }

    @Override
    public ResultT fundsCorrect(YGBizMessageContext bizCtx, String noticeNum) {
        FundsCorrect fundsCorrect = new FundsCorrect();
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        ResultT resultT = new ResultT();
        edb.setData("method", WhefssConstant.HTTP_METHOD_GET);
        edb.setData("url", WhefssConstant.FUNDS_CORRECT_URL);
        edb.setData("param", noticeNum);
        try {
            int i = YGPUBATCUtil.callThirdComm(bizCtx, "fundsCorrect", whefssService);
            if (i != 0) {
                resultT.setMsgCod(CommonMessageCode.FAIL);
            }
            fundsCorrect.setStatus(edb.getData("status"));
            fundsCorrect.setError(edb.getData("error"));
            fundsCorrect.setNoticeNum(edb.getData("notice_num"));
            fundsCorrect.setContractNum(edb.getData("contract_num"));
            fundsCorrect.setBankName(edb.getData("bank_name"));
            fundsCorrect.setBranchName(edb.getData("branch_name"));
            fundsCorrect.setAccountName(edb.getData("account_name"));
            fundsCorrect.setAccountNum(edb.getData("account_num"));
            fundsCorrect.setAmount(edb.getData("amount"));
            fundsCorrect.setOrgSn(edb.getData("sn"));
            fundsCorrect.setOrgTime(DateUtil.formatSdtToSdf(edb.getData("time")));

        } catch (Exception e) {
            resultT.setMsgCod(CommonMessageCode.FAIL);
            e.printStackTrace();
            return resultT;
        }
        resultT.setData(fundsCorrect);
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }

    @Override
    public ResultT fundsAppropriate(YGBizMessageContext bizCtx, String noticeNum) {
        FundsAppropriate fundsAppropriate = new FundsAppropriate();
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        ResultT resultT = new ResultT();
        edb.setData("method", WhefssConstant.HTTP_METHOD_GET);
        edb.setData("url", WhefssConstant.FUNDS_APPROPRIATE_URL);
        edb.setData("param", noticeNum);
        try {
            int i = YGPUBATCUtil.callThirdComm(bizCtx, "fundsAppropriate", whefssService);
            if (i != 0) {
                resultT.setMsgCod(CommonMessageCode.FAIL);
                return resultT;
            }
            fundsAppropriate.setStatus(edb.getData("status"));
            fundsAppropriate.setError(edb.getData("error"));
            fundsAppropriate.setNoticeNum(edb.getData("notice_num"));
            fundsAppropriate.setBankName(edb.getData("bank_name"));
            fundsAppropriate.setBranchName(edb.getData("branch_name"));
            fundsAppropriate.setAccountName(edb.getData("account_name"));
            fundsAppropriate.setAccountNum(edb.getData("account_num"));
            fundsAppropriate.setBankTo(edb.getData("bank_to"));
            fundsAppropriate.setNameTo(edb.getData("name_to"));
            fundsAppropriate.setAccountTo(edb.getData("account_to"));
            fundsAppropriate.setBranchTo(edb.getData("branch_to"));
            fundsAppropriate.setAmount(edb.getData("amount"));
        } catch (Exception e) {
            resultT.setMsgCod(CommonMessageCode.FAIL);
            e.printStackTrace();
            return resultT;
        }
        resultT.setData(fundsAppropriate);
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }

    @Override
    public ResultT fundsRefund(YGBizMessageContext bizCtx, String noticeNum) {
        FundsRefund fundsRefund = new FundsRefund();
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        ResultT resultT = new ResultT();
        edb.setData("method", WhefssConstant.HTTP_METHOD_GET);
        edb.setData("url", WhefssConstant.FUNDS_REFUND_URL);
        edb.setData("param", noticeNum);
        try {
            int i = YGPUBATCUtil.callThirdComm(bizCtx, "fundsRefund", whefssService);
            if (i != 0) {
                resultT.setMsgCod(CommonMessageCode.FAIL);
                return resultT;
            }
            fundsRefund.setStatus(edb.getData("status"));
            fundsRefund.setError(edb.getData("error"));
            fundsRefund.setNoticeNum(edb.getData("notice_num"));

            fundsRefund.setContractNum(edb.getData("contract_num"));
            fundsRefund.setSnoticeNum(edb.getData("contract_num"));
            fundsRefund.setBuyers(edb.getData("buyers"));
            fundsRefund.setIdType(edb.getData("id_type"));
            fundsRefund.setIdNum(edb.getData("id_num"));
            fundsRefund.setBankName(edb.getData("bank_name"));

            fundsRefund.setAccountName(edb.getData("account_name"));
            fundsRefund.setAccountNum(edb.getData("account_num"));
            fundsRefund.setTotalPrice(edb.getData("total_price"));
            fundsRefund.setRefundPrice(edb.getData("refund_price"));
            fundsRefund.setCorpAccountName(edb.getData("corp_account_name"));

            fundsRefund.setCorpAccountNum(edb.getData("corp_account_num"));
            fundsRefund.setCorpAccountBank(edb.getData("corp_account_bank"));
            fundsRefund.setCorpAccountBranchNum(edb.getData("corp_account_branch_num"));

        } catch (Exception e) {
            resultT.setMsgCod(CommonMessageCode.FAIL);
            e.printStackTrace();
            return resultT;
        }
        resultT.setData(fundsRefund);
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }

    @Override
    public ResultT fundsDetail(YGBizMessageContext bizCtx, String accountNum, String startTime, String endTime, int pageIndex, int pageSize) {

        FundsDetailList fundsDetailList = new FundsDetailList();
        List<FundsDetail> list = new ArrayList<>();
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        ResultT resultT = new ResultT();
        edb.setData("method", WhefssConstant.HTTP_METHOD_POST);
        edb.setData("url", WhefssConstant.FUNDS_DETAIL_URL);
        edb.setData("param", accountNum);
        edb.setData("account_num", accountNum);


        edb.setData("pageindex", String.valueOf(pageIndex));
        edb.setData("pagesize", String.valueOf(pageSize));
        try {
            edb.setData("start_time", DateUtil.formatSdfToSdt(startTime));
            edb.setData("end_time", DateUtil.formatSdfToSdt(endTime));

            int i = YGPUBATCUtil.callThirdComm(bizCtx, "fundsDetail", whefssService);
            if (i != 0) {
                resultT.setMsgCod(CommonMessageCode.FAIL);
                return resultT;
            }
            fundsDetailList.setStatus(edb.getData("status"));
            fundsDetailList.setError(edb.getData("error"));
            int total = Integer.parseInt(edb.getData("total"));
            fundsDetailList.setTotal(total);
            List<YGEDB> edbList = edb.getChildNode("qryData").getChildNodes();
            for (YGEDB qryedb : edbList) {
                FundsDetail fundsDetail = new FundsDetail();
                fundsDetail.setSn(qryedb.getData("sn"));
                fundsDetail.setNoticeNum(qryedb.getData("notice_num"));
                fundsDetail.setAmount(qryedb.getData("amount"));
                fundsDetail.setTime(DateUtil.formatSdtToSdf(qryedb.getData("time")));
                fundsDetail.setType(qryedb.getData("type"));
                fundsDetail.setState(qryedb.getData("state"));
                list.add(fundsDetail);
            }
            fundsDetailList.setFundsDetail(list);
        } catch (Exception e) {
            resultT.setMsgCod(CommonMessageCode.FAIL);
            e.printStackTrace();
            return resultT;

        }
        resultT.setData(fundsDetailList);
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }

    @Override
    public ResultT fundsUploadInfo(YGBizMessageContext bizCtx, FundsUploadInfo fundsUploadInfo) {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        //YGEDB edb = bizMsg.getEDBBody();
        RespondHead respondHead = new RespondHead();
        ResultT resultT = new ResultT();
        YGEDB edblist = YGEDBUtils.fromJSON(JSONObject.fromObject(fundsUploadInfo));
        edblist.setData("method", WhefssConstant.HTTP_METHOD_POST);
        edblist.setData("url", WhefssConstant.FUNDS_UPLOADINFO_URL);
        edblist.setData("param", fundsUploadInfo.getAccount_num());
        bizMsg.setBody(edblist);
        JSONObject data = new JSONObject();
        data.put("balance", fundsUploadInfo.getBalance());
        data.put("time", fundsUploadInfo.getTime());
        data.put("account_num", fundsUploadInfo.getAccount_num());
        //JSONArray jsonArray = new JSONArray();
        List<JSONObject> jsonArray  = new ArrayList<>();
        List<FundsUploadDetail> list = fundsUploadInfo.getDetail();
        for (int i = 0; i < list.size(); i++) {
            JSONObject temp = new JSONObject();
            temp.put("data_type",list.get(i).getDataType());
            temp.put("amount",list.get(i).getAmount());
            temp.put("commission",list.get(i).getCommission());
            temp.put("notice_num",list.get(i).getNoticeNum());
            temp.put("sn",list.get(i).getSn());
            temp.put("time",list.get(i).getTime());
            temp.put("sender_name",list.get(i).getSenderName());
            temp.put("sender_num",list.get(i).getSenderNum());
            temp.put("note",list.get(i).getNote());
            temp.put("unknown_type",list.get(i).getUnknownType());
            jsonArray.add(temp);
        }
        data.put("detail",jsonArray);
        edblist.setData("data", data.toString());
        try {
            int i = YGPUBATCUtil.callThirdComm(bizCtx, "fundsUploadInfo", whefssService);
            if (i != 0) {
                resultT.setMsgCod(CommonMessageCode.FAIL);
                return resultT;
            }
            YGEDB edb = bizMsg.getEDBBody();
            respondHead.setStatus(edb.getData("status"));
            respondHead.setError(edb.getData("error"));
        } catch (Exception e) {
            resultT.setMsgCod(CommonMessageCode.FAIL);
            e.printStackTrace();
            return resultT;
        }
        resultT.setData(respondHead);
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }

    @Override
    public ResultT contractsLoanInfo(YGBizMessageContext bizCtx, String contractNum) {
        ContractsLoanInfo contractsLoanInfo = new ContractsLoanInfo();
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        ResultT resultT = new ResultT();
        edb.setData("method", WhefssConstant.HTTP_METHOD_GET);
        edb.setData("url", WhefssConstant.CONTRACTS_LOANINFO_URL);
        edb.setData("param", contractNum);
        try {
            int i = YGPUBATCUtil.callThirdComm(bizCtx, "contractsLoanInfo", whefssService);
            if (i != 0) {
                resultT.setMsgCod(CommonMessageCode.FAIL);
            }
            contractsLoanInfo.setStatus(edb.getData("status"));
            contractsLoanInfo.setError(edb.getData("error"));
            contractsLoanInfo.setNoticeNum(edb.getData("notice_num"));
            contractsLoanInfo.setBuyer(edb.getData("buyer"));
            contractsLoanInfo.setIdType(edb.getData("id_type"));
            contractsLoanInfo.setIdNum(edb.getData("id_num"));
            contractsLoanInfo.setPriceSum(edb.getData("price_sum"));
            contractsLoanInfo.setFirstMoney(edb.getData("first_money"));
            contractsLoanInfo.setFirstPaied(edb.getData("first_paied"));
            contractsLoanInfo.setAccountName(edb.getData("account_name"));
            contractsLoanInfo.setAccountNum(edb.getData("account_num"));
            contractsLoanInfo.setArea(edb.getData("area"));
        } catch (Exception e) {
            resultT.setMsgCod(CommonMessageCode.FAIL);
            e.printStackTrace();
            return resultT;
        }
        resultT.setData(contractsLoanInfo);
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }


    @Override
    public ResultT fundsBalance(YGBizMessageContext bizCtx, String accountNum) {
        FundsBalance fundsBalance = new FundsBalance();
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        YGEDB edb = bizMsg.getEDBBody();
        ResultT resultT = new ResultT();
        edb.setData("method", WhefssConstant.HTTP_METHOD_GET);
        edb.setData("url", WhefssConstant.FUNDS_BALANCE_URL);
        edb.setData("param", accountNum);
        try {
            int i = YGPUBATCUtil.callThirdComm(bizCtx, "fundsBalance", whefssService);
            if (i != 0) {
                resultT.setMsgCod(CommonMessageCode.FAIL);
            }
            fundsBalance.setStatus(edb.getData("status"));
            fundsBalance.setError(edb.getData("error"));
            fundsBalance.setMonitorNum(edb.getData("monitor_num"));
            fundsBalance.setAccountName(edb.getData("account_name"));
            fundsBalance.setAccountNum(edb.getData("account_num"));
            fundsBalance.setBalanceBank(edb.getData("balance_bank"));
            fundsBalance.setBalanceSystem(edb.getData("balance_system"));
            fundsBalance.setBalanceTime(edb.getData("balance_time"));
            fundsBalance.setAccountState(edb.getData("account_state"));

        } catch (Exception e) {
            resultT.setMsgCod(CommonMessageCode.FAIL);
            e.printStackTrace();
        }
        resultT.setData(fundsBalance);
        resultT.setMsgCod(CommonMessageCode.SUCC);
        return resultT;
    }

    public static void main(String[] args) {
        FundsUploadInfo fundsUploadInfo = new FundsUploadInfo();

        List<FundsUploadDetail> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            FundsUploadDetail temp = new FundsUploadDetail();
            temp.setAmount(String.valueOf(i));
            temp.setCommission(String.valueOf(i));
            temp.setDataType(String.valueOf(i));
            list.add(temp);
        }
        fundsUploadInfo.setDetail(list);

        YGEDB edblist = YGEDBUtils.fromJSON(JSONObject.fromObject(fundsUploadInfo));
        System.out.println(edblist.toString());
    }
}
