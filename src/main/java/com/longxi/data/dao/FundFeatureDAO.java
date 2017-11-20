package com.longxi.data.dao;

import java.util.List;

import com.longxi.data.obj.FundFeatureDO;
import com.longxi.data.obj.Query;

public interface FundFeatureDAO {

	/**
	* 插入单条记录
	* @param instance
	*/
	public Long insertFundFeature(FundFeatureDO instance);

	/**
	* 根据主键更新单条记录
	* @param instance
	*/
	public int updateFundFeature(FundFeatureDO instance);

	/**
	* 根据主键查询单条记录
	* @param id 主键
	*/
	public FundFeatureDO queryFundFeatureById(Long id);
	
	/**
	* 根据自定义条件分页查询多条记录
	* @param query 分页查询条件
	*/
	public List<FundFeatureDO> queryFundFeatureByPage(Query<FundFeatureDO> query);

	/**
	* 根据自定义条件统计总记录条数，配合分页查询使用
	* @param query 分页查询条件
	*/
	public int countFundFeatureForPage(Query<FundFeatureDO> query);

	/**
	 * 根据自定义条件统计总记录条数，配合分页查询使用
	 * @param code 分页查询条件
	 */
	public FundFeatureDO queryFundFeatureByFeature(String code, String feature);
}