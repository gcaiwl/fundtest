package com.longxi.data.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.longxi.data.obj.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.longxi.data.obj.FundIndexDO;

import com.longxi.data.dao.FundIndexDAO;

/**
 * fund_index表的DAO层实现类
 * Date 2017-12-02 15:26:52
 */
public class FundIndexDAOImpl extends SqlMapBaseDAO implements FundIndexDAO {
	private static final Logger logger = LoggerFactory.getLogger(FundIndexDAOImpl.class);

	@Override
	public FundIndexDO queryFundIndexById(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<FundIndexDO> list = queryFundIndex(params);
		if(list == null || list.isEmpty()){
			return null;
		}else if(list.size() != 1){
			String errMsg = "queryFundIndexById return too many records,the param="+params;
			logger.error(errMsg);
			throw new RuntimeException(errMsg);
		}
		return list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	private List<FundIndexDO> queryFundIndex(Map<String,Object> params) {
		List<FundIndexDO> list = Collections.emptyList();
		try {
			list = (List<FundIndexDO>) sqlMapClient.queryForList("FundIndexDAO.queryFundIndex", params);
		} catch (Exception e) {
			logger.error("queryFundIndex ERROR", e);
		}
		return list;
	}
	
	
	@Override
	public int updateFundIndex(FundIndexDO instance) {
		int num = -1;
		try {
			num = sqlMapClient.update("FundIndexDAO.updateFundIndex", instance);
		} catch (Exception e) {
			logger.error("updateFundIndex error", e);
		}
		return num;
	}
	
	@Override
	public Long insertFundIndex(FundIndexDO instance) {
		Long id = null;
		try {
			id = (Long) sqlMapClient.insert("FundIndexDAO.insertFundIndex", instance);
		} catch (Exception e) {
			logger.error("insertFundIndex error", e);
		}
		return id;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FundIndexDO> queryFundIndexByPage(Query<FundIndexDO> query) {
		Map<String, Object> params = convertQuery2Param(query);
		int count = countFundIndexForPage(query);
		query.setTotalSize(count);
		List<FundIndexDO> list = Collections.emptyList();
		try {
			list = (List<FundIndexDO>) sqlMapClient.queryForList("FundIndexDAO.queryFundIndexByPage", params);
		} catch (Exception e) {
			logger.error("queryFundIndexByPage error", e);
		}
		return list;
	}

	@Override
	public int countFundIndexForPage(Query<FundIndexDO> query) {
		int count = 0;
		Map<String, Object> params = convertQuery2Param(query);
		try {
			count = (Integer) sqlMapClient.queryForObject("FundIndexDAO.countFundIndex", params);
		} catch (Exception e) {
			logger.error("countFundIndexForPage error", e);
		}
		return count;
	}
	
	private Map<String, Object> convertQuery2Param(Query<FundIndexDO> query){
		FundIndexDO instance = query.getModule();
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
	public FundIndexDO queryFundIndexByFeature(String code, String feature) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		params.put("feature", feature);
		List<FundIndexDO> list = queryFundIndex(params);
		if(list == null || list.isEmpty()){
			return null;
		}else if(list.size() != 1){
			String errMsg = "queryFundIndexByFeature return too many records,the param="+params;
			logger.error(errMsg);
			throw new RuntimeException(errMsg);
		}
		return list.get(0);
	}
}
