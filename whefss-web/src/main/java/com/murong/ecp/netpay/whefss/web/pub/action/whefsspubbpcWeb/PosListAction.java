package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;

import com.alibaba.fastjson.JSONObject;
import com.murong.ecp.netpay.whefss.web.common.utils.*;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.WhefssPosLogMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.TTransJnlDO;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssPosLogDO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssPosFileImportReqBo;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssPosListReqBO;
import com.murong.ecp.netpay.whefss.web.service.server.InsertPosFile;
import com.murong.ecp.netpay.whefss.web.service.server.bo.PosFileAcomaEntity;
import com.murong.ecp.netpay.whefss.web.service.server.bo.PosFileMapsEntity;
import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;
import com.yuangou.ecp.bp.comp.pubatc.common.YGAmt;
import com.yuangou.ecp.bp.core.common.exception.YGException;
import com.yuangou.ecp.bp.core.common.message.YGBizMessage;
import com.yuangou.ecp.bp.core.common.message.YGBizMessageContext;
import com.yuangou.ecp.bp.core.common.message.YGEDB;
import com.yuangou.ecp.bp.core.common.yglog4j.Logger;
import com.yuangou.ecp.bp.core.common.yglog4j.YGLogger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xu_lw on 2020-05-08 17:23:57.
 */
@Service("posList")
public class PosListAction extends BaseAction {

    @Autowired
    private WhefssPosLogMapper posLogMapper;

    public ResultT execute(YGBizMessageContext bizCtx, WhefssPosListReqBO reqBO) throws YGException {
        Logger logger = YGLogger.getLogger(bizCtx.getCurrentMsg());
        logger.info("export, reqBo=" + JSONObject.toJSON(reqBO));
        ValidatorUtil.validateObject(reqBO);

        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        Logger msgLog = YGLogger.getLogger(bizMsg);
        YGEDB root = bizMsg.getEDBBody();
        msgLog.info("list, reqBO=" + JSONObject.toJSON(reqBO));

        ValidatorUtil.validateObject(reqBO);

        WhefssPosLogDO entity = new WhefssPosLogDO();
        BeanUtils.copyProperties(reqBO, entity);
        YGPageEntity pageEntity = YGPageEntity.createPageEntity(root);
        //pageEntity.setTot_rec_num(NumberUtils.toInt(root.getData("totalrows")));

        List<WhefssPosLogDO> list = posLogMapper.selectTotalPage(pageEntity, entity);

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
