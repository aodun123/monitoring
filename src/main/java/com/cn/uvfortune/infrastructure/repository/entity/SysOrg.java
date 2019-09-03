package com.cn.uvfortune.infrastructure.repository.entity;

public class SysOrg {

    private Integer id;

    private String orgname;

    private String orgdescription;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname == null ? null : orgname.trim();
    }

    public String getOrgdescription() {
        return orgdescription;
    }

    public void setOrgdescription(String orgdescription) {
        this.orgdescription = orgdescription == null ? null : orgdescription.trim();
    }
}