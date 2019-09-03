package com.cn.uvfortune.infrastructure.repository.entity.parameter;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/25 11:55
 * @Description:  查询参数
 */
public class Pager {

    private int page;  // 当前页

    private int limit; // 每页条数

    private String sKey; // 搜索关键字

    private String starttime; // 开始时间

    private String endtime; // 结束时间

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

    public String getsKey() {
        return sKey;
    }

    public void setsKey(String sKey) {
        this.sKey = sKey;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

}
