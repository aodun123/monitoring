package com.cn.uvfortune.domain.model.org;

import com.cn.uvfortune.domain.repository.orgrepo.IOrgRepository;
import com.cn.uvfortune.domain.repository.orgrepo.QueryOrgRepository;
import com.cn.uvfortune.infrastructure.repository.entity.SysOrg;
import com.cn.uvfortune.infrastructure.repository.entity.SysOrgAgency;
import com.cn.uvfortune.infrastructure.repository.entity.TreeNode.ZNodes;

import java.util.List;

/**
 * @author: xiaojixiang
 * @version: v1.0
 * @description: 组织（聚合根）
 * @date:18-7-5
 */
public class Org {

    /**
     * 组织D
     */
    private Integer id;

    /**
     * 组织名称
     */
    private String orgname;

    /**
     * 组织描述
     */
    private String orgdescription;

    /**
     * 机构单元集合
     */
    private List<OrgAgency> orgAgencyList;

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
        this.orgname = orgname;
    }

    public String getOrgdescription() {
        return orgdescription;
    }

    public void setOrgdescription(String orgdescription) {
        this.orgdescription = orgdescription;
    }

    public List<OrgAgency> getOrgAgencyList() {
        return orgAgencyList;
    }

    public void setOrgAgencyList(List<OrgAgency> orgAgencyList) {
        this.orgAgencyList = orgAgencyList;
    }

    IOrgRepository orgRepository;

    QueryOrgRepository queryOrgRepository;

    public void setOrgRepository(IOrgRepository orgRepository) {
        this.orgRepository = orgRepository;
    }

    public void setQueryOrgRepository(QueryOrgRepository queryOrgRepository) {
        this.queryOrgRepository = queryOrgRepository;
    }

    /**
     * 添加组织
     *
     * @param org
     * @return
     */
    public int addOrganization(Org org) {
        return orgRepository.add(org);
    }

    /**
     * 删除组织
     *
     * @param id
     * @return
     */
    public int deleteOrganization(String id) {
        return orgRepository.delete(id);
    }

    /**
     * 修改组织
     *
     * @param org
     * @return
     */
    public int updateOrganization(Org org) {
        return orgRepository.update(org);
    }

    /**
     * 查询组织
     *
     * @param id
     * @return
     */
    public SysOrg queryOrganizationById(Integer id) {
        return queryOrgRepository.getOrgById(id);
    }

    /**
     * 查询组织
     *
     * @param orgname
     * @return
     */
    public List<SysOrg> queryOrganizationByName(String orgname) {
        return queryOrgRepository.getOrgByName(orgname);
    }

    /**
     * 查询组织树节点
     *
     * @return
     */
    public List<ZNodes> getOrgNodes() {
        return queryOrgRepository.getOrgNodes();
    }

    /**
     * 查询用户所属组织
     *
     * @return
     */
    public List<ZNodes> getOrgNodesByUser(String userId) {
        return queryOrgRepository.getOrgNodesByUser(userId);
    }

    /**
     * 查询机构树节点
     *
     * @return
     */
    public List<ZNodes> getOrgAgencyNodes() {
        return queryOrgRepository.getOrgAgencyNodes();
    }

    /**
     * 查询组织下的机构树节点
     *
     * @return
     */
    public List<ZNodes> getOrgAgencyNodesByOrgId(int orgId) {
        return queryOrgRepository.getOrgAgencyNodesByOrgId(orgId);
    }

    /**
     * 向组织添加机构
     *
     * @param orgAgency
     * @return 新增的组织机构ID
     */
    public int addOrgAgency(OrgAgency orgAgency) {
        return orgRepository.addOrgAgency(orgAgency);
    }

    /**
     * 修改某个组织的机构信息
     *
     * @param orgAgency
     * @return
     */
    public int updatedOrgAgency(OrgAgency orgAgency) {
        return orgRepository.updateOrgAgency(orgAgency);
    }

    /**
     * 删除某个组织的机构
     *
     * @return
     */
    public int deleteOrgAgency(String orgAgencyId) {
        return orgRepository.deleteOrgAgency(orgAgencyId);
    }

    /**
     * 删除某个组织的机构
     *
     * @return
     */
    public int deleteOrgAgencyByOrgId(String orgId) {
        return orgRepository.deleteOrgAgencyByOrgId(orgId);
    }

    /**
     * 查询组织
     *
     * @param id
     * @return
     */
    public SysOrgAgency queryOrgAgencyById(Integer id) {
        return queryOrgRepository.getOrgAgencyById(id);
    }

    /**
     * 查询组织
     *
     * @param orgId
     * @return
     */
    public List<SysOrgAgency> queryOrgAgencyByOrgId(Integer orgId) {
        return queryOrgRepository.getOrgAgencyByOrgId(orgId);
    }

    /**
     * 查询组织
     *
     * @param orgname
     * @return
     */
    public List<SysOrgAgency> queryOrgAgencyByName(String orgname) {
        return queryOrgRepository.getOrgAgencyByName(orgname);
    }

    /**
     * 查询所属组织
     *
     * @param treeId 树节点
     * @param pId    树节点父节点
     * @return
     */
    public List<SysOrgAgency> queryOrgAgencyByTreeId(String treeId, String pId) {
        return queryOrgRepository.queryOrgAgencyByTreeId(treeId, pId);
    }
}
