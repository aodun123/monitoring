package com.cn.uvfortune.contract.contractinfo.dao;

import java.util.List;
import java.util.Map;

import com.cn.uvfortune.common.dao.BaseDao;
import com.cn.uvfortune.infrastructure.repository.entity.Pages;
import org.springframework.stereotype.Repository;


@Repository
public class ContractDao extends BaseDao {
	
	public List<Map<String,Object>> getList(){
		String sql = "SELECT * FROM `bu_contract` where 1=1";
		List<Map<String,Object>> li = jdbcTemplate.queryForList(sql);
		for (Map<String,Object> mm: li) {
			mm.put("uploadUser","王文");
			mm.put("administrationUser","王文");
		}
		return li;
	}


	public List<Map<String,Object>> getcontracttypeList(){
		String sql = "SELECT * FROM `bu_contract_type` where 1=1";
		List<Map<String,Object>> li = jdbcTemplate.queryForList(sql);
		for (Map<String,Object> mm: li) {
			mm.put("uploadUser","王文");
			mm.put("administrationUser","王文");
		}
		return li;
	}
 }
