package com.longxi.data.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;

import com.longxi.data.dao.FundHolderDAO;
import com.longxi.data.obj.FundHolderDO;
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
public class FundHolderService extends FundService {
    private static Logger logger = LoggerFactory.getLogger(FundHolderService.class);

    private static String FUND_HOLDER_URL = "http://fund.eastmoney.com/f10/FundArchivesDatas.aspx?type=cyrjg&code=%s";

    @Resource
    private FundHolderDAO fundHolderDAO;

    /**
     * @param code
     */
    public void insertOrUpdate(String code) {
        List<FundHolderDO> fundHolderDOList = getFundHolder(code);
        if (null != fundHolderDOList) {
            for (int i = 0; i < fundHolderDOList.size(); i++) {
                insertOrUpdate(fundHolderDOList.get(i));
            }
        }
    }

    /**
     * @param instance
     */
    public void insertOrUpdate(FundHolderDO instance) {
        if (null == instance) {
            logger.error("fundHolderDO is null");
            return;
        }

        FundHolderDO fundHolderDO = fundHolderDAO.queryFundHolderByPublishTime(instance.getCode(), instance.getPublishTime());
        if (null != fundHolderDO) {
            instance.setId(fundHolderDO.getId());
            fundHolderDAO.updateFundHolder(instance);
        } else {
            fundHolderDAO.insertFundHolder(instance);
        }
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

        Result<String> response = HttpUtils.get(url);
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
                Elements tr = doc.select("table[class='w782 comm cyrjg'] tbody tr");
                for (int i = 0; i < tr.size(); i++) {
                    Elements td = tr.get(i).select("td");
                    FundHolderDO fundHolderDO = new FundHolderDO();
                    fundHolderDO.setCode(code);
                    fundHolderDO.setPublishTime(getDate(td.get(0).text()));
                    fundHolderDO.setMechanismRatio(getDoublePercent(td.get(1).text(), 2));
                    fundHolderDO.setPersonalRatio(getDoublePercent(td.get(2).text(), 2));
                    fundHolderDO.setInsideRatio(getDoublePercent(td.get(3).text(), 2));
                    fundHolderDO.setShare(getDouble(td.get(4).text(), 2));
                    fundHolderDOList.add(fundHolderDO);
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
