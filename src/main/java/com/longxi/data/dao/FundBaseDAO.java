package com.longxi.data.dao;

import java.util.List;

import com.longxi.data.obj.FundBaseDO;
import com.longxi.data.obj.Query;

public interface FundBaseDAO {

	/**
	* 插入单条记录
	* @param instance
	*/
	public Long insertFundBase(FundBaseDO instance);

	/**
	* 根据主键更新单条记录
	* @param instance
	*/
	public int updateFundBase(FundBaseDO instance);

	/**
	* 根据主键查询单条记录
	* @param id 主键
	*/
	public FundBaseDO queryFundBaseById(Long id);
	
	/**
	* 根据自定义条件分页查询多条记录
	* @param query 分页查询条件
	*/
	public List<FundBaseDO> queryFundBaseByPage(Query<FundBaseDO> query);

	/**
	* 根据自定义条件统计总记录条数，配合分页查询使用
	* @param query 分页查询条件
	*/
	public int countFundBaseForPage(Query<FundBaseDO> query);

	/**
	 * 根据自定义条件统计总记录条数，配合分页查询使用
	 * @param code 分页查询条件
	 */
	public FundBaseDO queryFundBaseByCode(String code);
}