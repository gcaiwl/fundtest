package com.longxi.data.service;

import com.longxi.data.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author longxi.cwl
 * @date 2017/11/14
 */
public class DetailService extends AbstractService {
    private static Logger logger = LoggerFactory.getLogger(DetailService.class);

    /**
     * @param code
     */
    public String queryFundDetail(String code) {
        if (StringUtils.isBlank(code)) {
            logger.error("queryFundDetail code is wrong");
            return null;
        }

        String response = HttpUtils.get(getFundDetailUrl(code));
        if (StringUtils.isBlank(response)) {
            logger.error("queryFundDetail is empty");
            return null;
        }
        return response;
    }
}
