package com.murong.ecp.netpay.whefss.web.common.utils;

import net.sf.oval.constraint.NotNull;

/**
 * Created by syxlw on 2018/4/28.
 */

public class PageRequest {
    /**
     * 请求页号
     */
    private Integer pagNo = null;

    /**
     * 开始记录号
     */
    @NotNull(message="offset必须输入")
    private Integer offset = Integer.valueOf(0);

    /**
     * 每页记录数
     */
    @NotNull(message="limit必须输入")
    private Integer limit = Integer.valueOf(20);

    /**
     * 总记录数
     */
    private Integer totalrows = null;

    public Integer getPagNo() {
        return pagNo;
    }

    public void setPagNo(Integer pagNo) {
        this.pagNo = pagNo;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getTotalrows() {
        return totalrows;
    }

    public void setTotalrows(Integer totalrows) {
        this.totalrows = totalrows;
    }
}
