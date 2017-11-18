package com.longxi.data.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.longxi.data.obj.Query;
import com.longxi.data.obj.FundIndustryDO;

import com.longxi.data.dao.FundIndustryDAO;

/**
 * fund_industry表的DAO层实现类
 * Date 2017-11-17 21:38:15
 */
public class FundIndustryDAOImpl extends SqlMapBaseDAO implements FundIndustryDAO {
	private static final Logger logger = LoggerFactory.getLogger(FundIndustryDAOImpl.class);

	@Override
	public FundIndustryDO queryFundIndustryById(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<FundIndustryDO> list = queryFundIndustry(params);
		if(list == null || list.isEmpty()){
			return null;
		}else if(list.size() != 1){
			String errMsg = "queryFundIndustryById return too many records,the param="+params;
			logger.error(errMsg);
			throw new RuntimeException(errMsg);
		}
		return list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	private List<FundIndustryDO> queryFundIndustry(Map<String,Object> params) {
		List<FundIndustryDO> list = Collections.emptyList();
		try {
			list = (List<FundIndustryDO>) sqlMapClient.queryForList("FundIndustryDAO.queryFundIndustry", params);
		} catch (Exception e) {
			logger.error("queryFundIndustry ERROR", e);
		}
		return list;
	}
	
	
	@Override
	public int updateFundIndustry(FundIndustryDO instance) {
		int num = -1;
		try {
			num = sqlMapClient.update("FundIndustryDAO.updateFundIndustry", instance);
		} catch (Exception e) {
			logger.error("updateFundIndustry error", e);
		}
		return num;
	}
	
	@Override
	public Long insertFundIndustry(FundIndustryDO instance) {
		Long id = null;
		try {
			id = (Long) sqlMapClient.insert("FundIndustryDAO.insertFundIndustry", instance);
		} catch (Exception e) {
			logger.error("insertFundIndustry error", e);
		}
		return id;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FundIndustryDO> queryFundIndustryByPage(Query<FundIndustryDO> query) {
		Map<String, Object> params = convertQuery2Param(query);
		int count = countFundIndustryForPage(query);
		query.setTotalSize(count);
		List<FundIndustryDO> list = Collections.emptyList();
		try {
			list = (List<FundIndustryDO>) sqlMapClient.queryForList("FundIndustryDAO.queryFundIndustryByPage", params);
		} catch (Exception e) {
			logger.error("queryFundIndustryByPage error", e);
		}
		return list;
	}

	@Override
	public int countFundIndustryForPage(Query<FundIndustryDO> query) {
		int count = 0;
		Map<String, Object> params = convertQuery2Param(query);
		try {
			count = (Integer) sqlMapClient.queryForObject("FundIndustryDAO.countFundIndustry", params);
		} catch (Exception e) {
			logger.error("countFundIndustryForPage error", e);
		}
		return count;
	}
	
	private Map<String, Object> convertQuery2Param(Query<FundIndustryDO> query){
		FundIndustryDO instance = query.getModule();
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
