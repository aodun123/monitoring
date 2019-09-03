package com.cn.uvfortune.infrastructure.repository.mapper.orgmapper;


import com.cn.uvfortune.infrastructure.repository.entity.SysUserAgency;
import com.cn.uvfortune.infrastructure.repository.entity.TreeNode.ZNodes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserAgencyMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(List list);

    List<ZNodes> getUserAgency(Integer sysAgencyId);

    List<SysUserAgency> getUserAgencyList(Integer sysAgencyId);

    List<SysUserAgency> getSysAgencyId(int userid);
}