/**
 * WhefssChkDetailJnlMapper.java
 * Copyright(C) 北京沐融信息科技股份有限公司
 * All rights reserved
 * ------------------------------------------------
 * Created by xu_lw on 2020-12-15 15:45:31.
 */
package com.murong.ecp.netpay.whefss.web.pub.dal.dao;

import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssChkDetailDO;
import com.murong.ecp.netpay.whefss.web.pub.dal.entity.WhefssChkDetailJnlDO;
import com.murong.ecp.netpay.whefss.web.service.server.bo.FundsUploadDetail;
import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;
import com.yuangou.ecp.biz.transengine.sqlsession.YGResultHandler;
import com.yuangou.ecp.biz.transengine.sqlsession.YGSqlCursor;

import java.util.List;

public interface WhefssChkDetailJnlMapper {
    WhefssChkDetailJnlDO selectByPrimaryKey(WhefssChkDetailJnlDO entity);

    int updateByPrimaryKey(WhefssChkDetailJnlDO entity);

    int updateByPrimaryKeySelective(WhefssChkDetailJnlDO entity);

    int deleteByIds(String ids);

    int deleteByPrimaryKey(WhefssChkDetailJnlDO entity);

    int insertSelective(WhefssChkDetailJnlDO entity);

    int insert(WhefssChkDetailJnlDO entity);

    List<WhefssChkDetailJnlDO> selectPage(YGPageEntity pageEntity, WhefssChkDetailJnlDO entity);

    YGSqlCursor selectCursor(WhefssChkDetailJnlDO entity);

    void selectResultHandler(WhefssChkDetailJnlDO entity, YGResultHandler resultHandler);

    int deleteChk(WhefssChkDetailJnlDO entity);

    WhefssChkDetailDO sumCheckDetail(WhefssChkDetailDO entity);

    List<FundsUploadDetail> selectChk(WhefssChkDetailDO entity);



}
