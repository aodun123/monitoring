package com.cn.uvfortune.infrastructure.repository.mapper.orgmapper;

import com.cn.uvfortune.domain.model.org.Org;
import com.cn.uvfortune.domain.model.org.OrgAgency;
import com.cn.uvfortune.infrastructure.repository.entity.SysOrg;
import com.cn.uvfortune.infrastructure.repository.entity.SysOrgAgency;
import com.cn.uvfortune.infrastructure.repository.entity.TreeNode.ZNodes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiaojixiang
 * @version v1.0
 * @description com.zzbj.organization.infrastructure.repository
 * @date 18-7-6
 */

@Mapper
public interface OrgMapper {

    /**
     * 该工程使用XML方式放置 sql文件!
     * 通过组织名称查询符合条件的组织
     *
     * @param orgname
     * @return List<SysOrg>
     */
    List<SysOrg> getOrgByName(@Param("orgname") String orgname);

    /**
     * 该工程使用XML方式放置 sql文件!
     * 查询所有组织树节点
     *
     * @return List<ZNodes>
     */
    List<ZNodes> getOrgNodes();

    /**
     * 该工程使用XML方式放置 sql文件!
     * 查询用户所属组织
     *
     * @return List<ZNodes>
     */
    List<ZNodes> getOrgNodesByUser(String userId);

    /**
     * 该工程使用XML方式放置 sql文件!
     * 查询所有机构树节点
     *
     * @return List<ZNodes>
     */
    List<ZNodes> getOrgAgencyNodes();

    /**
     * 该工程使用XML方式放置 sql文件!
     * 查询组织下的机构树节点
     *
     * @return List<ZNodes>
     */
    List<ZNodes> getOrgAgencyNodesByOrgId(int orgId);

    /**
     * 该工程使用XML方式放置 sql文件!
     * 查询组织下的二级机构树节点
     *
     * @return List<ZNodes>
     */
    List<ZNodes> getAgencyNodesByOrgId(int orgId);

    /**
     * 该工程使用XML方式放置 sql文件!
     * 通过组织id查询组织
     *
     * @param
     * @return SysOrg
     */
    SysOrg getOrgById(@Param("id") Integer id);

    /**
     * 该工程使用XML方式放置 sql文件!
     * 新增组织信息
     *
     * @param org
     * @return
     */
    Integer insertOrgInfo(Org org);

    /**
     * 该工程使用XML方式放置 sql文件!
     * 修改组织信息
     *
     * @param org
     * @return
     */
    Integer updateOrgInfo(Org org);

    /**
     * 该工程使用XML方式放置 sql文件!
     * 删除组织信息
     *
     * @param list 选中组织id集合
     * @return
     */
    Integer deleteOrgInfo(List list);


    /**
     * 该工程使用XML方式放置 sql文件!
     * 通过组织名称查询符合条件的机构
     *
     * @param agencyName
     * @return List<OrgAgency>
     */
    List<SysOrgAgency> getOrgAgencyByName(@Param("agencyName") String agencyName);

    /**
     * 该工程使用XML方式放置 sql文件!
     * 通过组织树节点查询符合条件的机构
     *
     * @param treeId 树节点
     * @param pId    树节点父节点
     * @return List<OrgAgency>
     */
    List<SysOrgAgency> getOrgAgencyByTreeId(@Param("treeId") String treeId, @Param("pId") String pId);

    /**
     * 该工程使用XML方式放置 sql文件!
     * 通过组织id查询组织下机构
     *
     * @param orgId
     * @return List<OrgAgency>
     */
    List<SysOrgAgency> getOrgAgencyByOrgId(@Param("orgId") Integer orgId);

    /**
     * 该工程使用XML方式放置 sql文件!
     *
     * @param id
     * @return OrgAgency
     */
    SysOrgAgency getOrgAgencyById(@Param("id") Integer id);

    /**
     * 该工程使用XML方式放置 sql文件!
     *
     * @param orgAgency
     * @return
     */
    Integer insertOrgAgencyInfo(OrgAgency orgAgency);

    /**
     * 该工程使用XML方式放置 sql文件!
     *
     * @param orgAgency
     * @return
     */
    Integer updateOrgAgencyInfo(OrgAgency orgAgency);

    /**
     * 该工程使用XML方式放置 sql文件!
     *
     * @param list 选中机构id集合
     * @return
     */
    Integer deleteOrgAgencyInfo(List list);

    /**
     * 该工程使用XML方式放置 sql文件!
     *
     * @param list 选中组织id集合
     * @return
     */
    Integer deleteOrgAgencyByOrgId(List list);

    /**
     * 根据用户id查询组织机构
     *
     * @param userid
     * @return
     */
    List<SysOrgAgency> queryUserAgencyById(Integer userid);

    List<SysOrgAgency> queryUserAgencyall();
}
