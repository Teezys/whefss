/**
 * WhefssChkDetailMapper.java
 * Copyright(C) 北京沐融信息科技股份有限公司
 * All rights reserved
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 18:10:01.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.dao;

import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssChkDetailDO;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssChkTotalDO;
import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;
import com.yuangou.ecp.biz.transengine.sqlsession.YGResultHandler;
import com.yuangou.ecp.biz.transengine.sqlsession.YGSqlCursor;

import java.util.List;
public interface WhefssChkDetailMapper {
   WhefssChkDetailDO selectByPrimaryKey(WhefssChkDetailDO entity);
   int  updateByPrimaryKey(WhefssChkDetailDO entity);
   int  updateByPrimaryKeySelective(WhefssChkDetailDO entity);
   int  deleteByIds(String ids);
   int  deleteByPrimaryKey(WhefssChkDetailDO entity);
   int insertSelective(WhefssChkDetailDO entity);
   int insert(WhefssChkDetailDO entity);
   List<WhefssChkDetailDO> selectPage(YGPageEntity pageEntity, WhefssChkDetailDO entity);
   List<WhefssChkDetailDO> selectListByFkid(WhefssChkDetailDO entity);
   YGSqlCursor selectCursor(WhefssChkDetailDO entity);
   void selectResultHandler(WhefssChkDetailDO entity, YGResultHandler resultHandler);
   WhefssChkTotalDO selectChk(WhefssChkTotalDO entity);
}
