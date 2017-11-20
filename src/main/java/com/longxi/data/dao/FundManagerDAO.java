package com.longxi.data.dao;

import java.util.List;

import com.longxi.data.obj.FundManagerDO;
import com.longxi.data.obj.Query;

public interface FundManagerDAO {

	/**
	* 插入单条记录
	* @param instance
	*/
	public Long insertFundManager(FundManagerDO instance);

	/**
	* 根据主键更新单条记录
	* @param instance
	*/
	public int updateFundManager(FundManagerDO instance);

	/**
	* 根据主键查询单条记录
	* @param id 主键
	*/
	public FundManagerDO queryFundManagerById(Long id);
	
	/**
	* 根据自定义条件分页查询多条记录
	* @param query 分页查询条件
	*/
	public List<FundManagerDO> queryFundManagerByPage(Query<FundManagerDO> query);

	/**
	* 根据自定义条件统计总记录条数，配合分页查询使用
	* @param query 分页查询条件
	*/
	public int countFundManagerForPage(Query<FundManagerDO> query);

	/**
	 * 根据自定义条件统计总记录条数，配合分页查询使用
	 * @param query 分页查询条件
	 */
	public FundManagerDO queryFundManagerByManager(String code, String manager);
}