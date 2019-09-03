package com.cn.uvfortune.domain.repository.sysrepo;

import com.cn.uvfortune.domain.model.org.OrgUser;
import com.cn.uvfortune.domain.model.security.RoleUser;
import com.cn.uvfortune.infrastructure.repository.entity.SysUser;
import com.cn.uvfortune.infrastructure.repository.entity.parameter.Pager;
import com.cn.uvfortune.infrastructure.repository.entity.Pages;

import java.util.List;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/24 18:56
 * @Description: 用户信息查询
 */
public interface QueryUserRepo {
    /**
     * 有Person resource资源
     *
     * @return
     */
    Pages queryAllUser(Pager pager, String sex, String status, String userNumber);

    /**
     * 有Person resource资源
     *
     * @return
     */
    List<SysUser> queryAllUser(int sysAgencyId);

    /**
     * 根据person id获取Person resource 资源
     *
     * @param userid
     * @return
     */
    OrgUser queryPersonById(Integer userid);

    /**
     * 查询用户拥有的角色
     *
     * @param userid
     * @return
     */
    List<RoleUser> queryRoleByUserId(Integer userid);

    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    SysUser queryUser(String username);

}
