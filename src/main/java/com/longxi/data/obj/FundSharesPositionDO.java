package com.longxi.data.obj;

import java.math.BigDecimal;
import java.util.Date;

/**
* fund_shares_position表对应的DO
* Date 2017-11-17 21:38:15
*/
@SuppressWarnings("serial")
public class FundSharesPositionDO extends BaseDO {
    /**
     * fund_shares_position.id
     * 主键
     */
    private Long id;

    /**
     * fund_shares_position.gmt_create
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * fund_shares_position.gmt_modified
     * 修改时间
     */
    private Date gmtModified;

    /**
     * fund_shares_position.code
     * 编码
     */
    private String code;

    /**
     * fund_shares_position.shares_code
     * 股票编码
     */
    private String sharesCode;

    /**
     * fund_shares_position.shares_name
     * 股票名称
     */
    private String sharesName;

    /**
     * fund_shares_position.shares_num
     * 持股数
     */
    private BigDecimal sharesNum;

    /**
     * fund_shares_position.assets_rate
     * 占净值比例
     */
    private BigDecimal assetsRate;

    /**
     * fund_shares_position.market_value
     * 持仓市值
     */
    private BigDecimal marketValue;

    /**
     * fund_shares_position.quarter
     * 季度
     */
    private String quarter;

    /**
     * 获取 主键
     * @return the value of fund_shares_position.id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 主键
     * @param id the value for fund_shares_position.id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 创建时间
     * @return the value of fund_shares_position.gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置 创建时间
     * @param gmtCreate the value for fund_shares_position.gmt_create
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取 修改时间
     * @return the value of fund_shares_position.gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置 修改时间
     * @param gmtModified the value for fund_shares_position.gmt_modified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 获取 编码
     * @return the value of fund_shares_position.code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置 编码
     * @param code the value for fund_shares_position.code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取 股票编码
     * @return the value of fund_shares_position.shares_code
     */
    public String getSharesCode() {
        return sharesCode;
    }

    /**
     * 设置 股票编码
     * @param sharesCode the value for fund_shares_position.shares_code
     */
    public void setSharesCode(String sharesCode) {
        this.sharesCode = sharesCode;
    }

    /**
     * 获取 股票名称
     * @return the value of fund_shares_position.shares_name
     */
    public String getSharesName() {
        return sharesName;
    }

    /**
     * 设置 股票名称
     * @param sharesName the value for fund_shares_position.shares_name
     */
    public void setSharesName(String sharesName) {
        this.sharesName = sharesName;
    }

    /**
     * 获取 持股数
     * @return the value of fund_shares_position.shares_num
     */
    public BigDecimal getSharesNum() {
        return sharesNum;
    }

    /**
     * 设置 持股数
     * @param sharesNum the value for fund_shares_position.shares_num
     */
    public void setSharesNum(BigDecimal sharesNum) {
        this.sharesNum = sharesNum;
    }

    /**
     * 获取 占净值比例
     * @return the value of fund_shares_position.assets_rate
     */
    public BigDecimal getAssetsRate() {
        return assetsRate;
    }

    /**
     * 设置 占净值比例
     * @param assetsRate the value for fund_shares_position.assets_rate
     */
    public void setAssetsRate(BigDecimal assetsRate) {
        this.assetsRate = assetsRate;
    }

    /**
     * 获取 持仓市值
     * @return the value of fund_shares_position.market_value
     */
    public BigDecimal getMarketValue() {
        return marketValue;
    }

    /**
     * 设置 持仓市值
     * @param marketValue the value for fund_shares_position.market_value
     */
    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }

    /**
     * 获取 季度
     * @return the value of fund_shares_position.quarter
     */
    public String getQuarter() {
        return quarter;
    }

    /**
     * 设置 季度
     * @param quarter the value for fund_shares_position.quarter
     */
    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }
}