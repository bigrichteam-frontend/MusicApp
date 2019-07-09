package com.riches.honour.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 王志坚
 * @createTime 2019.07.09.10:19
 */
public class PageParams extends LinkedHashMap<String,Long> {
    //当前页码
    private Long page;
    //每页条数
    private Long limit;

    public PageParams(Map<String, Long> params){
        this.putAll(params);

        //分页参数
        this.page = params.get("page");
        this.limit = params.get("limit");

        this.put("start", (page - 1) * limit);
        this.put("page", page);
        this.put("limit", limit);
    }

    public PageParams() {
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }
}
