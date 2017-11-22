package com.longxi.data.service;

import javax.annotation.Resource;

import com.longxi.data.dao.FundBaseDAO;
import com.longxi.data.dao.FundIndustryDAO;
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

    private static String FUND_INDUSTRY_URL = "http://fund.eastmoney.com/f10/jbgk_%s.html";

    @Resource
    private FundIndustryDAO fundIndustryDAO;

    public FundIndustryDAO getFundIndustryDAO() {
        return fundIndustryDAO;
    }

    public void setFundIndustryDAO(FundIndustryDAO fundIndustryDAO) {
        this.fundIndustryDAO = fundIndustryDAO;
    }
}
