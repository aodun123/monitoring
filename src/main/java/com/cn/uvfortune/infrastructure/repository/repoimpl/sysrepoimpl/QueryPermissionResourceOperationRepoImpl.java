package com.cn.uvfortune.infrastructure.repository.repoimpl.sysrepoimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.cn.uvfortune.domain.repository.sysrepo.QueryPermissionResourceOperationRepo;
import com.cn.uvfortune.infrastructure.repository.entity.Sys_Resources_Operation;
import com.cn.uvfortune.infrastructure.repository.mapper.sysmapper.ResourcesOperationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/21 18:18
 * @Description:
 */
@Component
public class QueryPermissionResourceOperationRepoImpl implements QueryPermissionResourceOperationRepo {
    @Autowired
    ResourcesOperationMapper operationMapper;

    /**
     * 分页查询按钮信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Sys_Resources_Operation> selectOperation(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Sys_Resources_Operation> operation = operationMapper.selectOperation();
        PageInfo pageInfo = new PageInfo(operation);
        return pageInfo;
    }

    /**
     * 根据用户id查询该用户所拥有的权限按钮
     *
     * @param userid
     * @return
     */
    @Override
    public List<Sys_Resources_Operation> queryOpeationById(String userid) {
        return operationMapper.queryOpeationById(userid);
    }

    /**
     * 根据用户id和页面id查询对应权限按钮信息
     *
     * @param userid
     * @param pageid
     * @return
     */
    @Override
    public List<Sys_Resources_Operation> queryOpeationByPageId(String userid, String pageid) {
        return operationMapper.queryOpeationByPageId(userid, pageid);
    }

}
