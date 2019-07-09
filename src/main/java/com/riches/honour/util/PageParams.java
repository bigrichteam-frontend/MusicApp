package com.riches.honour.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 王志坚
 * @createTime 2019.07.09.10:19
 */
public class PageParams extends LinkedHashMap<String,Object> {
    //当前页码
    private int page;
    //每页条数
    private int limit;

    public PageParams(Map<String, Object> params){
        this.putAll(params);

        //分页参数
        this.page = (int)params.get("page");
        this.limit = (int)params.get("limit");

        this.put("start", (page - 1) * limit);
        this.put("page", page);
        this.put("limit", limit);
    }

    public PageParams() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
