package com.cn.uvfortune.infrastructure.repository.repoimpl.orgrepoimpl;

import com.cn.uvfortune.domain.model.org.UserAgency;
import com.cn.uvfortune.domain.repository.orgrepo.IUserAgencyRepository;
import com.cn.uvfortune.infrastructure.repository.mapper.orgmapper.SysUserAgencyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: xiaojixiang
 * @version: v1.0
 * @description: 组织仓储实现
 * @date:18-7-5
 */
@Component
public class IUserAgencyRepositoryImpl implements IUserAgencyRepository {

    @Autowired
    SysUserAgencyMapper sysUserAgencyMapper;


    @Override
    public int addUserAgency(List list) {
        return sysUserAgencyMapper.insert(list);
    }

    @Override
    public int deleteUserAgency(Integer id) {
        return sysUserAgencyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updUserAgency(List list) {
        Integer agencyId = ((UserAgency) list.get(0)).getSysAgencyId();
        sysUserAgencyMapper.deleteByPrimaryKey(agencyId);
        return sysUserAgencyMapper.insert(list);
    }
}
