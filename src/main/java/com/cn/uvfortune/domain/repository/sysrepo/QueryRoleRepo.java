package com.cn.uvfortune.domain.repository.sysrepo;

import com.cn.uvfortune.domain.model.security.Role;
import com.cn.uvfortune.infrastructure.repository.entity.parameter.Pager;
import com.cn.uvfortune.infrastructure.repository.entity.Pages;

import java.util.List;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/20 16:55
 * @Description:角色信息查询
 */

public interface QueryRoleRepo {


    /**
     * 分页查询角色信息
     *
     * @param pager
     * @return
     */
    Pages selectrole(Pager pager);

    /**
     * 根据id查询角色实体
     *
     * @param id
     * @return
     */
    Role selectByPrimaryKey(int id);

    /**
     * 查询全部角色
     *
     * @return
     */
    List<Role> selectAllRole();
}
