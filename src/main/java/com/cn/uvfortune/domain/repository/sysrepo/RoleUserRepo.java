package com.cn.uvfortune.domain.repository.sysrepo;

import com.cn.uvfortune.domain.model.security.RoleUser;
import org.springframework.stereotype.Component;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/20 19:44
 * @Description:给用户赋予角色
 */
@Component
public interface RoleUserRepo {

    /**
     * 给用户赋角色
     *
     * @param roleUser
     * @return
     */
    int addUserRole(RoleUser roleUser, String roleid);

    /**
     * 给组织赋角色（就是给组织里面的每个人赋同一个角色）
     *
     * @param roleUser
     * @return
     */
    int addUser(RoleUser roleUser);
}
