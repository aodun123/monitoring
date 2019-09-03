package com.cn.uvfortune.domain.repository.sysrepo;

import com.cn.uvfortune.domain.model.security.RoleUser;

import java.util.List;

/**
 * @Auther: hancunyan
 * @Date: 2018/8/2 11:29
 * @Description: 角色查询
 */
public interface QueryRoleUserRepo {

    /**
     * 根据机构id查询对应的角色信息
     *
     * @param agencyId
     * @return
     */
    List<RoleUser> queryRoleByAid(int agencyId);
}
