package com.cn.uvfortune.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author create by xiaojixiang
 * @version v1.0
 * @description 数据源、连接池配置
 * @date 18-7-5
 */
@Configuration
@ConfigurationProperties(prefix = "monitoring.datasource.hikari")
public class DataBaseConfig {

    private String username;
    private String password;
    private String jdbcUrl;
    private String driverClassName;
    private String connectionTestQuery;
    private String connectionTimeout;
    private int maximumPoolSize;
    private int minimumIdle;

    /**
     * 验证超时毫秒
     */
    private long validateTimeOut = 3000;

    /**
     * 指明使用 HIKARI 连接池，默认使用了MYSQL数据库
     *
     * @return
     */
    @Bean
    public DataSource getDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setUsername(this.username);
        hikariDataSource.setPassword(this.password);
        hikariDataSource.setJdbcUrl(this.jdbcUrl);
        hikariDataSource.setDriverClassName(this.driverClassName);
        hikariDataSource.setMaximumPoolSize(this.maximumPoolSize);
        hikariDataSource.setMinimumIdle(this.minimumIdle);
        hikariDataSource.setConnectionTestQuery(this.connectionTestQuery);
        hikariDataSource.setValidationTimeout(this.validateTimeOut);
        return hikariDataSource;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getConnectionTestQuery() {
        return connectionTestQuery;
    }

    public void setConnectionTestQuery(String connectionTestQuery) {
        this.connectionTestQuery = connectionTestQuery;
    }

    public String getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(String connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public int getMinimumIdle() {
        return minimumIdle;
    }

    public void setMinimumIdle(int minimumIdle) {
        this.minimumIdle = minimumIdle;
    }
}
