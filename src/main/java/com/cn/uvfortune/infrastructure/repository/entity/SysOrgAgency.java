package com.cn.uvfortune.infrastructure.repository.entity;

public class SysOrgAgency {
    private Integer id;

    private Integer orgId;

    private Integer parentId;

    private String agencyName;

    private Integer agencyNum;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName == null ? null : agencyName.trim();
    }

    public Integer getAgencyNum() {
        return agencyNum;
    }

    public void setAgencyNum(Integer agencyNum) {
        this.agencyNum = agencyNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}