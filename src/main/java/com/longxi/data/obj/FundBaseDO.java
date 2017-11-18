package com.longxi.data.obj;

import java.math.BigDecimal;
import java.util.Date;

/**
 * fund_base表对应的DO
 * Date 2017-11-17 21:38:15
 */
@SuppressWarnings("serial")
public class FundBaseDO extends BaseDO {
    /**
     * fund_base.id
     * 主键
     */
    private Long id;

    /**
     * fund_base.gmt_create
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * fund_base.gmt_modified
     * 修改时间
     */
    private Date gmtModified;

    /**
     * fund_base.code
     * 代码
     */
    private String code;

    /**
     * fund_base.name
     * 名称
     */
    private String name;

    /**
     * fund_base.company
     * 公司
     */
    private String company;

    /**
     * fund_base.type
     * 类型
     */
    private String type;

    /**
     * fund_base.scale
     * 规模
     */
    private BigDecimal scale;

    /**
     * fund_base.share
     * 份额
     */
    private BigDecimal share;

    /**
     * fund_base.issue_time
     * 发行时间
     */
    private Date issueTime;

    /**
     * fund_base.status
     * 交易状态
     */
    private Integer status;

    /**
     * fund_base.fee
     * 手续费
     */
    private BigDecimal fee;

    /**
     * fund_base.quota
     * 限额
     */
    private Integer quota;

    /**
     * fund_base.establish_time
     * 成立时间
     */
    private Date establishTime;

    /**
     * 获取 主键
     *
     * @return the value of fund_base.id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 主键
     *
     * @param id the value for fund_base.id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 创建时间
     *
     * @return the value of fund_base.gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置 创建时间
     *
     * @param gmtCreate the value for fund_base.gmt_create
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取 修改时间
     *
     * @return the value of fund_base.gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置 修改时间
     *
     * @param gmtModified the value for fund_base.gmt_modified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 获取 代码
     *
     * @return the value of fund_base.code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置 代码
     *
     * @param code the value for fund_base.code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取 名称
     *
     * @return the value of fund_base.name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 名称
     *
     * @param name the value for fund_base.name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 公司
     *
     * @return the value of fund_base.company
     */
    public String getCompany() {
        return company;
    }

    /**
     * 设置 公司
     *
     * @param company the value for fund_base.company
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 获取 类型
     *
     * @return the value of fund_base.type
     */
    public String getType() {
        return type;
    }

    /**
     * 设置 类型
     *
     * @param type the value for fund_base.type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取 规模
     *
     * @return the value of fund_base.scale
     */
    public BigDecimal getScale() {
        return scale;
    }

    /**
     * 设置 规模
     *
     * @param scale the value for fund_base.scale
     */
    public void setScale(BigDecimal scale) {
        this.scale = scale;
    }

    /**
     * 获取 份额
     *
     * @return the value of fund_base.share
     */
    public BigDecimal getShare() {
        return share;
    }

    /**
     * 设置 份额
     *
     * @param share the value for fund_base.share
     */
    public void setShare(BigDecimal share) {
        this.share = share;
    }

    /**
     * 获取 发行时间
     *
     * @return the value of fund_base.issue_time
     */
    public Date getIssueTime() {
        return issueTime;
    }

    /**
     * 设置 发行时间
     *
     * @param issueTime the value for fund_base.issue_time
     */
    public void setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
    }

    /**
     * 获取 交易状态
     *
     * @return the value of fund_base.status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置 交易状态
     *
     * @param status the value for fund_base.status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取 手续费
     *
     * @return the value of fund_base.fee
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * 设置 手续费
     *
     * @param fee the value for fund_base.fee
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    /**
     * 获取 限额
     *
     * @return the value of fund_base.quota
     */
    public Integer getQuota() {
        return quota;
    }

    /**
     * 设置 限额
     *
     * @param quota the value for fund_base.quota
     */
    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    /**
     * 获取 成立时间
     *
     * @return the value of fund_base.establish_time
     */
    public Date getEstablishTime() {
        return establishTime;
    }

    /**
     * 设置 成立时间
     *
     * @param establishTime the value for fund_base.establish_time
     */
    public void setEstablishTime(Date establishTime) {
        this.establishTime = establishTime;
    }
}