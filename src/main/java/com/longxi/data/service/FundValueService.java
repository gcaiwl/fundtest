package com.longxi.data.service;

import javax.annotation.Resource;

import com.longxi.data.dao.FundValueDAO;
import com.longxi.data.obj.FundValueDO;
import com.longxi.data.obj.Result;
import com.longxi.data.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author longxi.cwl
 * @date 2017/11/14
 */
public class FundValueService extends FundService {
    private static Logger logger = LoggerFactory.getLogger(FundValueService.class);

    private final String FUND_VALUE_URL = "http://fundgz.1234567.com.cn/js/%s.js";

    @Resource
    private FundValueDAO fundValuveDAO;

    /**
     *
     * @param code
     */
    public void insertOrUpdate(String code) {

    }

    /**
     *
     * @param fundValueDO
     */
    public void insertOrUpdate(FundValueDO fundValueDO) {

    }

    /**
     * @param code
     */
    @Deprecated
    public String queryFundValue(String code) {
        if (StringUtils.isBlank(code)) {
            logger.error("queryFundValue code is wrong");
            return null;
        }

        Result<String> response = HttpUtils.get(getFundValueUrl(code));
        if (StringUtils.isBlank(response.getValue())) {
            logger.error("queryFundValue is empty");
            return null;
        }
        return response.getValue();
    }

    /**
     * @param code
     * @return
     */
    private String getFundValueUrl(String code) {
        return String.format(FUND_VALUE_URL, code);
    }
}
