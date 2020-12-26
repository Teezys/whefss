package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;

import com.alibaba.fastjson.JSONObject;
import com.murong.ecp.netpay.whefss.web.common.utils.BaseAction;
import com.murong.ecp.netpay.whefss.web.common.utils.CommonMessageCode;
import com.murong.ecp.netpay.whefss.web.common.utils.PageResultT;
import com.murong.ecp.netpay.whefss.web.common.utils.ValidatorUtil;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.AcctInfoMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.AcctInfoDO;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAmpTranidDO;
import com.murong.ecp.netpay.whefss.web.pub.model.AccInfoListReqBO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAmpTranidListReqBO;
import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;
import com.yuangou.ecp.bp.core.common.exception.YGException;
import com.yuangou.ecp.bp.core.common.message.YGBizMessage;
import com.yuangou.ecp.bp.core.common.message.YGBizMessageContext;
import com.yuangou.ecp.bp.core.common.message.YGEDB;
import com.yuangou.ecp.bp.core.common.yglog4j.Logger;
import com.yuangou.ecp.bp.core.common.yglog4j.YGLogger;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tianlp
 * 监管账户信息查询
 * @version Id: accInfoQueryList.java, v 0.1 2020/11/4 18:52 tinalp Exp $$
 */
@Service("accInfoQueryList")
@Slf4j
public class AccInfoQueryListAction extends BaseAction {
    @Autowired
    private AcctInfoMapper acctInfoMapper;

    /**
     * 分页查询
     * @param bizCtx 上下文
     * @param reqBO 请求对象
     * @return
     * @throws YGException
     */
    public PageResultT<AcctInfoDO> execute(YGBizMessageContext bizCtx,  AccInfoListReqBO reqBO) throws YGException {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        Logger msgLog = YGLogger.getLogger(bizMsg);
        YGEDB root = bizMsg.getEDBBody();
        msgLog.info("list, reqBO=" + JSONObject.toJSON(reqBO));

        ValidatorUtil.validateObject(reqBO);
        AcctInfoDO entity = new AcctInfoDO();
        System.out.println(reqBO.getSvsAcNo());
        System.out.println(entity.getSvsAcNo());
        BeanUtils.copyProperties(reqBO, entity);
        YGPageEntity pageEntity = YGPageEntity.createPageEntity(root);
        pageEntity.setTot_rec_num(NumberUtils.toInt(root.getData("tot_rec_num")));
        //执行查询司法操作信息
        List<AcctInfoDO> list = acctInfoMapper.selectPage(pageEntity, entity);
        PageResultT resultT = new PageResultT();
        resultT.setPageEntity(pageEntity);
        if( list == null || list.isEmpty()  ) {
            resultT.setMsgCod(CommonMessageCode.REC_NFND);
        } else {
            resultT.setMsgCod(CommonMessageCode.SUCC);
            resultT.setData(list);
        }
        return resultT;
    }


    /**
     *
     * @param bizCtx 上下文
     * @param reqBO 请求对象
     * @return
     * @throws YGException
     */
//    public PageResultT<AcctInfoDO> execute(YGBizMessageContext bizCtx, AccInfoListReqBO reqBO) throws YGException {
//        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
//        Logger msgLog = YGLogger.getLogger(bizMsg);
//        msgLog.info("query, reqBO=" + JSONObject.toJSON(reqBO));
//
//        ValidatorUtil.validateObject(reqBO);
//        AcctInfoDO entity = new AcctInfoDO();
//        BeanUtils.copyProperties(reqBO, entity);
//
//        YGEDB edb = bizMsg.getEDBBody();
//
//        YGPageEntity pageEntity = YGPageEntity.createPageEntity(edb);
//        msgLog.info("---------"+entity);
//        //entity.setFssFlg(edb.getData("fss_flg"));
//
//        List<AcctInfoDO> jnefssAcctInfoDos = acctInfoMapper.selectPage(pageEntity,entity);
//
//        PageResultT resultT = new PageResultT();
//        resultT.setPageEntity(pageEntity);
//        if( entity != null  ) {
//            resultT.setMsgCod(CommonMessageCode.SUCC);
//            resultT.setData(jnefssAcctInfoDos);
//        } else {
//            resultT.setMsgCod(CommonMessageCode.REC_NFND.getMsgCod());
//            resultT.setMsgInf(CommonMessageCode.REC_NFND.getMsgInf());
//        }
//
//        return resultT;
//    }


}
