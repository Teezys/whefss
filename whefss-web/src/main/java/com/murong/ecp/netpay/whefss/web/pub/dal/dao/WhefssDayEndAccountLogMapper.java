/**
 * WhefssDayEndAccountLogMapper.java
 * Copyright(C) 北京沐融信息科技股份有限公司
 * All rights reserved
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 17:58:53.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.dao;

import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssDayEndAccountLogDO;
import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;
import com.yuangou.ecp.biz.transengine.sqlsession.YGResultHandler;
import com.yuangou.ecp.biz.transengine.sqlsession.YGSqlCursor;

import java.util.List;
public interface WhefssDayEndAccountLogMapper {
   WhefssDayEndAccountLogDO selectByPrimaryKey(WhefssDayEndAccountLogDO entity);
   int  updateByPrimaryKey(WhefssDayEndAccountLogDO entity);
   int  updateByPrimaryKeySelective(WhefssDayEndAccountLogDO entity);
   int  deleteByIds(String ids);
   int  deleteByPrimaryKey(WhefssDayEndAccountLogDO entity);
   int insertSelective(WhefssDayEndAccountLogDO entity);
   int insert(WhefssDayEndAccountLogDO entity);
   List<WhefssDayEndAccountLogDO> selectPage(YGPageEntity pageEntity, WhefssDayEndAccountLogDO entity);
   YGSqlCursor selectCursor(WhefssDayEndAccountLogDO entity);
   void selectResultHandler(WhefssDayEndAccountLogDO entity, YGResultHandler resultHandler);
}
