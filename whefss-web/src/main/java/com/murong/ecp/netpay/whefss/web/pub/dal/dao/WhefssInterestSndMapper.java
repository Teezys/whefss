/**
 * WhefssInterestSndMapper.java
 * Copyright(C) 北京沐融信息科技股份有限公司
 * All rights reserved
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 18:03:10.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.dao;

import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssInterestSndDO;
import com.murong.ecp.netpay.whefss.web.pub.model.TranRecordReqBO;
import com.murong.ecp.netpay.whefss.web.pub.model.TranRecordRspBO;
import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;
import com.yuangou.ecp.biz.transengine.sqlsession.YGResultHandler;
import com.yuangou.ecp.biz.transengine.sqlsession.YGSqlCursor;

import java.util.List;

public interface WhefssInterestSndMapper {
    WhefssInterestSndDO selectByPrimaryKey(WhefssInterestSndDO entity);

    int updateByPrimaryKey(WhefssInterestSndDO entity);

    int updateByPrimaryKeySelective(WhefssInterestSndDO entity);

    int deleteByIds(String ids);

    int deleteByPrimaryKey(WhefssInterestSndDO entity);

    int insertSelective(WhefssInterestSndDO entity);

    int insert(WhefssInterestSndDO entity);

    List<WhefssInterestSndDO> selectPage(YGPageEntity pageEntity, WhefssInterestSndDO entity);

    List<TranRecordRspBO> tranRecordPage(YGPageEntity pageEntity, TranRecordReqBO entity);

    YGSqlCursor selectCursor(WhefssInterestSndDO entity);

    void selectResultHandler(WhefssInterestSndDO entity, YGResultHandler resultHandler);

    int getcount(WhefssInterestSndDO entity);

    List<WhefssInterestSndDO> selectChk(WhefssInterestSndDO entity);

    WhefssInterestSndDO selectByEntity(WhefssInterestSndDO entity);

    int updateSts(WhefssInterestSndDO entity);

    int updateByBatchNum(WhefssInterestSndDO entity);
}
