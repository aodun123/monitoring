package com.cn.uvfortune.infrastructure.repository.entity;

public class SysUserAgency {
    private Integer id;

    private Integer sysUserId;

    private Integer sysAgencyId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Integer sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Integer getSysAgencyId() {
        return sysAgencyId;
    }

    public void setSysAgencyId(Integer sysAgencyId) {
        this.sysAgencyId = sysAgencyId;
    }
}