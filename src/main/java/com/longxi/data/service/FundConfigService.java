package com.longxi.data.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;

import com.longxi.data.dao.FundBaseDAO;
import com.longxi.data.dao.FundConfigDAO;
import com.longxi.data.dao.impl.FundBaseDAOImpl;
import com.longxi.data.obj.FundBaseDO;
import com.longxi.data.obj.FundConfigDO;
import com.longxi.data.obj.FundScaleDO;
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
public class FundConfigService extends FundService {
    private static Logger logger = LoggerFactory.getLogger(FundConfigService.class);

    private static String FUND_CONFIG_URL = "http://fund.eastmoney.com/f10/zcpz_%s.html";

    @Resource
    private FundConfigDAO fundConfigDAO;

    /**
     * @param code
     * @return
     */
    @Override
    public boolean insertOrUpdate(String code) {
        boolean result = true;
        List<FundConfigDO> fundConfigDOList = getFundConfig(code);
        if (null != fundConfigDOList) {
            for (int i = 0; i < fundConfigDOList.size(); i++) {
                result = result && insertOrUpdate(fundConfigDOList.get(i));
            }
        }
        return result;
    }

    /**
     * @param instance
     * @return
     */
    public boolean insertOrUpdate(FundConfigDO instance) {
        boolean result = false;
        if (null == instance) {
            logger.error("fundConfigDO is null");
            return result;
        }

        FundConfigDO fundConfigDO = fundConfigDAO.queryFundConfigByPublishTime(instance.getCode(), instance.getPublishTime());
        if (null != fundConfigDO) {
            instance.setId(fundConfigDO.getId());
            int num = fundConfigDAO.updateFundConfig(instance);
            if (num > -1) {
                result = true;
            }
        } else {
            Long id = fundConfigDAO.insertFundConfig(instance);
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
    public List<FundConfigDO> getFundConfig(String code) {
        if (StringUtils.isBlank(code)) {
            logger.error("code is wrong");
            return null;
        }

        String url = getFundConfigUrl(code);
        logger.info(code + " fundConfig url is " + url);

        Result<String> response = HttpUtils.get(url);
        if (!response.isSuccess() || StringUtils.isBlank(response.getValue())) {
            logger.error(url + " status is " + response.getErrCode() + " resposne is " + response.getValue());
            return null;
        }

        List<FundConfigDO> fundConfigDOList = new ArrayList<>();
        try {
            Document doc = Jsoup.parse(response.getValue());
            if (null != doc) {
                Elements tr = doc.select("table[class='w782 comm tzxq'] tbody tr");
                for (int i = 0; i < tr.size(); i++) {
                    try {
                        Elements td = tr.get(i).select("td");
                        FundConfigDO fundConfigDO = new FundConfigDO();
                        fundConfigDO.setCode(code);
                        fundConfigDO.setPublishTime(getDate(td.get(0).text()));
                        fundConfigDO.setSharesRatio(getDoublePercent(td.get(1).text(), 2));
                        fundConfigDO.setBondRatio(getDoublePercent(td.get(2).text(), 2));
                        fundConfigDO.setCashRatio(getDoublePercent(td.get(3).text(), 2));
                        fundConfigDO.setAssets(getDouble(td.get(4).text(), 2));
                        fundConfigDOList.add(fundConfigDO);
                    } catch (Exception e) {
                        logger.error(code + "|" + tr.toString() + " exception ", e);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(code + " getFundConfig exception ", e);
        }
        return fundConfigDOList;
    }

    /**
     * @param code
     * @return
     */
    private String getFundConfigUrl(String code) {
        return String.format(FUND_CONFIG_URL, code);
    }

    public FundConfigDAO getFundConfigDAO() {
        return fundConfigDAO;
    }

    public void setFundConfigDAO(FundConfigDAO fundConfigDAO) {
        this.fundConfigDAO = fundConfigDAO;
    }
}
