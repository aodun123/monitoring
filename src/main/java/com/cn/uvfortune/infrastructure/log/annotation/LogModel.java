package com.cn.uvfortune.infrastructure.log.annotation;

import com.cn.uvfortune.infrastructure.log.annotation.repository.LogQueryRule;
import com.cn.uvfortune.infrastructure.log.annotation.repository.LogSaveRule;
import com.cn.uvfortune.infrastructure.repository.entity.Pages;

import java.util.Date;

/**
 * @author WangXiao
 * 2018-07-25
 * 日志模型
 */
public class LogModel {

    /**
     * 基础属性
     */
    //日志id
    private Integer id;

    //日志类型
    private String logType;

    //日志等级
    private String logLevel;

    //客户端ip
    private String ipAddress;

    //用户id
    private Integer operationUserId;

    //日志内容
    private String logContent;

    //生成时间
    private Date gmtCreate;

    //最后修改时间
    private Date gmtModified;

    /**
     * 业务属性
     */
    private LogQueryRule queryRule;

    private LogSaveRule saveRule;

    /**
     * 基础属性get、set
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getOperationUserId() {
        return operationUserId;
    }

    public void setOperationUserId(Integer operationUserId) {
        this.operationUserId = operationUserId;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 业务属性get、set
     */
    public LogQueryRule getQueryRule() {
        return queryRule;
    }

    public void setQueryRule(LogQueryRule queryRule) {
        this.queryRule = queryRule;
    }

    public LogSaveRule getSaveRule() {
        return saveRule;
    }

    public void setSaveRule(LogSaveRule saveRule) {
        this.saveRule = saveRule;
    }

    /**
     * 业务方法-添加日志
     */
    public int saveLog(LogModel logModel) {
        return saveRule.saveLog(logModel);
    }

    /**
     * 业务方法-根据id查询日志
     */
    public LogModel getLog(Integer id) {
        return queryRule.getLog(id);
    }

    /**
     * 业务方法-根据条件或无条件分页查询日志
     */
    public Pages listLog(int pageNum, int pageSize, String startTime, String endTime, String operator, String logInner, String log_type) {
        return queryRule.listLog(pageNum, pageSize, startTime, endTime, operator, logInner, log_type);
    }

    @Override
    public String toString() {
        return "LogModel{" +
                "id=" + id +
                ", logType='" + logType + '\'' +
                ", logLevel='" + logLevel + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", operationUserId=" + operationUserId +
                ", logContent='" + logContent + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
