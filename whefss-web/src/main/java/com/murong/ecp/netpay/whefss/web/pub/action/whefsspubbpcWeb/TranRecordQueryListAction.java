package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;

import com.alibaba.fastjson.JSONObject;
import com.murong.ecp.netpay.whefss.web.common.utils.*;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.*;
import com.murong.ecp.netpay.whefss.web.pub.model.TranRecordReqBO;
import com.murong.ecp.netpay.whefss.web.pub.model.TranRecordRspBO;
import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;
import com.yuangou.ecp.bp.core.common.exception.YGException;
import com.yuangou.ecp.bp.core.common.message.YGBizMessage;
import com.yuangou.ecp.bp.core.common.message.YGBizMessageContext;
import com.yuangou.ecp.bp.core.common.message.YGEDB;
import com.yuangou.ecp.bp.core.common.yglog4j.Logger;
import com.yuangou.ecp.bp.core.common.yglog4j.YGLogger;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanglei
 * 交易记录查询
 * @version Id: accInfoQueryList.java, v 0.1 2020/11/4 18:52 tinalp Exp $$
 */
@Service("tranRecordQueryList")
@Slf4j
public class TranRecordQueryListAction extends BaseAction {

    //资金缴存
    @Autowired
    private WhefssAccInSendMapper whefssAccInSendMapper;

    //POS进账
    @Autowired
    private WhefssAccPosInSendMapper whefssAccPosInSendMapper;

    //资金拨付/拨付失败查询
    @Autowired
    private WhefssAccOutSendMapper whefssAccOutSendMapper;

    //冲正查询
    @Autowired
    private WhefssAccRepealMapper whefssAccRepealMapper;

    //退款查询/退款失败查询
    @Autowired
    private WhefssAccRefundMapper whefssAccRefundMapper;

    //不明进账查询
    @Autowired
    private WhefssOtherInSndMapper whefssOtherInSndMapper;

    //账户年费利息查询
    @Autowired
    private WhefssInterestSndMapper whefssInterestSndMapper;

    //按揭贷款查询
    @Autowired
    private WhefssAjdkSndMapper whefssAjdkSndMapper;



    /**
     * 分页查询
     * @param bizCtx 上下文
     * @param reqBO 请求对象
     * @return
     * @throws YGException
     */
    public PageResultT<TranRecordRspBO> execute(YGBizMessageContext bizCtx, TranRecordReqBO reqBO) throws YGException {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        Logger msgLog = YGLogger.getLogger(bizMsg);
        YGEDB root = bizMsg.getEDBBody();
        msgLog.info("list, reqBO=" + JSONObject.toJSON(reqBO));
        ValidatorUtil.validateObject(reqBO);
        YGPageEntity pageEntity = YGPageEntity.createPageEntity(root);
        pageEntity.setTot_rec_num(NumberUtils.toInt(root.getData("tot_rec_num")));
        PageResultT resultT = new PageResultT();
        resultT.setPageEntity(pageEntity);
        //执行查询司法操作信息
        List<TranRecordRspBO> list = null;
        String tranType = reqBO.getTranType();
        switch (tranType){
            case "1":
                //收取房款
                list = whefssAccInSendMapper.tranRecordPage(pageEntity,reqBO);
                break;
            case "2":
                //POS进账
                list = whefssAccPosInSendMapper.tranRecordPage(pageEntity,reqBO);
                break;
            case "3":
                //拨付查询
                reqBO.setFlag(WhefssConstant.TXN_STS_S);
                list = whefssAccOutSendMapper.tranRecordPage(pageEntity,reqBO);
                break;
            case "4":
                //冲正查询
                list = whefssAccRepealMapper.tranRecordPage(pageEntity,reqBO);
                break;
            case "5":
                //退款查询
                reqBO.setFlag(WhefssConstant.TXN_STS_S);
                list = whefssAccRefundMapper.tranRecordPage(pageEntity,reqBO);
                break;
            case "6":
                //不明进账查询
                list = whefssOtherInSndMapper.tranRecordPage(pageEntity,reqBO);
                break;
            case "7":
                //账户年费利息查询
                list = whefssInterestSndMapper.tranRecordPage(pageEntity,reqBO);
                break;
            case "8":
                //拨付失败查询
                reqBO.setFlag(WhefssConstant.TXN_STS_F);
                list = whefssAccOutSendMapper.tranRecordPage(pageEntity,reqBO);
                break;
            case "9":
                //退款失败查询
                reqBO.setFlag(WhefssConstant.TXN_STS_F);
                list = whefssAccRefundMapper.tranRecordPage(pageEntity,reqBO);
                break;
            case "10":
                //按揭贷款查询
                list = whefssAjdkSndMapper.tranRecordPage(pageEntity,reqBO);
                break;
            default:
                resultT.setMsgCod(CommonMessageCode.TRAN_TYPE_ERROR);
                resultT.setData(list);
                return resultT;
        }
        if( list == null || list.isEmpty()  ) {
            resultT.setMsgCod(CommonMessageCode.REC_NFND);
        } else {
            resultT.setMsgCod(CommonMessageCode.SUCC);
            resultT.setData(list);
        }
        return resultT;
    }

}
