package com.cn.uvfortune.domain.factory.sysfactory;

import com.cn.uvfortune.domain.model.security.Role;
import com.cn.uvfortune.infrastructure.repository.repoimpl.sysrepoimpl.QueryRoleRepoImpl;
import com.cn.uvfortune.infrastructure.repository.repoimpl.sysrepoimpl.RoleRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xiaojixiang
 * @version v1.0
 * @description ResourceAggregate工厂
 * @date 18-7-17
 */
@Component
public class RoleAggregateFactory {
    @Autowired
    RoleRepoImpl sysRoleRepo;

    @Autowired
    QueryRoleRepoImpl querySysRoleRepo;

    /**
     * 生成 Role 对象
     *
     * @return
     */
    public Role getRoleInstance() {
        Role role = new Role();
        role.setRoleRepo(this.sysRoleRepo);
        role.setQueryRoleRepo(this.querySysRoleRepo);
        return role;
    }
}
