package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;

import com.alibaba.fastjson.JSONObject;
import com.murong.ecp.netpay.whefss.web.common.utils.BaseAction;
import com.murong.ecp.netpay.whefss.web.common.utils.CommonMessageCode;
import com.murong.ecp.netpay.whefss.web.common.utils.PageResultT;
import com.murong.ecp.netpay.whefss.web.common.utils.ValidatorUtil;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.TTransJnlMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.WhefssAccHaveAcceptedLogMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.TTransJnlDO;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAccHaveAcceptedLogDO;
import com.murong.ecp.netpay.whefss.web.pub.model.TTransJnlListReqBO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccHaveAcceptedLogListReqBO;
import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;
import com.yuangou.ecp.bp.core.common.exception.YGException;
import com.yuangou.ecp.bp.core.common.message.YGBizMessage;
import com.yuangou.ecp.bp.core.common.message.YGBizMessageContext;
import com.yuangou.ecp.bp.core.common.message.YGEDB;
import com.yuangou.ecp.bp.core.common.yglog4j.Logger;
import com.yuangou.ecp.bp.core.common.yglog4j.YGLogger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 房管流水查询
 * Created by xu_lw on 2020-09-03 15:20:33.
 */
@Service("haveAcceptedLogList")
public class HaveAcceptedLogListAction extends BaseAction {
    @Autowired
    private WhefssAccHaveAcceptedLogMapper whefssAccHaveAcceptedLogMapper;

    /**
     * 分页查询
     *
     * @param bizCtx 上下文
     * @param reqBO  请求对象
     * @return
     * @throws YGException
     */
    public PageResultT<WhefssAccHaveAcceptedLogDO> execute(YGBizMessageContext bizCtx, WhefssAccHaveAcceptedLogListReqBO reqBO) throws YGException {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        Logger msgLog = YGLogger.getLogger(bizMsg);
        YGEDB root = bizMsg.getEDBBody();
        msgLog.info("list, reqBO=" + JSONObject.toJSON(reqBO));

        ValidatorUtil.validateObject(reqBO);

        WhefssAccHaveAcceptedLogDO entity = new WhefssAccHaveAcceptedLogDO();
        entity.setAccountNum(reqBO.getSvsAcNo());
        entity.setTime(reqBO.getTranDate());

        YGPageEntity pageEntity = YGPageEntity.createPageEntity(root);
        List<WhefssAccHaveAcceptedLogDO> list = whefssAccHaveAcceptedLogMapper.selectPage(pageEntity, entity);

        PageResultT resultT = new PageResultT();
        resultT.setPageEntity(pageEntity);
        if (list == null || list.isEmpty()) {
            resultT.setMsgCod(CommonMessageCode.REC_NFND);
        } else {
            resultT.setMsgCod(CommonMessageCode.SUCC);
            resultT.setData(list);
        }

        return resultT;
    }
}
