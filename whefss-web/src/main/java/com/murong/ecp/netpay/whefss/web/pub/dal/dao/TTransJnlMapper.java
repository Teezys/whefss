/**
 * TTransJnlMapper.java
 * Copyright(C) 北京沐融信息科技股份有限公司
 * All rights reserved
 * ------------------------------------------------
 * Created by xu_lw on 2020-09-03 15:20:33.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.dao;

import com.murong.ecp.netpay.whefss.web.pub.dal.entity.TTransJnlDO;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.TTransJnlResult;
import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;
import com.yuangou.ecp.biz.transengine.sqlsession.YGResultHandler;
import com.yuangou.ecp.biz.transengine.sqlsession.YGSqlCursor;

import java.util.List;

public interface TTransJnlMapper {
    TTransJnlDO selectByPrimaryKey(TTransJnlDO entity);

    int updateByPrimaryKey(TTransJnlDO entity);

    int updateByPrimaryKeySelective(TTransJnlDO entity);

    int deleteByIds(String ids);

    int deleteByPrimaryKey(TTransJnlDO entity);

    int insertSelective(TTransJnlDO entity);

    int insert(TTransJnlDO entity);

    List<TTransJnlDO> selectPage(YGPageEntity pageEntity, TTransJnlDO entity);

    YGSqlCursor selectCursor(TTransJnlDO entity);

    void selectResultHandler(TTransJnlDO entity, YGResultHandler resultHandler);

    List<TTransJnlResult> selectByAcNo(TTransJnlDO entity);

    TTransJnlDO selectSelective(TTransJnlDO tTransJnlDO);

    TTransJnlDO selectSelectByBnkNumber(TTransJnlDO tTransJnlDO);

    String selectBalance(TTransJnlDO entity);

    List<TTransJnlDO> selectChkAjdk(TTransJnlDO entity);

    List<TTransJnlDO> selectChkInterest(TTransJnlDO entity);

    List<TTransJnlDO> selectChkOtherIn(TTransJnlDO entity);

}
