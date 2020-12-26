/**
 * WhefssAccHaveAcceptedLogMapper.java
 * Copyright(C) 北京沐融信息科技股份有限公司
 * All rights reserved
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 17:56:07.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.dao;

import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAccHaveAcceptedLogDO;
import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;
import com.yuangou.ecp.biz.transengine.sqlsession.YGResultHandler;
import com.yuangou.ecp.biz.transengine.sqlsession.YGSqlCursor;

import java.util.List;
public interface WhefssAccHaveAcceptedLogMapper {
   WhefssAccHaveAcceptedLogDO selectByPrimaryKey(WhefssAccHaveAcceptedLogDO entity);
   int  updateByPrimaryKey(WhefssAccHaveAcceptedLogDO entity);
   int  updateByPrimaryKeySelective(WhefssAccHaveAcceptedLogDO entity);
   int  deleteByIds(String ids);
   int  deleteByPrimaryKey(WhefssAccHaveAcceptedLogDO entity);
   int insertSelective(WhefssAccHaveAcceptedLogDO entity);
   int insert(WhefssAccHaveAcceptedLogDO entity);
   List<WhefssAccHaveAcceptedLogDO> selectPage(YGPageEntity pageEntity, WhefssAccHaveAcceptedLogDO entity);
   YGSqlCursor selectCursor(WhefssAccHaveAcceptedLogDO entity);
   void selectResultHandler(WhefssAccHaveAcceptedLogDO entity, YGResultHandler resultHandler);
}
