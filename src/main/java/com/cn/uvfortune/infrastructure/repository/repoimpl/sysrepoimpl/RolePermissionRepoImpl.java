package com.cn.uvfortune.infrastructure.repository.repoimpl.sysrepoimpl;


import com.cn.uvfortune.domain.model.security.RolePermission;
import com.cn.uvfortune.domain.repository.sysrepo.RolePermissionRepo;
import com.cn.uvfortune.infrastructure.repository.entity.SysPermissionRole;
import com.cn.uvfortune.infrastructure.repository.mapper.sysmapper.SysPermissionRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/21 19:50
 * @Description:
 */
@Component
public class RolePermissionRepoImpl implements RolePermissionRepo {

    @Autowired
    SysPermissionRoleMapper sysPermissionRoleMapper;


    /**
     * 给角色赋予权限
     *
     * @param rolePermission
     * @return
     */
    @Override
    public int addPermission(RolePermission rolePermission, String resourceId) {
        String[] split = resourceId.split("、");
        // 查询该角色下的权限
        List<SysPermissionRole> permission = sysPermissionRoleMapper.selectByPrimaryKey(rolePermission.getRoleId(), rolePermission.getResourceType());
        List<SysPermissionRole> list = new ArrayList<>();
        if (permission.size() > 0) {
            // 如果该角色下有权限  先删除
            int a = sysPermissionRoleMapper.deleteByPrimaryKey(rolePermission.getRoleId(), rolePermission.getResourceType());
            if (a > 0) {
                // 删除成功  循环取出权限id  将角色id和 权限id 放到 list集合中  批量添加
                for (int i = 0; i < split.length; i++) {
                    SysPermissionRole resourcesPermission = new SysPermissionRole();
                    String permissionid = split[i];
                    resourcesPermission.setRoleId(rolePermission.getRoleId());
                    resourcesPermission.setResourceId(Integer.valueOf(permissionid));
                    resourcesPermission.setResourceType(rolePermission.getResourceType());

                    list.add(resourcesPermission);
                }
                return sysPermissionRoleMapper.addrolepermissionBatch(list);
            }
            return 0;
        } else {
            // 如果该角色下没有权限  直接循环取出权限id  将角色id和 权限id 放到 list集合中  批量添加
            for (int i = 0; i < split.length; i++) {
                SysPermissionRole resourcesPermission = new SysPermissionRole();
                String permissionid = split[i];
                resourcesPermission.setRoleId(rolePermission.getRoleId());
                resourcesPermission.setResourceId(Integer.valueOf(permissionid));
                resourcesPermission.setResourceType(rolePermission.getResourceType());
                list.add(resourcesPermission);
            }
            return sysPermissionRoleMapper.addrolepermissionBatch(list);
        }
    }
}
