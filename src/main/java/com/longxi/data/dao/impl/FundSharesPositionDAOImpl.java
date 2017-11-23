package com.longxi.data.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.longxi.data.obj.Query;
import com.longxi.data.obj.FundSharesPositionDO;

import com.longxi.data.dao.FundSharesPositionDAO;

/**
 * fund_shares_position表的DAO层实现类
 * Date 2017-11-17 21:38:15
 */
public class FundSharesPositionDAOImpl extends SqlMapBaseDAO implements FundSharesPositionDAO {
	private static final Logger logger = LoggerFactory.getLogger(FundSharesPositionDAOImpl.class);

	@Override
	public FundSharesPositionDO queryFundSharesPositionById(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<FundSharesPositionDO> list = queryFundSharesPosition(params);
		if(list == null || list.isEmpty()){
			return null;
		}else if(list.size() != 1){
			String errMsg = "queryFundSharesPositionById return too many records,the param="+params;
			logger.error(errMsg);
			throw new RuntimeException(errMsg);
		}
		return list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	private List<FundSharesPositionDO> queryFundSharesPosition(Map<String,Object> params) {
		List<FundSharesPositionDO> list = Collections.emptyList();
		try {
			list = (List<FundSharesPositionDO>) sqlMapClient.queryForList("FundSharesPositionDAO.queryFundSharesPosition", params);
		} catch (Exception e) {
			logger.error("queryFundSharesPosition ERROR", e);
		}
		return list;
	}
	
	
	@Override
	public int updateFundSharesPosition(FundSharesPositionDO instance) {
		int num = -1;
		try {
			num = sqlMapClient.update("FundSharesPositionDAO.updateFundSharesPosition", instance);
		} catch (Exception e) {
			logger.error("updateFundSharesPosition error", e);
		}
		return num;
	}
	
	@Override
	public Long insertFundSharesPosition(FundSharesPositionDO instance) {
		Long id = null;
		try {
			id = (Long) sqlMapClient.insert("FundSharesPositionDAO.insertFundSharesPosition", instance);
		} catch (Exception e) {
			logger.error("insertFundSharesPosition error", e);
		}
		return id;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FundSharesPositionDO> queryFundSharesPositionByPage(Query<FundSharesPositionDO> query) {
		Map<String, Object> params = convertQuery2Param(query);
		int count = countFundSharesPositionForPage(query);
		query.setTotalSize(count);
		List<FundSharesPositionDO> list = Collections.emptyList();
		try {
			list = (List<FundSharesPositionDO>) sqlMapClient.queryForList("FundSharesPositionDAO.queryFundSharesPositionByPage", params);
		} catch (Exception e) {
			logger.error("queryFundSharesPositionByPage error", e);
		}
		return list;
	}

	@Override
	public int countFundSharesPositionForPage(Query<FundSharesPositionDO> query) {
		int count = 0;
		Map<String, Object> params = convertQuery2Param(query);
		try {
			count = (Integer) sqlMapClient.queryForObject("FundSharesPositionDAO.countFundSharesPosition", params);
		} catch (Exception e) {
			logger.error("countFundSharesPositionForPage error", e);
		}
		return count;
	}
	
	private Map<String, Object> convertQuery2Param(Query<FundSharesPositionDO> query){
		FundSharesPositionDO instance = query.getModule();
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
	public FundSharesPositionDO queryFundSharesPositionByQuarter(String code, String quarter, String sharesCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		params.put("quarter", quarter);
		params.put("sharesCode", sharesCode);
		List<FundSharesPositionDO> list = queryFundSharesPosition(params);
		if(list == null || list.isEmpty()){
			return null;
		}else if(list.size() != 1){
			String errMsg = "queryFundSharesPositionByQuarter return too many records,the param="+params;
			logger.error(errMsg);
			throw new RuntimeException(errMsg);
		}
		return list.get(0);
	}
}
