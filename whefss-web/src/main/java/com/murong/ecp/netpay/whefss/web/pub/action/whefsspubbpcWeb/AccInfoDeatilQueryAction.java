package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;

import com.alibaba.fastjson.JSONObject;
import com.murong.ecp.netpay.whefss.web.common.utils.BaseAction;
import com.murong.ecp.netpay.whefss.web.common.utils.CommonMessageCode;
import com.murong.ecp.netpay.whefss.web.common.utils.ResultT;
import com.murong.ecp.netpay.whefss.web.common.utils.ValidatorUtil;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.WhefssAccInfoMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAccInfoDO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssAccInfoDetailReqBO;
import com.yuangou.ecp.bp.core.common.exception.YGException;
import com.yuangou.ecp.bp.core.common.message.YGBizMessage;
import com.yuangou.ecp.bp.core.common.message.YGBizMessageContext;
import com.yuangou.ecp.bp.core.common.message.YGEDB;
import com.yuangou.ecp.bp.core.common.yglog4j.Logger;
import com.yuangou.ecp.bp.core.common.yglog4j.YGLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tianlp
 * 监管账户明细信息查询
 * @version Id: AccInfoDeatil.java, v 0.1 2020/11/5 11:19 tinalp Exp $$
 */
@Service("accInfoDetailQuery")
@Slf4j
public class AccInfoDeatilQueryAction extends BaseAction {

    @Autowired
    private WhefssAccInfoMapper whefssAccInfoMapper;

    /**
     * @param bizCtx 上下文
     * @param reqBO  请求对象
     * @return
     * @throws YGException
     */
    public ResultT<WhefssAccInfoDO> execute(YGBizMessageContext bizCtx, WhefssAccInfoDetailReqBO reqBO) throws YGException {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        Logger msgLog = YGLogger.getLogger(bizMsg);
        msgLog.info("query, reqBO=" + JSONObject.toJSON(reqBO));

        ValidatorUtil.validateObject(reqBO);
        WhefssAccInfoDO entity = new WhefssAccInfoDO();
        BeanUtils.copyProperties(reqBO, entity);

        YGEDB edb = bizMsg.getEDBBody();

        //查询该账户的信息
        entity = whefssAccInfoMapper.selectByPrimaryKey(entity);

        ResultT resultT = new ResultT();
        if (entity != null) {
            resultT.setMsgCod(CommonMessageCode.SUCC);
            resultT.setData(entity);
        } else {
            resultT.setMsgCod(CommonMessageCode.REC_NFND);
        }
        return resultT;
    }
}
