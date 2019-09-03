package com.cn.uvfortune.infrastructure.repository.mapper.sysmapper;

import com.cn.uvfortune.domain.model.security.ResourceButton;
import com.cn.uvfortune.domain.model.security.ResourcesPage;
import com.cn.uvfortune.infrastructure.repository.entity.SysPage;
import com.cn.uvfortune.infrastructure.repository.entity.SysPermissionRole;
import com.cn.uvfortune.infrastructure.repository.entity.Sys_Resources_Operation;
import com.cn.uvfortune.infrastructure.repository.entity.TreeNode.ZNodes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysPermissionRoleMapper {
    // 删除角色下的对应权限信息
    int deleteByPrimaryKey(@Param("id") Integer id, @Param("retype") String retype);

    // 根据角色id查询权限信息
    List<SysPermissionRole> selectByPrimaryKey(@Param("id") Integer id, @Param("retype") String retype);

    // 批量添加权限信息
    int addrolepermissionBatch(List<SysPermissionRole> list);

    // 根据角色id查询角色下的页面
    List<ZNodes> selectPageByRoleId(@Param("roleid") Integer roleid);

    // 查询全部页面信息
    List<ZNodes> selectAllPage();

    // 根据用户id 查询用户所拥有的权限页面
    List<ResourcesPage> selectPageByUserId(Integer userid);

    // 根据角色id查询角色下对应页面的按钮信息
    List<ResourceButton> selectOpeByRoleId(@Param("roleid") Integer roleid);

    // 查询全部按钮信息
    List<ResourceButton> selectAllOperation();

    List<SysPage> findByAdminUserId(Integer userid);

    List<Sys_Resources_Operation> findByOperation(Integer userid);
}