package com.longxi.data.obj;

import java.math.BigDecimal;
import java.util.Date;

/**
* fund_feature表对应的DO
* Date 2017-11-17 21:38:15
*/
@SuppressWarnings("serial")
public class FundFeatureDO extends BaseDO {
    /**
     * fund_feature.id
     * 主键
     */
    private Long id;

    /**
     * fund_feature.gmt_create
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * fund_feature.gmt_modified
     * 修改时间
     */
    private Date gmtModified;

    /**
     * fund_feature.code
     * 编码
     */
    private String code;

    /**
     * fund_feature.feature
     * 指标
     */
    private String feature;

    /**
     * fund_feature.year1
     * 近一年
     */
    private BigDecimal year1;

    /**
     * fund_feature.year2
     * 近两年
     */
    private BigDecimal year2;

    /**
     * fund_feature.year3
     * 近三年
     */
    private BigDecimal year3;

    /**
     * 获取 主键
     * @return the value of fund_feature.id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 主键
     * @param id the value for fund_feature.id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 创建时间
     * @return the value of fund_feature.gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置 创建时间
     * @param gmtCreate the value for fund_feature.gmt_create
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取 修改时间
     * @return the value of fund_feature.gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置 修改时间
     * @param gmtModified the value for fund_feature.gmt_modified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 获取 编码
     * @return the value of fund_feature.code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置 编码
     * @param code the value for fund_feature.code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取 指标
     * @return the value of fund_feature.feature
     */
    public String getFeature() {
        return feature;
    }

    /**
     * 设置 指标
     * @param feature the value for fund_feature.feature
     */
    public void setFeature(String feature) {
        this.feature = feature;
    }

    /**
     * 获取 近一年
     * @return the value of fund_feature.year1
     */
    public BigDecimal getYear1() {
        return year1;
    }

    /**
     * 设置 近一年
     * @param year1 the value for fund_feature.year1
     */
    public void setYear1(BigDecimal year1) {
        this.year1 = year1;
    }

    /**
     * 获取 近两年
     * @return the value of fund_feature.year2
     */
    public BigDecimal getYear2() {
        return year2;
    }

    /**
     * 设置 近两年
     * @param year2 the value for fund_feature.year2
     */
    public void setYear2(BigDecimal year2) {
        this.year2 = year2;
    }

    /**
     * 获取 近三年
     * @return the value of fund_feature.year3
     */
    public BigDecimal getYear3() {
        return year3;
    }

    /**
     * 设置 近三年
     * @param year3 the value for fund_feature.year3
     */
    public void setYear3(BigDecimal year3) {
        this.year3 = year3;
    }
}