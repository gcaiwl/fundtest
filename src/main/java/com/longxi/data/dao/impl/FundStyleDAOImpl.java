package com.longxi.data.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.longxi.data.obj.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.longxi.data.obj.FundStyleDO;

import com.longxi.data.dao.FundStyleDAO;

/**
 * fund_style表的DAO层实现类
 * Date 2017-12-14 23:06:28
 */
public class FundStyleDAOImpl extends SqlMapBaseDAO implements FundStyleDAO {
	private static final Logger logger = LoggerFactory.getLogger(FundStyleDAOImpl.class);

	@Override
	public FundStyleDO queryFundStyleById(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<FundStyleDO> list = queryFundStyle(params);
		if(list == null || list.isEmpty()){
			return null;
		}else if(list.size() != 1){
			String errMsg = "queryFundStyleById return too many records,the param="+params;
			logger.error(errMsg);
			throw new RuntimeException(errMsg);
		}
		return list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	private List<FundStyleDO> queryFundStyle(Map<String,Object> params) {
		List<FundStyleDO> list = Collections.emptyList();
		try {
			list = (List<FundStyleDO>) sqlMapClient.queryForList("FundStyleDAO.queryFundStyle", params);
		} catch (Exception e) {
			logger.error("queryFundStyle ERROR", e);
		}
		return list;
	}
	
	
	@Override
	public int updateFundStyle(FundStyleDO instance) {
		int num = -1;
		try {
			num = sqlMapClient.update("FundStyleDAO.updateFundStyle", instance);
		} catch (Exception e) {
			logger.error("updateFundStyle error", e);
		}
		return num;
	}
	
	@Override
	public Long insertFundStyle(FundStyleDO instance) {
		Long id = null;
		try {
			id = (Long) sqlMapClient.insert("FundStyleDAO.insertFundStyle", instance);
		} catch (Exception e) {
			logger.error("insertFundStyle error", e);
		}
		return id;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FundStyleDO> queryFundStyleByPage(Query<FundStyleDO> query) {
		Map<String, Object> params = convertQuery2Param(query);
		int count = countFundStyleForPage(query);
		query.setTotalSize(count);
		List<FundStyleDO> list = Collections.emptyList();
		try {
			list = (List<FundStyleDO>) sqlMapClient.queryForList("FundStyleDAO.queryFundStyleByPage", params);
		} catch (Exception e) {
			logger.error("queryFundStyleByPage error", e);
		}
		return list;
	}

	@Override
	public int countFundStyleForPage(Query<FundStyleDO> query) {
		int count = 0;
		Map<String, Object> params = convertQuery2Param(query);
		try {
			count = (Integer) sqlMapClient.queryForObject("FundStyleDAO.countFundStyle", params);
		} catch (Exception e) {
			logger.error("countFundStyleForPage error", e);
		}
		return count;
	}
	
	private Map<String, Object> convertQuery2Param(Query<FundStyleDO> query){
		FundStyleDO instance = query.getModule();
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
	public FundStyleDO queryFundStyleByStyle(String code, String style, String quarter) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		params.put("style", style);
		params.put("quarter", quarter);
		List<FundStyleDO> list = queryFundStyle(params);
		if(list == null || list.isEmpty()){
			return null;
		}else if(list.size() != 1){
			String errMsg = "queryFundStyleByStyle return too many records,the param="+params;
			logger.error(errMsg);
			throw new RuntimeException(errMsg);
		}
		return list.get(0);
	}
}
