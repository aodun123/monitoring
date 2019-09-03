package com.cn.uvfortune.domain.factory.sysfactory;

import com.cn.uvfortune.domain.model.security.RolePermission;
import com.cn.uvfortune.infrastructure.repository.repoimpl.sysrepoimpl.QueryRolePermissionRepoImpl;
import com.cn.uvfortune.infrastructure.repository.repoimpl.sysrepoimpl.RolePermissionRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/22 10:39
 * @Description: RolePermission工厂
 */
@Component
public class RolePermissionFactory {
    @Autowired
    RolePermissionRepoImpl rolePermissionRepo;

    @Autowired
    QueryRolePermissionRepoImpl queryRolePermissionRepo;

    /**
     * 生成 Role 对象
     *
     * @return
     */
    public RolePermission getRoleInstance() {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setPermissionRepo(this.rolePermissionRepo);
        rolePermission.setQueryRolePermissionRepo(this.queryRolePermissionRepo);
        return rolePermission;
    }
}
