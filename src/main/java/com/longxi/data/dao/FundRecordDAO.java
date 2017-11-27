package com.longxi.data.dao;

import java.util.List;

import com.longxi.data.obj.FundRecordDO;
import com.longxi.data.obj.Query;

public interface FundRecordDAO {

	/**
	* 插入单条记录
	* @param instance
	*/
	public Long insertFundRecord(FundRecordDO instance);

	/**
	* 根据主键更新单条记录
	* @param instance
	*/
	public int updateFundRecord(FundRecordDO instance);

	/**
	* 根据主键查询单条记录
	* @param id 主键
	*/
	public FundRecordDO queryFundRecordById(Long id);
	
	/**
	* 根据自定义条件分页查询多条记录
	* @param query 分页查询条件
	*/
	public List<FundRecordDO> queryFundRecordByPage(Query<FundRecordDO> query);

	/**
	* 根据自定义条件统计总记录条数，配合分页查询使用
	* @param query 分页查询条件
	*/
	public int countFundRecordForPage(Query<FundRecordDO> query);

	/**
	 * 根据自定义条件统计总记录条数，配合分页查询使用
	 * @param status 分页查询条件
	 */
	public List<FundRecordDO> queryFundRecordByStatus(int status);

	/**
	 * 根据主键更新单条记录
	 * @param code
	 */
	public int updateFundRecordByCode(String code, int status, int pass);
}