/**
 * WhefssOtherInSndMapper.java
 * Copyright(C) 北京沐融信息科技股份有限公司
 * All rights reserved
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 17:57:29.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.dao;

import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssOtherInSndDO;
import com.murong.ecp.netpay.whefss.web.pub.model.TranRecordReqBO;
import com.murong.ecp.netpay.whefss.web.pub.model.TranRecordRspBO;
import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;
import com.yuangou.ecp.biz.transengine.sqlsession.YGResultHandler;
import com.yuangou.ecp.biz.transengine.sqlsession.YGSqlCursor;

import java.util.List;

public interface WhefssOtherInSndMapper {
    WhefssOtherInSndDO selectByPrimaryKey(WhefssOtherInSndDO entity);

    int updateByPrimaryKey(WhefssOtherInSndDO entity);

    int updateByPrimaryKeySelective(WhefssOtherInSndDO entity);

    int deleteByIds(String ids);

    int deleteByPrimaryKey(WhefssOtherInSndDO entity);

    int insertSelective(WhefssOtherInSndDO entity);

    int insert(WhefssOtherInSndDO entity);

    List<WhefssOtherInSndDO> selectPage(YGPageEntity pageEntity, WhefssOtherInSndDO entity);

    List<TranRecordRspBO> tranRecordPage(YGPageEntity pageEntity, TranRecordReqBO entity);

    YGSqlCursor selectCursor(WhefssOtherInSndDO entity);

    void selectResultHandler(WhefssOtherInSndDO entity, YGResultHandler resultHandler);

    WhefssOtherInSndDO selectByEntity(WhefssOtherInSndDO entity);

    int updateSts(WhefssOtherInSndDO entity);

    List<WhefssOtherInSndDO> selectChk(WhefssOtherInSndDO entity);

    int updateByBatchNum(WhefssOtherInSndDO entity);

}
