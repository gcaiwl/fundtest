package com.longxi.data.dao;

import java.util.List;

import com.longxi.data.obj.FundIndustryDO;
import com.longxi.data.obj.Query;

public interface FundIndustryDAO {

	/**
	* 插入单条记录
	* @param instance
	*/
	public Long insertFundIndustry(FundIndustryDO instance);

	/**
	* 根据主键更新单条记录
	* @param instance
	*/
	public int updateFundIndustry(FundIndustryDO instance);

	/**
	* 根据主键查询单条记录
	* @param id 主键
	*/
	public FundIndustryDO queryFundIndustryById(Long id);
	
	/**
	* 根据自定义条件分页查询多条记录
	* @param query 分页查询条件
	*/
	public List<FundIndustryDO> queryFundIndustryByPage(Query<FundIndustryDO> query);

	/**
	* 根据自定义条件统计总记录条数，配合分页查询使用
	* @param query 分页查询条件
	*/
	public int countFundIndustryForPage(Query<FundIndustryDO> query);

	/**
	 * 根据自定义条件统计总记录条数，配合分页查询使用
	 * @param query 分页查询条件
	 */
	public FundIndustryDO queryFundIndustryByQuarter(String code, String quarter, String industry);
}