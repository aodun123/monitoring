package com.cn.uvfortune.domain.factory.sysfactory;

import com.cn.uvfortune.domain.model.org.OrgUser;
import com.cn.uvfortune.infrastructure.repository.repoimpl.sysrepoimpl.QueryUserRepoImpl;
import com.cn.uvfortune.infrastructure.repository.repoimpl.sysrepoimpl.UserRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/24 19:16
 * @Description: User工厂
 */
@Component
public class UserFactory {
    @Autowired
    UserRepoImpl userRepo;

    @Autowired
    QueryUserRepoImpl queryUserRepo;

    /**
     * 生成 ResourcePage 对象
     *
     * @return
     */
    public OrgUser getUserInstance() {
        OrgUser orgUser = new OrgUser();
        orgUser.setUserRepo(this.userRepo);
        orgUser.setQueryUserRepo(this.queryUserRepo);
        return orgUser;
    }
}
