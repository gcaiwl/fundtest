package com.longxi.data.dao;

import java.util.List;

import com.longxi.data.obj.FundStyleDO;
import com.longxi.data.obj.Query;

public interface FundStyleDAO {

	/**
	* 插入单条记录
	* @param instance
	*/
	public Long insertFundStyle(FundStyleDO instance);

	/**
	* 根据主键更新单条记录
	* @param instance
	*/
	public int updateFundStyle(FundStyleDO instance);

	/**
	* 根据主键查询单条记录
	* @param id 主键
	*/
	public FundStyleDO queryFundStyleById(Long id);
	
	/**
	* 根据自定义条件分页查询多条记录
	* @param query 分页查询条件
	*/
	public List<FundStyleDO> queryFundStyleByPage(Query<FundStyleDO> query);

	/**
	* 根据自定义条件统计总记录条数，配合分页查询使用
	* @param query 分页查询条件
	*/
	public int countFundStyleForPage(Query<FundStyleDO> query);

	/**
	 * 根据主键查询单条记录
	 * @param code 主键
	 */
	public FundStyleDO queryFundStyleByStyle(String code, String style, String quarter);

	/**
	 * 根据主键查询单条记录
	 * @param code 主键
	 */
	public FundStyleDO queryFundStyleLatestByCode(String code);
}