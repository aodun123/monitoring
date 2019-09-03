package com.cn.uvfortune.infrastructure.repository.repoimpl.sysrepoimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.cn.uvfortune.domain.model.security.Role;
import com.cn.uvfortune.domain.repository.sysrepo.QueryRoleRepo;
import com.cn.uvfortune.infrastructure.repository.entity.parameter.Pager;
import com.cn.uvfortune.infrastructure.repository.entity.Pages;
import com.cn.uvfortune.infrastructure.repository.mapper.sysmapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/20 16:56
 * @Description:
 */
@Component
public class QueryRoleRepoImpl implements QueryRoleRepo {

    @Autowired
    SysRoleMapper sysRoleMapper;

    /**
     * 分页查询角色信息
     *
     * @param pager
     * @return
     */
    @Override
    public Pages selectrole(Pager pager) {
        PageHelper.startPage(pager.getPage(), pager.getLimit());
        List<Role> userDomains = sysRoleMapper.selectrole(pager.getsKey());
        PageInfo result = new PageInfo(userDomains);
        Pages pages = new Pages();
        pages.setCount(result.getTotal());
        pages.setData(result.getList());
        return pages;
    }

    /**
     * 根据id查询角色实体
     *
     * @param id
     * @return
     */
    @Override
    public Role selectByPrimaryKey(int id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询全部角色
     *
     * @return
     */
    @Override
    public List<Role> selectAllRole() {
        List<Role> roles = sysRoleMapper.selectAll();
        return sysRoleMapper.selectAll();
    }
}
