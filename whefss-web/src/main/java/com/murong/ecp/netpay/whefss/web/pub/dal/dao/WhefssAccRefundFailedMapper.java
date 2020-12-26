/**
 * WhefssAccRefundFailedMapper.java
 * Copyright(C) 北京沐融信息科技股份有限公司
 * All rights reserved
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 17:51:16.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.dao;

import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAccRefundFailedDO;
import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;
import com.yuangou.ecp.biz.transengine.sqlsession.YGResultHandler;
import com.yuangou.ecp.biz.transengine.sqlsession.YGSqlCursor;

import java.util.List;
public interface WhefssAccRefundFailedMapper {
   WhefssAccRefundFailedDO selectByPrimaryKey(WhefssAccRefundFailedDO entity);
   int  updateByPrimaryKey(WhefssAccRefundFailedDO entity);
   int  updateByPrimaryKeySelective(WhefssAccRefundFailedDO entity);
   int  deleteByIds(String ids);
   int  deleteByPrimaryKey(WhefssAccRefundFailedDO entity);
   int insertSelective(WhefssAccRefundFailedDO entity);
   int insert(WhefssAccRefundFailedDO entity);
   List<WhefssAccRefundFailedDO> selectPage(YGPageEntity pageEntity, WhefssAccRefundFailedDO entity);
   YGSqlCursor selectCursor(WhefssAccRefundFailedDO entity);
   void selectResultHandler(WhefssAccRefundFailedDO entity, YGResultHandler resultHandler);
}
