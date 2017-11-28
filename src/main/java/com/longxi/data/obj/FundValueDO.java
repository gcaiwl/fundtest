package com.longxi.data.obj;

import java.math.BigDecimal;
import java.util.Date;

/**
* fund_value表对应的DO
* Date 2017-11-17 21:38:15
*/
@SuppressWarnings("serial")
public class FundValueDO extends BaseDO {
    /**
     * fund_value.id
     * 主键
     */
    private Long id;

    /**
     * fund_value.gmt_create
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * fund_value.gmt_modified
     * 修改时间
     */
    private Date gmtModified;

    /**
     * fund_value.code
     * 编码
     */
    private String code;

    /**
     * fund_value.value
     * 净值
     */
    private BigDecimal value;

    /**
     * fund_value.total_value
     * 累计净值
     */
    private BigDecimal totalValue;

    /**
     * fund_value.increase
     * 日增长率
     */
    private BigDecimal increase;

    /**
     * fund_value.publish_time
     * 报告时间
     */
    private Date publishTime;

    /**
     * 动态分表名
     */
    private String tableName;

    /**
     * 获取 主键
     * @return the value of fund_value.id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 主键
     * @param id the value for fund_value.id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 创建时间
     * @return the value of fund_value.gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置 创建时间
     * @param gmtCreate the value for fund_value.gmt_create
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取 修改时间
     * @return the value of fund_value.gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置 修改时间
     * @param gmtModified the value for fund_value.gmt_modified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 获取 编码
     * @return the value of fund_value.code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置 编码
     * @param code the value for fund_value.code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取 净值
     * @return the value of fund_value.value
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * 设置 净值
     * @param value the value for fund_value.value
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * 获取 累计净值
     * @return the value of fund_value.total_value
     */
    public BigDecimal getTotalValue() {
        return totalValue;
    }

    /**
     * 设置 累计净值
     * @param totalValue the value for fund_value.total_value
     */
    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    /**
     * 获取 日增长率
     * @return the value of fund_value.increase
     */
    public BigDecimal getIncrease() {
        return increase;
    }

    /**
     * 设置 日增长率
     * @param increase the value for fund_value.increase
     */
    public void setIncrease(BigDecimal increase) {
        this.increase = increase;
    }

    /**
     * 获取 报告时间
     * @return the value of fund_value.publish_time
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * 设置 报告时间
     * @param publishTime the value for fund_value.publish_time
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}