package com.longxi.data.obj;

import java.math.BigDecimal;
import java.util.Date;

/**
* fund_index表对应的DO
* Date 2017-12-02 15:26:52
*/
@SuppressWarnings("serial")
public class FundIndexDO extends BaseDO {
    /**
     * fund_index.id
     * 主键
     */
    private Long id;

    /**
     * fund_index.gmt_create
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * fund_index.gmt_modified
     * 修改时间
     */
    private Date gmtModified;

    /**
     * fund_index.code
     * 编码
     */
    private String code;

    /**
     * fund_index.feature
     * 跟踪指数
     */
    private String feature;

    /**
     * fund_index.index_value
     * 跟踪误差
     */
    private BigDecimal indexValue;

    /**
     * fund_index.avg_value
     * 同类跟踪误差
     */
    private BigDecimal avgValue;

    /**
     * 获取 主键
     * @return the value of fund_index.id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 主键
     * @param id the value for fund_index.id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 创建时间
     * @return the value of fund_index.gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置 创建时间
     * @param gmtCreate the value for fund_index.gmt_create
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取 修改时间
     * @return the value of fund_index.gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置 修改时间
     * @param gmtModified the value for fund_index.gmt_modified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 获取 编码
     * @return the value of fund_index.code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置 编码
     * @param code the value for fund_index.code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取 跟踪指数
     * @return the value of fund_index.feature
     */
    public String getFeature() {
        return feature;
    }

    /**
     * 设置 跟踪指数
     * @param feature the value for fund_index.feature
     */
    public void setFeature(String feature) {
        this.feature = feature;
    }

    /**
     * 获取 跟踪误差
     * @return the value of fund_index.index_value
     */
    public BigDecimal getIndexValue() {
        return indexValue;
    }

    /**
     * 设置 跟踪误差
     * @param indexValue the value for fund_index.index_value
     */
    public void setIndexValue(BigDecimal indexValue) {
        this.indexValue = indexValue;
    }

    /**
     * 获取 同类跟踪误差
     * @return the value of fund_index.avg_value
     */
    public BigDecimal getAvgValue() {
        return avgValue;
    }

    /**
     * 设置 同类跟踪误差
     * @param avgValue the value for fund_index.avg_value
     */
    public void setAvgValue(BigDecimal avgValue) {
        this.avgValue = avgValue;
    }
}