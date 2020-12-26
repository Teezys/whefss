/**
 * WhefssAccInSendMapper.java
 * Copyright(C) 北京沐融信息科技股份有限公司
 * All rights reserved
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 17:42:39.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.dao;

import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAccInSendDO;
import com.murong.ecp.netpay.whefss.web.pub.model.TranRecordReqBO;
import com.murong.ecp.netpay.whefss.web.pub.model.TranRecordRspBO;
import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;
import com.yuangou.ecp.biz.transengine.sqlsession.YGResultHandler;
import com.yuangou.ecp.biz.transengine.sqlsession.YGSqlCursor;

import java.util.List;

public interface WhefssAccInSendMapper {

    WhefssAccInSendDO selectByPrimaryKey(WhefssAccInSendDO entity);

    int updateByPrimaryKey(WhefssAccInSendDO entity);

    int updateByPrimaryKeySelective(WhefssAccInSendDO entity);

    int deleteByIds(String ids);

    int deleteByPrimaryKey(WhefssAccInSendDO entity);

    int insertSelective(WhefssAccInSendDO entity);

    int insert(WhefssAccInSendDO entity);

    List<WhefssAccInSendDO> selectPage(YGPageEntity pageEntity, WhefssAccInSendDO entity);

    List<TranRecordRspBO> tranRecordPage(YGPageEntity pageEntity, TranRecordReqBO entity);

    YGSqlCursor selectCursor(WhefssAccInSendDO entity);

    void selectResultHandler(WhefssAccInSendDO entity, YGResultHandler resultHandler);

    List<WhefssAccInSendDO> selectChk(WhefssAccInSendDO entity);

    int updateByBatchNum(WhefssAccInSendDO entity);

}
