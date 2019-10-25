package com.longxi.data.dao.impl;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.longxi.data.obj.Query;
import com.longxi.data.obj.FundValueDO;

import com.longxi.data.dao.FundValueDAO;

/**
 * fund_value表的DAO层实现类
 * Date 2017-11-21 23:51:41
 */
public class FundValueDAOImpl extends SqlMapBaseDAO implements FundValueDAO {
	private static final Logger logger = LoggerFactory.getLogger(FundValueDAOImpl.class);

	@Override
	public FundValueDO queryFundValueById(Long id, String code) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("tableName", getTableName(code));
		List<FundValueDO> list = queryFundValue(params);
		if(list == null || list.isEmpty()){
			return null;
		}else if(list.size() != 1){
			String errMsg = "queryFundValueById return too many records,the param="+params;
			logger.error(errMsg);
			throw new RuntimeException(errMsg);
		}
		return list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	private List<FundValueDO> queryFundValue(Map<String,Object> params) {
		List<FundValueDO> list = Collections.emptyList();
		try {
			list = (List<FundValueDO>) sqlMapClient.queryForList("FundValueDAO.queryFundValue", params);
		} catch (Exception e) {
			logger.error("queryFundValue ERROR", e);
		}
		return list;
	}
	
	
	@Override
	public int updateFundValue(FundValueDO instance, String code) {
		int num = -1;
		try {
			instance.setTableName(getTableName(code));
			num = sqlMapClient.update("FundValueDAO.updateFundValue", instance);
		} catch (Exception e) {
			logger.error("updateFundValue error", e);
		}
		return num;
	}
	
	@Override
	public Long insertFundValue(FundValueDO instance, String code) {
		Long id = null;
		try {
			instance.setTableName(getTableName(code));
			id = (Long) sqlMapClient.insert("FundValueDAO.insertFundValue", instance);
		} catch (Exception e) {
			logger.error("insertFundValue error", e);
		}
		return id;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FundValueDO> queryFundValueByPage(Query<FundValueDO> query, String code) {
		Map<String, Object> params = convertQuery2Param(query);
		int count = countFundValueForPage(query, code);
		query.setTotalSize(count);
		params.put("tableName", getTableName(code));
		List<FundValueDO> list = Collections.emptyList();
		try {
			list = (List<FundValueDO>) sqlMapClient.queryForList("FundValueDAO.queryFundValueByPage", params);
		} catch (Exception e) {
			logger.error("queryFundValueByPage error", e);
		}
		return list;
	}

	@Override
	public int countFundValueForPage(Query<FundValueDO> query, String code) {
		int count = 0;
		Map<String, Object> params = convertQuery2Param(query);
		params.put("tableName", getTableName(code));
		try {
			count = (Integer) sqlMapClient.queryForObject("FundValueDAO.countFundValue", params);
		} catch (Exception e) {
			logger.error("countFundValueForPage error", e);
		}
		return count;
	}
	
	private Map<String, Object> convertQuery2Param(Query<FundValueDO> query){
		FundValueDO instance = query.getModule();
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
	public FundValueDO queryFundValueByPublishTime(String code, Date publishTime) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		params.put("publishTime", publishTime);
		params.put("tableName", getTableName(code));
		List<FundValueDO> list = queryFundValue(params);
		if(list == null || list.isEmpty()){
			return null;
		}else if(list.size() != 1){
			String errMsg = "queryFundValueByPublishTime return too many records,the param="+params;
			throw new RuntimeException(errMsg);
		}
		return list.get(0);
	}

	/**
	 * @param code
	 * @return
	 */
	private String getTableName(String code) {
		String tableName = "fund_value_%04d";
		if (StringUtils.isBlank(code)) {
			return String.format(tableName, 0);
		}
		return String.format(tableName, Integer.parseInt(code) % 128);
	}

	@Override
	public int deleteFundValueByPublishTime(String code, Date publishTime) {
		int num = -1;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("code", code);
			params.put("publishTime", publishTime);
			params.put("tableName", getTableName(code));
			num = sqlMapClient.update("FundValueDAO.deleteFundValueByPublishTime", params);
		} catch (Exception e) {
			logger.error("deleteFundValueByPublishTime error", e);
		}
		return num;
	}
}
