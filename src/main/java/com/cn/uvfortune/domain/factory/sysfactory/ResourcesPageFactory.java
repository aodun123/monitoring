package com.cn.uvfortune.domain.factory.sysfactory;

import com.cn.uvfortune.domain.model.security.ResourcesPage;
import com.cn.uvfortune.infrastructure.repository.repoimpl.sysrepoimpl.PermissionResourcePageRepoImpl;
import com.cn.uvfortune.infrastructure.repository.repoimpl.sysrepoimpl.QueryPermissionResourcePageRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/21 11:55
 * @Description: ResourcesPage工厂
 */
@Component
public class ResourcesPageFactory {

    @Autowired
    PermissionResourcePageRepoImpl pageRepo;

    @Autowired
    QueryPermissionResourcePageRepoImpl queryPermissionResourcePageRepo;

    /**
     * 生成 ResourcePage 对象
     *
     * @return
     */
    public ResourcesPage getPageInstance() {
        ResourcesPage resourcesPage = new ResourcesPage();
        resourcesPage.setPageRepo(this.pageRepo);
        resourcesPage.setQueryPermissionResourcePageRepo(queryPermissionResourcePageRepo);
        return resourcesPage;
    }
}
