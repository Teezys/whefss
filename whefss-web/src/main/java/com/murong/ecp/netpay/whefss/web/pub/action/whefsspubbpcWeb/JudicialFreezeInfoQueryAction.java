package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;

import com.alibaba.fastjson.JSONObject;
import com.murong.ecp.netpay.whefss.web.common.utils.BaseAction;
import com.murong.ecp.netpay.whefss.web.common.utils.CommonMessageCode;
import com.murong.ecp.netpay.whefss.web.common.utils.PageResultT;
import com.murong.ecp.netpay.whefss.web.common.utils.ValidatorUtil;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.AcctInfoMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.WhefssAmpTranidMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAmpTranidDO;
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
import com.yuangou.ecp.biz.transengine.sqlsession.YGSqlSession;
import com.yuangou.ecp.biz.transengine.sqlsession.MapperProxy;
import java.util.List;

/**
 * @author tianlp
 * 司法操作信息查询
 * @version Id: JudicialFreezeInfoQuery.java, v 0.1 2020/10/30 14:02 tinalp Exp $$
 */
@Service("judicialFreezeInfoQuery")
@Slf4j
public class JudicialFreezeInfoQueryAction extends BaseAction {

    @Autowired
    private WhefssAmpTranidMapper whefssAmpTranidMapper;
    @Autowired
    private AcctInfoMapper acctInfoMapper;

    /**
     * 分页查询
     * @param bizCtx 上下文
     * @param reqBO 请求对象
     * @return
     * @throws YGException
     */
    public PageResultT<WhefssAmpTranidDO> execute(YGBizMessageContext bizCtx, WhefssAmpTranidListReqBO reqBO) throws YGException {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        Logger msgLog = YGLogger.getLogger(bizMsg);
        YGEDB root = bizMsg.getEDBBody();
        msgLog.info("list, reqBO=" + JSONObject.toJSON(reqBO));
        ValidatorUtil.validateObject(reqBO);
        WhefssAmpTranidDO entity = new WhefssAmpTranidDO();

        BeanUtils.copyProperties(reqBO, entity);
        YGPageEntity pageEntity = YGPageEntity.createPageEntity(root);
        pageEntity.setTot_rec_num(NumberUtils.toInt(root.getData("totalrows")));
        System.out.println(entity);
        //执行查询司法操作信息
        List<WhefssAmpTranidDO> list = whefssAmpTranidMapper.selectInfoPage(pageEntity, entity);
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

}
