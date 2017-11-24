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

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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
        time = time.replaceAll("\\D", "-").replaceAll("-$", "");

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
        if (StringUtils.isBlank(value) || !value.matches(".*\\d.*")) {
            return null;
        }
        value = value.replaceAll(",", "");

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
        if (StringUtils.isBlank(value) || !value.matches(".*\\d.*")) {
            return null;
        }
        value = value.trim().replaceAll("(.*)\\D[元|份].*", "$1").replaceAll(",", "");

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
        if (StringUtils.isBlank(value) || !value.matches(".*\\d.*")) {
            return null;
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
