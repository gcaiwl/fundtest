package com.longxi.data.dao.impl;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.longxi.data.obj.FundHolderDO;
import com.longxi.data.obj.FundTurnoverDO;
import com.longxi.data.obj.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.longxi.data.dao.FundTurnoverDAO;

/**
 * fund_turnover表的DAO层实现类
 * Date 2017-11-21 23:51:41
 */
public class FundTurnoverDAOImpl extends SqlMapBaseDAO implements FundTurnoverDAO {
	private static final Logger logger = LoggerFactory.getLogger(FundTurnoverDAOImpl.class);

	@Override
	public FundTurnoverDO queryFundTurnoverById(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<FundTurnoverDO> list = queryFundTurnover(params);
		if(list == null || list.isEmpty()){
			return null;
		}else if(list.size() != 1){
			String errMsg = "queryFundTurnoverById return too many records,the param="+params;
			logger.error(errMsg);
			throw new RuntimeException(errMsg);
		}
		return list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	private List<FundTurnoverDO> queryFundTurnover(Map<String,Object> params) {
		List<FundTurnoverDO> list = Collections.emptyList();
		try {
			list = (List<FundTurnoverDO>) sqlMapClient.queryForList("FundTurnoverDAO.queryFundTurnover", params);
		} catch (Exception e) {
			logger.error("queryFundTurnover ERROR", e);
		}
		return list;
	}
	
	
	@Override
	public int updateFundTurnover(FundTurnoverDO instance) {
		int num = -1;
		try {
			num = sqlMapClient.update("FundTurnoverDAO.updateFundTurnover", instance);
		} catch (Exception e) {
			logger.error("updateFundTurnover error", e);
		}
		return num;
	}
	
	@Override
	public Long insertFundTurnover(FundTurnoverDO instance) {
		Long id = null;
		try {
			id = (Long) sqlMapClient.insert("FundTurnoverDAO.insertFundTurnover", instance);
		} catch (Exception e) {
			logger.error("insertFundTurnover error", e);
		}
		return id;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FundTurnoverDO> queryFundTurnoverByPage(Query<FundTurnoverDO> query) {
		Map<String, Object> params = convertQuery2Param(query);
		int count = countFundTurnoverForPage(query);
		query.setTotalSize(count);
		List<FundTurnoverDO> list = Collections.emptyList();
		try {
			list = (List<FundTurnoverDO>) sqlMapClient.queryForList("FundTurnoverDAO.queryFundTurnoverByPage", params);
		} catch (Exception e) {
			logger.error("queryFundTurnoverByPage error", e);
		}
		return list;
	}

	@Override
	public int countFundTurnoverForPage(Query<FundTurnoverDO> query) {
		int count = 0;
		Map<String, Object> params = convertQuery2Param(query);
		try {
			count = (Integer) sqlMapClient.queryForObject("FundTurnoverDAO.countFundTurnover", params);
		} catch (Exception e) {
			logger.error("countFundTurnoverForPage error", e);
		}
		return count;
	}
	
	private Map<String, Object> convertQuery2Param(Query<FundTurnoverDO> query){
		FundTurnoverDO instance = query.getModule();
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
	public FundTurnoverDO queryFundTurnoverByPublishTime(String code, Date publishTime) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		params.put("publishTime", publishTime);
		List<FundTurnoverDO> list = queryFundTurnover(params);
		if(list == null || list.isEmpty()){
			return null;
		}else if(list.size() != 1){
			String errMsg = "queryFundTurnoverByPublishTime return too many records,the param="+params;
			throw new RuntimeException(errMsg);
		}
		return list.get(0);
	}

	@Override
	public FundTurnoverDO queryFundTurnoverLatestByCode(String code) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);

		FundTurnoverDO fundTurnoverDO = null;
		try {
			fundTurnoverDO = (FundTurnoverDO)sqlMapClient.queryForObject("FundTurnoverDAO.queryFundTurnoverLatestByCode", params);
		} catch (Exception e) {
			logger.error(code + " queryFundTurnoverLatestByCode ERROR", e);
		}
		return fundTurnoverDO;
	}

	@Override
	public int deleteFundTurnoverByPublishTime(String code, Date publishTime) {
		int num = -1;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("code", code);
			params.put("publishTime", publishTime);
			num = sqlMapClient.update("FundTurnoverDAO.deleteFundTurnoverByPublishTime", params);
		} catch (Exception e) {
			logger.error("deleteFundTurnoverByPublishTime error", e);
		}
		return num;
	}
}
