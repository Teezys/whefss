/**
 * WhefssBalanceMapper.java
 * Copyright(C) 北京沐融信息科技股份有限公司
 * All rights reserved
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 18:04:34.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.dao;

import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssBalanceDO;
import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;
import com.yuangou.ecp.biz.transengine.sqlsession.YGResultHandler;
import com.yuangou.ecp.biz.transengine.sqlsession.YGSqlCursor;

import java.util.List;
public interface WhefssBalanceMapper {
   WhefssBalanceDO selectByPrimaryKey(WhefssBalanceDO entity);
   int  updateByPrimaryKey(WhefssBalanceDO entity);
   int  updateByPrimaryKeySelective(WhefssBalanceDO entity);
   int  deleteByIds(String ids);
   int  deleteByPrimaryKey(WhefssBalanceDO entity);
   int insertSelective(WhefssBalanceDO entity);
   int insert(WhefssBalanceDO entity);
   List<WhefssBalanceDO> selectPage(YGPageEntity pageEntity, WhefssBalanceDO entity);
   YGSqlCursor selectCursor(WhefssBalanceDO entity);
   void selectResultHandler(WhefssBalanceDO entity, YGResultHandler resultHandler);
}
