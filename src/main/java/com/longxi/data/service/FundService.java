package com.longxi.data.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author longxi.cwl
 * @date 2017/11/15
 */
public class FundService {
    private static Logger logger = LoggerFactory.getLogger(FundService.class);
    /**
     *
     */
    private final String FUND_LIST_URL = "http://service.eastmoney.com/js/fundcode_search.js";
    /**
     *
     */
    private final String FUND_VALUE_URL = "http://fundgz.1234567.com.cn/js/%s.js";
    /**
     *
     */
    private final String FUND_DETAIL_URL = "http://service.eastmoney.com/pingzhongdata/%s.js";
    /**
     *
     */
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

    /**
     * @return
     */
    protected String getFundListUrl() {
        return FUND_LIST_URL;
    }

    /**
     * @param code
     * @return
     */
    protected String getFundDetailUrl(String code) {
        return String.format(FUND_DETAIL_URL, code);
    }

    /**
     * @param code
     * @return
     */
    protected String getFundValueUrl(String code) {
        return String.format(FUND_VALUE_URL, code);
    }

    /**
     * @param time
     * @return
     */
    protected Date getDate(String time) {
        Date date = null;
        if (StringUtils.isBlank(time)) {
            logger.error("time is null or empty");
            return date;
        }

        try {
            date = sdf.parse(time.trim());
        } catch (ParseException e) {
            logger.error(time + " getDate exception ", e);
        }
        return date;
    }

    /**
     * @param value
     * @return
     */
    protected BigDecimal getDouble(String value, int scale) {
        if (StringUtils.isBlank(value)) {
            return null;
        }

        BigDecimal bigDecimal = new BigDecimal(value.trim());
        bigDecimal.setScale(scale);
        return bigDecimal;
    }

    /**
     * @param value
     * @param scale
     * @return
     */
    protected BigDecimal getDoubleUnit(String value, int scale) {
        if (StringUtils.isBlank(value)) {
            value = "0";
        }
        value = value.trim().replaceAll("(.*)\\D[元|份|%].*", "$1");

        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal.setScale(scale);
        return bigDecimal;
    }

    /**
     * @param value
     * @param scale
     * @return
     */
    protected BigDecimal getDoublePercent(String value, int scale) {
        if (StringUtils.isBlank(value)) {
            value = "0";
        }
        value = value.trim().replaceAll("(\\d*)%", "$1");

        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal.setScale(scale);
        return bigDecimal;
    }

    /**
     * @param value
     * @return
     */
    protected String getString(String value) {
        if (StringUtils.isBlank(value)) {
            return value;
        }
        return value.trim();
    }
}
