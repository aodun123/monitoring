package com.cn.uvfortune.domain.model.security;

import com.cn.uvfortune.domain.repository.sysrepo.QueryRoleUserRepo;
import com.cn.uvfortune.domain.repository.sysrepo.RoleUserRepo;

import java.util.List;

/**
 * @author: hancunyan
 * @Date: 2018/7/20 19:42
 * @Description:用户赋角色模型
 */
public class RoleUser {

    private Integer id;

    private Integer sysUserId;

    private Integer sysRoleId;

    private Integer agencyId;

    public RoleUser() {

    }

    public RoleUser(RoleUserRepo roleUserRepo) {
        this.roleUserRepo = roleUserRepo;
    }

    /**
     * 用户角色模型
     */
    RoleUserRepo roleUserRepo;
    /**
     * 角色信息查询
     */
    QueryRoleUserRepo queryRoleUserRepo;

    /**
     * 给用户赋予角色
     */
    public int addRoleUser(RoleUser roleUser, String roleid) {
        return roleUserRepo.addUserRole(roleUser, roleid);
    }

    /**
     * 给组织赋予角色
     */
    public int addRole(RoleUser roleUser) {
        return roleUserRepo.addUser(roleUser);
    }

    /**
     * 根据机构id查询所属角色信息
     *
     * @param agencyId
     * @return
     */
    public List<RoleUser> queryRoleByAid(int agencyId) {
        return queryRoleUserRepo.queryRoleByAid(agencyId);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Integer sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Integer getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(Integer sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    public RoleUserRepo getRoleUserRepo() {
        return roleUserRepo;
    }

    public void setRoleUserRepo(RoleUserRepo roleUserRepo) {
        this.roleUserRepo = roleUserRepo;
    }

    public QueryRoleUserRepo getQueryRoleUserRepo() {
        return queryRoleUserRepo;
    }

    public void setQueryRoleUserRepo(QueryRoleUserRepo queryRoleUserRepo) {
        this.queryRoleUserRepo = queryRoleUserRepo;
    }
}
