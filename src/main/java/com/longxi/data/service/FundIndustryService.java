package com.longxi.data.service;

import javax.annotation.Resource;

import com.longxi.data.dao.FundBaseDAO;
import com.longxi.data.dao.impl.FundBaseDAOImpl;
import com.longxi.data.obj.FundBaseDO;
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

    private static String FUND_BASE_URL = "http://fund.eastmoney.com/f10/jbgk_%s.html";

    @Resource
    private FundBaseDAO fundBaseDAO;

    /**
     * @param code
     */
    public void insertOrUpdate(String code) {
        FundBaseDO instance = getFundBase(code);
        insertOrUpdate(instance);
    }

    /**
     * @param instance
     */
    public void insertOrUpdate(FundBaseDO instance) {
        if (null == instance) {
            logger.error("fundBaseDO is null");
            return;
        }

        FundBaseDAOImpl fundBaseDAO = new FundBaseDAOImpl();
        FundBaseDO fundBaseDO = fundBaseDAO.queryFundBaseByCode(instance.getCode());
        if (null != fundBaseDO) {
            fundBaseDAO.updateFundBase(instance);
        } else {
            fundBaseDAO.insertFundBase(instance);
        }
    }

    /**
     * @param code
     * @return
     */
    public FundBaseDO getFundBase(String code) {
        if (StringUtils.isBlank(code)) {
            logger.error("fundCode is wrong");
            return null;
        }

        String url = getFundBaseUrl(code);
        logger.info(code + " fundbase url is " + url);

        Result<String> response = HttpUtils.get(url);
        if (!response.isSuccess() || StringUtils.isBlank(response.getValue())) {
            logger.error(url + " status is " + response.getErrCode() + " resposne is " + response.getValue());
            return null;
        }

        FundBaseDO fundBase = null;
        Document doc = Jsoup.parse(response.getValue());
        if (null != doc) {
            fundBase = new FundBaseDO();
            Elements base = doc.select("table[class='info w790'] td");
            fundBase.setName(getString(base.get(1).text()));
            fundBase.setCode(getCode(base.get(2).text()));
            fundBase.setType(getString(base.get(3).text()));
            fundBase.setIssueTime(getDate(base.get(4).text()));
            fundBase.setEstablishTime(getDate(base.get(5).text()));
            fundBase.setScale(getDoubleUnit(base.get(6).text(), 2));
            fundBase.setShare(getDoubleUnit(base.get(7).text(), 4));
            fundBase.setCompany(getString(base.get(10).text()));

            Elements fee = doc.select("div[class='bs_jz'] b");
            fundBase.setFee(getDoublePercent(fee.get(2).text(), 2));

            Elements status = doc.select("div[class='bs_jz'] span");
            if (status.get(9).outerHtml().contains("span")) {
                fundBase.setStatus(getStatus(status.get(7).text(), status.get(10).text()));
                fundBase.setQuota(getQutoa(status.get(9).text()));
            } else {
                fundBase.setStatus(getStatus(status.get(7).text(), status.get(9).text()));
                fundBase.setQuota(0);
            }
        }
        return fundBase;
    }

    /**
     * @param code
     * @return
     */
    private String getFundBaseUrl(String code) {
        return String.format(FUND_BASE_URL, code);
    }

    /**
     * @param apply
     * @param redeem
     * @return
     */
    private Integer getStatus(String apply, String redeem) {
        int v1 = apply.contains("开放") ? 10 : 0;
        int v2 = redeem.contains("开放") ? 1 : 0;
        return v1 + v2;
    }

    /**
     * @param code
     * @return
     */
    private String getCode(String code) {
        if (StringUtils.isBlank(code)) {
            logger.error("code is null or empty");
            return code;
        }
        return code.trim().replaceAll("\\D*", "");
    }

    /**
     * @param value
     * @return
     */
    private Integer getQutoa(String value) {
        if (StringUtils.isBlank(value)) {
            value = "0";
        }
        return Integer.parseInt(value.trim().replaceAll("\\D*(\\d*)\\D*", "$1"));
    }

    public FundBaseDAO getFundBaseDAO() {
        return fundBaseDAO;
    }

    public void setFundBaseDAO(FundBaseDAO fundBaseDAO) {
        this.fundBaseDAO = fundBaseDAO;
    }
}
