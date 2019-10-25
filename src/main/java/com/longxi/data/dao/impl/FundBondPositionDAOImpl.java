package com.longxi.data.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.longxi.data.obj.FundIndustryDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.longxi.data.obj.Query;
import com.longxi.data.obj.FundBondPositionDO;

import com.longxi.data.dao.FundBondPositionDAO;

/**
 * fund_bond_position表的DAO层实现类
 * Date 2017-11-17 21:38:15
 */
public class FundBondPositionDAOImpl extends SqlMapBaseDAO implements FundBondPositionDAO {
	private static final Logger logger = LoggerFactory.getLogger(FundBondPositionDAOImpl.class);

	@Override
	public FundBondPositionDO queryFundBondPositionById(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<FundBondPositionDO> list = queryFundBondPosition(params);
		if(list == null || list.isEmpty()){
			return null;
		}else if(list.size() != 1){
			String errMsg = "queryFundBondPositionById return too many records,the param="+params;
			logger.error(errMsg);
			throw new RuntimeException(errMsg);
		}
		return list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	private List<FundBondPositionDO> queryFundBondPosition(Map<String,Object> params) {
		List<FundBondPositionDO> list = Collections.emptyList();
		try {
			list = (List<FundBondPositionDO>) sqlMapClient.queryForList("FundBondPositionDAO.queryFundBondPosition", params);
		} catch (Exception e) {
			logger.error("queryFundBondPosition ERROR", e);
		}
		return list;
	}
	
	
	@Override
	public int updateFundBondPosition(FundBondPositionDO instance) {
		int num = -1;
		try {
			num = sqlMapClient.update("FundBondPositionDAO.updateFundBondPosition", instance);
		} catch (Exception e) {
			logger.error("updateFundBondPosition error", e);
		}
		return num;
	}
	
	@Override
	public Long insertFundBondPosition(FundBondPositionDO instance) {
		Long id = null;
		try {
			id = (Long) sqlMapClient.insert("FundBondPositionDAO.insertFundBondPosition", instance);
		} catch (Exception e) {
			logger.error("insertFundBondPosition error", e);
		}
		return id;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FundBondPositionDO> queryFundBondPositionByPage(Query<FundBondPositionDO> query) {
		Map<String, Object> params = convertQuery2Param(query);
		int count = countFundBondPositionForPage(query);
		query.setTotalSize(count);
		List<FundBondPositionDO> list = Collections.emptyList();
		try {
			list = (List<FundBondPositionDO>) sqlMapClient.queryForList("FundBondPositionDAO.queryFundBondPositionByPage", params);
		} catch (Exception e) {
			logger.error("queryFundBondPositionByPage error", e);
		}
		return list;
	}

	@Override
	public int countFundBondPositionForPage(Query<FundBondPositionDO> query) {
		int count = 0;
		Map<String, Object> params = convertQuery2Param(query);
		try {
			count = (Integer) sqlMapClient.queryForObject("FundBondPositionDAO.countFundBondPosition", params);
		} catch (Exception e) {
			logger.error("countFundBondPositionForPage error", e);
		}
		return count;
	}
	
	private Map<String, Object> convertQuery2Param(Query<FundBondPositionDO> query){
		FundBondPositionDO instance = query.getModule();
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
	public FundBondPositionDO queryFundBondPositionByQuarter(String code, String quarter, String bondCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		params.put("quarter", quarter);
		params.put("bondCode", bondCode);
		List<FundBondPositionDO> list = queryFundBondPosition(params);
		if(list == null || list.isEmpty()){
			return null;
		}else if(list.size() != 1){
			String errMsg = "queryFundBondPositionByQuarter return too many records,the param="+params;
			throw new RuntimeException(errMsg);
		}
		return list.get(0);
	}

	@Override
	public List<FundBondPositionDO> queryFundBondPositionLatestByCode(String code) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);

		String quarter = null;
		try {
			quarter = (String) sqlMapClient.queryForObject("FundBondPositionDAO.maxBondPositionQuarter", params);
		} catch (Exception e) {
			logger.error(code + " maxBondPositionQuarter error", e);
		}

		params.put("quarter", quarter);
		List<FundBondPositionDO> list = queryFundBondPosition(params);
		return list;
	}

	@Override
	public int deleteFundBondPositionByQuarter(String code, String quarter, String bondCode) {
		int num = -1;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("code", code);
			params.put("quarter", quarter);
			params.put("bondCode", bondCode);
			num = sqlMapClient.update("FundBondPositionDAO.deleteFundBondPositionByQuarter", params);
		} catch (Exception e) {
			logger.error("deleteFundBondPositionByQuarter error", e);
		}
		return num;
	}
}
