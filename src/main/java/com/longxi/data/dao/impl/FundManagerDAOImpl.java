package com.longxi.data.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.longxi.data.obj.Query;
import com.longxi.data.obj.FundManagerDO;

import com.longxi.data.dao.FundManagerDAO;

/**
 * fund_manager表的DAO层实现类
 * Date 2017-11-17 21:38:15
 */
public class FundManagerDAOImpl extends SqlMapBaseDAO implements FundManagerDAO {
	private static final Logger logger = LoggerFactory.getLogger(FundManagerDAOImpl.class);

	@Override
	public FundManagerDO queryFundManagerById(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<FundManagerDO> list = queryFundManager(params);
		if(list == null || list.isEmpty()){
			return null;
		}else if(list.size() != 1){
			String errMsg = "queryFundManagerById return too many records,the param="+params;
			logger.error(errMsg);
			throw new RuntimeException(errMsg);
		}
		return list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	private List<FundManagerDO> queryFundManager(Map<String,Object> params) {
		List<FundManagerDO> list = Collections.emptyList();
		try {
			list = (List<FundManagerDO>) sqlMapClient.queryForList("FundManagerDAO.queryFundManager", params);
		} catch (Exception e) {
			logger.error("queryFundManager ERROR", e);
		}
		return list;
	}
	
	
	@Override
	public int updateFundManager(FundManagerDO instance) {
		int num = -1;
		try {
			num = sqlMapClient.update("FundManagerDAO.updateFundManager", instance);
		} catch (Exception e) {
			logger.error("updateFundManager error", e);
		}
		return num;
	}
	
	@Override
	public Long insertFundManager(FundManagerDO instance) {
		Long id = null;
		try {
			id = (Long) sqlMapClient.insert("FundManagerDAO.insertFundManager", instance);
		} catch (Exception e) {
			logger.error("insertFundManager error", e);
		}
		return id;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FundManagerDO> queryFundManagerByPage(Query<FundManagerDO> query) {
		Map<String, Object> params = convertQuery2Param(query);
		int count = countFundManagerForPage(query);
		query.setTotalSize(count);
		List<FundManagerDO> list = Collections.emptyList();
		try {
			list = (List<FundManagerDO>) sqlMapClient.queryForList("FundManagerDAO.queryFundManagerByPage", params);
		} catch (Exception e) {
			logger.error("queryFundManagerByPage error", e);
		}
		return list;
	}

	@Override
	public int countFundManagerForPage(Query<FundManagerDO> query) {
		int count = 0;
		Map<String, Object> params = convertQuery2Param(query);
		try {
			count = (Integer) sqlMapClient.queryForObject("FundManagerDAO.countFundManager", params);
		} catch (Exception e) {
			logger.error("countFundManagerForPage error", e);
		}
		return count;
	}
	
	private Map<String, Object> convertQuery2Param(Query<FundManagerDO> query){
		FundManagerDO instance = query.getModule();
		Map<String, Object> params = new HashMap<String, Object>();
		
		// TODO 自行添加分页查询可能的条件
		//params.put("id", instance.getId());
		//params.put("isDel", instance.getIsDel()==null?0:instance.getIsDel());//默认只查询未被删除的
		
		params.put("_order", query.getOrderBy());
		
		Map<String, Object> _limit = new HashMap<String, Object>();
		_limit.put("start", query.getStart());
		_limit.put("pageSize",query.getPageSize());
		params.put("_limit", _limit);
		
		return params;
	}

	@Override
	public FundManagerDO queryFundManagerByManager(String code, String manager) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		params.put("manager", manager);
		List<FundManagerDO> list = queryFundManager(params);
		if(list == null || list.isEmpty()){
			return null;
		}else if(list.size() != 1){
			String errMsg = "queryFundManagerByManager return too many records,the param="+params;
			logger.error(errMsg);
			throw new RuntimeException(errMsg);
		}
		return list.get(0);
	}
}
