package com.cn.uvfortune.infrastructure.repository.entity;

public class SysPage {
    private Integer id;

    private String name;

    private String descritpion;

    private String url;

    private Integer pid;

    private String pageOrder;

    private String pageType;

    private String pageJb;

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public String getPageJb() {
        return pageJb;
    }

    public void setPageJb(String pageJb) {
        this.pageJb = pageJb;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion == null ? null : descritpion.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPageOrder() {
        return pageOrder;
    }

    public void setPageOrder(String pageOrder) {
        this.pageOrder = pageOrder == null ? null : pageOrder.trim();
    }
}