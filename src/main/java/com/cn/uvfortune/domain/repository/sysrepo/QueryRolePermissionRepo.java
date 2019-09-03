package com.cn.uvfortune.domain.repository.sysrepo;

import com.cn.uvfortune.domain.model.security.ResourcesPage;
import com.cn.uvfortune.infrastructure.repository.entity.SysPage;
import com.cn.uvfortune.infrastructure.repository.entity.Sys_Resources_Operation;
import com.cn.uvfortune.infrastructure.repository.entity.TreeNode.ZNodes;

import java.util.List;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/28 12:30
 * @Description: 角色权限查询
 */
public interface QueryRolePermissionRepo {

    /**
     * 根据用户id查询该用户所拥有的权限页面
     *
     * @param userId
     * @return
     */
    List<ResourcesPage> selectPageByUserId(int userId);

    /**
     * 根据角色id 查询 角色下的页面信息
     *
     * @param roleid
     * @return
     */
    List<ZNodes> selectPageByRoleId(int roleid);

    /**
     * 查询全部页面
     *
     * @return
     */
    List<ZNodes> selectAllPage();

    /**
     * 根据角色id查询角色下对应页面的按钮信息
     *
     * @param roleid
     * @return
     */
    List selectOpeByRoleid(int roleid);


    /**
     * 查询全部按钮信息
     *
     * @return
     */
    List selectAllOperation();

    /**
     * 根据用户id查询权限页面
     *
     * @param userId
     * @return
     */
    List<SysPage> queryPageById(int userId);


    List<Sys_Resources_Operation> queryOperationById(int userid);

}
