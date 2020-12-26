/**
 * WhefssPosLogMapper.java
 * Copyright(C) 北京沐融信息科技股份有限公司
 * All rights reserved
 * ------------------------------------------------
 * Created by xu_lw on 2020-10-29 18:01:55.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.dao;

import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssPosLogDO;
import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;
import com.yuangou.ecp.biz.transengine.sqlsession.YGResultHandler;
import com.yuangou.ecp.biz.transengine.sqlsession.YGSqlCursor;

import java.util.List;

public interface WhefssPosLogMapper {
    WhefssPosLogDO selectByPrimaryKey(WhefssPosLogDO entity);

    int updateByPrimaryKey(WhefssPosLogDO entity);

    int updateByPrimaryKeySelective(WhefssPosLogDO entity);

    int deleteByIds(String ids);

    int deleteByPrimaryKey(WhefssPosLogDO entity);

    int insertSelective(WhefssPosLogDO entity);

    int insert(WhefssPosLogDO entity);

    List<WhefssPosLogDO> selectPage(YGPageEntity pageEntity, WhefssPosLogDO entity);

    List<WhefssPosLogDO> selectTotalPage(YGPageEntity pageEntity, WhefssPosLogDO entity);

    YGSqlCursor selectCursor(WhefssPosLogDO entity);

    void selectResultHandler(WhefssPosLogDO entity, YGResultHandler resultHandler);

    int deleteByTranDate(String tranDate);

    int deleteByTranAmt(String tranDate);

    int deleteByEntity(WhefssPosLogDO entity);

    int updateAcoma(WhefssPosLogDO entity);

    int updateSn(WhefssPosLogDO entity);

    List<WhefssPosLogDO> selectByEntity(WhefssPosLogDO entity);


}
