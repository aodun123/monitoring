package com.cn.uvfortune.domain.repository.sysrepo;

import com.github.pagehelper.PageInfo;
import com.cn.uvfortune.infrastructure.repository.entity.Sys_Resources_Operation;

import java.util.List;
import java.util.Map;

/**
 * @author: hancunyan
 * @Date: 2018/7/21 18:15
 * @Description: 按钮信息查询
 */
public interface QueryPermissionResourceOperationRepo {

    /**
     * 分页查询按钮信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Sys_Resources_Operation> selectOperation(int pageNum, int pageSize);

    List<Sys_Resources_Operation> queryOpeationById(String userid);


    /**
     * 根据用户id和页面id查询对应权限按钮信息
     *
     * @param userid
     * @param pageid
     * @return
     */
    List<Sys_Resources_Operation> queryOpeationByPageId(String userid, String pageid);
}
