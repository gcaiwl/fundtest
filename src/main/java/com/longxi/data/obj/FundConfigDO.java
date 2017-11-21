package com.longxi.data.obj;

import java.math.BigDecimal;
import java.util.Date;

/**
* fund_config表对应的DO
* Date 2017-11-17 21:38:15
*/
@SuppressWarnings("serial")
public class FundConfigDO extends BaseDO {
    /**
     * fund_config.id
     * 主键
     */
    private Long id;

    /**
     * fund_config.gmt_create
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * fund_config.gmt_modified
     * 修改时间
     */
    private Date gmtModified;

    /**
     * fund_config.code
     * 编码
     */
    private String code;

    /**
     * fund_config.shares_ratio
     * 股票占比
     */
    private BigDecimal sharesRatio;

    /**
     * fund_config.cash_ratio
     * 现金占比
     */
    private BigDecimal cashRatio;

    /**
     * fund_config.bond_ratio
     * 债券占比
     */
    private BigDecimal bondRatio;

    /**
     * fund_config.assets
     * 净资产
     */
    private BigDecimal assets;

    /**
     * fund_config.publish_time
     * 报告时间
     */
    private Date publishTime;

    /**
     * 获取 主键
     * @return the value of fund_config.id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 主键
     * @param id the value for fund_config.id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 创建时间
     * @return the value of fund_config.gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置 创建时间
     * @param gmtCreate the value for fund_config.gmt_create
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取 修改时间
     * @return the value of fund_config.gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置 修改时间
     * @param gmtModified the value for fund_config.gmt_modified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 获取 编码
     * @return the value of fund_config.code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置 编码
     * @param code the value for fund_config.code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取 股票占比
     * @return the value of fund_config.shares_ratio
     */
    public BigDecimal getSharesRatio() {
        return sharesRatio;
    }

    /**
     * 设置 股票占比
     * @param sharesRatio the value for fund_config.shares_ratio
     */
    public void setSharesRatio(BigDecimal sharesRatio) {
        this.sharesRatio = sharesRatio;
    }

    /**
     * 获取 现金占比
     * @return the value of fund_config.cash_ratio
     */
    public BigDecimal getCashRatio() {
        return cashRatio;
    }

    /**
     * 设置 现金占比
     * @param cashRatio the value for fund_config.cash_ratio
     */
    public void setCashRatio(BigDecimal cashRatio) {
        this.cashRatio = cashRatio;
    }

    /**
     * 获取 债券占比
     * @return the value of fund_config.bond_ratio
     */
    public BigDecimal getBondRatio() {
        return bondRatio;
    }

    /**
     * 设置 债券占比
     * @param bondRatio the value for fund_config.bond_ratio
     */
    public void setBondRatio(BigDecimal bondRatio) {
        this.bondRatio = bondRatio;
    }

    /**
     * 获取 净资产
     * @return the value of fund_config.assets
     */
    public BigDecimal getAssets() {
        return assets;
    }

    /**
     * 设置 净资产
     * @param assets the value for fund_config.assets
     */
    public void setAssets(BigDecimal assets) {
        this.assets = assets;
    }

    /**
     * 获取 报告时间
     * @return the value of fund_config.publish_time
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * 设置 报告时间
     * @param publishTime the value for fund_config.publish_time
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
}