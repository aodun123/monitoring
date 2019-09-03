package com.cn.uvfortune.common.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class BaseDao {
	private static Logger logger = LoggerFactory.getLogger(BaseDao.class);
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
        this.jdbcTemplate = jdbcTemplate;  
    } 
    
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
}
