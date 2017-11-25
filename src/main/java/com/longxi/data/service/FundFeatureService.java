package com.longxi.data.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;

import com.longxi.data.dao.FundFeatureDAO;
import com.longxi.data.obj.FundFeatureDO;
import com.longxi.data.obj.Result;
import com.longxi.data.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author longxi.cwl
 * @date 2017/11/15
 */
public class FundFeatureService extends FundService {
    private static Logger logger = LoggerFactory.getLogger(FundFeatureService.class);

    private static String FUND_FEATURE_URL = "http://fund.eastmoney.com/f10/tsdata_%s.html";

    @Resource
    private FundFeatureDAO fundFeatureDAO;

    /**
     * @param code
     * @return
     */
    public boolean insertOrUpdate(String code) {
        boolean result = true;
        List<FundFeatureDO> fundFeatureDOList = getFundFeature(code);
        if (null != fundFeatureDOList) {
            for (FundFeatureDO fundFeatureDO : fundFeatureDOList) {
                result = result && insertOrUpdate(fundFeatureDO);
            }
        }
        return result;
    }

    /**
     * @param instance
     * @return
     */
    public boolean insertOrUpdate(FundFeatureDO instance) {
        boolean result = false;
        if (null == instance) {
            logger.error("fundFeatureDO is null");
            return result;
        }

        FundFeatureDO fundFeatureDO = fundFeatureDAO.queryFundFeatureByFeature(instance.getCode(), instance.getFeature());
        if (null != fundFeatureDO) {
            instance.setId(fundFeatureDO.getId());
            int num = fundFeatureDAO.updateFundFeature(instance);
            if (num > -1) {
                result = true;
            }
        } else {
            Long id = fundFeatureDAO.insertFundFeature(instance);
            if (null != id && id.longValue() > 0) {
                result = true;
            }
        }

        if (!result) {
            logger.error("insertOrUpdate failed " + JSONObject.toJSONString(instance));
        }
        return result;
    }

    /**
     * @param code
     * @return
     */
    public List<FundFeatureDO> getFundFeature(String code) {
        if (StringUtils.isBlank(code)) {
            logger.error("fundCode is wrong");
            return null;
        }

        String url = getFundFeatureUrl(code);
        logger.info(code + " fundFeature url is " + url);

        Result<String> response = HttpUtils.get(url);
        if (!response.isSuccess() || StringUtils.isBlank(response.getValue())) {
            logger.error(url + " status is " + response.getErrCode() + " resposne is " + response.getValue());
            return null;
        }

        List<FundFeatureDO> fundFeatureDOList = new ArrayList<>();
        try {
            Document doc = Jsoup.parse(response.getValue());
            if (null != doc) {
                Elements tr = doc.select("table[class='fxtb'] tbody tr");
                for (int i = 1; i < tr.size(); i++) {
                    Elements td = tr.get(i).select("td");
                    FundFeatureDO fundFeatureDO = new FundFeatureDO();
                    fundFeatureDO.setCode(code);
                    fundFeatureDO.setFeature(getString(td.get(0).text()));
                    fundFeatureDO.setYear1(getDouble(td.get(1).text()));
                    fundFeatureDO.setYear2(getDouble(td.get(2).text()));
                    fundFeatureDO.setYear3(getDouble(td.get(3).text()));
                    fundFeatureDOList.add(fundFeatureDO);
                }
            }
        } catch (Exception e) {
            logger.error(code + " getFundFeature exception ", e);
        }
        return fundFeatureDOList;
    }

    /**
     * @param code
     * @return
     */
    private String getFundFeatureUrl(String code) {
        return String.format(FUND_FEATURE_URL, code);
    }

    /**
     * @param value
     * @return
     */
    private BigDecimal getDouble(String value) {
        return value.contains("%") ? getDoublePercent(value, 2) : getDouble(value, 2);
    }

    public FundFeatureDAO getFundFeatureDAO() {
        return fundFeatureDAO;
    }

    public void setFundFeatureDAO(FundFeatureDAO fundFeatureDAO) {
        this.fundFeatureDAO = fundFeatureDAO;
    }
}
