package com.cn.uvfortune.domain.factory.sysfactory;

import com.cn.uvfortune.domain.model.security.ResourceButton;
import com.cn.uvfortune.infrastructure.repository.repoimpl.sysrepoimpl.PermissionResourceOperationRepoImpl;
import com.cn.uvfortune.infrastructure.repository.repoimpl.sysrepoimpl.QueryPermissionResourceOperationRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/21 18:34
 * @Description: RecourcesOperation工厂
 */
@Component
public class ResourcesOperationFactory {

    @Autowired
    PermissionResourceOperationRepoImpl operationRepo;

    @Autowired
    QueryPermissionResourceOperationRepoImpl queryPermissionResourceOperationRepo;


    /**
     * 生成 Role 对象
     *
     * @return
     */
    public ResourceButton getOperationInstance() {
        ResourceButton resourceOperation = new ResourceButton();
        resourceOperation.setOperationRepo(this.operationRepo);
        resourceOperation.setResourceOperationRepo(this.queryPermissionResourceOperationRepo);
        return resourceOperation;
    }

}
