package com.longxi.data.service;

import com.longxi.data.obj.Result;
import com.longxi.data.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author longxi.cwl
 * @date 2017/11/14
 */
public class FundDetailService extends FundService {
    private static Logger logger = LoggerFactory.getLogger(FundDetailService.class);

    private final String FUND_DETAIL_URL = "http://service.eastmoney.com/pingzhongdata/%s.js";

    /**
     * @param code
     */
    public String getFundDetail(String code) {
        if (StringUtils.isBlank(code)) {
            logger.error("getFundDetail code is wrong");
            return null;
        }

        Result<String> response = HttpUtils.get(getFundDetailUrl(code));
        if (StringUtils.isBlank(response.getValue())) {
            logger.error("getFundDetail is empty");
            return null;
        }
        return response.getValue();
    }

    /**
     * @param code
     * @return
     */
    private String getFundDetailUrl(String code) {
        return String.format(FUND_DETAIL_URL, code);
    }
}
