package com.cn.uvfortune.infrastructure.repository.repoimpl.sysrepoimpl;

import com.cn.uvfortune.infrastructure.repository.entity.parameter.Pager;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.cn.uvfortune.domain.repository.sysrepo.QueryPermissionResourcePageRepo;
import com.cn.uvfortune.infrastructure.repository.entity.Sys_Resources_Operation;
import com.cn.uvfortune.infrastructure.repository.entity.Pages;
import com.cn.uvfortune.infrastructure.repository.entity.SysPage;
import com.cn.uvfortune.infrastructure.repository.mapper.sysmapper.SysPageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/21 11:58
 * @Description: 页面信息查询
 */
@Component
public class QueryPermissionResourcePageRepoImpl implements QueryPermissionResourcePageRepo {
    @Autowired
    SysPageMapper sysPageMapper;

    /**
     * 分页查询页面信息
     *
     * @param pager
     * @return
     */
    @Override
    public Pages selectPages(Pager pager) {
        PageHelper.startPage(pager.getPage(), pager.getLimit());
        List<SysPage> userDomains = sysPageMapper.selectPages(pager.getsKey());
        PageInfo result = new PageInfo(userDomains);
        Pages pages = new Pages();
        pages.setData(result.getList());
        pages.setCount(result.getTotal());
        return pages;
    }

    /**
     * 查询全部页面信息
     *
     * @return
     */
    @Override
    public List<SysPage> findAll() {
        return sysPageMapper.selectAllPages();
    }

    /**
     * 根据用户id查询该用户所拥有的权限页面
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysPage> findByAdminUserId(String userId) {
        List<SysPage> byAdminUserId = sysPageMapper.findByAdminUserId(userId);
        return byAdminUserId;
    }

    @Override
    public List<SysPage> queryType() {
        return sysPageMapper.queryType();
    }

    @Override
    public List<Sys_Resources_Operation> findAllOperation() {
        return sysPageMapper.findAllOperation();
    }
}
