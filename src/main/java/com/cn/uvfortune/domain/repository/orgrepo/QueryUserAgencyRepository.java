package com.cn.uvfortune.domain.repository.orgrepo;

import com.cn.uvfortune.infrastructure.repository.entity.SysOrgAgency;
import com.cn.uvfortune.infrastructure.repository.entity.SysUserAgency;
import com.cn.uvfortune.infrastructure.repository.entity.TreeNode.ZNodes;

import java.util.List;

/**
 * @author: create by xiaojixiang
 * @version: v1.0
 * @description: 组织领域仓储接口，根据需求自定义相关功能
 * @date:18-7-5
 */
public interface QueryUserAgencyRepository {

    /**
     * 根据sysAgencyId获取UserAgency resource 资源
     *
     * @param sysAgencyId
     * @return List
     */
    List<ZNodes> getUserAgency(Integer sysAgencyId);

    List<SysUserAgency> getSysAgency(int userid);

    /**
     * 根据用户id查询组织机构
     *
     * @param userid
     * @return
     */
    List<SysOrgAgency> queryUserAgencyById(Integer userid);

    List<SysOrgAgency> queryUserAgencyall();
}
