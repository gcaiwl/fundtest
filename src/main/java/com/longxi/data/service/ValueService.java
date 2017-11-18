package com.longxi.data.service;

import com.longxi.data.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author longxi.cwl
 * @date 2017/11/14
 */
public class ValueService extends AbstractService {
    private static Logger logger = LoggerFactory.getLogger(ValueService.class);

    /**
     * @param code
     */
    @Deprecated
    public String queryFundValue(String code) {
        if (StringUtils.isBlank(code)) {
            logger.error("queryFundValue code is wrong");
            return null;
        }

        String response = HttpUtils.get(getFundValueUrl(code));
        if (StringUtils.isBlank(response)) {
            logger.error("queryFundValue is empty");
            return null;
        }
        return response;
    }
}
