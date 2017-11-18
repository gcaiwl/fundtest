package com.longxi.data.obj;

import java.math.BigDecimal;
import java.util.Date;

/**
* fund_bond_position表对应的DO
* Date 2017-11-17 21:38:15
*/
@SuppressWarnings("serial")
public class FundBondPositionDO extends BaseDO {
    /**
     * fund_bond_position.id
     * 主键
     */
    private Long id;

    /**
     * fund_bond_position.gmt_create
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * fund_bond_position.gmt_modified
     * 修改时间
     */
    private Date gmtModified;

    /**
     * fund_bond_position.code
     * 编码
     */
    private String code;

    /**
     * fund_bond_position.bond_code
     * 债券编码
     */
    private String bondCode;

    /**
     * fund_bond_position.bond_name
     * 债券名称
     */
    private String bondName;

    /**
     * fund_bond_position.assets_rate
     * 占净值比例
     */
    private BigDecimal assetsRate;

    /**
     * fund_bond_position.market_value
     * 持仓市值
     */
    private BigDecimal marketValue;

    /**
     * fund_bond_position.quarter
     * 季度
     */
    private String quarter;

    /**
     * 获取 主键
     * @return the value of fund_bond_position.id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 主键
     * @param id the value for fund_bond_position.id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 创建时间
     * @return the value of fund_bond_position.gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置 创建时间
     * @param gmtCreate the value for fund_bond_position.gmt_create
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取 修改时间
     * @return the value of fund_bond_position.gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置 修改时间
     * @param gmtModified the value for fund_bond_position.gmt_modified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 获取 编码
     * @return the value of fund_bond_position.code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置 编码
     * @param code the value for fund_bond_position.code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取 债券编码
     * @return the value of fund_bond_position.bond_code
     */
    public String getBondCode() {
        return bondCode;
    }

    /**
     * 设置 债券编码
     * @param bondCode the value for fund_bond_position.bond_code
     */
    public void setBondCode(String bondCode) {
        this.bondCode = bondCode;
    }

    /**
     * 获取 债券名称
     * @return the value of fund_bond_position.bond_name
     */
    public String getBondName() {
        return bondName;
    }

    /**
     * 设置 债券名称
     * @param bondName the value for fund_bond_position.bond_name
     */
    public void setBondName(String bondName) {
        this.bondName = bondName;
    }

    /**
     * 获取 占净值比例
     * @return the value of fund_bond_position.assets_rate
     */
    public BigDecimal getAssetsRate() {
        return assetsRate;
    }

    /**
     * 设置 占净值比例
     * @param assetsRate the value for fund_bond_position.assets_rate
     */
    public void setAssetsRate(BigDecimal assetsRate) {
        this.assetsRate = assetsRate;
    }

    /**
     * 获取 持仓市值
     * @return the value of fund_bond_position.market_value
     */
    public BigDecimal getMarketValue() {
        return marketValue;
    }

    /**
     * 设置 持仓市值
     * @param marketValue the value for fund_bond_position.market_value
     */
    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }

    /**
     * 获取 季度
     * @return the value of fund_bond_position.quarter
     */
    public String getQuarter() {
        return quarter;
    }

    /**
     * 设置 季度
     * @param quarter the value for fund_bond_position.quarter
     */
    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }
}