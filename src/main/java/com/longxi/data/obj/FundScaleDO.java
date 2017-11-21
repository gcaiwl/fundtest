package com.longxi.data.obj;

import java.math.BigDecimal;
import java.util.Date;

/**
* fund_scale表对应的DO
* Date 2017-11-17 21:38:15
*/
@SuppressWarnings("serial")
public class FundScaleDO extends BaseDO {
    /**
     * fund_scale.id
     * 主键
     */
    private Long id;

    /**
     * fund_scale.gmt_create
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * fund_scale.gmt_modified
     * 修改时间
     */
    private Date gmtModified;

    /**
     * fund_scale.code
     * 编码
     */
    private String code;

    /**
     * fund_scale.purchase
     * 期间申购份额
     */
    private BigDecimal purchase;

    /**
     * fund_scale.redeem
     * 期间赎回份额
     */
    private BigDecimal redeem;

    /**
     * fund_scale.share
     * 期末总份额
     */
    private BigDecimal share;

    /**
     * fund_scale.assets
     * 期末净资产
     */
    private BigDecimal assets;

    /**
     * fund_scale.assets_rate
     * 净资产变动率
     */
    private BigDecimal assetsRate;

    /**
     * fund_scale.publish_time
     * 报告时间
     */
    private Date publishTime;

    /**
     * 获取 主键
     * @return the value of fund_scale.id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 主键
     * @param id the value for fund_scale.id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 创建时间
     * @return the value of fund_scale.gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置 创建时间
     * @param gmtCreate the value for fund_scale.gmt_create
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取 修改时间
     * @return the value of fund_scale.gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置 修改时间
     * @param gmtModified the value for fund_scale.gmt_modified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 获取 编码
     * @return the value of fund_scale.code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置 编码
     * @param code the value for fund_scale.code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取 期间申购份额
     * @return the value of fund_scale.purchase
     */
    public BigDecimal getPurchase() {
        return purchase;
    }

    /**
     * 设置 期间申购份额
     * @param purchase the value for fund_scale.purchase
     */
    public void setPurchase(BigDecimal purchase) {
        this.purchase = purchase;
    }

    /**
     * 获取 期间赎回份额
     * @return the value of fund_scale.redeem
     */
    public BigDecimal getRedeem() {
        return redeem;
    }

    /**
     * 设置 期间赎回份额
     * @param redeem the value for fund_scale.redeem
     */
    public void setRedeem(BigDecimal redeem) {
        this.redeem = redeem;
    }

    /**
     * 获取 期末总份额
     * @return the value of fund_scale.share
     */
    public BigDecimal getShare() {
        return share;
    }

    /**
     * 设置 期末总份额
     * @param share the value for fund_scale.share
     */
    public void setShare(BigDecimal share) {
        this.share = share;
    }

    /**
     * 获取 期末净资产
     * @return the value of fund_scale.assets
     */
    public BigDecimal getAssets() {
        return assets;
    }

    /**
     * 设置 期末净资产
     * @param assets the value for fund_scale.assets
     */
    public void setAssets(BigDecimal assets) {
        this.assets = assets;
    }

    /**
     * 获取 净资产变动率
     * @return the value of fund_scale.assets_rate
     */
    public BigDecimal getAssetsRate() {
        return assetsRate;
    }

    /**
     * 设置 净资产变动率
     * @param assetsRate the value for fund_scale.assets_rate
     */
    public void setAssetsRate(BigDecimal assetsRate) {
        this.assetsRate = assetsRate;
    }

    /**
     * 获取 报告时间
     * @return the value of fund_scale.publish_time
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * 设置 报告时间
     * @param publishTime the value for fund_scale.publish_time
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
}