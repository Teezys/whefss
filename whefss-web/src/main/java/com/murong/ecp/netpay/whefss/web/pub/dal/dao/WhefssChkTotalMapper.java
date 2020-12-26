/**
 * WhefssChkTotalMapper.java
 * Copyright(C) 北京沐融信息科技股份有限公司
 * All rights reserved
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 18:08:34.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.dao;

import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssChkTotalDO;
import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;
import com.yuangou.ecp.biz.transengine.sqlsession.YGResultHandler;
import com.yuangou.ecp.biz.transengine.sqlsession.YGSqlCursor;

import java.util.List;
public interface WhefssChkTotalMapper {
   WhefssChkTotalDO selectByPrimaryKey(WhefssChkTotalDO entity);
   WhefssChkTotalDO selectByChkDt(WhefssChkTotalDO entity);
   int  updateByPrimaryKey(WhefssChkTotalDO entity);
   int  updateByPrimaryKeySelective(WhefssChkTotalDO entity);
   int  deleteByIds(String ids);
   int  deleteByPrimaryKey(WhefssChkTotalDO entity);
   int insertSelective(WhefssChkTotalDO entity);
   int insert(WhefssChkTotalDO entity);
   List<WhefssChkTotalDO> selectPage(YGPageEntity pageEntity, WhefssChkTotalDO entity);
   YGSqlCursor selectCursor(WhefssChkTotalDO entity);
   void selectResultHandler(WhefssChkTotalDO entity, YGResultHandler resultHandler);
}
