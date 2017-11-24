package com.longxi.data.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONArray;

import com.longxi.data.dao.FundValueDAO;
import com.longxi.data.obj.FundValueDO;
import com.longxi.data.obj.Result;
import com.longxi.data.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author longxi.cwl
 * @date 2017/11/14
 */
public class FundValueService extends FundService {
    private static Logger logger = LoggerFactory.getLogger(FundValueService.class);

    private final String FUND_VALUE_URL = "http://fund.eastmoney.com/pingzhongdata/%s.js";

    @Resource
    private FundValueDAO fundValueDAO;

    /**
     *
     * @param code
     */
    public void insertOrUpdate(String code) {
        List<FundValueDO> fundValueDOList = getFundValue(code);
        if (null != fundValueDOList) {
            for (int i = 0; i < fundValueDOList.size(); i++) {
                insertOrUpdate(fundValueDOList.get(i));
            }
        }
    }

    /**
     *
     * @param instance
     */
    public void insertOrUpdate(FundValueDO instance) {
        if (null == instance) {
            logger.error("fundValueDO is null");
            return;
        }

        FundValueDO fundValueDO = fundValueDAO.queryFundValueByPublishTime(instance.getCode(), instance.getPublishTime());
        if (null != fundValueDO) {
            instance.setId(fundValueDO.getId());
            fundValueDAO.updateFundValue(instance);
        } else {
            fundValueDAO.insertFundValue(instance);
        }
    }

    /**
     * @param code
     */
    public List<FundValueDO> getFundValue(String code) {
        if (StringUtils.isBlank(code)) {
            logger.error("code is wrong");
            return null;
        }

        String url = getFundValueUrl(code);
        logger.info(code + " fundValue url is " + url);

        Result<String> response = HttpUtils.get(url);
        if (!response.isSuccess() || StringUtils.isBlank(response.getValue())) {
            logger.error(url + " status is " + response.getErrCode() + " resposne is " + response.getValue());
            return null;
        }

        List<FundValueDO> fundValueDOList = new ArrayList<>();
        try {
            Map<Long, String> valueMap = new HashMap<>();
            Map<Long, String> totalMap = new HashMap<>();
            Map<Long, String> incrMap = new HashMap<>();
            String[] tmp = response.getValue().split("\r\n");
            for (String t : tmp) {
                if (t.contains("Data_netWorthTrend")) {
                    t = t.replaceFirst("^.*\\[", "\\[").replaceAll(";", "");
                    JSONArray jsonArray = JSONArray.parseArray(t);
                    for (int i = 0; i < jsonArray.size(); i++) {
                        Long k = jsonArray.getJSONObject(i).getLong("x");
                        valueMap.put(k, jsonArray.getJSONObject(i).getString("y"));
                        incrMap.put(k, jsonArray.getJSONObject(i).getString("equityReturn"));
                    }
                } else if (t.contains("Data_ACWorthTrend")) {
                    t = t.replaceFirst("^.*?\\[", "\\[").replaceAll(";", "");
                    JSONArray jsonArray = JSONArray.parseArray(t);
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONArray totalArray = jsonArray.getJSONArray(i);
                        totalMap.put(totalArray.getLong(0), totalArray.getString(1));
                    }
                }
            }

            for (Long k : valueMap.keySet()) {
                FundValueDO fundValueDO = new FundValueDO();
                fundValueDO.setCode(code);
                fundValueDO.setValue(getDouble(valueMap.get(k), 3));
                fundValueDO.setTotalValue(getDouble(totalMap.get(k), 3));
                fundValueDO.setIncrease(getDouble(incrMap.get(k), 4));
                fundValueDO.setPublishTime(new Date(k));
                fundValueDOList.add(fundValueDO);
            }
        } catch (Exception e) {
            logger.error(code + " getFundValue exception ", e);
        }
        return fundValueDOList;
    }

    /**
     * @param code
     * @return
     */
    private String getFundValueUrl(String code) {
        return String.format(FUND_VALUE_URL, code);
    }

    public FundValueDAO getFundValueDAO() {
        return fundValueDAO;
    }

    public void setFundValueDAO(FundValueDAO fundValueDAO) {
        this.fundValueDAO = fundValueDAO;
    }
}
