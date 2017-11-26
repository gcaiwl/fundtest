package com.longxi.data.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;

import com.longxi.data.dao.FundBaseDAO;
import com.longxi.data.dao.FundSharesPositionDAO;
import com.longxi.data.dao.impl.FundBaseDAOImpl;
import com.longxi.data.obj.FundBaseDO;
import com.longxi.data.obj.FundIndustryDO;
import com.longxi.data.obj.FundSharesPositionDO;
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
public class FundSharesPositionService extends FundService {
    private static Logger logger = LoggerFactory.getLogger(FundSharesPositionService.class);

    private static String FUND_SHARES_POSITION_URL = "http://fund.eastmoney.com/f10/FundArchivesDatas.aspx?type=jjcc&code=%s&topline=100";

    @Resource
    private FundSharesPositionDAO fundSharesPositionDAO;

    /**
     * @param code
     * @return
     */
    @Override
    public boolean insertOrUpdate(String code) {
        boolean result = true;
        List<FundSharesPositionDO> fundIndustryDOList = getFundSharesPosition(code);
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
    public boolean insertOrUpdate(FundSharesPositionDO instance) {
        boolean result = false;
        if (null == instance) {
            logger.error("fundSharesPositionDO is null");
            return result;
        }

        FundSharesPositionDO fundSharesPositionDO = fundSharesPositionDAO.queryFundSharesPositionByQuarter(instance.getCode(), instance.getQuarter(), instance.getSharesCode());
        if (null != fundSharesPositionDO) {
            instance.setId(fundSharesPositionDO.getId());
            int num = fundSharesPositionDAO.updateFundSharesPosition(instance);
            if (num > -1) {
                result = true;
            }
        } else {
            Long id = fundSharesPositionDAO.insertFundSharesPosition(instance);
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
    public List<FundSharesPositionDO> getFundSharesPosition(String code) {
        if (StringUtils.isBlank(code)) {
            logger.error("code is wrong");
            return null;
        }

        String url = getFundSharesPositionUrl(code);
        logger.info(code + " fundSharesPosition url is " + url);

        Result<String> response = HttpUtils.get(url);
        if (!response.isSuccess() || StringUtils.isBlank(response.getValue())) {
            logger.error(url + " status is " + response.getErrCode() + " resposne is " + response.getValue());
            return null;
        }

        List<FundSharesPositionDO> fundSharesPositionDOList = new ArrayList<>();
        try {
            String result = response.getValue().replaceAll("var apidata=", "").replaceAll(";", "")
                .replaceAll("content:", "\"content\":")
                .replaceAll("arryear:", "\"arryear\":")
                .replaceAll("curyear:", "\"curyear\":");
            JSONObject resultJson = JSONObject.parseObject(result);
            Document doc = Jsoup.parse(resultJson.getString("content"));
            if (null != doc) {
                Elements label = doc.select("h4 label[class='left']");
                Elements table = doc.select("table[class='w782 comm tzxq']");
                for (int i = 0; i < table.size(); i++) {
                    String num = label.get(i).text().replaceAll(".*(\\d)季度.*", "$1");
                    Elements tr = table.get(i).select("tbody tr");
                    for (int j = 0; j < tr.size(); j++) {
                        try {
                            int ar = 4;
                            int sn = 5;
                            int mv = 6;

                            Elements td = tr.get(j).select("td");
                            if (td.size() > 7) {
                                ar = 6;
                                sn = 7;
                                mv = 8;
                            }

                            FundSharesPositionDO fundSharesPositionDO = new FundSharesPositionDO();
                            fundSharesPositionDO.setCode(code);
                            fundSharesPositionDO.setSharesCode(getString(td.get(1).text()));
                            fundSharesPositionDO.setSharesName(getString(td.get(2).text()));
                            fundSharesPositionDO.setAssetsRate(getDoublePercent(td.get(ar).text(), 2));
                            fundSharesPositionDO.setSharesNum(getDouble(td.get(sn).text(), 2));
                            fundSharesPositionDO.setMarketValue(getDouble(td.get(mv).text(), 2));
                            fundSharesPositionDO.setQuarter(resultJson.getString("curyear") + num);
                            fundSharesPositionDOList.add(fundSharesPositionDO);
                        } catch (Exception e) {
                            logger.error(code + "|" + tr.toString() + " exception ", e);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error(code + " getFundSharesPosition exception ", e);
        }
        return fundSharesPositionDOList;
    }

    /**
     *
     * @param code
     * @return
     */
    private String getFundSharesPositionUrl(String code) {
        return String.format(FUND_SHARES_POSITION_URL, code);
    }

    public FundSharesPositionDAO getFundSharesPositionDAO() {
        return fundSharesPositionDAO;
    }

    public void setFundSharesPositionDAO(FundSharesPositionDAO fundSharesPositionDAO) {
        this.fundSharesPositionDAO = fundSharesPositionDAO;
    }
}
