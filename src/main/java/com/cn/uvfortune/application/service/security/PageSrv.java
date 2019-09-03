package com.cn.uvfortune.application.service.security;

import com.cn.uvfortune.domain.factory.sysfactory.ResourcesPageFactory;
import com.cn.uvfortune.domain.model.security.ResourcesPage;
import com.cn.uvfortune.infrastructure.repository.entity.parameter.Pager;
import com.cn.uvfortune.infrastructure.repository.entity.Pages;
import com.cn.uvfortune.infrastructure.repository.entity.SysPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/21 12:02
 * @Description: 页面信息操作服务
 */
@Service
public class PageSrv {
    @Autowired
    ResourcesPageFactory resourcesPageFactory;

    /**
     * 添加页面信息
     *
     * @param resourcesPage
     * @return
     */
    public int addPage(ResourcesPage resourcesPage) {
        ResourcesPage pageInstance = resourcesPageFactory.getPageInstance();
        return pageInstance.addPage(resourcesPage);
    }

    /**
     * 修改页面信息
     *
     * @param resourcesPage
     * @return
     */
    public int upPage(ResourcesPage resourcesPage) {
        ResourcesPage pageInstance = resourcesPageFactory.getPageInstance();
        return pageInstance.upPage(resourcesPage);
    }

    /**
     * 删除页面信息
     *
     * @param id
     * @return
     */
    public int delPage(int id) {
        ResourcesPage pageInstance = resourcesPageFactory.getPageInstance();
        int del = pageInstance.delPage(id);
        return del;
    }

    /**
     * 分页查询页面信息
     *
     * @param pager
     * @return
     */
    public Pages selectPage(Pager pager) {
        ResourcesPage pageInstance = resourcesPageFactory.getPageInstance();
        return pageInstance.queryPage(pager);
    }

    /**
     * 查询全部页面信息
     *
     * @return
     */
    public List<SysPage> findAll() {
        ResourcesPage pageInstance = resourcesPageFactory.getPageInstance();
        return pageInstance.findAllPage();
    }

    /**
     * 根据用户id查询该用户所拥有的权限页面
     *
     * @param userid
     * @return
     */
    public List<SysPage> queryPageById(String userid) {
        ResourcesPage pageInstance = resourcesPageFactory.getPageInstance();
        return pageInstance.findByAdminUserId(userid);
    }

    public List<SysPage> queryType() {
        ResourcesPage pageInstance = resourcesPageFactory.getPageInstance();
        return pageInstance.queryType();
    }
}
