package com.longxi.data.dao;

import java.util.List;

import com.longxi.data.obj.FundBondPositionDO;
import com.longxi.data.obj.Query;

public interface FundBondPositionDAO {

	/**
	* 插入单条记录
	* @param instance
	*/
	public Long insertFundBondPosition(FundBondPositionDO instance);

	/**
	* 根据主键更新单条记录
	* @param instance
	*/
	public int updateFundBondPosition(FundBondPositionDO instance);

	/**
	* 根据主键查询单条记录
	* @param id 主键
	*/
	public FundBondPositionDO queryFundBondPositionById(Long id);
	
	/**
	* 根据自定义条件分页查询多条记录
	* @param query 分页查询条件
	*/
	public List<FundBondPositionDO> queryFundBondPositionByPage(Query<FundBondPositionDO> query);

	/**
	* 根据自定义条件统计总记录条数，配合分页查询使用
	* @param query 分页查询条件
	*/
	public int countFundBondPositionForPage(Query<FundBondPositionDO> query);

	/**
	 * 根据主键查询单条记录
	 * @param code 主键
	 */
	public FundBondPositionDO queryFundBondPositionByQuarter(String code, String quarter, String bondCode);

	/**
	 * 根据主键查询单条记录
	 * @param code 主键
	 */
	public List<FundBondPositionDO> queryFundBondPositionLatestByCode(String code);
}