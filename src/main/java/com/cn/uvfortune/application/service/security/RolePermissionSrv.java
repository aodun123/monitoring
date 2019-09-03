package com.cn.uvfortune.application.service.security;

import com.cn.uvfortune.domain.factory.sysfactory.RolePermissionFactory;
import com.cn.uvfortune.domain.model.security.ResourcesPage;
import com.cn.uvfortune.domain.model.security.RolePermission;
import com.cn.uvfortune.infrastructure.repository.entity.TreeNode.ZNodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/22 10:43
 * @Description: 角色权限服务
 */
@Service
public class RolePermissionSrv {
    @Autowired
    RolePermissionFactory rolePermissionFactory;

    /**
     * 给角色赋予权限
     *
     * @param rolePermission
     * @return
     */
    public int addPermission(RolePermission rolePermission, String resourceId) {
        RolePermission roleInstance = rolePermissionFactory.getRoleInstance();
        return roleInstance.addPermission(rolePermission, resourceId);
    }

    /**
     * 根据角色id查询角色下的页面
     *
     * @param roleid
     * @return
     */
    public List<ZNodes> queryPageById(int roleid) {
        RolePermission roleInstance = rolePermissionFactory.getRoleInstance();
        return roleInstance.queryPageById(roleid);
    }

    /**
     * 查询全部页面信息
     *
     * @return
     */
    public List<ZNodes> queryAllPage() {
        RolePermission roleInstance = rolePermissionFactory.getRoleInstance();
        return roleInstance.queryAllPage();
    }

    /**
     * 根据用户id查询页面信息
     *
     * @param userid
     * @return
     */
    public List<ResourcesPage> selectPageByUserId(int userid) {
        RolePermission roleInstance = rolePermissionFactory.getRoleInstance();
        return roleInstance.selectPageByUserId(userid);
    }

    /**
     * 根据角色id查询角色下对应页面的按钮信息
     *
     * @param roleid
     * @return
     */
    public List selectOpeByRoleid(int roleid) {
        RolePermission roleInstance = rolePermissionFactory.getRoleInstance();
        return roleInstance.selectOpeByRoleid(roleid);
    }

    /**
     * 查询全部按钮信息
     *
     * @return
     */
    public List selectAllOperation() {
        RolePermission roleInstance = rolePermissionFactory.getRoleInstance();
        return roleInstance.selectAllOperation();
    }

}
