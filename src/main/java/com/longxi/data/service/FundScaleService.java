package com.longxi.data.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;

import com.longxi.data.dao.FundScaleDAO;
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
public class FundScaleService extends FundService {
    private static Logger logger = LoggerFactory.getLogger(FundScaleService.class);

    private static String FUND_SCALE_URL = "http://fund.eastmoney.com/f10/FundArchivesDatas.aspx?type=gmbd&code=%s";

    @Resource
    private FundScaleDAO fundScaleDAO;

    /**
     * @param code
     * @return
     */
    @Override
    public boolean insertOrUpdate(String code) {
        boolean result = true;
        List<FundScaleDO> fundScaleDOList = getFundScale(code);
        if (null != fundScaleDOList) {
            for (int i = 0; i < fundScaleDOList.size(); i++) {
                result = result && insertOrUpdate(fundScaleDOList.get(i));
            }
        }
        return result;
    }

    /**
     * @param instance
     * @return
     */
    public boolean insertOrUpdate(FundScaleDO instance) {
        boolean result = false;
        if (null == instance) {
            logger.error("fundScaleDO is null");
            return result;
        }

        FundScaleDO fundScaleDO = fundScaleDAO.queryFundScaleByPublishTime(instance.getCode(), instance.getPublishTime());
        if (null != fundScaleDO) {
            instance.setId(fundScaleDO.getId());
            int num = fundScaleDAO.updateFundScale(instance);
            if (num > -1) {
                result = true;
            }
        } else {
            Long id = fundScaleDAO.insertFundScale(instance);
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
    public List<FundScaleDO> getFundScale(String code) {
        if (StringUtils.isBlank(code)) {
            logger.error("code is wrong");
            return null;
        }

        String url = getFundScaleUrl(code);
        logger.info(code + " fundScale url is " + url);

        Result<String> response = HttpUtils.get(url);
        if (!response.isSuccess() || StringUtils.isBlank(response.getValue())) {
            logger.error(url + " status is " + response.getErrCode() + " resposne is " + response.getValue());
            return null;
        }

        List<FundScaleDO> fundScaleDOList = new ArrayList<>();
        try {
            String result = response.getValue().replaceAll("var gmbd_apidata=", "").replaceAll(";", "")
                .replaceAll("content:", "\"content\":")
                .replaceAll("summary:", "\"summary\":");
            JSONObject resultJson = JSONObject.parseObject(result);
            Document doc = Jsoup.parse(resultJson.getString("content"));
            if (null != doc) {
                Elements tr = doc.select("table[class='w782 comm gmbd'] tbody tr");
                for (int i = 0; i < tr.size(); i++) {
                    try {
                        Elements td = tr.get(i).select("td");
                        if (td.size() > 1) {
                            FundScaleDO fundScaleDO = new FundScaleDO();
                            fundScaleDO.setCode(code);
                            fundScaleDO.setPublishTime(getDate(td.get(0).text()));
                            fundScaleDO.setPurchase(getDouble(td.get(1).text(), 2));
                            fundScaleDO.setRedeem(getDouble(td.get(2).text(), 2));
                            fundScaleDO.setShare(getDouble(td.get(3).text(), 2));
                            fundScaleDO.setAssets(getDouble(td.get(4).text(), 2));
                            fundScaleDO.setAssetsRate(getDoublePercent(td.get(5).text(), 2));
                            fundScaleDOList.add(fundScaleDO);
                        }
                    } catch (Exception e) {
                        logger.error(code + "|" + tr.get(i).toString() + " exception ", e);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(code + " getFundScale exception ", e);
        }
        return fundScaleDOList;
    }

    /**
     *
     * @param code
     * @return
     */
    private String getFundScaleUrl(String code) {
        return String.format(FUND_SCALE_URL, code);
    }

    public FundScaleDAO getFundScaleDAO() {
        return fundScaleDAO;
    }

    public void setFundScaleDAO(FundScaleDAO fundScaleDAO) {
        this.fundScaleDAO = fundScaleDAO;
    }
}
