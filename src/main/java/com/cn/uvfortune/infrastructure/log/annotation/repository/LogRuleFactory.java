package com.cn.uvfortune.infrastructure.log.annotation.repository;

import com.cn.uvfortune.infrastructure.log.annotation.LogModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author WangXiao
 * 2018-07-25
 */
@Component
public class LogRuleFactory {

    @Autowired
    LogSaveRule logSaveRule;

    @Autowired
    LogQueryRule logQueryRule;

    public LogModel getLogModel() {
        LogModel logModel = new LogModel();
        logModel.setSaveRule(logSaveRule);
        logModel.setQueryRule(logQueryRule);
        return logModel;
    }
}
