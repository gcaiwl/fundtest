package com.longxi.data.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;

import com.longxi.data.dao.FundBaseDAO;
import com.longxi.data.dao.FundBondPositionDAO;
import com.longxi.data.dao.impl.FundBaseDAOImpl;
import com.longxi.data.obj.FundBaseDO;
import com.longxi.data.obj.FundBondPositionDO;
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
public class FundBondPositionService extends FundService {
    private static Logger logger = LoggerFactory.getLogger(FundBondPositionService.class);

    private static String FUND_BOND_POSITION_URL = "http://fund.eastmoney.com/f10/FundArchivesDatas.aspx?type=zqcc&code=%s&topline=100";

    @Resource
    private FundBondPositionDAO fundBondPositionDAO;

    /**
     * @param code
     */
    public void insertOrUpdate(String code) {
        List<FundBondPositionDO> fundBondPositionDOList = getFundBondPosition(code);
        if (null != fundBondPositionDOList) {
            for (int i = 0; i < fundBondPositionDOList.size(); i++) {
                insertOrUpdate(fundBondPositionDOList.get(i));
            }
        }
    }

    /**
     * @param instance
     */
    public void insertOrUpdate(FundBondPositionDO instance) {
        if (null == instance) {
            logger.error("fundBondPositionDO is null");
            return;
        }

        FundBondPositionDO fundBondPositionDO = fundBondPositionDAO.queryFundBondPositionByQuarter(instance.getCode(), instance.getQuarter(), instance.getBondCode());
        if (null != fundBondPositionDO) {
            instance.setId(fundBondPositionDO.getId());
            fundBondPositionDAO.updateFundBondPosition(instance);
        } else {
            fundBondPositionDAO.insertFundBondPosition(instance);
        }
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

        Result<String> response = HttpUtils.get(url);
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
            JSONObject resultJson = JSONObject.parseObject(result);
            Document doc = Jsoup.parse(resultJson.getString("content"));
            if (null != doc) {
                Elements table = doc.select("table[class='w782 comm tzxq']");
                int tableNum = table.size();
                for (int i = 0; i < table.size(); i++) {
                    Elements tr = table.select("tbody tr");
                    for (int j = 0; j < tr.size(); j++) {
                        Elements td = tr.select("td");
                        FundBondPositionDO fundBondPositionDO = new FundBondPositionDO();
                        fundBondPositionDO.setCode(code);
                        fundBondPositionDO.setBondCode(getString(td.get(1).text()));
                        fundBondPositionDO.setBondName(getString(td.get(2).text()));
                        fundBondPositionDO.setAssetsRate(getDoublePercent(td.get(3).text(), 2));
                        fundBondPositionDO.setMarketValue(getDouble(td.get(4).text(), 2));
                        fundBondPositionDO.setQuarter(resultJson.getString("curyear") + tableNum);
                        fundBondPositionDOList.add(fundBondPositionDO);
                    }
                    tableNum--;
                }
            }
        } catch (Exception e) {
            logger.error(code + " getFundBondPosition exception ", e);
        }
        return fundBondPositionDOList;
    }

    /**
     *
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
