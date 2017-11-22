package com.longxi.data.service;

import javax.annotation.Resource;

import com.longxi.data.dao.FundBaseDAO;
import com.longxi.data.dao.FundSharesPositionDAO;
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
public class FundSharesPositionService extends FundService {
    private static Logger logger = LoggerFactory.getLogger(FundSharesPositionService.class);

    private static String FUND_SHARES_POSITION_URL = "http://fund.eastmoney.com/f10/jbgk_%s.html";

    @Resource
    private FundSharesPositionDAO fundSharesPositionDAO;

    public FundSharesPositionDAO getFundSharesPositionDAO() {
        return fundSharesPositionDAO;
    }

    public void setFundSharesPositionDAO(FundSharesPositionDAO fundSharesPositionDAO) {
        this.fundSharesPositionDAO = fundSharesPositionDAO;
    }
}
