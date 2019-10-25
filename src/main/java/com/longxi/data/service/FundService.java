package com.longxi.data.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author longxi.cwl
 * @date 2017/11/15
 */
public abstract class FundService implements IFundService {
    private static Logger logger = LoggerFactory.getLogger(FundService.class);

    /**
     *
     */
    protected static boolean isUpdateIncr = false;
    protected static boolean isUpdateForce = true;

    /**
     * @param time
     * @return
     * @throws ParseException
     */
    protected Date getDate(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isBlank(time) || !time.matches(".*\\d.*")) {
            return null;
        }
        time = time.replaceAll("\\D", "-").replaceAll("-$", "");
        return sdf.parse(time.trim());
    }

    /**
     * @param value
     * @return
     */
    protected BigDecimal getDouble(String value, int scale) {
        if (StringUtils.isBlank(value) || !value.matches(".*\\d.*")) {
            return null;
        }
        value = value.replaceAll(",", "").replaceAll("\\*", "");

        BigDecimal bigDecimal = new BigDecimal(value.trim());
        bigDecimal.setScale(scale, BigDecimal.ROUND_DOWN);
        return bigDecimal;
    }

    /**
     * @param value
     * @param unit
     * @param scale
     * @return
     */
    protected BigDecimal getDoubleUnit(String value, Integer unit, int scale) {
        if (StringUtils.isBlank(value) || !value.matches(".*\\d.*")) {
            return null;
        }

        value = value.trim().replaceAll("(.*)\\D[元|份].*", "$1").replaceAll(",", "").replaceAll("\\*", "");
        BigDecimal bigDecimal = new BigDecimal(value);
        BigDecimal unitDecimal = new BigDecimal(unit);
        bigDecimal = bigDecimal.multiply(unitDecimal);
        bigDecimal.setScale(scale, BigDecimal.ROUND_DOWN);
        return bigDecimal;
    }

    /**
     * @param value
     * @param scale
     * @return
     */
    protected BigDecimal getDoubleUnit(String value, int scale) {
        int unit = getUnit(value);
        return getDoubleUnit(value, unit, scale);
    }

    /**
     * @param value
     * @return
     */
    protected int getUnit(String value) {
        int unit = 1;
        if (value.contains("亿")) {
            unit = 10000;
        } else if (value.contains("千万")) {
            unit = 1000;
        } else if (value.contains("百万")) {
            unit = 100;
        } else if (value.contains("十万")) {
            unit = 10;
        }
        return unit;
    }

    /**
     * @return
     */
    protected String getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    /**
     * @return
     */
    protected long getYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime().getTime();
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
        value = value.trim().replaceAll("(\\d*)%", "$1").replaceAll(",", "").replaceAll("\\*", "");

        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal.setScale(scale, BigDecimal.ROUND_DOWN);
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
