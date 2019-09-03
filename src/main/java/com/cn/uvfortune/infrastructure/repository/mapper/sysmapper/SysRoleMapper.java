package com.cn.uvfortune.infrastructure.repository.mapper.sysmapper;


import com.cn.uvfortune.domain.model.security.Role;
import com.cn.uvfortune.infrastructure.repository.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMapper {
    // 删除角色信息
    int deleteByPrimaryKey(Integer id);

    // 添加角色信息
    int insert(SysRole record);

    // 根据id查询实体
    Role selectByPrimaryKey(Integer id);

    // 修改用户信息
    int updateByPrimaryKey(SysRole record);

    // 分页条件查询角色信息
    List<Role> selectrole(String sKey);

    // 查询全部角色信息
    List<Role> selectAll();
}