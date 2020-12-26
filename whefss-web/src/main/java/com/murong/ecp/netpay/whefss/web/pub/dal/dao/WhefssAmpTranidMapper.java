/**
 * WhefssAmpTranidMapper.java
 * Copyright(C) 北京沐融信息科技股份有限公司
 * All rights reserved
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 17:54:12.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.dao;

import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAmpTranidDO;
import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;
import com.yuangou.ecp.biz.transengine.sqlsession.YGResultHandler;
import com.yuangou.ecp.biz.transengine.sqlsession.YGSqlCursor;

import java.util.List;

public interface WhefssAmpTranidMapper {
    int updateByPrimaryKey(WhefssAmpTranidDO entity);

    int updateByPrimaryKeySelective(WhefssAmpTranidDO entity);

    int deleteByIds(String ids);

    int deleteByPrimaryKey(WhefssAmpTranidDO entity);

    int insertSelective(WhefssAmpTranidDO entity);

    int insert(WhefssAmpTranidDO entity);

    List<WhefssAmpTranidDO> selectPage(YGPageEntity pageEntity, WhefssAmpTranidDO entity);

    YGSqlCursor selectCursor(WhefssAmpTranidDO entity);

    void selectResultHandler(WhefssAmpTranidDO entity, YGResultHandler resultHandler);

    List<WhefssAmpTranidDO> selectInfoPage(YGPageEntity pageEntity, WhefssAmpTranidDO entity);

    List<WhefssAmpTranidDO> selectChk(WhefssAmpTranidDO entity);

    int updateByBatchNum(WhefssAmpTranidDO entity);


}
