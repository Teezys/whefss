/**
 * WhefssAccOutFailedMapper.java
 * Copyright(C) 北京沐融信息科技股份有限公司
 * All rights reserved
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 17:44:16.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.dao;

import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAccOutFailedDO;
import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;
import com.yuangou.ecp.biz.transengine.sqlsession.YGResultHandler;
import com.yuangou.ecp.biz.transengine.sqlsession.YGSqlCursor;

import java.util.List;
public interface WhefssAccOutFailedMapper {
   WhefssAccOutFailedDO selectByPrimaryKey(WhefssAccOutFailedDO entity);
   int  updateByPrimaryKey(WhefssAccOutFailedDO entity);
   int  updateByPrimaryKeySelective(WhefssAccOutFailedDO entity);
   int  deleteByIds(String ids);
   int  deleteByPrimaryKey(WhefssAccOutFailedDO entity);
   int insertSelective(WhefssAccOutFailedDO entity);
   int insert(WhefssAccOutFailedDO entity);
   List<WhefssAccOutFailedDO> selectPage(YGPageEntity pageEntity, WhefssAccOutFailedDO entity);
   YGSqlCursor selectCursor(WhefssAccOutFailedDO entity);
   void selectResultHandler(WhefssAccOutFailedDO entity, YGResultHandler resultHandler);
}
