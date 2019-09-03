package com.cn.uvfortune.infrastructure.repository.mapper.sysmapper;

import com.cn.uvfortune.infrastructure.repository.entity.Sys_Resources_Operation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ResourcesOperationMapper {

    int deleteByPrimaryKey(String btnId);

    int insert(Sys_Resources_Operation record);

    int updateByPrimaryKey(Sys_Resources_Operation record);

    List<Sys_Resources_Operation> selectOperation();

    List<Sys_Resources_Operation> queryOpeationById(String userid);

    List<Sys_Resources_Operation> queryOpeationByPageId(@Param("userid") String userid, @Param("pageid") String pageid);

    List<Map<String, Object>> selectById(@Param("userid") String userid, @Param("pageid") String pageid);
}