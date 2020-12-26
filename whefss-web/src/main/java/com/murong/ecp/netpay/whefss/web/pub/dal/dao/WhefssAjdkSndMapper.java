/**
 * WhefssAjdkSndMapper.java
 * Copyright(C) 北京沐融信息科技股份有限公司
 * All rights reserved
 * ------------------------------------------------
 * Created by xu_lw on 2020-11-16 16:24:45.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.dao;

import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAjdkSndDO;
import com.murong.ecp.netpay.whefss.web.pub.model.TranRecordReqBO;
import com.murong.ecp.netpay.whefss.web.pub.model.TranRecordRspBO;
import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;
import com.yuangou.ecp.biz.transengine.sqlsession.YGResultHandler;
import com.yuangou.ecp.biz.transengine.sqlsession.YGSqlCursor;

import java.util.List;

public interface WhefssAjdkSndMapper {

    WhefssAjdkSndDO selectByPrimaryKey(WhefssAjdkSndDO entity);

    int updateSts(WhefssAjdkSndDO entity);

    int updateByPrimaryKeySelective(WhefssAjdkSndDO entity);

    int updateByPrimaryKey(WhefssAjdkSndDO entity);

    int deleteByIds(String ids);

    int deleteByPrimaryKey(WhefssAjdkSndDO entity);

    int insertSelective(WhefssAjdkSndDO entity);

    int insert(WhefssAjdkSndDO entity);

    List<WhefssAjdkSndDO> selectPage(YGPageEntity pageEntity, WhefssAjdkSndDO entity);

    List<TranRecordRspBO> tranRecordPage(YGPageEntity pageEntity, TranRecordReqBO entity);

    YGSqlCursor selectCursor(WhefssAjdkSndDO entity);

    void selectResultHandler(WhefssAjdkSndDO entity, YGResultHandler resultHandler);

    WhefssAjdkSndDO selectByEntity(WhefssAjdkSndDO entity);

    List<WhefssAjdkSndDO> selectChk(WhefssAjdkSndDO entity);

    int updateByBatchNum(WhefssAjdkSndDO entity);


}
