package com.cn.uvfortune.infrastructure.repository.mapper.sysmapper;

import com.cn.uvfortune.domain.model.security.RoleUser;
import com.cn.uvfortune.infrastructure.repository.entity.SysRoleUser;
import com.cn.uvfortune.infrastructure.repository.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleUserMapper {
    //根据用户ID删除该用户下的角色信息
    int deleteByPrimaryKey(Integer id);

    // 根据用户id查询该用户下的将角色信息
    List<SysRoleUser> selectByPrimaryKey(Integer id);

    // 根据组织id查询该组织下的角色信息
    List<SysRoleUser> selectByAgencyId(@Param("agencyId") Integer agencyId);

    // 批量添加角色信息
    int insertBatch(List<SysRoleUser> list);

    //  根据组织id删除该组织对应的角色信息
    int deleteByAgencyId(@Param("agencyId") Integer agencyId);

    SysUser findByUserName(@Param("username") String username);

    List<RoleUser> queryRoleByAid(@Param("agencyId") Integer agencyId);
}