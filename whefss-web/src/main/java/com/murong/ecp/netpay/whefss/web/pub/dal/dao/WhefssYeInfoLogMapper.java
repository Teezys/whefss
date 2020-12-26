/**
 * WhefssYeInfoLogMapper.java
 * Copyright(C) 北京沐融信息科技股份有限公司
 * All rights reserved
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 18:00:31.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.dao;

import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssYeInfoLogDO;
import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;
import com.yuangou.ecp.biz.transengine.sqlsession.YGResultHandler;
import com.yuangou.ecp.biz.transengine.sqlsession.YGSqlCursor;

import java.util.List;
public interface WhefssYeInfoLogMapper {
   WhefssYeInfoLogDO selectByPrimaryKey(WhefssYeInfoLogDO entity);
   int  updateByPrimaryKey(WhefssYeInfoLogDO entity);
   int  updateByPrimaryKeySelective(WhefssYeInfoLogDO entity);
   int  deleteByIds(String ids);
   int  deleteByPrimaryKey(WhefssYeInfoLogDO entity);
   int insertSelective(WhefssYeInfoLogDO entity);
   int insert(WhefssYeInfoLogDO entity);
   List<WhefssYeInfoLogDO> selectPage(YGPageEntity pageEntity, WhefssYeInfoLogDO entity);
   YGSqlCursor selectCursor(WhefssYeInfoLogDO entity);
   void selectResultHandler(WhefssYeInfoLogDO entity, YGResultHandler resultHandler);
}
