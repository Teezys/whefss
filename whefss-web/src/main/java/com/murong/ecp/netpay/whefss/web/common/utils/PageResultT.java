package com.murong.ecp.netpay.whefss.web.common.utils;

import com.yuangou.ecp.biz.transengine.sqlsession.YGPageEntity;

/**
 * Created by syxlw on 2018/4/28.
 */

public class PageResultT<T> extends ResultT<T> {
    private YGPageEntity pageEntity = null;

    public YGPageEntity getPageEntity() {
        return pageEntity;
    }

    public void setPageEntity(YGPageEntity pageEntity) {
        this.pageEntity = pageEntity;
    }
}
