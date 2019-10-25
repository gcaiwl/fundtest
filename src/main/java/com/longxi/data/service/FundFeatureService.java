package com.longxi.data.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;

import com.longxi.data.dao.FundFeatureDAO;
import com.longxi.data.dao.FundIndexDAO;
import com.longxi.data.dao.FundStyleDAO;
import com.longxi.data.obj.FundFeatureDO;
import com.longxi.data.obj.FundIndexDO;
import com.longxi.data.obj.FundStyleDO;
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
public class FundFeatureService extends FundService {
    private static Logger logger = LoggerFactory.getLogger(FundFeatureService.class);

    private static String FUND_FEATURE_URL = "http://fund.eastmoney.com/f10/tsdata_%s.html";

    @Resource
    private FundFeatureDAO fundFeatureDAO;
    @Resource
    private FundIndexDAO fundIndexDAO;
    @Resource
    private FundStyleDAO fundStyleDAO;

    /**
     * @param code
     * @return
     */
    @Override
    public boolean insertOrUpdate(String code) {
        boolean result = true;
        Map<String, Object> map = getFundFeature(code);
        List<FundFeatureDO> fundFeatureDOList = (List<FundFeatureDO>)map.get("feature");
        if (null != fundFeatureDOList && !fundFeatureDOList.isEmpty()) {
            for (FundFeatureDO fundFeatureDO : fundFeatureDOList) {
                result = result && insertOrUpdate(fundFeatureDO);
            }
        }

        List<FundIndexDO> fundIndexDOList = (List<FundIndexDO>)map.get("index");
        if (null != fundIndexDOList && !fundIndexDOList.isEmpty()) {
            for (FundIndexDO fundIndexDO : fundIndexDOList) {
                result = result && insertOrUpdate(fundIndexDO);
            }
        }

        List<FundStyleDO> fundStyleDOList = (List<FundStyleDO>)map.get("style");
        if (null != fundStyleDOList && !fundStyleDOList.isEmpty()) {
            for (FundStyleDO fundStyleDO : fundStyleDOList) {
                result = result && insertOrUpdate(fundStyleDO);
            }
        }
        return result;
    }

    /**
     * @param instance
     * @return
     */
    public boolean insertOrUpdate(FundFeatureDO instance) {
        boolean result = false;
        if (null == instance) {
            logger.error("fundFeatureDO is null");
            return result;
        }

        FundFeatureDO fundFeatureDO = null;
        try {
            fundFeatureDAO.queryFundFeatureByFeature(instance.getCode(), instance.getFeature());
        } catch (Exception e) {
            fundFeatureDAO.deleteFundFeatureByFeature(instance.getCode(), instance.getFeature());
        }

        if (null != fundFeatureDO) {
            instance.setId(fundFeatureDO.getId());
            int num = fundFeatureDAO.updateFundFeature(instance);
            if (num > -1) {
                result = true;
            }
        } else {
            Long id = fundFeatureDAO.insertFundFeature(instance);
            if (null != id && id.longValue() > 0) {
                result = true;
            }
        }

        if (!result) {
            logger.error("feature insertOrUpdate failed " + JSONObject.toJSONString(instance));
        }
        return result;
    }

    /**
     * @param instance
     * @return
     */
    public boolean insertOrUpdate(FundIndexDO instance) {
        boolean result = false;
        if (null == instance) {
            logger.error("fundIndexDO is null");
            return result;
        }

        FundIndexDO fundIndexDO = null;
        try {
            fundIndexDO = fundIndexDAO.queryFundIndexByFeature(instance.getCode(), instance.getFeature());
        } catch (Exception e) {
            fundIndexDAO.deleteFundIndexByFeature(instance.getCode(), instance.getFeature());
        }

        if (null != fundIndexDO) {
            instance.setId(fundIndexDO.getId());
            int num = fundIndexDAO.updateFundIndex(instance);
            if (num > -1) {
                result = true;
            }
        } else {
            Long id = fundIndexDAO.insertFundIndex(instance);
            if (null != id && id.longValue() > 0) {
                result = true;
            }
        }

        if (!result) {
            logger.error("index insertOrUpdate failed " + JSONObject.toJSONString(instance));
        }
        return result;
    }

    /**
     * @param instance
     * @return
     */
    public boolean insertOrUpdate(FundStyleDO instance) {
        boolean result = false;
        if (null == instance) {
            logger.error("fundStyleDO is null");
            return result;
        }

        FundStyleDO fundStyleDO = null;
        try {
            fundStyleDAO.queryFundStyleByStyle(instance.getCode(), instance.getStyle(), instance.getQuarter());
        } catch (Exception e) {
            fundStyleDAO.deleteFundStyleByStyle(instance.getCode(), instance.getStyle(), instance.getQuarter());
        }

        if (null != fundStyleDO) {
            instance.setId(fundStyleDO.getId());
            int num = fundStyleDAO.updateFundStyle(instance);
            if (num > -1) {
                result = true;
            }
        } else {
            Long id = fundStyleDAO.insertFundStyle(instance);
            if (null != id && id.longValue() > 0) {
                result = true;
            }
        }

        if (!result) {
            logger.error("style insertOrUpdate failed " + JSONObject.toJSONString(instance));
        }
        return result;
    }

    /**
     * @param code
     * @return
     */
    public Map<String, Object> getFundFeature(String code) {
        if (StringUtils.isBlank(code)) {
            logger.error("fundCode is wrong");
            return null;
        }

        String url = getFundFeatureUrl(code);
        logger.info(code + " fundFeature url is " + url);

        Result<String> response = HttpUtil.get(url);
        if (!response.isSuccess() || StringUtils.isBlank(response.getValue())) {
            logger.error(url + " status is " + response.getErrCode() + " resposne is " + response.getValue());
            return null;
        }

        List<FundFeatureDO> fundFeatureDOList = new ArrayList<>();
        List<FundIndexDO> fundIndexDOList = new ArrayList<>();
        List<FundStyleDO> fundStyleDOList = new ArrayList<>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("feature", fundFeatureDOList);
        map.put("index", fundIndexDOList);
        map.put("style", fundStyleDOList);

        try {
            Document doc = Jsoup.parse(response.getValue());
            if (null != doc) {
                Elements tr = doc.select("table[class='fxtb']").eq(0).select("tbody tr");
                for (int i = 1; i < tr.size(); i++) {
                    try {
                        Elements td = tr.get(i).select("td");
                        FundFeatureDO fundFeatureDO = new FundFeatureDO();
                        fundFeatureDO.setCode(code);
                        fundFeatureDO.setFeature(getString(td.get(0).text()));
                        fundFeatureDO.setYear1(getDoublePercent(td.get(1).text(), 2));
                        fundFeatureDO.setYear2(getDoublePercent(td.get(2).text(), 2));
                        fundFeatureDO.setYear3(getDoublePercent(td.get(3).text(), 2));
                        fundFeatureDOList.add(fundFeatureDO);
                    } catch (Exception e) {
                        logger.error("feature|" + code + "|" + tr.get(i).toString() + " exception ", e);
                    }
                }

                if (doc.select("table[class='fxtb']").size() > 1) {
                    Elements tr2 = doc.select("table[class='fxtb']").eq(1).select("tbody tr");
                    for (int i = 1; i < tr2.size(); i++) {
                        try {
                            Elements td = tr2.get(i).select("td");
                            FundIndexDO fundIndexDO = new FundIndexDO();
                            fundIndexDO.setCode(code);
                            fundIndexDO.setFeature(getString(td.get(0).text()));
                            fundIndexDO.setIndexValue(getDouble(td.get(1).text()));
                            fundIndexDO.setAvgValue(getDouble(td.get(2).text()));
                            fundIndexDOList.add(fundIndexDO);
                        } catch (Exception e) {
                            logger.error("index|" + code + "|" + tr.get(i).toString() + " exception ", e);
                        }
                    }
                }

                String year = getCurrentYear();
                Elements style = doc.select("table[class='fgtb']");
                if (null != style) {
                    Elements tr3 = style.select("tbody tr");
                    for (int i = 1; i < tr3.size(); i++) {
                        Elements td = tr3.get(i).select("td");
                        String qr = getQuarter(td.get(0).text());
                        if (!qr.contains(year) && isUpdateIncr) {
                            continue;
                        }

                        FundStyleDO fundStyleDO = new FundStyleDO();
                        fundStyleDO.setCode(code);
                        fundStyleDO.setQuarter(qr);
                        fundStyleDO.setStyle(getString(td.get(1).text()));
                        fundStyleDOList.add(fundStyleDO);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(code + " getFundFeature exception ", e);
        }
        return map;
    }

    /**
     * @param code
     * @return
     */
    private String getFundFeatureUrl(String code) {
        return String.format(FUND_FEATURE_URL, code);
    }

    /**
     * @param value
     * @return
     */
    private BigDecimal getDouble(String value) {
        return value.contains("%") ? getDoublePercent(value, 2) : getDouble(value, 2);
    }

    /**
     * @param time
     * @return
     */
    private String getQuarter(String time) {
        if (StringUtils.isBlank(time)) {
            return null;
        }
        return time.replaceAll("\\D*", "");
    }

    public FundFeatureDAO getFundFeatureDAO() {
        return fundFeatureDAO;
    }

    public void setFundFeatureDAO(FundFeatureDAO fundFeatureDAO) {
        this.fundFeatureDAO = fundFeatureDAO;
    }

    public FundIndexDAO getFundIndexDAO() {
        return fundIndexDAO;
    }

    public void setFundIndexDAO(FundIndexDAO fundIndexDAO) {
        this.fundIndexDAO = fundIndexDAO;
    }

    public FundStyleDAO getFundStyleDAO() {
        return fundStyleDAO;
    }

    public void setFundStyleDAO(FundStyleDAO fundStyleDAO) {
        this.fundStyleDAO = fundStyleDAO;
    }
}
