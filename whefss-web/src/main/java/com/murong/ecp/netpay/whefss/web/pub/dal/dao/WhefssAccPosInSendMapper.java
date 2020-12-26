/**
 * WhefssAccPosInSendMapper.java
 * Copyright(C) 北京沐融信息科技股份有限公司
 * All rights reserved
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 17:48:12.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.dao;

import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAccPosInSendDO;
import com.murong.ecp.netpay.whefss.web.pub.model.TranRecordReqBO;
import com.murong.ecp.netpay.whefss.web.pub.model.TranRecordRspBO;
import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;
import com.yuangou.ecp.biz.transengine.sqlsession.YGResultHandler;
import com.yuangou.ecp.biz.transengine.sqlsession.YGSqlCursor;

import java.util.List;

public interface WhefssAccPosInSendMapper {
    WhefssAccPosInSendDO selectByPrimaryKey(WhefssAccPosInSendDO entity);

    int updateByPrimaryKey(WhefssAccPosInSendDO entity);

    int updateByPrimaryKeySelective(WhefssAccPosInSendDO entity);

    int deleteByIds(String ids);

    int deleteByPrimaryKey(WhefssAccPosInSendDO entity);

    int insertSelective(WhefssAccPosInSendDO entity);

    int insert(WhefssAccPosInSendDO entity);

    List<WhefssAccPosInSendDO> selectPage(YGPageEntity pageEntity, WhefssAccPosInSendDO entity);

    List<TranRecordRspBO> tranRecordPage(YGPageEntity pageEntity, TranRecordReqBO entity);

    YGSqlCursor selectCursor(WhefssAccPosInSendDO entity);

    void selectResultHandler(WhefssAccPosInSendDO entity, YGResultHandler resultHandler);

    List<WhefssAccPosInSendDO> selectChk(WhefssAccPosInSendDO entity);

    int updateByBatchNum(WhefssAccPosInSendDO entity);

}
