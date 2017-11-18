package com.longxi.data.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.longxi.data.obj.Query;
import com.longxi.data.obj.FundFeatureDO;

import com.longxi.data.dao.FundFeatureDAO;

/**
 * fund_feature表的DAO层实现类
 * Date 2017-11-17 21:38:15
 */
public class FundFeatureDAOImpl extends SqlMapBaseDAO implements FundFeatureDAO {
	private static final Logger logger = LoggerFactory.getLogger(FundFeatureDAOImpl.class);

	@Override
	public FundFeatureDO queryFundFeatureById(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<FundFeatureDO> list = queryFundFeature(params);
		if(list == null || list.isEmpty()){
			return null;
		}else if(list.size() != 1){
			String errMsg = "queryFundFeatureById return too many records,the param="+params;
			logger.error(errMsg);
			throw new RuntimeException(errMsg);
		}
		return list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	private List<FundFeatureDO> queryFundFeature(Map<String,Object> params) {
		List<FundFeatureDO> list = Collections.emptyList();
		try {
			list = (List<FundFeatureDO>) sqlMapClient.queryForList("FundFeatureDAO.queryFundFeature", params);
		} catch (Exception e) {
			logger.error("queryFundFeature ERROR", e);
		}
		return list;
	}
	
	
	@Override
	public int updateFundFeature(FundFeatureDO instance) {
		int num = -1;
		try {
			num = sqlMapClient.update("FundFeatureDAO.updateFundFeature", instance);
		} catch (Exception e) {
			logger.error("updateFundFeature error", e);
		}
		return num;
	}
	
	@Override
	public Long insertFundFeature(FundFeatureDO instance) {
		Long id = null;
		try {
			id = (Long) sqlMapClient.insert("FundFeatureDAO.insertFundFeature", instance);
		} catch (Exception e) {
			logger.error("insertFundFeature error", e);
		}
		return id;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FundFeatureDO> queryFundFeatureByPage(Query<FundFeatureDO> query) {
		Map<String, Object> params = convertQuery2Param(query);
		int count = countFundFeatureForPage(query);
		query.setTotalSize(count);
		List<FundFeatureDO> list = Collections.emptyList();
		try {
			list = (List<FundFeatureDO>) sqlMapClient.queryForList("FundFeatureDAO.queryFundFeatureByPage", params);
		} catch (Exception e) {
			logger.error("queryFundFeatureByPage error", e);
		}
		return list;
	}

	@Override
	public int countFundFeatureForPage(Query<FundFeatureDO> query) {
		int count = 0;
		Map<String, Object> params = convertQuery2Param(query);
		try {
			count = (Integer) sqlMapClient.queryForObject("FundFeatureDAO.countFundFeature", params);
		} catch (Exception e) {
			logger.error("countFundFeatureForPage error", e);
		}
		return count;
	}
	
	private Map<String, Object> convertQuery2Param(Query<FundFeatureDO> query){
		FundFeatureDO instance = query.getModule();
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

}
