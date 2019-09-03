package com.cn.uvfortune.application.service.security;

import com.cn.uvfortune.domain.factory.sysfactory.RoleAggregateFactory;
import com.cn.uvfortune.domain.model.security.Role;
import com.cn.uvfortune.infrastructure.repository.entity.parameter.Pager;
import com.cn.uvfortune.infrastructure.repository.entity.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/20 10:47
 * @Description:角色信息操作服务
 */
@Service
public class RoleSrv {

    @Autowired
    RoleAggregateFactory roleAggregateFactory;

    /**
     * 添加角色信息
     *
     * @param role
     * @return
     */
    public int insert(Role role) {
        Role roleInstance = roleAggregateFactory.getRoleInstance();
        return roleInstance.insertRole(role);
    }

    /**
     * 删除角色信息
     *
     * @param id
     * @return
     */
    public int delrole(int id) {
        Role roleInstance = roleAggregateFactory.getRoleInstance();
        return roleInstance.delRole(id);
    }

    /**
     * 修改角色信息
     *
     * @param role
     * @return
     */
    public int uprole(Role role) {
        Role roleInstance = roleAggregateFactory.getRoleInstance();
        return roleInstance.upRole(role);
    }

    /**
     * 分页查询角色信息
     *
     * @param pager
     * @return
     */
    public Pages queryRolePageInfo(Pager pager) {
        Role roleInstance = roleAggregateFactory.getRoleInstance();
        return roleInstance.rolePageInfo(pager);
    }

    /**
     * 根据id查询实体
     *
     * @param id
     * @return
     */
    public Role queryrole(int id) {
        Role roleInstance = roleAggregateFactory.getRoleInstance();
        return roleInstance.queryRoleById(id);
    }

    /**
     * 查询全部角色
     *
     * @return
     */
    public List<Role> selectRoleAll() {
        Role roleInstance = roleAggregateFactory.getRoleInstance();
        return roleInstance.selectRoleAll();
    }
}
