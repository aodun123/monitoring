package com.cn.uvfortune.infrastructure.log.annotation.repository;

import com.cn.uvfortune.infrastructure.log.annotation.LogModel;
import org.springframework.stereotype.Component;

/**
 * 日志添加规则
 *
 * @author WangXiao
 * 2018-07-25
 */
@Component
public interface LogSaveRule {

    /**
     * 日志添加
     */
    int saveLog(LogModel logModel);
}
