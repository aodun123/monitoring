package com.cn.uvfortune.infrastructure.log.annotation.repository;

import com.cn.uvfortune.infrastructure.log.annotation.LogModel;
import com.cn.uvfortune.infrastructure.repository.entity.Pages;
import org.springframework.stereotype.Component;

/**
 * 日志查询规则
 *
 * @author WangXiao
 * 2018-07-25
 */
@Component
public interface LogQueryRule {

    /**
     * 根据id查询日志
     *
     * @param id 日志id
     */
    LogModel getLog(Integer id);

    /**
     * 根据条件或无条件分页查询
     *
     * @param pageNum   页码
     * @param pageSize  查询条数
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param log_type  日志类型
     * @return
     */
    Pages listLog(int pageNum, int pageSize, String startTime, String endTime, String operator, String logInner, String log_type);
}
