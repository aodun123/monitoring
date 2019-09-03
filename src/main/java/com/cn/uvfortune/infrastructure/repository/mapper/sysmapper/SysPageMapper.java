package com.cn.uvfortune.infrastructure.repository.mapper.sysmapper;

import com.cn.uvfortune.infrastructure.repository.entity.SysPage;
import com.cn.uvfortune.infrastructure.repository.entity.Sys_Resources_Operation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysPageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPage record);

    SysPage selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(SysPage record);

    List<SysPage> selectPages(@Param("pagename") String pagename);

    List<SysPage> selectAllPages();

    List<SysPage> findByAdminUserId(String userId);

    List<SysPage> queryType();

    List<Sys_Resources_Operation> findAllOperation();

}