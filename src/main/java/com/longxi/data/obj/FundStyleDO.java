package com.longxi.data.obj;

import java.util.Date;

/**
* fund_style表对应的DO
* Date 2017-12-14 23:06:28
*/
@SuppressWarnings("serial")
public class FundStyleDO extends BaseDO {
    /**
     * fund_style.id
     * 主键
     */
    private Long id;

    /**
     * fund_style.gmt_create
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * fund_style.gmt_modified
     * 修改时间
     */
    private Date gmtModified;

    /**
     * fund_style.code
     * 代码
     */
    private String code;

    /**
     * fund_style.style
     * 风格
     */
    private String style;

    /**
     * fund_style.quarter
     * 季度
     */
    private String quarter;

    /**
     * 获取 主键
     * @return the value of fund_style.id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 主键
     * @param id the value for fund_style.id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 创建时间
     * @return the value of fund_style.gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置 创建时间
     * @param gmtCreate the value for fund_style.gmt_create
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取 修改时间
     * @return the value of fund_style.gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置 修改时间
     * @param gmtModified the value for fund_style.gmt_modified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 获取 代码
     * @return the value of fund_style.code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置 代码
     * @param code the value for fund_style.code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取 风格
     * @return the value of fund_style.style
     */
    public String getStyle() {
        return style;
    }

    /**
     * 设置 风格
     * @param style the value for fund_style.style
     */
    public void setStyle(String style) {
        this.style = style;
    }

    /**
     * 获取 季度
     * @return the value of fund_style.quarter
     */
    public String getQuarter() {
        return quarter;
    }

    /**
     * 设置 季度
     * @param quarter the value for fund_style.quarter
     */
    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }
}