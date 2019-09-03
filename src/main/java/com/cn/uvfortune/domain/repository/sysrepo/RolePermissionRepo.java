package com.cn.uvfortune.domain.repository.sysrepo;


import com.cn.uvfortune.domain.model.security.RolePermission;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/21 19:17
 * @Description: 角色权限操作
 */
public interface RolePermissionRepo {

    /**
     * 给角色赋予权限
     *
     * @param rolePermission
     * @return
     */
    int addPermission(RolePermission rolePermission, String resourceId);


}
