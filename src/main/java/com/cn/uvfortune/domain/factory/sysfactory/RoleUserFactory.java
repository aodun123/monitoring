package com.cn.uvfortune.domain.factory.sysfactory;

import com.cn.uvfortune.domain.model.security.RoleUser;
import com.cn.uvfortune.infrastructure.repository.repoimpl.sysrepoimpl.QueryRoleUserRepoImpl;
import com.cn.uvfortune.infrastructure.repository.repoimpl.sysrepoimpl.RoleUserRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/20 19:52
 * @Description:RoleUserFactory 工厂
 */
@Component
public class RoleUserFactory {

    @Autowired
    RoleUserRepoImpl roleUserRepo;

    @Autowired
    QueryRoleUserRepoImpl queryRoleUserRepo;

    /**
     * 生成 RoleUser 对象
     *
     * @return
     */
    public RoleUser getRoleUserInstance() {
        RoleUser roleUser = new RoleUser();
        roleUser.setRoleUserRepo(this.roleUserRepo);
        roleUser.setQueryRoleUserRepo(this.queryRoleUserRepo);
        return roleUser;
    }
}
