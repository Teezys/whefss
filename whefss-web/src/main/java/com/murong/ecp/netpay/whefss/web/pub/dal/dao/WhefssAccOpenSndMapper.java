/**
 * WhefssAccOpenSndMapper.java
 * Copyright(C) 北京沐融信息科技股份有限公司
 * All rights reserved
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 18:07:10.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.dao;

import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssAccOpenSndDO;
import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;
import com.yuangou.ecp.biz.transengine.sqlsession.YGResultHandler;
import com.yuangou.ecp.biz.transengine.sqlsession.YGSqlCursor;

import java.util.List;
public interface WhefssAccOpenSndMapper {
   WhefssAccOpenSndDO selectByPrimaryKey(WhefssAccOpenSndDO entity);
   int  updateByPrimaryKey(WhefssAccOpenSndDO entity);
   int  updateByPrimaryKeySelective(WhefssAccOpenSndDO entity);
   int  deleteByIds(String ids);
   int  deleteByPrimaryKey(WhefssAccOpenSndDO entity);
   int insertSelective(WhefssAccOpenSndDO entity);
   int insert(WhefssAccOpenSndDO entity);
   List<WhefssAccOpenSndDO> selectPage(YGPageEntity pageEntity, WhefssAccOpenSndDO entity);
   YGSqlCursor selectCursor(WhefssAccOpenSndDO entity);
   void selectResultHandler(WhefssAccOpenSndDO entity, YGResultHandler resultHandler);
}
