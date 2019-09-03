package com.cn.uvfortune.domain.model.security;

import com.cn.uvfortune.domain.repository.sysrepo.PermissionResourcePageRepo;
import com.cn.uvfortune.domain.repository.sysrepo.QueryPermissionResourcePageRepo;
import com.cn.uvfortune.infrastructure.repository.entity.Sys_Resources_Operation;
import com.cn.uvfortune.infrastructure.repository.entity.parameter.Pager;
import com.cn.uvfortune.infrastructure.repository.entity.Pages;
import com.cn.uvfortune.infrastructure.repository.entity.SysPage;

import java.util.List;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/21 11:29
 * @Description: 页面模型
 */
public class ResourcesPage {

    private Integer id;  // 主键id

    private String name; // 页面名称

    private String descritpion; // 页面描述

    private String url; // 页面路径

    private Integer pid; // 父级id

    private String pageOrder; // 页面顺序

    private String pageType;

    private String pageJb;


    public ResourcesPage() {
    }


    // 页面模型功能
    PermissionResourcePageRepo pageRepos;

    // 页面模型查询功能
    QueryPermissionResourcePageRepo queryPermissionResourcePageRepo;


    public List<SysPage> queryType() {
        return queryPermissionResourcePageRepo.queryType();
    }

    // 分页查询页面信息
    public Pages queryPage(Pager pager) {
        return queryPermissionResourcePageRepo.selectPages(pager);
    }

    // 根据用户id查询该用户所拥有的权限页面
    public List<SysPage> findByAdminUserId(String userid) {
        return queryPermissionResourcePageRepo.findByAdminUserId(userid);
    }

    // 查询全部页面信息
    public List<SysPage> findAllPage() {
        return queryPermissionResourcePageRepo.findAll();
    }

    public List<Sys_Resources_Operation> findAllOperation() {
        return queryPermissionResourcePageRepo.findAllOperation();
    }

    // 删除页面信息
    public int delPage(int id) {
        return pageRepos.deleteByPrimaryKey(id);
    }

    // 添加页面信息
    public int addPage(ResourcesPage resourcesPage) {
        return pageRepos.insert(resourcesPage);
    }

    // 修改页面信息
    public int upPage(ResourcesPage resourcesPage) {
        return pageRepos.updateByPrimaryKey(resourcesPage);
    }

    public PermissionResourcePageRepo getPageRepos() {
        return pageRepos;
    }

    public void setPageRepos(PermissionResourcePageRepo pageRepos) {
        this.pageRepos = pageRepos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPageOrder() {
        return pageOrder;
    }


    public void setPageOrder(String pageOrder) {
        this.pageOrder = pageOrder;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public String getPageJb() {
        return pageJb;
    }

    public void setPageJb(String pageJb) {
        this.pageJb = pageJb;
    }

    public QueryPermissionResourcePageRepo getQueryPermissionResourcePageRepo() {
        return queryPermissionResourcePageRepo;
    }

    public PermissionResourcePageRepo getPageRepo() {
        return pageRepos;
    }

    public void setPageRepo(PermissionResourcePageRepo pageRepo) {
        this.pageRepos = pageRepo;
    }

    public void setQueryPermissionResourcePageRepo(QueryPermissionResourcePageRepo queryPermissionResourcePageRepo) {
        this.queryPermissionResourcePageRepo = queryPermissionResourcePageRepo;
    }
}
