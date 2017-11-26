package com.longxi.data.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;

import com.longxi.data.dao.FundIndustryDAO;
import com.longxi.data.obj.FundIndustryDO;
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
public class FundIndustryService extends FundService {
    private static Logger logger = LoggerFactory.getLogger(FundIndustryService.class);

    private static String FUND_INDUSTRY_URL = "http://fund.eastmoney.com/f10/F10DataApi.aspx?type=hypz&code=%s";

    @Resource
    private FundIndustryDAO fundIndustryDAO;

    /**
     * @param code
     * @return
     */
    @Override
    public boolean insertOrUpdate(String code) {
        boolean result = true;
        List<FundIndustryDO> fundIndustryDOList = getFundIndustry(code);
        if (null != fundIndustryDOList) {
            for (int i = 0; i < fundIndustryDOList.size(); i++) {
                result = result && insertOrUpdate(fundIndustryDOList.get(i));
            }
        }
        return result;
    }

    /**
     * @param instance
     * @return
     */
    public boolean insertOrUpdate(FundIndustryDO instance) {
        boolean result = false;
        if (null == instance) {
            logger.error("fundIndustryDO is null");
            return result;
        }

        FundIndustryDO fundIndustryDO = fundIndustryDAO.queryFundIndustryByQuarter(instance.getCode(), instance.getQuarter(), instance.getIndustry());
        if (null != fundIndustryDO) {
            instance.setId(fundIndustryDO.getId());
            int num = fundIndustryDAO.updateFundIndustry(instance);
            if (num > -1) {
                result = true;
            }
        } else {
            Long id = fundIndustryDAO.insertFundIndustry(instance);
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
    public List<FundIndustryDO> getFundIndustry(String code) {
        if (StringUtils.isBlank(code)) {
            logger.error("code is wrong");
            return null;
        }

        String url = getFundIndustryUrl(code);
        logger.info(code + " fundIndustry url is " + url);

        Result<String> response = HttpUtils.get(url);
        if (!response.isSuccess() || StringUtils.isBlank(response.getValue())) {
            logger.error(url + " status is " + response.getErrCode() + " resposne is " + response.getValue());
            return null;
        }

        List<FundIndustryDO> fundIndustryDOList = new ArrayList<>();
        try {
            String result = response.getValue().replaceAll("var apidata=", "").replaceAll(";", "")
                .replaceAll("content:", "\"content\":")
                .replaceAll("arryear:", "\"arryear\":")
                .replaceAll("curyear:", "\"curyear\":");
            JSONObject resultJson = JSONObject.parseObject(result);
            Document doc = Jsoup.parse(resultJson.getString("content"));
            if (null != doc) {
                Elements label = doc.select("h4 label[class='left']");
                Elements table = doc.select("table[class='w782 comm hypz']");
                for (int i = 0; i < table.size(); i++) {
                    String num = label.get(i).text().replaceAll(".*(\\d)季度.*", "$1");
                    Elements tr = table.get(i).select("tbody tr");
                    for (int j = 0; j < tr.size(); j++) {
                        try {
                            int mr = 2;
                            int mv = 3;

                            Elements td = tr.get(j).select("td");
                            if (td.size() > 4) {
                                mr = 3;
                                mv = 4;
                            }

                            FundIndustryDO fundIndustryDO = new FundIndustryDO();
                            fundIndustryDO.setCode(code);
                            fundIndustryDO.setIndustry(getString(td.get(1).text()));
                            fundIndustryDO.setMarketRatio(getDoublePercent(td.get(mr).text(), 2));
                            fundIndustryDO.setMarketValue(getDouble(td.get(mv).text(), 2));
                            fundIndustryDO.setQuarter(resultJson.getString("curyear") + num);
                            fundIndustryDOList.add(fundIndustryDO);
                        } catch (Exception e) {
                            logger.error(code + "|" + tr.get(j).toString() + " exception ", e);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error(code + " getFundIndustry exception ", e);
        }
        return fundIndustryDOList;
    }

    /**
     * @param code
     * @return
     */
    private String getFundIndustryUrl(String code) {
        return String.format(FUND_INDUSTRY_URL, code);
    }

    public FundIndustryDAO getFundIndustryDAO() {
        return fundIndustryDAO;
    }

    public void setFundIndustryDAO(FundIndustryDAO fundIndustryDAO) {
        this.fundIndustryDAO = fundIndustryDAO;
    }
}
