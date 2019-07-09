package com.riches.honour.util;

import java.util.List;


/*
* @author 吴星辰
 * @createTime 2019.07.09.40:32
* */

public class PageResult<T>{
    private Long total;// 总条数
    private Long currPage;//当前页
    private Long totalPage;// 总页数
    private List<T> items;// 当前页数据

    public PageResult() {
    }

    public void setCurrPage(Long currPage) {
        this.currPage = currPage;
    }

    public Long getCurrPage() {
        return currPage;
    }

    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResult(Long total, Long totalPage, List<T> items) {
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
    }

    public PageResult(Long total, Long currPage, Long totalPage, List<T> items) {
        this.total = total;
        this.currPage = currPage;
        this.totalPage = totalPage;
        this.items = items;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Long getTotal() {
        return total;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public List<T> getItems() {
        return items;
    }
}
