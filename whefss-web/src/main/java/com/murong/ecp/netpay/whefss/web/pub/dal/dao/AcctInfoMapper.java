/**
 * AcctInfoMapper.java
 * Copyright(C) 北京沐融信息科技股份有限公司
 * All rights reserved
 * ------------------------------------------------
 * Created by xu_lw on 2020-09-06 16:49:51.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.dao;


import com.murong.ecp.netpay.whefss.web.pub.dal.entity.AcctInfoDO;
import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;
import com.yuangou.ecp.biz.transengine.sqlsession.YGResultHandler;
import com.yuangou.ecp.biz.transengine.sqlsession.YGSqlCursor;
import org.apache.ibatis.annotations.Param;

import java.util.List;
public interface AcctInfoMapper {
   AcctInfoDO selectByPrimaryKey(AcctInfoDO entity);
   List<AcctInfoDO> selectListBySts(AcctInfoDO entity);
   int  updateByPrimaryKey(AcctInfoDO entity);
   int  updateByPrimaryKeySelective(AcctInfoDO entity);
   int insert(AcctInfoDO entity);
   List<AcctInfoDO> selectPage(YGPageEntity pageEntity, AcctInfoDO entity);
   YGSqlCursor selectCursor(AcctInfoDO entity);
   void selectResultHandler(AcctInfoDO entity, YGResultHandler resultHandler);


//   AcctInfoDO selectAccSts(AcctInfoDO entity);
   /**
    *查看公共流水表中是否有账户
    * @param entity
    * @return
    */
   int countAcc(AcctInfoDO entity);

   /**
    * 监管账户名是否正确
    * @param entity
    * @return
    */
   int countName(AcctInfoDO entity);



   /**
    * 更新公共账户表中的账户状态及日期
    * @return
    */
   int updateStatus(AcctInfoDO entity);

}
