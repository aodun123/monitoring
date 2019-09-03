package com.cn.uvfortune.infrastructure.log.annotation.repository;

import com.cn.uvfortune.infrastructure.log.annotation.LogModel;
import com.cn.uvfortune.infrastructure.repository.entity.SystemLog;
import com.cn.uvfortune.infrastructure.repository.mapper.sysmapper.SystemLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 日志添加实现
 *
 * @author WangXiao
 * 2018-07-25
 */
@Component
public class LogSaveImpl implements LogSaveRule {

    @Autowired
    SystemLogMapper logMapper;


    @Override
    public int saveLog(LogModel logModel) {
        SystemLog log = new SystemLog();
        log.setIpAddress(logModel.getIpAddress());
        log.setOperationUserId(logModel.getOperationUserId());
        log.setLogLevel(logModel.getLogLevel());
        log.setLogType(logModel.getLogType());
        log.setLogContent(logModel.getLogContent());
        return logMapper.insert(log);
    }
}
