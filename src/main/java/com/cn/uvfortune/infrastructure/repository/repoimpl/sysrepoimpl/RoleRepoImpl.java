package com.cn.uvfortune.infrastructure.repository.repoimpl.sysrepoimpl;

import com.cn.uvfortune.domain.model.security.Role;
import com.cn.uvfortune.domain.repository.sysrepo.RoleRepo;
import com.cn.uvfortune.infrastructure.repository.entity.SysRole;
import com.cn.uvfortune.infrastructure.repository.mapper.sysmapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/20 10:42
 * @Description:
 */
@Component
public class RoleRepoImpl implements RoleRepo {

    @Autowired
    SysRoleMapper sysRoleMapper;

    /**
     * 删除角色信息
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(int id) {
        return sysRoleMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加角色信息
     *
     * @param record
     * @return
     */
    @Override
    public int insert(Role record) {
        SysRole sysRole = new SysRole();
        sysRole.setName(record.getRolename());
        sysRole.setRoledescription(record.getRoledescription());
        return sysRoleMapper.insert(sysRole);
    }

    /**
     * 修改角色信息
     *
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKey(Role record) {
        SysRole sysRole = new SysRole();
        sysRole.setId(record.getId());
        sysRole.setName(record.getRolename());
        sysRole.setRoledescription(record.getRoledescription());
        return sysRoleMapper.updateByPrimaryKey(sysRole);
    }

}
