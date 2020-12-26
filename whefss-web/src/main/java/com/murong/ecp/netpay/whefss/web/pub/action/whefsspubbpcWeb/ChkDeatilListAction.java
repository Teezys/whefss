package com.murong.ecp.netpay.whefss.web.pub.action.whefsspubbpcWeb;

import com.alibaba.fastjson.JSONObject;
import com.murong.ecp.netpay.whefss.web.common.utils.BaseAction;
import com.murong.ecp.netpay.whefss.web.common.utils.CommonMessageCode;
import com.murong.ecp.netpay.whefss.web.common.utils.PageResultT;
import com.murong.ecp.netpay.whefss.web.common.utils.ValidatorUtil;
import com.murong.ecp.netpay.whefss.web.pub.dal.dao.WhefssChkDetailMapper;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssChkDetailDO;
import com.murong.ecp.netpay.whefss.web.pub.model.TranRecordRspBO;
import com.murong.ecp.netpay.whefss.web.pub.model.WhefssChkDetailListReqBO;
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
 * @author wanglei
 * 对账结果信息查询
 * @version Id: ChkDeatilListAction.java, v 0.1 2020/11/5 11:19 tinalp Exp $$
 */
@Service("chkDetailList")
@Slf4j
public class ChkDeatilListAction extends BaseAction {

    @Autowired
    private WhefssChkDetailMapper chkDetailMapper;

    /**
     * @param bizCtx 上下文
     * @param reqBO  请求对象
     * @return
     * @throws YGException
     */
    public PageResultT<TranRecordRspBO> execute(YGBizMessageContext bizCtx, WhefssChkDetailListReqBO reqBO) throws YGException {
        YGBizMessage bizMsg = bizCtx.getCurrentMsg();
        Logger msgLog = YGLogger.getLogger(bizMsg);
        YGEDB root = bizMsg.getEDBBody();
        msgLog.info("list, reqBO=" + JSONObject.toJSON(reqBO));
        ValidatorUtil.validateObject(reqBO);
        WhefssChkDetailDO chkDetailDO = new WhefssChkDetailDO();
        BeanUtils.copyProperties(reqBO, chkDetailDO);
        YGPageEntity pageEntity = YGPageEntity.createPageEntity(root);
        pageEntity.setTot_rec_num(NumberUtils.toInt(root.getData("tot_rec_num")));
        PageResultT resultT = new PageResultT();
        resultT.setPageEntity(pageEntity);

        //查询该账户的信息
        List<WhefssChkDetailDO> list = chkDetailMapper.selectPage(pageEntity, chkDetailDO);
        if (list == null || list.isEmpty()) {
            resultT.setMsgCod(CommonMessageCode.REC_NFND);
        } else {
            resultT.setMsgCod(CommonMessageCode.SUCC);
            resultT.setData(list);
        }
        return resultT;
    }
}
