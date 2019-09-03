package com.cn.uvfortune.infrastructure.log.annotation;

import com.cn.uvfortune.infrastructure.log.annotation.repository.LogRuleFactory;
import com.cn.uvfortune.infrastructure.repository.entity.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("LogService")
public class LogService {

    @Autowired
    LogRuleFactory logRuleFactory;

    public int saveLog(LogModel logModel) {
        LogModel model = logRuleFactory.getLogModel();
        return model.saveLog(logModel);
    }

    public LogModel getLog(Integer id) {
        LogModel model = logRuleFactory.getLogModel();
        return model.getLog(id);
    }

    public Pages listLog(int pageNum, int pageSize, String startTime, String endTime, String operator, String logInner, String log_type) {
        LogModel model = logRuleFactory.getLogModel();
        return model.listLog(pageNum, pageSize, startTime, endTime, operator, logInner, log_type);
    }
}
