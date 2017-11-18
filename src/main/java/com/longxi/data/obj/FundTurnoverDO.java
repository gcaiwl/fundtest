package com.longxi.data.obj;

import java.math.BigDecimal;
import java.util.Date;

/**
* fund_turnover表对应的DO
* Date 2017-11-17 21:38:15
*/
@SuppressWarnings("serial")
public class FundTurnoverDO extends BaseDO {
    /**
     * fund_turnover.id
     * 主键
     */
    private Long id;

    /**
     * fund_turnover.gmt_create
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * fund_turnover.gmt_modified
     * 修改时间
     */
    private Date gmtModified;

    /**
     * fund_turnover.code
     * 编码
     */
    private String code;

    /**
     * fund_turnover.turn_rate
     * 换手率
     */
    private BigDecimal turnRate;

    /**
     * 获取 主键
     * @return the value of fund_turnover.id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 主键
     * @param id the value for fund_turnover.id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 创建时间
     * @return the value of fund_turnover.gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置 创建时间
     * @param gmtCreate the value for fund_turnover.gmt_create
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取 修改时间
     * @return the value of fund_turnover.gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置 修改时间
     * @param gmtModified the value for fund_turnover.gmt_modified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 获取 编码
     * @return the value of fund_turnover.code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置 编码
     * @param code the value for fund_turnover.code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取 换手率
     * @return the value of fund_turnover.turn_rate
     */
    public BigDecimal getTurnRate() {
        return turnRate;
    }

    /**
     * 设置 换手率
     * @param turnRate the value for fund_turnover.turn_rate
     */
    public void setTurnRate(BigDecimal turnRate) {
        this.turnRate = turnRate;
    }
}