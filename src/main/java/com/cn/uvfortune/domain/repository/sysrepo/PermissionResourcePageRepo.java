package com.cn.uvfortune.domain.repository.sysrepo;

import com.cn.uvfortune.domain.model.security.ResourcesPage;
import org.springframework.stereotype.Component;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/21 11:31
 * @Description: 页面信息操作
 */
@Component
public interface PermissionResourcePageRepo {

    /**
     * 删除页面信息
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(int id);

    /**
     * 添加页面信息
     *
     * @param resourcesPage
     * @return
     */
    int insert(ResourcesPage resourcesPage);

    /**
     * 修改页面信息
     *
     * @param resourcesPage
     * @return
     */
    int updateByPrimaryKey(ResourcesPage resourcesPage);
}
