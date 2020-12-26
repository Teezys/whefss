/**
 * WhefssAccRefundMapper.java
 * Copyright(C) 北京沐融信息科技股份有限公司
 * All rights reserved
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 17:49:42.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.dao;

import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAccRefundDO;
import com.murong.ecp.netpay.whefss.web.pub.model.TranRecordReqBO;
import com.murong.ecp.netpay.whefss.web.pub.model.TranRecordRspBO;
import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;
import com.yuangou.ecp.biz.transengine.sqlsession.YGResultHandler;
import com.yuangou.ecp.biz.transengine.sqlsession.YGSqlCursor;

import java.util.List;

public interface WhefssAccRefundMapper {
    WhefssAccRefundDO selectByPrimaryKey(WhefssAccRefundDO entity);

    int updateByPrimaryKey(WhefssAccRefundDO entity);

    int updateByPrimaryKeySelective(WhefssAccRefundDO entity);

    int deleteByIds(String ids);

    int deleteByPrimaryKey(WhefssAccRefundDO entity);

    int insertSelective(WhefssAccRefundDO entity);

    int insert(WhefssAccRefundDO entity);

    List<WhefssAccRefundDO> selectPage(YGPageEntity pageEntity, WhefssAccRefundDO entity);

    List<TranRecordRspBO> tranRecordPage(YGPageEntity pageEntity, TranRecordReqBO entity);

    YGSqlCursor selectCursor(WhefssAccRefundDO entity);

    void selectResultHandler(WhefssAccRefundDO entity, YGResultHandler resultHandler);

    List<WhefssAccRefundDO> selectChk(WhefssAccRefundDO entity);

    int updateByBatchNum(WhefssAccRefundDO entity);

}
