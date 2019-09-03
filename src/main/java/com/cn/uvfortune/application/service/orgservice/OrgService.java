package com.cn.uvfortune.application.service.orgservice;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.cn.uvfortune.domain.factory.orgfactory.OrgFactory;
import com.cn.uvfortune.domain.model.org.Org;
import com.cn.uvfortune.domain.model.org.OrgAgency;
import com.cn.uvfortune.infrastructure.log.annotation.Log;
import com.cn.uvfortune.infrastructure.repository.entity.*;
import com.cn.uvfortune.infrastructure.repository.entity.TreeNode.ZNodes;
import com.cn.uvfortune.infrastructure.repository.entity.parameter.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: renpengfei
 * @Date: 2018/7/31 12:19
 * @Description: 组织信息管理
 */
@Service
@Component
public class OrgService {

    @Autowired
    OrgFactory orgFactory;

    /**
     * 新增组织
     *
     * @param o
     * @return
     */
    @Log(content = "新增组织信息")
    public int addOrganization(Org o) {
        Org org = orgFactory.getInstance();
        return org.addOrganization(o);
    }

    /**
     * 删除组织
     *
     * @param id 组织id
     * @return
     */
    @Log(content = "删除组织信息")
    public int deleteOrganization(String id) {
        Org org = orgFactory.getInstance();
        return org.deleteOrganization(id);
    }

    /**
     * 修改组织
     *
     * @param o
     * @return
     */
    @Log(content = "修改组织信息")
    public int updateOrganization(Org o) {
        Org org = orgFactory.getInstance();
        //执行修改组织方法后影响的数据条数
        return org.updateOrganization(o);
    }

    /**
     * 查询组织
     *
     * @param orgname 组织名称
     * @return
     */
    public Pages getOrganizationByName(Pager pager, String orgname) {
        Org org = orgFactory.getInstance();
        Pages pages = new Pages();
        PageHelper.startPage(pager.getPage(), pager.getLimit());
        //查询出的符合条件的人员集合信息
        List<SysOrg> list = org.queryOrganizationByName(orgname);
        PageInfo result = new PageInfo(list);
        pages.setData(result.getList());
        pages.setCount(result.getTotal());
        return pages;
    }

    /**
     * 查询组织
     *
     * @param id 组织id
     * @return
     */
    public SysOrg getOrganizationById(Integer id) {
        Org org = orgFactory.getInstance();
        //查询出的符合条件的人员集合信息
        return org.queryOrganizationById(id);
    }

    /**
     * 新增组织机构
     *
     * @param oa
     * @return
     */
    @Log(content = "新增组织机构信息")
    public int addOrgAgency(OrgAgency oa) {
        Org org = orgFactory.getInstance();
        return org.addOrgAgency(oa);
    }

    /**
     * 删除组织机构
     *
     * @param orgAgencyId 组织机构id
     * @return
     */
    @Log(content = "删除组织机构信息")
    public int deleteOrgAgency(String orgAgencyId) {
        Org org = orgFactory.getInstance();
        return org.deleteOrgAgency(orgAgencyId);
    }

    /**
     * 删除组织下的机构
     *
     * @param orgId 组织id
     * @return
     */
    @Log(content = "删除选中组织下的机构信息")
    public int deleteOrgAgencyByOrgId(String orgId) {
        Org org = orgFactory.getInstance();
        return org.deleteOrgAgencyByOrgId(orgId);
    }

    /**
     * 修改组织机构
     *
     * @param oa
     * @return
     */
    @Log(content = "修改组织机构信息")
    public int updateOrgAgency(OrgAgency oa) {
        Org org = orgFactory.getInstance();
        //执行修改组织方法后影响的数据条数
        return org.updatedOrgAgency(oa);
    }

    /**
     * 查询机构
     *
     * @param agencyName 机构名称
     * @return
     */
    public Pages getOrgAgencyByName(Pager pager, String agencyName) {
        Org org = orgFactory.getInstance();
        Pages pages = new Pages();
        PageHelper.startPage(pager.getPage(), pager.getLimit());
        //查询出的符合条件的机构集合信息
        List<SysOrgAgency> list = org.queryOrgAgencyByName(agencyName);
        PageInfo result = new PageInfo(list);
        pages.setData(result.getList());
        pages.setCount(result.getTotal());
        return pages;
    }

    /**
     * 查询所属机构
     *
     * @param treeId 树节点
     * @param pId    树节点父节点
     * @return
     */
    public Pages getOrgAgencyByTreeId(Pager pager, String treeId, String pId) {
        Org org = orgFactory.getInstance();
        Pages pages = new Pages();
        PageHelper.startPage(pager.getPage(), pager.getLimit());
        //查询出的符合条件的机构集合信息
        List<SysOrgAgency> list = org.queryOrgAgencyByTreeId(treeId, pId);
        PageInfo result = new PageInfo(list);
        pages.setData(result.getList());
        pages.setCount(result.getTotal());
        return pages;
    }

    /**
     * 查询机构
     *
     * @param id 机构id
     * @return
     */
    public SysOrgAgency getOrgAgencyById(Integer id) {
        Org org = orgFactory.getInstance();
        //查询出的符合条件的人员集合信息
        return org.queryOrgAgencyById(id);
    }

    /**
     * 查询组织下所有机构
     *
     * @param
     * @return
     */
    public Pages getOrgAgencyByOrgId(Pager pager, Integer orgId) {
        Org org = orgFactory.getInstance();
        Pages pages = new Pages();
        PageHelper.startPage(pager.getPage(), pager.getLimit());
        //查询出的符合条件的人员集合信息
        List<SysOrgAgency> list = org.queryOrgAgencyByOrgId(orgId);
        PageInfo result = new PageInfo(list);
        pages.setData(result.getList());
        pages.setCount(result.getTotal());
        return pages;
    }

    /**
     * 查询组织的树结构
     *
     * @return
     */
    public List<ZNodes> getOrgNodes() {
        Org org = orgFactory.getInstance();
        return org.getOrgNodes();
    }

    /**
     * 查询用户所属组织
     *
     * @return
     */
    public List<ZNodes> getOrgNodesByUser(String userId) {
        Org org = orgFactory.getInstance();
        return org.getOrgNodesByUser(userId);
    }

    /**
     * 查询组织下的机构的树结构
     *
     * @return
     */
    public List<ZNodes> getOrgAgencyNodes() {
        Org org = orgFactory.getInstance();
        return org.getOrgAgencyNodes();
    }

    /**
     * 查询组织下的机构的树结构
     *
     * @return
     */
    public List<ZNodes> getOrgAgencyNodesByOrgId(int orgId) {
        Org org = orgFactory.getInstance();
        return org.getOrgAgencyNodesByOrgId(orgId);
    }

    /**
     * 查询所有组织
     *
     * @return
     */
    public List<SysOrg> getOrgList() {
        Org org = orgFactory.getInstance();
        //查询出的符合条件的组织集合信息
        return org.queryOrganizationByName("");
    }
}
