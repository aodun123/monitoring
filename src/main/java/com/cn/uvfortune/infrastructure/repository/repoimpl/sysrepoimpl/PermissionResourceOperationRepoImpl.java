package com.cn.uvfortune.infrastructure.repository.repoimpl.sysrepoimpl;

import com.cn.uvfortune.domain.model.security.ResourceButton;
import com.cn.uvfortune.domain.repository.sysrepo.PermissionResourceOperationRepo;
import com.cn.uvfortune.infrastructure.repository.entity.Sys_Resources_Operation;
import com.cn.uvfortune.infrastructure.repository.mapper.sysmapper.ResourcesOperationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/21 18:10
 * @Description:
 */
@Component
public class PermissionResourceOperationRepoImpl implements PermissionResourceOperationRepo {
    @Autowired
    ResourcesOperationMapper operationMapper;

    /**
     * 删除按钮信息
     *
     * @param btnId
     * @return
     */
    @Override
    public int deleteByPrimaryKey(String btnId) {
        return operationMapper.deleteByPrimaryKey(btnId);
    }

    /**
     * 添加按钮信息
     *
     * @param recourcesOperation
     * @return
     */
    @Override
    public int insert(ResourceButton recourcesOperation) {
        Sys_Resources_Operation op = new Sys_Resources_Operation();
        op.setBtnCode(recourcesOperation.getBtnCode());
        op.setBtnName(recourcesOperation.getBtnName());
        op.setBtnTitle(recourcesOperation.getBtnTitle());
        op.setPageId(recourcesOperation.getPageId());
        return operationMapper.insert(op);
    }

    /**
     * 修改按钮信息
     *
     * @param recourcesOperation
     * @return
     */
    @Override
    public int updateByPrimaryKey(ResourceButton recourcesOperation) {
        Sys_Resources_Operation op = new Sys_Resources_Operation();
        op.setBtnId(recourcesOperation.getBtnId());
        op.setBtnCode(recourcesOperation.getBtnCode());
        op.setBtnName(recourcesOperation.getBtnName());
        op.setBtnTitle(recourcesOperation.getBtnTitle());
        op.setPageId(recourcesOperation.getPageId());
        return operationMapper.updateByPrimaryKey(op);
    }
}
