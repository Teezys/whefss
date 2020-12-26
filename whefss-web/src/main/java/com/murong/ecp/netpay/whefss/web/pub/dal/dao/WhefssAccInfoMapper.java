/**
 * WhefssAccInfoMapper.java
 * Copyright(C) 北京沐融信息科技股份有限公司
 * All rights reserved
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 18:05:50.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.dao;

import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAccInfoDO;
import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;
import com.yuangou.ecp.biz.transengine.sqlsession.YGResultHandler;
import com.yuangou.ecp.biz.transengine.sqlsession.YGSqlCursor;

import java.util.List;
public interface WhefssAccInfoMapper {
   WhefssAccInfoDO selectByPrimaryKey(WhefssAccInfoDO entity);
   int  updateByPrimaryKey(WhefssAccInfoDO entity);
   int  updateByPrimaryKeySelective(WhefssAccInfoDO entity);
   int  deleteByIds(String ids);
   int  deleteByPrimaryKey(WhefssAccInfoDO entity);
   int insertSelective(WhefssAccInfoDO entity);
   int insert(WhefssAccInfoDO entity);
   List<WhefssAccInfoDO> selectPage(YGPageEntity pageEntity, WhefssAccInfoDO entity);
   YGSqlCursor selectCursor(WhefssAccInfoDO entity);
   void selectResultHandler(WhefssAccInfoDO entity, YGResultHandler resultHandler);
}
