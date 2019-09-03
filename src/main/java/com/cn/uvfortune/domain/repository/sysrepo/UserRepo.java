package com.cn.uvfortune.domain.repository.sysrepo;

import com.cn.uvfortune.domain.model.org.OrgUser;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/24 18:45
 * @Description: 用户信息操作
 */
public interface UserRepo {
    /**
     * 添加用户信息
     *
     * @param orgUser
     * @return
     */
    int addUser(OrgUser orgUser);

    /**
     * 删除用户信息
     *
     * @param id
     * @return
     */
    int delUser(Integer id);

    /**
     * 修改用户信息
     *
     * @param orgUser
     * @return
     */
    int upUser(OrgUser orgUser);

    /**
     * 批量删除用户信息
     *
     * @param ids
     * @return
     */
    int deleteBatch(String ids);

    /**
     * 重置密码
     *
     * @param orgUser
     * @return
     */
    int upPass(OrgUser orgUser);
}
