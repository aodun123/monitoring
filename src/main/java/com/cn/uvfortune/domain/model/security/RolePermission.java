package com.cn.uvfortune.domain.model.security;

import com.cn.uvfortune.domain.repository.sysrepo.QueryRolePermissionRepo;
import com.cn.uvfortune.domain.repository.sysrepo.RolePermissionRepo;
import com.cn.uvfortune.infrastructure.repository.entity.SysPage;
import com.cn.uvfortune.infrastructure.repository.entity.Sys_Resources_Operation;
import com.cn.uvfortune.infrastructure.repository.entity.TreeNode.ZNodes;

import java.util.List;

/**
 * @author: hancunyan
 * @Date: 2018/7/21 19:07
 * @Description: 角色权限模型
 */
public class RolePermission {

    // 主键id
    private Integer id;

    // 角色id
    private Integer roleId;

    // 权限id
    private Integer resourceId;

    // 权限类型
    private String resourceType;

    // 角色权限模型
    RolePermissionRepo permissionRepo;
    // 角色查询模型
    QueryRolePermissionRepo queryRolePermissionRepo;

    // 给角色赋予权限
    public int addPermission(RolePermission rolePermission, String resourceId) {
        return permissionRepo.addPermission(rolePermission, resourceId);
    }

    /**
     * 根据角色id查询角色下的页面
     */
    public List<ZNodes> queryPageById(int roleid) {
        return queryRolePermissionRepo.selectPageByRoleId(roleid);
    }

    // 查询全部页面信息
    public List<ZNodes> queryAllPage() {
        return queryRolePermissionRepo.selectAllPage();
    }

    // 根据用户id查询页面信息
    public List<ResourcesPage> selectPageByUserId(int userid) {
        return queryRolePermissionRepo.selectPageByUserId(userid);
    }

    // 根据角色id查询角色下对应页面的按钮信息
    public List selectOpeByRoleid(int roleId) {
        return queryRolePermissionRepo.selectOpeByRoleid(roleId);
    }


    public List<SysPage> queryPagesById(int userid) {
        return queryRolePermissionRepo.queryPageById(userid);
    }

    public List<Sys_Resources_Operation> queryOperationById(int userid) {
        return queryRolePermissionRepo.queryOperationById(userid);
    }

    //查询全部按钮信息
    public List selectAllOperation() {
        return queryRolePermissionRepo.selectAllOperation();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType == null ? null : resourceType.trim();
    }

    public RolePermissionRepo getPermissionRepo() {
        return permissionRepo;
    }

    public void setPermissionRepo(RolePermissionRepo permissionRepo) {
        this.permissionRepo = permissionRepo;
    }

    public QueryRolePermissionRepo getQueryRolePermissionRepo() {
        return queryRolePermissionRepo;
    }

    public void setQueryRolePermissionRepo(QueryRolePermissionRepo queryRolePermissionRepo) {
        this.queryRolePermissionRepo = queryRolePermissionRepo;
    }
}
