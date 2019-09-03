package com.cn.uvfortune.domain.model.security;

import com.cn.uvfortune.domain.repository.sysrepo.QueryRoleRepo;
import com.cn.uvfortune.domain.repository.sysrepo.RoleRepo;
import com.cn.uvfortune.infrastructure.repository.entity.parameter.Pager;
import com.cn.uvfortune.infrastructure.repository.entity.Pages;

import java.util.List;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/19 16:47
 * @Description: 角色模型
 */
public class Role {

    private Integer id; // 角色id

    private String rolename; // 角色名称

    private String roledescription; // 角色描述

    //角色查询
    QueryRoleRepo queryRoleRepo;

    // 角色信息分页查询
    public Pages rolePageInfo(Pager pager) {
        return queryRoleRepo.selectrole(pager);
    }

    // 根据id查询角色实体
    public Role queryRoleById(int id) {
        return queryRoleRepo.selectByPrimaryKey(id);
    }

    // 查询全部角色
    public List<Role> selectRoleAll() {
        return queryRoleRepo.selectAllRole();
    }


    /**
     * 角色模型功能
     */
    RoleRepo roleRepo;

    /**
     * 添加角色信息
     */
    public int insertRole(Role role) {
        return roleRepo.insert(role);
    }

    /**
     * 删除角色信息
     *
     * @param id
     * @return
     */
    public int delRole(int id) {
        return roleRepo.deleteByPrimaryKey(id);
    }

    /**
     * 修改角色信息
     *
     * @param role
     * @return
     */
    public int upRole(Role role) {
        return roleRepo.updateByPrimaryKey(role);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRoledescription() {
        return roledescription;
    }

    public void setRoledescription(String roledescription) {
        this.roledescription = roledescription == null ? null : roledescription.trim();
    }

    public RoleRepo getRoleRepo() {
        return roleRepo;
    }

    public void setRoleRepo(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public QueryRoleRepo getQueryRoleRepo() {
        return queryRoleRepo;
    }

    public void setQueryRoleRepo(QueryRoleRepo queryRoleRepo) {
        this.queryRoleRepo = queryRoleRepo;
    }
}
