package com.cn.uvfortune.domain.repository.sysrepo;

import com.cn.uvfortune.domain.model.security.Role;
import org.springframework.stereotype.Component;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/19 16:49
 * @Description: 角色信息操作
 */
@Component
public interface RoleRepo {

    /**
     * 删除角色信息
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(int id);

    /**
     * 添加角色信息
     *
     * @param record
     * @return
     */
    int insert(Role record);

    /**
     * 修改角色信息
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Role record);
}
