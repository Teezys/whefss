/**
 * WhefssAccOutSendMapper.java
 * Copyright(C) 北京沐融信息科技股份有限公司
 * All rights reserved
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 17:37:28.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.dao;

import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAccOutSendDO;
import com.murong.ecp.netpay.whefss.web.pub.model.TranRecordReqBO;
import com.murong.ecp.netpay.whefss.web.pub.model.TranRecordRspBO;
import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;
import com.yuangou.ecp.biz.transengine.sqlsession.YGResultHandler;
import com.yuangou.ecp.biz.transengine.sqlsession.YGSqlCursor;

import java.util.List;

public interface WhefssAccOutSendMapper {
    WhefssAccOutSendDO selectByPrimaryKey(WhefssAccOutSendDO entity);

    int updateByPrimaryKey(WhefssAccOutSendDO entity);

    int updateByPrimaryKeySelective(WhefssAccOutSendDO entity);

    int deleteByIds(String ids);

    int deleteByPrimaryKey(WhefssAccOutSendDO entity);

    int insertSelective(WhefssAccOutSendDO entity);

    int insert(WhefssAccOutSendDO entity);

    List<WhefssAccOutSendDO> selectPage(YGPageEntity pageEntity, WhefssAccOutSendDO entity);

    List<TranRecordRspBO> tranRecordPage(YGPageEntity pageEntity, TranRecordReqBO entity);

    YGSqlCursor selectCursor(WhefssAccOutSendDO entity);

    void selectResultHandler(WhefssAccOutSendDO entity, YGResultHandler resultHandler);

    List<WhefssAccOutSendDO> selectChk(WhefssAccOutSendDO entity);

    int updateByBatchNum(WhefssAccOutSendDO entity);

}
