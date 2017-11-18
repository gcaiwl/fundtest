package com.longxi.data.service;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;

import com.longxi.data.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author longxi.cwl
 * @date 2017/11/14
 */
public class ListService extends AbstractService {
    private static Logger logger = LoggerFactory.getLogger(ListService.class);

    /**
     * @return
     */
    public List<String> queryFundList() {
        List<String> fundList = new ArrayList<String>();
        String response = HttpUtils.get(getFundListUrl());
        if (StringUtils.isBlank(response)) {
            logger.error("queryFundList return is null or empty");
            return fundList;
        }

        try {
            String funds = response.replaceFirst("var r = ", "").replaceAll(";", "").trim();
            JSONArray fundArray = JSONArray.parseArray(funds);
            for (int i = 0; i < fundArray.size(); i++) {
                fundList.add(fundArray.getJSONArray(i).getString(0));
            }
        } catch (Exception e) {
            logger.error("queryFundList exception ", e);
        }
        return fundList;
    }
}
