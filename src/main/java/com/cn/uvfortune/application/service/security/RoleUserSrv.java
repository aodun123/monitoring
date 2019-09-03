package com.cn.uvfortune.application.service.security;

import com.cn.uvfortune.domain.factory.sysfactory.RoleUserFactory;
import com.cn.uvfortune.domain.model.security.RoleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: hancunyan
 * @Date: 2018/7/20 19:55
 * @Description： 用户角色操作服务
 */
@Service
public class RoleUserSrv {
    @Autowired
    RoleUserFactory roleUserFactory;

    /**
     * 给用户赋予角色
     *
     * @param roleUser
     * @return
     */
    public int addRoleUser(RoleUser roleUser, String roleid) {
        RoleUser roleUserInstance = roleUserFactory.getRoleUserInstance();
        return roleUserInstance.addRoleUser(roleUser, roleid);
    }

    /**
     * 给组织赋予角色（给组织下的每个用户赋予同一角色）
     *
     * @param roleUser
     * @return
     */
    public int addRole(RoleUser roleUser) {
        RoleUser roleUserInstance = roleUserFactory.getRoleUserInstance();
        return roleUserInstance.addRole(roleUser);
    }

    /**
     * 根据机构id查询所属角色信息
     *
     * @param agencyId
     * @return
     */
    public List<RoleUser> queryRoleByAid(int agencyId) {
        RoleUser roleUserInstance = roleUserFactory.getRoleUserInstance();
        return roleUserInstance.queryRoleByAid(agencyId);
    }
}
