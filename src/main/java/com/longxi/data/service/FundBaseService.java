package com.longxi.data.service;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;

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
public class FundBaseService extends FundService {
    private static Logger logger = LoggerFactory.getLogger(FundBaseService.class);

    private static String FUND_BASE_URL = "http://fund.eastmoney.com/f10/jbgk_%s.html";

    @Resource
    private FundBaseDAO fundBaseDAO;

    /**
     * @param code
     * @return
     */
    @Override
    public boolean insertOrUpdate(String code) {
        FundBaseDO instance = getFundBase(code);
        return insertOrUpdate(instance);
    }

    /**
     * @param instance
     * @return
     */
    public boolean insertOrUpdate(FundBaseDO instance) {
        boolean result = false;
        if (null == instance) {
            logger.error("fundBaseDO is null");
            return result;
        }

        FundBaseDAOImpl fundBaseDAO = new FundBaseDAOImpl();
        FundBaseDO fundBaseDO = fundBaseDAO.queryFundBaseByCode(instance.getCode());
        if (null != fundBaseDO) {
            instance.setId(fundBaseDO.getId());
            int num = fundBaseDAO.updateFundBase(instance);
            if (num > -1) {
                result = true;
            }
        } else {
            Long id = fundBaseDAO.insertFundBase(instance);
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
    public FundBaseDO getFundBase(String code) {
        if (StringUtils.isBlank(code)) {
            logger.error("code is wrong");
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
        try {
            Document doc = Jsoup.parse(response.getValue());
            if (null != doc) {
                fundBase = new FundBaseDO();
                Elements base = doc.select("table[class='info w790'] td");
                try {
                    fundBase.setName(getString(base.get(1).text()));
                    fundBase.setCode(code);
                    fundBase.setType(getString(base.get(3).text()));
                    fundBase.setIssueTime(getDate(base.get(4).text()));
                    fundBase.setEstablishTime(getDate(base.get(5).text()));
                    fundBase.setScale(getDoubleUnit(base.get(6).text(), 2));
                    fundBase.setShare(getDoubleUnit(base.get(7).text(), 4));
                    fundBase.setCompany(getString(base.get(8).text()));
                } catch (Exception e) {
                    logger.error(code + "|" + base.toString() + " exception ", e);
                }

                Elements p = doc.select("div[class='bs_jz'] p");
                try {
                    Elements status = p.get(1).select("span");
                    fundBase.setStatus(getStatus(status.get(0).text(), status.get(status.size() - 1).text()));
                    fundBase.setQuota(getQutoa(status.get(status.size() - 2).text()));
                    fundBase.setFee(getDoublePercent(p.get(2).select("b").eq(1).text(), 2));
                } catch (Exception e) {
                    logger.error(code + "|" + p.toString() + " exception ", e);
                }
            }
        } catch (Exception e) {
            logger.error(code  + " getFundBase exception ", e);
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
     * 认购期 = 2
     * 开放申购、开放赎回 = 11
     * 开放申购、暂停赎回 = 10
     * 暂停申购、开放赎回 = 1
     * 暂停申购、暂停赎回 = 0
     * @param apply
     * @param redeem
     * @return
     */
    private Integer getStatus(String apply, String redeem) {
        if (apply.contains("认购")) {
            return 2;
        }
        int v1 = apply.contains("开放") ? 10 : 0;
        int v2 = redeem.contains("开放") ? 1 : 0;
        return v1 + v2;
    }

    /**
     * @param value
     * @return
     */
    private Integer getQutoa(String value) {
        if (StringUtils.isBlank(value) || value.trim().equals("&nbsp;")) {
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
