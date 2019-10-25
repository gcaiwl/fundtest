package com.longxi.data.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;

import com.longxi.data.dao.FundBondPositionDAO;
import com.longxi.data.obj.FundBondPositionDO;
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
public class FundBondPositionService extends FundService {
    private static Logger logger = LoggerFactory.getLogger(FundBondPositionService.class);

    private static String FUND_BOND_POSITION_URL = "http://fund.eastmoney.com/f10/FundArchivesDatas.aspx?type=zqcc&code=%s&topline=100";

    @Resource
    private FundBondPositionDAO fundBondPositionDAO;

    /**
     * @param code
     * @return
     */
    @Override
    public boolean insertOrUpdate(String code) {
        boolean result = true;
        List<FundBondPositionDO> fundBondPositionDOList = getFundBondPosition(code);
        if (null != fundBondPositionDOList) {
            for (int i = 0; i < fundBondPositionDOList.size(); i++) {
                result = result && insertOrUpdate(fundBondPositionDOList.get(i));
            }
        }
        return result;
    }

    /**
     * @param instance
     * @return
     */
    public boolean insertOrUpdate(FundBondPositionDO instance) {
        boolean result = false;
        if (null == instance) {
            logger.error("fundBondPositionDO is null");
            return result;
        }

        FundBondPositionDO fundBondPositionDO = null;
        try {
            fundBondPositionDAO.queryFundBondPositionByQuarter(instance.getCode(), instance.getQuarter(), instance.getBondCode());
        } catch (Exception e) {
            fundBondPositionDAO.deleteFundBondPositionByQuarter(instance.getCode(), instance.getQuarter(), instance.getBondCode());
        }

        if (null != fundBondPositionDO) {
            instance.setId(fundBondPositionDO.getId());
            int num = fundBondPositionDAO.updateFundBondPosition(instance);
            if (num > -1) {
                result = true;
            }
        } else {
            Long id = fundBondPositionDAO.insertFundBondPosition(instance);
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
    public List<FundBondPositionDO> getFundBondPosition(String code) {
        if (StringUtils.isBlank(code)) {
            logger.error("code is wrong");
            return null;
        }

        String url = getFundBondPositionUrl(code);
        logger.info(code + " fundBondPosition url is " + url);

        Result<String> response = HttpUtil.get(url);
        if (!response.isSuccess() || StringUtils.isBlank(response.getValue())) {
            logger.error(url + " status is " + response.getErrCode() + " resposne is " + response.getValue());
            return null;
        }

        List<FundBondPositionDO> fundBondPositionDOList = new ArrayList<>();
        try {
            String result = response.getValue().replaceAll("var apidata=", "").replaceAll(";", "")
                .replaceAll("content:", "\"content\":")
                .replaceAll("arryear:", "\"arryear\":")
                .replaceAll("curyear:", "\"curyear\":");

            String year = getCurrentYear();
            JSONObject resultJson = JSONObject.parseObject(result);
            if (!resultJson.getString("curyear").contains(year) && isUpdateIncr) {
                return fundBondPositionDOList;
            }

            Document doc = Jsoup.parse(resultJson.getString("content"));
            if (null != doc) {
                Elements label = doc.select("h4 label[class='left']");
                Elements table = doc.select("table[class='w782 comm tzxq']");
                for (int i = 0; i < table.size(); i++) {
                    String num = label.get(i).text().replaceAll(".*(\\d)季度.*", "$1");
                    int unit = getUnit(table.get(i).select("th[class='last']").text());
                    Elements tr = table.get(i).select("tbody tr");
                    for (int j = 0; j < tr.size(); j++) {
                        try {
                            Elements td = tr.get(j).select("td");
                            FundBondPositionDO fundBondPositionDO = new FundBondPositionDO();
                            fundBondPositionDO.setCode(code);
                            fundBondPositionDO.setBondCode(getString(td.get(1).text()));
                            fundBondPositionDO.setBondName(getString(td.get(2).text()));
                            fundBondPositionDO.setAssetsRate(getDoublePercent(td.get(3).text(), 2));
                            fundBondPositionDO.setMarketValue(getDoubleUnit(td.get(4).text(), unit, 2));
                            fundBondPositionDO.setQuarter(resultJson.getString("curyear") + num);
                            fundBondPositionDOList.add(fundBondPositionDO);
                        } catch (Exception e) {
                            logger.error(code + "|" + tr.get(j).toString() + " exception ", e);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error(code + " getFundBondPosition exception ", e);
        }
        return fundBondPositionDOList;
    }

    /**
     * @param code
     * @return
     */
    private String getFundBondPositionUrl(String code) {
        return String.format(FUND_BOND_POSITION_URL, code);
    }

    public FundBondPositionDAO getFundBondPositionDAO() {
        return fundBondPositionDAO;
    }

    public void setFundBondPositionDAO(FundBondPositionDAO fundBondPositionDAO) {
        this.fundBondPositionDAO = fundBondPositionDAO;
    }
}
