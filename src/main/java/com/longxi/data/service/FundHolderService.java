package com.longxi.data.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;

import com.longxi.data.dao.FundHolderDAO;
import com.longxi.data.obj.FundHolderDO;
import com.longxi.data.obj.Result;
import com.longxi.data.utils.HttpUtil;
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
public class FundHolderService extends FundService {
    private static Logger logger = LoggerFactory.getLogger(FundHolderService.class);

    private static String FUND_HOLDER_URL = "http://fund.eastmoney.com/f10/FundArchivesDatas.aspx?type=cyrjg&code=%s";

    @Resource
    private FundHolderDAO fundHolderDAO;

    /**
     * @param code
     * @return
     */
    @Override
    public boolean insertOrUpdate(String code) {
        boolean result = true;
        List<FundHolderDO> fundHolderDOList = getFundHolder(code);
        if (null != fundHolderDOList) {
            for (int i = 0; i < fundHolderDOList.size(); i++) {
                result = result && insertOrUpdate(fundHolderDOList.get(i));
            }
        }
        return result;
    }

    /**
     * @param instance
     * @return
     */
    public boolean insertOrUpdate(FundHolderDO instance) {
        boolean result = false;
        if (null == instance) {
            logger.error("fundHolderDO is null");
            return result;
        }

        FundHolderDO fundHolderDO = null;
        try {
            fundHolderDO = fundHolderDAO.queryFundHolderByPublishTime(instance.getCode(), instance.getPublishTime());
        } catch (Exception e) {
            fundHolderDAO.deleteFundHolderByPublishTime(instance.getCode(), instance.getPublishTime());
        }

        if (null != fundHolderDO) {
            instance.setId(fundHolderDO.getId());
            int num = fundHolderDAO.updateFundHolder(instance);
            if (num > -1) {
                result = true;
            }
        } else {
            Long id = fundHolderDAO.insertFundHolder(instance);
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
    public List<FundHolderDO> getFundHolder(String code) {
        if (StringUtils.isBlank(code)) {
            logger.error("code is wrong");
            return null;
        }

        String url = getFundHolderUrl(code);
        logger.info(code + " fundHolder url is " + url);

        Result<String> response = HttpUtil.get(url);
        if (!response.isSuccess() || StringUtils.isBlank(response.getValue())) {
            logger.error(url + " status is " + response.getErrCode() + " resposne is " + response.getValue());
            return null;
        }

        List<FundHolderDO> fundHolderDOList = new ArrayList<>();
        try {
            String result = response.getValue().replaceAll("var apidata=", "").replaceAll(";", "")
                .replaceAll("content:", "\"content\":")
                .replaceAll("summary:", "\"summary\":");
            JSONObject resultJson = JSONObject.parseObject(result);
            Document doc = Jsoup.parse(resultJson.getString("content"));
            if (null != doc) {
                String year = getCurrentYear();
                int unit = getUnit(doc.select("table[class='w782 comm cyrjg'] th[class='last']").text());
                Elements tr = doc.select("table[class='w782 comm cyrjg'] tbody tr");
                for (int i = 0; i < tr.size(); i++) {
                    try {
                        Elements td = tr.get(i).select("td");
                        if (!td.get(0).text().contains(year) && isUpdateIncr) {
                            continue;
                        }

                        FundHolderDO fundHolderDO = new FundHolderDO();
                        fundHolderDO.setCode(code);
                        fundHolderDO.setPublishTime(getDate(td.get(0).text()));
                        fundHolderDO.setMechanismRatio(getDoublePercent(td.get(1).text(), 2));
                        fundHolderDO.setPersonalRatio(getDoublePercent(td.get(2).text(), 2));
                        fundHolderDO.setInsideRatio(getDoublePercent(td.get(3).text(), 2));
                        fundHolderDO.setShare(getDoubleUnit(td.get(4).text(), unit, 2));
                        fundHolderDOList.add(fundHolderDO);
                    } catch (Exception e) {
                        logger.error(code + "|" + tr.get(i).toString() + " exception ", e);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(code + " getFundHolder exception ", e);
        }
        return fundHolderDOList;
    }

    /**
     * @param code
     * @return
     */
    private String getFundHolderUrl(String code) {
        return String.format(FUND_HOLDER_URL, code);
    }

    public FundHolderDAO getFundHolderDAO() {
        return fundHolderDAO;
    }

    public void setFundHolderDAO(FundHolderDAO fundHolderDAO) {
        this.fundHolderDAO = fundHolderDAO;
    }
}
