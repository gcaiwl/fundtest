package com.longxi.data.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.longxi.data.obj.Query;
import com.longxi.data.obj.FundHolderDO;

import com.longxi.data.dao.FundHolderDAO;

/**
 * fund_holder表的DAO层实现类
 * Date 2017-11-17 21:38:15
 */
public class FundHolderDAOImpl extends SqlMapBaseDAO implements FundHolderDAO {
	private static final Logger logger = LoggerFactory.getLogger(FundHolderDAOImpl.class);

	@Override
	public FundHolderDO queryFundHolderById(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<FundHolderDO> list = queryFundHolder(params);
		if(list == null || list.isEmpty()){
			return null;
		}else if(list.size() != 1){
			String errMsg = "queryFundHolderById return too many records,the param="+params;
			logger.error(errMsg);
			throw new RuntimeException(errMsg);
		}
		return list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	private List<FundHolderDO> queryFundHolder(Map<String,Object> params) {
		List<FundHolderDO> list = Collections.emptyList();
		try {
			list = (List<FundHolderDO>) sqlMapClient.queryForList("FundHolderDAO.queryFundHolder", params);
		} catch (Exception e) {
			logger.error("queryFundHolder ERROR", e);
		}
		return list;
	}
	
	
	@Override
	public int updateFundHolder(FundHolderDO instance) {
		int num = -1;
		try {
			num = sqlMapClient.update("FundHolderDAO.updateFundHolder", instance);
		} catch (Exception e) {
			logger.error("updateFundHolder error", e);
		}
		return num;
	}
	
	@Override
	public Long insertFundHolder(FundHolderDO instance) {
		Long id = null;
		try {
			id = (Long) sqlMapClient.insert("FundHolderDAO.insertFundHolder", instance);
		} catch (Exception e) {
			logger.error("insertFundHolder error", e);
		}
		return id;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FundHolderDO> queryFundHolderByPage(Query<FundHolderDO> query) {
		Map<String, Object> params = convertQuery2Param(query);
		int count = countFundHolderForPage(query);
		query.setTotalSize(count);
		List<FundHolderDO> list = Collections.emptyList();
		try {
			list = (List<FundHolderDO>) sqlMapClient.queryForList("FundHolderDAO.queryFundHolderByPage", params);
		} catch (Exception e) {
			logger.error("queryFundHolderByPage error", e);
		}
		return list;
	}

	@Override
	public int countFundHolderForPage(Query<FundHolderDO> query) {
		int count = 0;
		Map<String, Object> params = convertQuery2Param(query);
		try {
			count = (Integer) sqlMapClient.queryForObject("FundHolderDAO.countFundHolder", params);
		} catch (Exception e) {
			logger.error("countFundHolderForPage error", e);
		}
		return count;
	}
	
	private Map<String, Object> convertQuery2Param(Query<FundHolderDO> query){
		FundHolderDO instance = query.getModule();
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
