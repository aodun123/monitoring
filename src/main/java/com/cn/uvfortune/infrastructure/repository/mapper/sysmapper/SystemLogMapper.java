package com.cn.uvfortune.infrastructure.repository.mapper.sysmapper;

import com.cn.uvfortune.infrastructure.log.annotation.LogModel;
import com.cn.uvfortune.infrastructure.repository.entity.SystemLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SystemLogMapper {

    int insert(SystemLog record);

    LogModel getLog(Integer id);

    List<Map<String, Object>> listLog(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("operator") String operator, @Param("logInner") String logInner, @Param("log_type") String log_type);

}