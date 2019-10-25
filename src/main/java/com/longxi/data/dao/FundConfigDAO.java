package com.longxi.data.dao;

import java.util.Date;
import java.util.List;

import com.longxi.data.obj.FundConfigDO;
import com.longxi.data.obj.Query;

public interface FundConfigDAO {

	/**
	* 插入单条记录
	* @param instance
	*/
	public Long insertFundConfig(FundConfigDO instance);

	/**
	* 根据主键更新单条记录
	* @param instance
	*/
	public int updateFundConfig(FundConfigDO instance);

	/**
	* 根据主键查询单条记录
	* @param id 主键
	*/
	public FundConfigDO queryFundConfigById(Long id);
	
	/**
	* 根据自定义条件分页查询多条记录
	* @param query 分页查询条件
	*/
	public List<FundConfigDO> queryFundConfigByPage(Query<FundConfigDO> query);

	/**
	* 根据自定义条件统计总记录条数，配合分页查询使用
	* @param query 分页查询条件
	*/
	public int countFundConfigForPage(Query<FundConfigDO> query);

	/**
	 * 根据主键查询单条记录
	 * @param code 主键
	 */
	public FundConfigDO queryFundConfigByPublishTime(String code, Date publishTime);

	/**
	 * 根据主键查询单条记录
	 * @param code 主键
	 */
	public FundConfigDO queryFundConfigLatestByCode(String code);

	/**
	 * 根据主键查询单条记录
	 * @param code 主键
	 */
	public int deleteFundConfigByPublishTime(String code, Date publishTime);
}