package com.longxi.data.obj;

import java.math.BigDecimal;
import java.util.Date;

/**
* fund_manager表对应的DO
* Date 2017-11-17 21:38:15
*/
@SuppressWarnings("serial")
public class FundManagerDO extends BaseDO {
    /**
     * fund_manager.id
     * 主键
     */
    private Long id;

    /**
     * fund_manager.gmt_create
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * fund_manager.gmt_modified
     * 结束时间
     */
    private Date gmtModified;

    /**
     * fund_manager.code
     * 编码
     */
    private String code;

    /**
     * fund_manager.manager
     * 经理
     */
    private String manager;

    /**
     * fund_manager.start_time
     * 开始时间
     */
    private Date startTime;

    /**
     * fund_manager.end_time
     * 结束时间
     */
    private Date endTime;

    /**
     * fund_manager.redound
     * 
     */
    private BigDecimal redound;

    /**
     * 获取 主键
     * @return the value of fund_manager.id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 主键
     * @param id the value for fund_manager.id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 创建时间
     * @return the value of fund_manager.gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置 创建时间
     * @param gmtCreate the value for fund_manager.gmt_create
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取 结束时间
     * @return the value of fund_manager.gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置 结束时间
     * @param gmtModified the value for fund_manager.gmt_modified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 获取 编码
     * @return the value of fund_manager.code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置 编码
     * @param code the value for fund_manager.code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取 经理
     * @return the value of fund_manager.manager
     */
    public String getManager() {
        return manager;
    }

    /**
     * 设置 经理
     * @param manager the value for fund_manager.manager
     */
    public void setManager(String manager) {
        this.manager = manager;
    }

    /**
     * 获取 开始时间
     * @return the value of fund_manager.start_time
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置 开始时间
     * @param startTime the value for fund_manager.start_time
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取 结束时间
     * @return the value of fund_manager.end_time
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置 结束时间
     * @param endTime the value for fund_manager.end_time
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取 
     * @return the value of fund_manager.redound
     */
    public BigDecimal getRedound() {
        return redound;
    }

    /**
     * 设置 
     * @param redound the value for fund_manager.redound
     */
    public void setRedound(BigDecimal redound) {
        this.redound = redound;
    }
}