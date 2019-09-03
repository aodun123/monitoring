package com.cn.uvfortune.domain.repository.orgrepo;

import com.cn.uvfortune.infrastructure.repository.entity.SysOrg;
import com.cn.uvfortune.infrastructure.repository.entity.SysOrgAgency;
import com.cn.uvfortune.infrastructure.repository.entity.TreeNode.ZNodes;

import java.util.List;

/**
 * @author: create by xiaojixiang
 * @version: v1.0
 * @description: 组织领域仓储接口，根据需求自定义相关功能
 * @date:18-7-5
 */
public interface QueryOrgRepository {

    /**
     * 根据org id获取Org resource 资源
     *
     * @param id
     * @return
     */
    SysOrg getOrgById(Integer id);

    /**
     * 根据org name获取Org resource 资源
     *
     * @param orgname
     * @return List
     */
    List<SysOrg> getOrgByName(String orgname);

    /**
     * 获取所有组织树节点
     *
     * @return List
     */
    List<ZNodes> getOrgNodes();

    /**
     * 获取用户所属组织
     *
     * @return List
     */
    List<ZNodes> getOrgNodesByUser(String userId);

    /**
     * 获取所有机构树节点
     *
     * @return List
     */
    List<ZNodes> getOrgAgencyNodes();

    /**
     * 获取组织下的机构树节点
     *
     * @return List
     */
    List<ZNodes> getOrgAgencyNodesByOrgId(int orgId);

    /**
     * 查询机构
     *
     * @param orgname
     * @return List<SysOrgAgency>
     */
    public List<SysOrgAgency> getOrgAgencyByName(String orgname);

    /**
     * 查询所属机构
     *
     * @param treeId 树节点
     * @param pId    树节点父节点
     * @return List<SysOrgAgency>
     */
    public List<SysOrgAgency> queryOrgAgencyByTreeId(String treeId, String pId);

    /**
     * 查询机构
     *
     * @param
     * @return SysOrgAgency
     */
    public SysOrgAgency getOrgAgencyById(Integer id);

    /**
     * 查询组织下的机构
     *
     * @param
     * @return List<SysOrgAgency>
     */
    public List<SysOrgAgency> getOrgAgencyByOrgId(Integer id);
}
