package com.cn.uvfortune.domain.repository.orgrepo;

import com.cn.uvfortune.domain.model.org.Org;
import com.cn.uvfortune.domain.model.org.OrgAgency;

/**
 * @author: create by xiaojixiang
 * @version: v1.0
 * @description: 组织领域仓储接口，根据需求自定义相关功能
 * @date:18-7-5
 */
public interface IOrgRepository extends IRepository<Org> {

    /**
     * 删除某机构单元
     *
     * @param id
     */
    int deleteOrgAgency(String id);

    /**
     * 删除某机构下的机构单元
     *
     * @param orgId
     */
    int deleteOrgAgencyByOrgId(String orgId);

    /**
     * 添加一个机构单元
     *
     * @param orgAgency
     */
    int addOrgAgency(OrgAgency orgAgency);

    /**
     * 修改一个机构单元
     *
     * @param orgAgency
     */
    int updateOrgAgency(OrgAgency orgAgency);


}
