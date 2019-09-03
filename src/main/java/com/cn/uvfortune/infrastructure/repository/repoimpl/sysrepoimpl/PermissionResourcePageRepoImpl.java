package com.cn.uvfortune.infrastructure.repository.repoimpl.sysrepoimpl;

import com.cn.uvfortune.domain.model.security.ResourcesPage;
import com.cn.uvfortune.domain.repository.sysrepo.PermissionResourcePageRepo;
import com.cn.uvfortune.infrastructure.repository.entity.SysPage;
import com.cn.uvfortune.infrastructure.repository.mapper.sysmapper.SysPageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/21 11:56
 * @Description: 页面
 */
@Component
public class PermissionResourcePageRepoImpl implements PermissionResourcePageRepo {
    @Autowired
    SysPageMapper sysPageMapper;

    /**
     * 删除页面信息
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(int id) {
        return sysPageMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加页面信息
     *
     * @param resourcesPage
     * @return
     */
    @Override
    public int insert(ResourcesPage resourcesPage) {
        SysPage sysPage = new SysPage();
        sysPage.setName(resourcesPage.getName());
        sysPage.setUrl(resourcesPage.getUrl());
        sysPage.setDescritpion(resourcesPage.getDescritpion());
        sysPage.setPid(resourcesPage.getPid());
        sysPage.setPageOrder(resourcesPage.getPageOrder());
        sysPage.setPageType(resourcesPage.getPageType());
        sysPage.setPageJb(resourcesPage.getPageJb());
        sysPage.setPageOrder("1");
        return sysPageMapper.insert(sysPage);
    }

    /**
     * 修改页面信息
     *
     * @param resourcesPage
     * @return
     */
    @Override
    public int updateByPrimaryKey(ResourcesPage resourcesPage) {
        SysPage sysPage = new SysPage();
        sysPage.setId(resourcesPage.getId());
        sysPage.setName(resourcesPage.getName());
        sysPage.setUrl(resourcesPage.getUrl());
        sysPage.setDescritpion(resourcesPage.getDescritpion());
        sysPage.setPid(resourcesPage.getPid());
        sysPage.setPageOrder(resourcesPage.getPageOrder());
        sysPage.setPageType(resourcesPage.getPageType());
        sysPage.setPageJb(resourcesPage.getPageJb());
        return sysPageMapper.updateByPrimaryKey(sysPage);
    }
}
