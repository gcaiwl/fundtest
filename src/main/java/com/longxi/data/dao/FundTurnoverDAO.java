package com.longxi.data.dao;

import java.util.Date;
import java.util.List;

import com.longxi.data.obj.FundTurnoverDO;
import com.longxi.data.obj.Query;

public interface FundTurnoverDAO {

	/**
	* 插入单条记录
	* @param instance
	*/
	public Long insertFundTurnover(FundTurnoverDO instance);

	/**
	* 根据主键更新单条记录
	* @param instance
	*/
	public int updateFundTurnover(FundTurnoverDO instance);

	/**
	* 根据主键查询单条记录
	* @param id 主键
	*/
	public FundTurnoverDO queryFundTurnoverById(Long id);
	
	/**
	* 根据自定义条件分页查询多条记录
	* @param query 分页查询条件
	*/
	public List<FundTurnoverDO> queryFundTurnoverByPage(Query<FundTurnoverDO> query);

	/**
	* 根据自定义条件统计总记录条数，配合分页查询使用
	* @param query 分页查询条件
	*/
	public int countFundTurnoverForPage(Query<FundTurnoverDO> query);

	/**
	 * 根据主键查询单条记录
	 * @param code 主键
	 */
	public FundTurnoverDO queryFundTurnoverByPublishTime(String code, Date publishTime);

	/**
	 * 根据主键查询单条记录
	 * @param code 主键
	 */
	public FundTurnoverDO queryFundTurnoverLatestByCode(String code);
}