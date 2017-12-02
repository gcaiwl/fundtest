package com.longxi.data.dao;

import java.util.List;

import com.longxi.data.obj.FundIndexDO;
import com.longxi.data.obj.Query;

public interface FundIndexDAO {

	/**
	* 插入单条记录
	* @param instance
	*/
	public Long insertFundIndex(FundIndexDO instance);

	/**
	* 根据主键更新单条记录
	* @param instance
	*/
	public int updateFundIndex(FundIndexDO instance);

	/**
	* 根据主键查询单条记录
	* @param id 主键
	*/
	public FundIndexDO queryFundIndexById(Long id);
	
	/**
	* 根据自定义条件分页查询多条记录
	* @param query 分页查询条件
	*/
	public List<FundIndexDO> queryFundIndexByPage(Query<FundIndexDO> query);

	/**
	* 根据自定义条件统计总记录条数，配合分页查询使用
	* @param query 分页查询条件
	*/
	public int countFundIndexForPage(Query<FundIndexDO> query);

	/**
	 * 根据主键查询单条记录
	 * @param code 主键
	 */
	public FundIndexDO queryFundIndexByFeature(String code, String feature);
}