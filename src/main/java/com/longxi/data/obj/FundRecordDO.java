package com.longxi.data.obj;

import java.util.Date;

/**
* fund_record表对应的DO
* Date 2017-11-27 23:34:31
*/
@SuppressWarnings("serial")
public class FundRecordDO extends BaseDO {
    /**
     * fund_record.id
     * 主键
     */
    private Long id;

    /**
     * fund_record.gmt_create
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * fund_record.gmt_modified
     * 修改时间
     */
    private Date gmtModified;

    /**
     * fund_record.code
     * 编码
     */
    private String code;

    /**
     * fund_record.status
     * 执行状态(1=未运行,2=运行完成)
     */
    private Integer status;

    /**
     * fund_record.pass
     * 是否通过(1=不通过,2=通过)
     */
    private Integer pass;

    /**
     * 获取 主键
     * @return the value of fund_record.id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 主键
     * @param id the value for fund_record.id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 创建时间
     * @return the value of fund_record.gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置 创建时间
     * @param gmtCreate the value for fund_record.gmt_create
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取 修改时间
     * @return the value of fund_record.gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置 修改时间
     * @param gmtModified the value for fund_record.gmt_modified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 获取 编码
     * @return the value of fund_record.code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置 编码
     * @param code the value for fund_record.code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取 执行状态(1=未运行,2=运行完成)
     * @return the value of fund_record.status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置 执行状态(1=未运行,2=运行完成)
     * @param status the value for fund_record.status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取 是否通过(1=不通过,2=通过)
     * @return the value of fund_record.pass
     */
    public Integer getPass() {
        return pass;
    }

    /**
     * 设置 是否通过(1=不通过,2=通过)
     * @param pass the value for fund_record.pass
     */
    public void setPass(Integer pass) {
        this.pass = pass;
    }
}