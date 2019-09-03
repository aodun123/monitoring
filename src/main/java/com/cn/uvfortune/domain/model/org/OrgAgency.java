package com.cn.uvfortune.domain.model.org;

/**
 * @author: xiaojixiang
 * @version: v1.0
 * @description: 机构单元，组织的组成成员之一
 * @date:18-7-5
 */
public class OrgAgency {

    /**
     * 机构 ID
     */
    public Integer id;

    /**
     * 所属组织 id
     */
    public Integer orgId;

    /**
     * 父机构ID
     */
    public Integer parentId;

    /**
     * 机构单元 名称
     */
    public String agencyName;

    /**
     * 机构编号(可用于排序，保留字段)
     */
    public int agencyNum;

    /**
     * 描述
     */
    public String description;

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
        this.agencyName = agencyName;
    }

    public int getAgencyNum() {
        return agencyNum;
    }

    public void setAgencyNum(int agencyNum) {
        this.agencyNum = agencyNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
