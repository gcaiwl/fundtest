package com.longxi.data.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.longxi.data.obj.Query;
import com.longxi.data.obj.FundScaleDO;

import com.longxi.data.dao.FundScaleDAO;

/**
 * fund_scale表的DAO层实现类
 * Date 2017-11-21 23:51:41
 */
public class FundScaleDAOImpl extends SqlMapBaseDAO implements FundScaleDAO {
	private static final Logger logger = LoggerFactory.getLogger(FundScaleDAOImpl.class);

	@Override
	public FundScaleDO queryFundScaleById(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<FundScaleDO> list = queryFundScale(params);
		if(list == null || list.isEmpty()){
			return null;
		}else if(list.size() != 1){
			String errMsg = "queryFundScaleById return too many records,the param="+params;
			logger.error(errMsg);
			throw new RuntimeException(errMsg);
		}
		return list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	private List<FundScaleDO> queryFundScale(Map<String,Object> params) {
		List<FundScaleDO> list = Collections.emptyList();
		try {
			list = (List<FundScaleDO>) sqlMapClient.queryForList("FundScaleDAO.queryFundScale", params);
		} catch (Exception e) {
			logger.error("queryFundScale ERROR", e);
		}
		return list;
	}
	
	
	@Override
	public int updateFundScale(FundScaleDO instance) {
		int num = -1;
		try {
			num = sqlMapClient.update("FundScaleDAO.updateFundScale", instance);
		} catch (Exception e) {
			logger.error("updateFundScale error", e);
		}
		return num;
	}
	
	@Override
	public Long insertFundScale(FundScaleDO instance) {
		Long id = null;
		try {
			id = (Long) sqlMapClient.insert("FundScaleDAO.insertFundScale", instance);
		} catch (Exception e) {
			logger.error("insertFundScale error", e);
		}
		return id;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FundScaleDO> queryFundScaleByPage(Query<FundScaleDO> query) {
		Map<String, Object> params = convertQuery2Param(query);
		int count = countFundScaleForPage(query);
		query.setTotalSize(count);
		List<FundScaleDO> list = Collections.emptyList();
		try {
			list = (List<FundScaleDO>) sqlMapClient.queryForList("FundScaleDAO.queryFundScaleByPage", params);
		} catch (Exception e) {
			logger.error("queryFundScaleByPage error", e);
		}
		return list;
	}

	@Override
	public int countFundScaleForPage(Query<FundScaleDO> query) {
		int count = 0;
		Map<String, Object> params = convertQuery2Param(query);
		try {
			count = (Integer) sqlMapClient.queryForObject("FundScaleDAO.countFundScale", params);
		} catch (Exception e) {
			logger.error("countFundScaleForPage error", e);
		}
		return count;
	}
	
	private Map<String, Object> convertQuery2Param(Query<FundScaleDO> query){
		FundScaleDO instance = query.getModule();
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
