package com.cn.uvfortune.domain.repository.orgrepo;

import java.util.List;

/**
 * @author: create by renpengfei
 * @version: v1.0
 * @description: 机构用户领域仓储接口，根据需求自定义相关功能
 * @date:18-7-5
 */
public interface IUserAgencyRepository {

    /**
     * 添加机构用户
     *
     * @param list
     */
    int addUserAgency(List list);

    /**
     * 删除某机构用户
     *
     * @param id
     */
    int deleteUserAgency(Integer id);

    /**
     * 修改机构用户
     *
     * @param list
     */
    int updUserAgency(List list);


}
