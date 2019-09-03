package com.cn.uvfortune.domain.model.org;

import com.cn.uvfortune.domain.repository.orgrepo.IUserAgencyRepository;
import com.cn.uvfortune.domain.repository.orgrepo.QueryUserAgencyRepository;
import com.cn.uvfortune.infrastructure.repository.entity.SysOrgAgency;
import com.cn.uvfortune.infrastructure.repository.entity.SysUserAgency;
import com.cn.uvfortune.infrastructure.repository.entity.TreeNode.ZNodes;

import java.util.List;

public class UserAgency {

    private Integer id;

    private Integer sysUserId;

    private Integer sysAgencyId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Integer sysUserId) {
        this.sysUserId = sysUserId;
    }

    /**
     * 根据用户id查询用户所属机构id
     *
     * @param userid
     * @return
     */
    public List<SysUserAgency> getSysAgency(int userid) {
        return queryUserAgencyRepository.getSysAgency(userid);
    }

    public Integer getSysAgencyId() {
        return sysAgencyId;
    }

    public void setSysAgencyId(Integer sysAgencyId) {
        this.sysAgencyId = sysAgencyId;
    }

    IUserAgencyRepository userAgencyRepository;

    QueryUserAgencyRepository queryUserAgencyRepository;

    public void setUserAgencyRepository(IUserAgencyRepository userAgencyRepository) {
        this.userAgencyRepository = userAgencyRepository;
    }

    public void setQueryUserAgencyRepository(QueryUserAgencyRepository queryUserAgencyRepository) {
        this.queryUserAgencyRepository = queryUserAgencyRepository;
    }

    /**
     * 添加机构用户
     *
     * @param list
     * @return
     */
    public int addUserAgency(List list) {
        return userAgencyRepository.addUserAgency(list);
    }

    /**
     * 删除机构用户
     *
     * @param sysAgencyId
     * @return
     */
    public int deleteUserAgency(Integer sysAgencyId) {
        return userAgencyRepository.deleteUserAgency(sysAgencyId);
    }

    /**
     * 更新机构用户
     *
     * @param list
     * @return
     */
    public int updUserAgency(List list) {
        return userAgencyRepository.updUserAgency(list);
    }

    /**
     * 添加机构用户
     *
     * @param sysAgencyId
     * @return
     */
    public List<ZNodes> getUserAgency(Integer sysAgencyId) {
        return queryUserAgencyRepository.getUserAgency(sysAgencyId);
    }

    /**
     * 根据用户id查询组织机构
     *
     * @param userid
     * @return
     */
    public List<SysOrgAgency> queryUserAgencyById(Integer userid) {
        return queryUserAgencyRepository.queryUserAgencyById(userid);
    }

    public List<SysOrgAgency> queryUserAgencyall() {
        return queryUserAgencyRepository.queryUserAgencyall();
    }
}
