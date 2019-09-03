package com.cn.uvfortune.domain.repository.sysrepo;

import com.cn.uvfortune.domain.model.security.ResourceButton;
import org.springframework.stereotype.Component;

/**
 * @author: hancunyan
 * @Date: 2018/7/21 17:51
 * @Description: 按钮信息操作
 */
@Component
public interface PermissionResourceOperationRepo {

    /**
     * 删除按钮信息
     *
     * @param btnId
     * @return
     */
    int deleteByPrimaryKey(String btnId);

    /**
     * 添加按钮信息
     *
     * @param recourcesOperation
     * @return
     */
    int insert(ResourceButton recourcesOperation);

    /**
     * 修改按钮信息
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(ResourceButton record);
}
