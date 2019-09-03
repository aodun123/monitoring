package com.cn.uvfortune.infrastructure.repository.repoimpl.sysrepoimpl;

import com.cn.uvfortune.domain.model.security.ResourceButton;
import com.cn.uvfortune.domain.model.security.ResourcesPage;
import com.cn.uvfortune.domain.repository.sysrepo.QueryRolePermissionRepo;
import com.cn.uvfortune.infrastructure.repository.entity.SysPage;
import com.cn.uvfortune.infrastructure.repository.entity.Sys_Resources_Operation;
import com.cn.uvfortune.infrastructure.repository.entity.TreeNode.ZNodes;
import com.cn.uvfortune.infrastructure.repository.mapper.sysmapper.SysPermissionRoleMapper;
import com.cn.uvfortune.infrastructure.utils.operation.OpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/28 12:31
 * @Description: 角色权限信息查询
 */
@Component
public class QueryRolePermissionRepoImpl implements QueryRolePermissionRepo {

    @Autowired
    SysPermissionRoleMapper sysPermissionRoleMapper;

    /**
     * 根据用户id查询该用户所拥有的权限页面
     *
     * @param userId
     * @return
     */
    @Override
    public List<ResourcesPage> selectPageByUserId(int userId) {
        return sysPermissionRoleMapper.selectPageByUserId(userId);
    }

    /**
     * 根据角色id 查询 角色下的页面信息
     *
     * @param roleid
     * @return
     */
    @Override
    public List<ZNodes> selectPageByRoleId(int roleid) {
        return sysPermissionRoleMapper.selectPageByRoleId(roleid);
    }

    /**
     * 查询全部页面
     *
     * @return
     */
    @Override
    public List<ZNodes> selectAllPage() {
        return sysPermissionRoleMapper.selectAllPage();
    }

    /**
     * 根据角色id查询角色下对应页面的按钮信息
     *
     * @param roleid
     * @return
     */
    @Override
    public List selectOpeByRoleid(int roleid) {
        List<ResourceButton> op = sysPermissionRoleMapper.selectOpeByRoleId(roleid);
        List<ResourceButton> equal = OpUtil.transFormation(op);
        return equal;
    }

    /**
     * 查询全部按钮信息
     *
     * @return
     */
    @Override
    public List selectAllOperation() {
        List<ResourceButton> operationList = sysPermissionRoleMapper.selectAllOperation();
        List list = OpUtil.transFormation(operationList);
        return list;
    }

    /**
     * 根据用户id查询权限页面
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysPage> queryPageById(int userId) {
        return sysPermissionRoleMapper.findByAdminUserId(userId);
    }

    @Override
    public List<Sys_Resources_Operation> queryOperationById(int userid) {
        return sysPermissionRoleMapper.findByOperation(userid);
    }

}
