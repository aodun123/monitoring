package com.cn.uvfortune.application.service.security;

import com.cn.uvfortune.domain.factory.sysfactory.UserFactory;
import com.cn.uvfortune.domain.model.org.OrgUser;
import com.cn.uvfortune.domain.model.security.RoleUser;
import com.cn.uvfortune.infrastructure.repository.entity.SysUser;
import com.cn.uvfortune.infrastructure.repository.entity.parameter.Pager;
import com.cn.uvfortune.infrastructure.repository.entity.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/24 19:21
 * @Description: 用户信息管理
 */
@Service
public class UserService {

    @Autowired
    UserFactory userFactory;

    /**
     * 新增组织人员
     *
     * @param user
     * @return
     */
    public int addUser(OrgUser user) {
        OrgUser orgUser = userFactory.getUserInstance();
        return orgUser.addUser(user);
    }

    /**
     * 删除组织人员
     *
     * @param id 人员id
     * @return
     */
    public int deleteUser(int id) {
        OrgUser orgUser = userFactory.getUserInstance();
        return orgUser.delUser(id);
    }

    /**
     * 修改组织人员
     *
     * @param user
     * @return
     */
    public int updateUser(OrgUser user) {
        OrgUser orgUser = userFactory.getUserInstance();
        return orgUser.upUser(user);
    }

    /**
     * 查询所有组织人员
     *
     * @param
     * @return
     */
    public Pages getAllUser(Pager pager, String sex, String status, String userNumber) {
        OrgUser orgUser = userFactory.getUserInstance();
        return orgUser.selectAll(pager, sex, status, userNumber);
    }

    /**
     * 查询所有人员
     *
     * @param
     * @return
     */
    public List<SysUser> getAllUser(int sysAgencyId) {
        OrgUser orgUser = userFactory.getUserInstance();
        return orgUser.selectAllUser(sysAgencyId);
    }

    /**
     * 查询组织人员
     *
     * @param userid 人员id
     * @return
     */
    public OrgUser getUserById(int userid) {
        OrgUser orgUser = userFactory.getUserInstance();
        return orgUser.selectUserById(userid);
    }

    /**
     * 批量删除用户信息
     *
     * @param ids
     * @return
     */
    public int deleteBctch(String ids) {
        OrgUser orgUser = userFactory.getUserInstance();
        return orgUser.deletebatch(ids);
    }

    /**
     * 查询用户拥有的角色
     *
     * @param userid
     * @return
     */
    public List<RoleUser> queryRole(int userid) {
        OrgUser orgUser = userFactory.getUserInstance();
        return orgUser.queryRole(userid);
    }

    /**
     * 重置密码
     *
     * @param user
     * @return
     */
    public int upPassword(OrgUser user) {
        OrgUser orgUser = userFactory.getUserInstance();
        return orgUser.upPassword(user);
    }

}
