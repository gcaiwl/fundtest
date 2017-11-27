package com.longxi.data.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.longxi.data.obj.FundRecordDO;
import com.longxi.data.obj.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.longxi.data.dao.FundRecordDAO;

/**
 * fund_record表的DAO层实现类
 * Date 2017-11-27 23:34:31
 */
public class FundRecordDAOImpl extends SqlMapBaseDAO implements FundRecordDAO {
	private static final Logger logger = LoggerFactory.getLogger(FundRecordDAOImpl.class);

	@Override
	public FundRecordDO queryFundRecordById(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<FundRecordDO> list = queryFundRecord(params);
		if(list == null || list.isEmpty()){
			return null;
		}else if(list.size() != 1){
			String errMsg = "queryFundRecordById return too many records,the param="+params;
			logger.error(errMsg);
			throw new RuntimeException(errMsg);
		}
		return list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	private List<FundRecordDO> queryFundRecord(Map<String,Object> params) {
		List<FundRecordDO> list = Collections.emptyList();
		try {
			list = (List<FundRecordDO>) sqlMapClient.queryForList("FundRecordDAO.queryFundRecord", params);
		} catch (Exception e) {
			logger.error("queryFundRecord ERROR", e);
		}
		return list;
	}
	
	
	@Override
	public int updateFundRecord(FundRecordDO instance) {
		int num = -1;
		try {
			num = sqlMapClient.update("FundRecordDAO.updateFundRecord", instance);
		} catch (Exception e) {
			logger.error("updateFundRecord error", e);
		}
		return num;
	}
	
	@Override
	public Long insertFundRecord(FundRecordDO instance) {
		Long id = null;
		try {
			id = (Long) sqlMapClient.insert("FundRecordDAO.insertFundRecord", instance);
		} catch (Exception e) {
			logger.error("insertFundRecord error", e);
		}
		return id;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FundRecordDO> queryFundRecordByPage(Query<FundRecordDO> query) {
		Map<String, Object> params = convertQuery2Param(query);
		int count = countFundRecordForPage(query);
		query.setTotalSize(count);
		List<FundRecordDO> list = Collections.emptyList();
		try {
			list = (List<FundRecordDO>) sqlMapClient.queryForList("FundRecordDAO.queryFundRecordByPage", params);
		} catch (Exception e) {
			logger.error("queryFundRecordByPage error", e);
		}
		return list;
	}

	@Override
	public int countFundRecordForPage(Query<FundRecordDO> query) {
		int count = 0;
		Map<String, Object> params = convertQuery2Param(query);
		try {
			count = (Integer) sqlMapClient.queryForObject("FundRecordDAO.countFundRecord", params);
		} catch (Exception e) {
			logger.error("countFundRecordForPage error", e);
		}
		return count;
	}
	
	private Map<String, Object> convertQuery2Param(Query<FundRecordDO> query){
		FundRecordDO instance = query.getModule();
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
	public List<FundRecordDO> queryFundRecordByStatus(int status) {
		Map<String, Object> params = new HashMap<>();
		params.put("status", status);
		List<FundRecordDO> list = Collections.emptyList();
		try {
			list = (List<FundRecordDO>) sqlMapClient.queryForList("FundRecordDAO.queryFundRecordByStatus", params);
		} catch (Exception e) {
			logger.error("queryFundRecordByStatus error", e);
		}
		return list;
	}

	@Override
	public int updateFundRecordByCode(String code, int status, int pass) {
		int num = -1;
		try {
			FundRecordDO instance = new FundRecordDO();
			instance.setCode(code);
			instance.setStatus(status);
			instance.setPass(pass);
			num = sqlMapClient.update("FundRecordDAO.updateFundRecordByCode", instance);
		} catch (Exception e) {
			logger.error("updateFundRecord error", e);
		}
		return num;
	}
}
