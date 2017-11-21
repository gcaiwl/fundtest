package com.longxi.data.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.longxi.data.dao.FundManagerDAO;
import com.longxi.data.obj.FundManagerDO;
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
 * @date 2017/11/20
 */
public class FundManagerService extends FundService {
    private static Logger logger = LoggerFactory.getLogger(FundManagerService.class);

    private static String FUND_MANAGER_URL = "http://fund.eastmoney.com/f10/jjjl_%s.html";

    @Resource
    private FundManagerDAO fundManagerDAO;

    /**
     * @param code
     */
    private void insertOrUpdate(String code) {
        List<FundManagerDO> fundManagerDOList = getFundManager(code);
        if (null != fundManagerDOList) {
            for (FundManagerDO fundManagerDO : fundManagerDOList) {
                insertOrUpdate(fundManagerDO);
            }
        }
    }

    /**
     * @param instance
     */
    public void insertOrUpdate(FundManagerDO instance) {
        if (null == instance) {
            logger.error("FundManagerDO is null");
            return;
        }

        FundManagerDO fundManagerDO = fundManagerDAO.queryFundManagerByManager(instance.getCode(), instance.getManager());
        if (null != fundManagerDO) {
            instance.setId(fundManagerDO.getId());
            fundManagerDAO.updateFundManager(instance);
        } else {
            fundManagerDAO.insertFundManager(instance);
        }
    }

    /**
     * @param code
     * @return
     */
    private List<FundManagerDO> getFundManager(String code) {
        if (StringUtils.isBlank(code)) {
            logger.error("code is wrong");
            return null;
        }

        String url = getFundManagerUrl(code);
        logger.info(code + " fundManager url is " + url);

        Result<String> response = HttpUtils.get(url);
        if (!response.isSuccess() || StringUtils.isBlank(response.getValue())) {
            logger.error(url + " status is " + response.getErrCode() + " resposne is " + response.getValue());
            return null;
        }

        List<FundManagerDO> fundManagerDOList = new ArrayList<>();
        try {
            Document doc = Jsoup.parse(response.getValue());
            if (null != doc) {
                FundManagerDO fundManagerDO = new FundManagerDO();
                Elements tr = doc.select("table[class='w782 comm  jloff'] tbody tr");
                for (int i = 0; i < tr.size(); i++) {
                    Elements td = tr.select("td");
                    fundManagerDO.setCode(code);
                    fundManagerDO.setStartTime(getDate(td.get(0).text()));
                    fundManagerDO.setEndTime(td.get(1).text().equals("至今") ? null : getDate(td.get(1).text()));
                    fundManagerDO.setManager(getString(td.get(2).text()));
                    fundManagerDO.setRedound(getDoublePercent(td.get(4).text(), 2));
                    fundManagerDOList.add(fundManagerDO);
                }
            }
        } catch (Exception e) {
            logger.error(code + " getFundManager exception ", e);
        }
        return fundManagerDOList;
    }

    /**
     * @param code
     * @return
     */
    private String getFundManagerUrl(String code) {
        return String.format(FUND_MANAGER_URL, code);
    }

    public FundManagerDAO getFundManagerDAO() {
        return fundManagerDAO;
    }

    public void setFundManagerDAO(FundManagerDAO fundManagerDAO) {
        this.fundManagerDAO = fundManagerDAO;
    }
}
