package com.cn.uvfortune.domain.repository.sysrepo;

import com.cn.uvfortune.infrastructure.repository.entity.Sys_Resources_Operation;
import com.cn.uvfortune.infrastructure.repository.entity.parameter.Pager;
import com.cn.uvfortune.infrastructure.repository.entity.Pages;
import com.cn.uvfortune.infrastructure.repository.entity.SysPage;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/21 11:41
 * @Description:页面信息查询
 */
@Component
public interface QueryPermissionResourcePageRepo {

    /**
     * 分页查询页面信息
     *
     * @param pager
     * @return
     */
    Pages selectPages(Pager pager);

    /**
     * 查询全部页面信息
     *
     * @return
     */
    List<SysPage> findAll();

    /**
     * 根据用户id查询该用户所拥有的权限页面
     *
     * @param userId
     * @return
     */
    List<SysPage> findByAdminUserId(String userId);

    List<SysPage> queryType();

    List<Sys_Resources_Operation> findAllOperation();
}
