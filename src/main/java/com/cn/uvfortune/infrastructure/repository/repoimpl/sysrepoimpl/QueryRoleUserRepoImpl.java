package com.cn.uvfortune.infrastructure.repository.repoimpl.sysrepoimpl;

import com.cn.uvfortune.domain.model.security.RoleUser;
import com.cn.uvfortune.domain.repository.sysrepo.QueryRoleUserRepo;
import com.cn.uvfortune.infrastructure.repository.mapper.sysmapper.SysRoleUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: hancunyan
 * @Date: 2018/8/2 11:32
 * @Description:
 */
@Component
public class QueryRoleUserRepoImpl implements QueryRoleUserRepo {

    @Autowired
    SysRoleUserMapper sysRoleUserMapper;

    /**
     * 根据机构id查询对应的角色信息
     *
     * @param agencyId
     * @return
     */
    @Override
    public List<RoleUser> queryRoleByAid(int agencyId) {
        return sysRoleUserMapper.queryRoleByAid(agencyId);
    }
}
