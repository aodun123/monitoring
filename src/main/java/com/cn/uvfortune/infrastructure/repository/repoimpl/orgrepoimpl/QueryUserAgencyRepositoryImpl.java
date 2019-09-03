package com.cn.uvfortune.infrastructure.repository.repoimpl.orgrepoimpl;

import com.cn.uvfortune.domain.repository.orgrepo.QueryUserAgencyRepository;
import com.cn.uvfortune.infrastructure.repository.entity.SysOrgAgency;
import com.cn.uvfortune.infrastructure.repository.entity.SysUserAgency;
import com.cn.uvfortune.infrastructure.repository.entity.TreeNode.ZNodes;
import com.cn.uvfortune.infrastructure.repository.mapper.orgmapper.OrgMapper;
import com.cn.uvfortune.infrastructure.repository.mapper.orgmapper.SysUserAgencyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: xiaojixiang
 * @version: v1.0
 * @description: com.zzbj.organization.domain.repository
 * @date:18-7-5
 */
@Component
public class QueryUserAgencyRepositoryImpl implements QueryUserAgencyRepository {

    @Autowired
    SysUserAgencyMapper sysUserAgencyMapper;

    @Autowired
    OrgMapper orgMapper;

    /**
     * 查询选中机构下的机构用户信息
     *
     * @param sysAgencyId
     * @return List
     */
    @Override
    public List<ZNodes> getUserAgency(Integer sysAgencyId) {
        return sysUserAgencyMapper.getUserAgency(sysAgencyId);
    }

    @Override
    public List<SysUserAgency> getSysAgency(int userid) {
        return sysUserAgencyMapper.getSysAgencyId(userid);
    }

    /**
     * 根据用户id查询组织机构
     *
     * @param userid
     * @return
     */
    @Override
    public List<SysOrgAgency> queryUserAgencyById(Integer userid) {
        return orgMapper.queryUserAgencyById(userid);
    }

    @Override
    public List<SysOrgAgency> queryUserAgencyall() {
        return orgMapper.queryUserAgencyall();
    }
}
