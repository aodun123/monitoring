package com.cn.uvfortune.infrastructure.repository.repoimpl.sysrepoimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.cn.uvfortune.domain.model.org.OrgUser;
import com.cn.uvfortune.domain.model.security.RoleUser;
import com.cn.uvfortune.domain.repository.sysrepo.QueryUserRepo;
import com.cn.uvfortune.infrastructure.repository.entity.parameter.Pager;
import com.cn.uvfortune.infrastructure.repository.entity.Pages;
import com.cn.uvfortune.infrastructure.repository.entity.SysUser;
import com.cn.uvfortune.infrastructure.repository.mapper.sysmapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/24 19:02
 * @Description:用户信息操作
 */
@Component
public class QueryUserRepoImpl implements QueryUserRepo {
    @Autowired
    SysUserMapper sysUserMapper;

    /**
     * 获取所有Person resource资源
     *
     * @return
     */
    @Override
    public Pages queryAllUser(Pager pager, String sex, String status, String userNumber) {
        Pages pages = new Pages();
        PageHelper.startPage(pager.getPage(), pager.getLimit());
        List<SysUser> userDomains = sysUserMapper.getAllUser(pager.getsKey(), sex, status, userNumber);
        PageInfo result = new PageInfo(userDomains);
        pages.setData(result.getList());
        pages.setCount(result.getTotal());
        return pages;
    }

    /**
     * 获取所有Person resource资源
     *
     * @return
     */
    @Override
    public List<SysUser> queryAllUser(int sysAgencyId) {
        return sysUserMapper.getAllUserList(sysAgencyId);
    }

    /**
     * 根据person id获取Person resource 资源
     *
     * @param userid
     * @return
     */
    @Override
    public OrgUser queryPersonById(Integer userid) {
        return sysUserMapper.getPersonById(userid);
    }

    /**
     * 查询用户拥有的角色
     *
     * @param userid
     * @return
     */
    @Override
    public List<RoleUser> queryRoleByUserId(Integer userid) {
        return sysUserMapper.queryRoleByUserId(userid);
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    @Override
    public SysUser queryUser(String username) {
        return sysUserMapper.queryUser(username);
    }

}
