package com.cn.uvfortune.infrastructure.log.annotation.repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.cn.uvfortune.infrastructure.log.annotation.LogModel;
import com.cn.uvfortune.infrastructure.repository.entity.Pages;
import com.cn.uvfortune.infrastructure.repository.mapper.sysmapper.SystemLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 日志查询实现
 *
 * @author WangXiao
 * 2018-07-25
 */
@Component
public class LogQueryImpl implements LogQueryRule {

    @Autowired
    SystemLogMapper logMapper;


    @Override
    public LogModel getLog(Integer id) {
        return logMapper.getLog(id);
    }

    @Override
    public Pages listLog(int pageNum, int pageSize, String startTime, String endTime, String operator, String logInner, String log_type) {
        PageHelper.startPage(pageNum, pageSize);
        Pages pages = new Pages();
        List<Map<String, Object>> modelList = logMapper.listLog(startTime, endTime, operator, logInner, log_type);
        PageInfo pageInfo = new PageInfo(modelList);
        pages.setData(modelList);
        pages.setCount(pageInfo.getTotal());
        return pages;
    }
}
