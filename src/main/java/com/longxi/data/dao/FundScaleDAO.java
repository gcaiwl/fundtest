package com.longxi.data.dao;

import java.util.Date;
import java.util.List;

import com.longxi.data.obj.FundScaleDO;
import com.longxi.data.obj.Query;

public interface FundScaleDAO {

	/**
	* 插入单条记录
	* @param instance
	*/
	public Long insertFundScale(FundScaleDO instance);

	/**
	* 根据主键更新单条记录
	* @param instance
	*/
	public int updateFundScale(FundScaleDO instance);

	/**
	* 根据主键查询单条记录
	* @param id 主键
	*/
	public FundScaleDO queryFundScaleById(Long id);
	
	/**
	* 根据自定义条件分页查询多条记录
	* @param query 分页查询条件
	*/
	public List<FundScaleDO> queryFundScaleByPage(Query<FundScaleDO> query);

	/**
	* 根据自定义条件统计总记录条数，配合分页查询使用
	* @param query 分页查询条件
	*/
	public int countFundScaleForPage(Query<FundScaleDO> query);

	/**
	 * 根据自定义条件统计总记录条数，配合分页查询使用
	 * @param code 分页查询条件
	 */
	public FundScaleDO queryFundScaleByPublishTime(String code, Date publishTime);
}