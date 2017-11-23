package com.longxi.data.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.longxi.data.dao.FundTurnoverDAO;
import com.longxi.data.obj.FundTurnoverDO;
import com.longxi.data.obj.Result;
import com.longxi.data.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author longxi.cwl
 * @date 2017/11/15
 */
public class FundTurnoverService extends FundService {
    private static Logger logger = LoggerFactory.getLogger(FundTurnoverService.class);

    private static String FUND_TURNOVER_URL = "http://fund.eastmoney.com/f10/F10DataApi.aspx?type=ccbdzs&code=%s&page=1&per=200&mode=2";

    @Resource
    private FundTurnoverDAO fundTurnoverDAO;

    /**
     * @param code
     */
    public void insertOrUpdate(String code) {
        List<FundTurnoverDO> fundTurnoverDOList = getFundTurnover(code);
        if (null != fundTurnoverDOList) {
            for (int i = 0; i < fundTurnoverDOList.size(); i++) {
                insertOrUpdate(fundTurnoverDOList.get(i));
            }
        }
    }

    /**
     * @param instance
     */
    public void insertOrUpdate(FundTurnoverDO instance) {
        if (null == instance) {
            logger.error("fundTurnoverDO is null");
            return;
        }

        FundTurnoverDO fundTurnoverDO = fundTurnoverDAO.queryFundTurnoverByPublishTime(instance.getCode(), instance.getPublishTime());
        if (null != fundTurnoverDO) {
            instance.setId(fundTurnoverDO.getId());
            fundTurnoverDAO.updateFundTurnover(instance);
        } else {
            fundTurnoverDAO.insertFundTurnover(instance);
        }
    }

    /**
     * @param code
     * @return
     */
    public List<FundTurnoverDO> getFundTurnover(String code) {
        if (StringUtils.isBlank(code)) {
            logger.error("code is wrong");
            return null;
        }

        String url = getFundTurnoverUrl(code);
        logger.info(code + " fundTurnover url is " + url);

        Result<String> response = HttpUtils.get(url);
        if (!response.isSuccess() || StringUtils.isBlank(response.getValue())) {
            logger.error(url + " status is " + response.getErrCode() + " resposne is " + response.getValue());
            return null;
        }

        List<FundTurnoverDO> fundTurnoverDOList = new ArrayList<>();
        try {
            String result = response.getValue().replaceAll("var apidata_ccbdzs=", "").replaceAll(";", "")
                .replaceAll("Data:", "\"Data\":")
                .replaceAll("TotalCount:", "\"TotalCount\":")
                .replaceAll("PageIndex:", "\"PageIndex\":")
                .replaceAll("PageSize:", "\"PageSize\":")
                .replaceAll("PageCount:", "\"PageCount\":");
            JSONObject resultJson = JSONObject.parseObject(result);
            if (null != resultJson) {
                JSONArray dataArray = resultJson.getJSONArray("Data");
                if (null != dataArray) {
                    for (int i = 0; i < dataArray.size(); i++) {
                        FundTurnoverDO fundTurnoverDO = new FundTurnoverDO();
                        fundTurnoverDO.setCode(code);
                        fundTurnoverDO.setPublishTime(getDate(dataArray.getJSONObject(i).getString("REPORTDATE")));
                        fundTurnoverDO.setTurnRate(getDoublePercent(dataArray.getJSONObject(i).getString("STOCKTURNOVER"), 2));
                        fundTurnoverDOList.add(fundTurnoverDO);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(code + " getFundTurnover exception ", e);
        }
        return fundTurnoverDOList;
    }

    /**
     * @param code
     * @return
     */
    private String getFundTurnoverUrl(String code) {
        return String.format(FUND_TURNOVER_URL, code);
    }

    public FundTurnoverDAO getFundTurnoverDAO() {
        return fundTurnoverDAO;
    }

    public void setFundTurnoverDAO(FundTurnoverDAO fundTurnoverDAO) {
        this.fundTurnoverDAO = fundTurnoverDAO;
    }
}
