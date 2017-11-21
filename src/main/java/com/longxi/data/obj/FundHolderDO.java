package com.longxi.data.obj;

import java.math.BigDecimal;
import java.util.Date;

/**
* fund_holder表对应的DO
* Date 2017-11-17 21:38:15
*/
@SuppressWarnings("serial")
public class FundHolderDO extends BaseDO {
    /**
     * fund_holder.id
     * 主键
     */
    private Long id;

    /**
     * fund_holder.gmt_create
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * fund_holder.gmt_modified
     * 修改时间
     */
    private Date gmtModified;

    /**
     * fund_holder.code
     * 编码
     */
    private String code;

    /**
     * fund_holder.mechanism_ratio
     * 机构持有占比
     */
    private BigDecimal mechanismRatio;

    /**
     * fund_holder.personal_ratio
     * 个人持有占比
     */
    private BigDecimal personalRatio;

    /**
     * fund_holder.inside_ratio
     * 内部持有占比
     */
    private BigDecimal insideRatio;

    /**
     * fund_holder.share
     * 总份额
     */
    private BigDecimal share;

    /**
     * fund_holder.publish_time
     * 报告时间
     */
    private Date publishTime;

    /**
     * 获取 主键
     * @return the value of fund_holder.id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 主键
     * @param id the value for fund_holder.id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 创建时间
     * @return the value of fund_holder.gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置 创建时间
     * @param gmtCreate the value for fund_holder.gmt_create
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取 修改时间
     * @return the value of fund_holder.gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置 修改时间
     * @param gmtModified the value for fund_holder.gmt_modified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 获取 编码
     * @return the value of fund_holder.code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置 编码
     * @param code the value for fund_holder.code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取 机构持有占比
     * @return the value of fund_holder.mechanism_ratio
     */
    public BigDecimal getMechanismRatio() {
        return mechanismRatio;
    }

    /**
     * 设置 机构持有占比
     * @param mechanismRatio the value for fund_holder.mechanism_ratio
     */
    public void setMechanismRatio(BigDecimal mechanismRatio) {
        this.mechanismRatio = mechanismRatio;
    }

    /**
     * 获取 个人持有占比
     * @return the value of fund_holder.personal_ratio
     */
    public BigDecimal getPersonalRatio() {
        return personalRatio;
    }

    /**
     * 设置 个人持有占比
     * @param personalRatio the value for fund_holder.personal_ratio
     */
    public void setPersonalRatio(BigDecimal personalRatio) {
        this.personalRatio = personalRatio;
    }

    /**
     * 获取 内部持有占比
     * @return the value of fund_holder.inside_ratio
     */
    public BigDecimal getInsideRatio() {
        return insideRatio;
    }

    /**
     * 设置 内部持有占比
     * @param insideRatio the value for fund_holder.inside_ratio
     */
    public void setInsideRatio(BigDecimal insideRatio) {
        this.insideRatio = insideRatio;
    }

    /**
     * 获取 总份额
     * @return the value of fund_holder.share
     */
    public BigDecimal getShare() {
        return share;
    }

    /**
     * 设置 总份额
     * @param share the value for fund_holder.share
     */
    public void setShare(BigDecimal share) {
        this.share = share;
    }

    /**
     * 获取 报告时间
     * @return the value of fund_holder.publish_time
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * 设置 报告时间
     * @param publishTime the value for fund_holder.publish_time
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
}