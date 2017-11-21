package com.longxi.data.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.longxi.data.obj.Query;
import com.longxi.data.obj.FundConfigDO;

import com.longxi.data.dao.FundConfigDAO;

/**
 * fund_config表的DAO层实现类
 * Date 2017-11-21 23:51:41
 */
public class FundConfigDAOImpl extends SqlMapBaseDAO implements FundConfigDAO {
	private static final Logger logger = LoggerFactory.getLogger(FundConfigDAOImpl.class);

	@Override
	public FundConfigDO queryFundConfigById(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<FundConfigDO> list = queryFundConfig(params);
		if(list == null || list.isEmpty()){
			return null;
		}else if(list.size() != 1){
			String errMsg = "queryFundConfigById return too many records,the param="+params;
			logger.error(errMsg);
			throw new RuntimeException(errMsg);
		}
		return list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	private List<FundConfigDO> queryFundConfig(Map<String,Object> params) {
		List<FundConfigDO> list = Collections.emptyList();
		try {
			list = (List<FundConfigDO>) sqlMapClient.queryForList("FundConfigDAO.queryFundConfig", params);
		} catch (Exception e) {
			logger.error("queryFundConfig ERROR", e);
		}
		return list;
	}
	
	
	@Override
	public int updateFundConfig(FundConfigDO instance) {
		int num = -1;
		try {
			num = sqlMapClient.update("FundConfigDAO.updateFundConfig", instance);
		} catch (Exception e) {
			logger.error("updateFundConfig error", e);
		}
		return num;
	}
	
	@Override
	public Long insertFundConfig(FundConfigDO instance) {
		Long id = null;
		try {
			id = (Long) sqlMapClient.insert("FundConfigDAO.insertFundConfig", instance);
		} catch (Exception e) {
			logger.error("insertFundConfig error", e);
		}
		return id;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FundConfigDO> queryFundConfigByPage(Query<FundConfigDO> query) {
		Map<String, Object> params = convertQuery2Param(query);
		int count = countFundConfigForPage(query);
		query.setTotalSize(count);
		List<FundConfigDO> list = Collections.emptyList();
		try {
			list = (List<FundConfigDO>) sqlMapClient.queryForList("FundConfigDAO.queryFundConfigByPage", params);
		} catch (Exception e) {
			logger.error("queryFundConfigByPage error", e);
		}
		return list;
	}

	@Override
	public int countFundConfigForPage(Query<FundConfigDO> query) {
		int count = 0;
		Map<String, Object> params = convertQuery2Param(query);
		try {
			count = (Integer) sqlMapClient.queryForObject("FundConfigDAO.countFundConfig", params);
		} catch (Exception e) {
			logger.error("countFundConfigForPage error", e);
		}
		return count;
	}
	
	private Map<String, Object> convertQuery2Param(Query<FundConfigDO> query){
		FundConfigDO instance = query.getModule();
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
