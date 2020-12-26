/**
 * WhefssAccRepealMapper.java
 * Copyright(C) 北京沐融信息科技股份有限公司
 * All rights reserved
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 17:46:05.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.dao;

import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAccRepealDO;
import com.murong.ecp.netpay.whefss.web.pub.model.TranRecordReqBO;
import com.murong.ecp.netpay.whefss.web.pub.model.TranRecordRspBO;
import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;
import com.yuangou.ecp.biz.transengine.sqlsession.YGResultHandler;
import com.yuangou.ecp.biz.transengine.sqlsession.YGSqlCursor;

import java.util.List;

public interface WhefssAccRepealMapper {
    WhefssAccRepealDO selectByPrimaryKey(WhefssAccRepealDO entity);

    int updateByPrimaryKey(WhefssAccRepealDO entity);

    int updateByPrimaryKeySelective(WhefssAccRepealDO entity);

    int deleteByIds(String ids);

    int deleteByPrimaryKey(WhefssAccRepealDO entity);

    int insertSelective(WhefssAccRepealDO entity);

    int insert(WhefssAccRepealDO entity);

    List<WhefssAccRepealDO> selectPage(YGPageEntity pageEntity, WhefssAccRepealDO entity);

    List<TranRecordRspBO> tranRecordPage(YGPageEntity pageEntity, TranRecordReqBO entity);

    YGSqlCursor selectCursor(WhefssAccRepealDO entity);

    void selectResultHandler(WhefssAccRepealDO entity, YGResultHandler resultHandler);

    List<WhefssAccRepealDO> selectChk(WhefssAccRepealDO entity);

    int updateByBatchNum(WhefssAccRepealDO entity);

}
