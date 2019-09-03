package com.cn.uvfortune.infrastructure.repository.repoimpl.orgrepoimpl;

import com.cn.uvfortune.domain.repository.orgrepo.QueryOrgRepository;
import com.cn.uvfortune.infrastructure.repository.entity.SysOrg;
import com.cn.uvfortune.infrastructure.repository.entity.SysOrgAgency;
import com.cn.uvfortune.infrastructure.repository.entity.TreeNode.ZNodes;
import com.cn.uvfortune.infrastructure.repository.mapper.orgmapper.OrgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: xiaojixiang
 * @version: v1.0
 * @description: com.zzbj.organization.domain.repository
 * @date:18-7-5
 */
@Component
public class QueryOrgRepositoryImpl implements QueryOrgRepository {

    @Autowired
    OrgMapper orgMapper;

    /**
     * 查询组织
     *
     * @param orgname
     * @return List<SysOrg>
     */
    @Override
    public List<SysOrg> getOrgByName(String orgname) {
        return orgMapper.getOrgByName(orgname);
    }

    /**
     * 查询组织树节点
     *
     * @return List<ZNodes>
     */
    @Override
    public List<ZNodes> getOrgNodes() {
        return orgMapper.getOrgNodes();
    }

    /**
     * 查询用户所属组织
     *
     * @return List<ZNodes>
     */
    @Override
    public List<ZNodes> getOrgNodesByUser(String userId) {
        return orgMapper.getOrgNodesByUser(userId);
    }

    /**
     * 查询机构树节点
     *
     * @return List<ZNodes>
     */
    @Override
    public List<ZNodes> getOrgAgencyNodes() {
        return orgMapper.getOrgAgencyNodes();
    }

    /**
     * 查询机构树节点
     *
     * @return List<ZNodes>
     */
    @Override
    public List<ZNodes> getOrgAgencyNodesByOrgId(int orgId) {
        List<ZNodes> list = orgMapper.getOrgAgencyNodesByOrgId(orgId);
        List<ZNodes> list1 = orgMapper.getAgencyNodesByOrgId(orgId);
        list.addAll(list1);
        return list;
    }

    /**
     * 查询组织
     *
     * @param
     * @return Org
     */
    @Override
    public SysOrg getOrgById(Integer id) {
        return orgMapper.getOrgById(id);
    }


    /**
     * 查询机构
     *
     * @param orgname
     * @return List<SysOrgAgency>
     */
    @Override
    public List<SysOrgAgency> getOrgAgencyByName(String orgname) {
        return orgMapper.getOrgAgencyByName(orgname);
    }

    /**
     * 查询所属机构
     *
     * @param treeId 树节点
     * @param pId    树节点父节点
     * @return List<SysOrgAgency>
     */
    @Override
    public List<SysOrgAgency> queryOrgAgencyByTreeId(String treeId, String pId) {
        return orgMapper.getOrgAgencyByTreeId(treeId, pId);
    }

    /**
     * 查询组织下的机构
     *
     * @param
     * @return List<SysOrgAgency>
     */
    @Override
    public List<SysOrgAgency> getOrgAgencyByOrgId(Integer id) {
        return orgMapper.getOrgAgencyByOrgId(id);
    }

    /**
     * 查询机构
     *
     * @param
     * @return Org
     */
    @Override
    public SysOrgAgency getOrgAgencyById(Integer id) {
        return orgMapper.getOrgAgencyById(id);
    }

}
