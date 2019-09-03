package com.cn.uvfortune.application.service.orgservice;


import com.cn.uvfortune.domain.factory.orgfactory.UserAgencyFactory;
import com.cn.uvfortune.domain.model.org.UserAgency;
import com.cn.uvfortune.infrastructure.log.annotation.Log;
import com.cn.uvfortune.infrastructure.repository.entity.SysOrgAgency;
import com.cn.uvfortune.infrastructure.repository.entity.TreeNode.ZNodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: renpengfei
 * @Date: 2018/7/31 12:19
 * @Description: 机构用户信息管理
 */
@Service
@Component
public class UserAgencyService {

    @Autowired
    UserAgencyFactory userAgencyFactory;

    /**
     * 新增机构用户
     *
     * @param list
     * @return
     */
    @Log(content = "新增机构用户")
    public int addUserAgency(List list) {
        UserAgency userAgency = userAgencyFactory.getInstance();
        return userAgency.addUserAgency(list);
    }

    /**
     * 删除机构用户
     *
     * @param sysAgencyId 机构id
     * @return
     */
    @Log(content = "删除机构用户")
    public int deleteUserAgency(Integer sysAgencyId) {
        UserAgency userAgency = userAgencyFactory.getInstance();
        return userAgency.deleteUserAgency(sysAgencyId);
    }

    /**
     * 修改机构用户
     *
     * @param list
     * @return
     */
    @Log(content = "修改机构用户")
    public int updUserAgency(List list) {
        UserAgency userAgency = userAgencyFactory.getInstance();
        //执行修改组织方法后影响的数据条数
        return userAgency.updUserAgency(list);
    }

    /**
     * 查询机构用户
     *
     * @param sysAgencyId 机构id
     * @return
     */
    public List<ZNodes> getUserAgency(Integer sysAgencyId) {
        UserAgency userAgency = userAgencyFactory.getInstance();
        //查询出的符合条件的人员集合信息
        return userAgency.getUserAgency(sysAgencyId);
    }

    /**
     * 根据用户id查询组织机构
     *
     * @param userid
     * @return
     */
    public List<SysOrgAgency> queryUserAgencyById(Integer userid) {
        UserAgency userAgency = userAgencyFactory.getInstance();
        return userAgency.queryUserAgencyById(userid);
    }

    public List<SysOrgAgency> queryUserAgencyall() {
        UserAgency userAgency = userAgencyFactory.getInstance();
        return userAgency.queryUserAgencyall();
    }

}
