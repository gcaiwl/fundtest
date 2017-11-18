package com.longxi.data.obj;

import java.math.BigDecimal;
import java.util.Date;

/**
* fund_industry表对应的DO
* Date 2017-11-17 21:38:15
*/
@SuppressWarnings("serial")
public class FundIndustryDO extends BaseDO {
    /**
     * fund_industry.id
     * 主键
     */
    private Long id;

    /**
     * fund_industry.gmt_create
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * fund_industry.gmt_modified
     * 修改时间
     */
    private Date gmtModified;

    /**
     * fund_industry.code
     * 编码
     */
    private String code;

    /**
     * fund_industry.industry
     * 行业
     */
    private String industry;

    /**
     * fund_industry.market_ratio
     * 占净值比例
     */
    private BigDecimal marketRatio;

    /**
     * fund_industry.market_value
     * 市值
     */
    private BigDecimal marketValue;

    /**
     * fund_industry.quarter
     * 季度
     */
    private String quarter;

    /**
     * 获取 主键
     * @return the value of fund_industry.id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 主键
     * @param id the value for fund_industry.id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 创建时间
     * @return the value of fund_industry.gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置 创建时间
     * @param gmtCreate the value for fund_industry.gmt_create
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取 修改时间
     * @return the value of fund_industry.gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置 修改时间
     * @param gmtModified the value for fund_industry.gmt_modified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 获取 编码
     * @return the value of fund_industry.code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置 编码
     * @param code the value for fund_industry.code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取 行业
     * @return the value of fund_industry.industry
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * 设置 行业
     * @param industry the value for fund_industry.industry
     */
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    /**
     * 获取 占净值比例
     * @return the value of fund_industry.market_ratio
     */
    public BigDecimal getMarketRatio() {
        return marketRatio;
    }

    /**
     * 设置 占净值比例
     * @param marketRatio the value for fund_industry.market_ratio
     */
    public void setMarketRatio(BigDecimal marketRatio) {
        this.marketRatio = marketRatio;
    }

    /**
     * 获取 市值
     * @return the value of fund_industry.market_value
     */
    public BigDecimal getMarketValue() {
        return marketValue;
    }

    /**
     * 设置 市值
     * @param marketValue the value for fund_industry.market_value
     */
    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }

    /**
     * 获取 季度
     * @return the value of fund_industry.quarter
     */
    public String getQuarter() {
        return quarter;
    }

    /**
     * 设置 季度
     * @param quarter the value for fund_industry.quarter
     */
    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }
}