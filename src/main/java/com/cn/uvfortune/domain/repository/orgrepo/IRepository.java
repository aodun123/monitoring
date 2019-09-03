package com.cn.uvfortune.domain.repository.orgrepo;


/**
 * @author: xiaojixiang
 * @version: v1.0
 * @description: 仓储接口，定义公共的泛型GRUD，DDD里面仓储只能对 聚合根 做操作。
 * @date:18-7-5
 */
public interface IRepository<T> {

    /**
     * 添加
     *
     * @param obj
     * @return id
     */
    int add(T obj);

    /**
     * 删除
     *
     * @param id
     * @return id
     */
    int delete(Object id);

    /**
     * 修改
     *
     * @param obj
     * @return id
     */
    int update(T obj);


}
