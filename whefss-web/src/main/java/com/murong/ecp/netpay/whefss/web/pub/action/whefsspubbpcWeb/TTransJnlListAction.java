package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;

import com.alibaba.fastjson.JSONObject;
import com.murong.ecp.netpay.whefss.web.common.utils.BaseAction;
import com.murong.ecp.netpay.whefss.web.common.utils.CommonMessageCode;
import com.murong.ecp.netpay.whefss.web.common.utils.PageResultT;
import com.murong.ecp.netpay.whefss.web.common.utils.ValidatorUtil;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.TTransJnlMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.TTransJnlDO;
import com.murong.ecp.netpay.whefss.web.pub.model.TTransJnlListReqBO;
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
 * 房管交易流水公共表管理服务
 * Created by xu_lw on 2020-09-03 15:20:33.
 */
@Service("transJnlList")
public class TTransJnlListAction extends BaseAction {
    @Autowired
    private TTransJnlMapper tTransJnlMapper;

    /**
     * 分页查询
     *
     * @param bizCtx 上下文
     * @param reqBO  请求对象
     * @return
     * @throws YGException
     */
    public PageResultT<TTransJnlDO> execute(YGBizMessageContext bizCtx, TTransJnlListReqBO reqBO) throws YGException {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        Logger msgLog = YGLogger.getLogger(bizMsg);
        YGEDB root = bizMsg.getEDBBody();
        msgLog.info("list, reqBO=" + JSONObject.toJSON(reqBO));

        ValidatorUtil.validateObject(reqBO);

        TTransJnlDO entity = new TTransJnlDO();
        BeanUtils.copyProperties(reqBO, entity);
        entity.setFssFlg("whef");
        YGPageEntity pageEntity = YGPageEntity.createPageEntity(root);
        //pageEntity.setTot_rec_num(NumberUtils.toInt(root.getData("totalrows")));

        List<TTransJnlDO> list = tTransJnlMapper.selectPage(pageEntity, entity);

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
