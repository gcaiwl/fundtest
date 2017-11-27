package com.longxi.data.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONArray;

import com.longxi.data.dao.FundRecordDAO;
import com.longxi.data.obj.FundRecordDO;
import com.longxi.data.obj.Result;
import com.longxi.data.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author longxi.cwl
 * @date 2017/11/14
 */
public class FundListService extends FundService {
    private static Logger logger = LoggerFactory.getLogger(FundListService.class);

    private final String FUND_LIST_URL = "http://fund.eastmoney.com/js/fundcode_search.js";

    @Resource
    private FundRecordDAO fundRecordDAO;

    /**
     * @return
     */
    public List<String> getFundList() {
        List<String> fundList = new ArrayList<String>();

        List<FundRecordDO> fundRecordDOList = fundRecordDAO.queryFundRecordByStatus(1);
        if (null != fundRecordDOList && !fundRecordDOList.isEmpty()) {
            for (int i = 0; i < fundRecordDOList.size(); i++) {
                fundList.add(fundRecordDOList.get(i).getCode());
            }
        } else {
            Result<String> response = HttpUtils.get(getFundListUrl());
            if (!response.isSuccess() || StringUtils.isBlank(response.getValue())) {
                logger.error("getFundList return is null or empty");
                return fundList;
            }

            try {
                String funds = response.getValue().replaceFirst("var r = ", "").replaceAll(";", "").trim();
                JSONArray fundArray = JSONArray.parseArray(funds);
                for (int i = 0; i < fundArray.size(); i++) {
                    // 后端基金过滤掉
                    if (fundArray.getJSONArray(i).getString(2).contains("(后端)")) {
                        continue;
                    }

                    String code = fundArray.getJSONArray(i).getString(0);
                    fundList.add(code);

                    FundRecordDO fundRecordDO = new FundRecordDO();
                    fundRecordDO.setCode(code);
                    fundRecordDO.setStatus(1);
                    fundRecordDAO.insertFundRecord(fundRecordDO);
                }
            } catch (Exception e) {
                logger.error("getFundList exception ", e);
            }
        }
        return fundList;
    }

    /**
     * @param code
     * @param result
     */
    public void updateFundRecord(String code, boolean result) {
        int status = result ? 2 : 1;
        fundRecordDAO.updateFundRecordByCode(code, 2, status);
    }

    @Override
    public boolean insertOrUpdate(String code) {
        return false;
    }

    /**
     * @return
     */
    private String getFundListUrl() {
        return FUND_LIST_URL;
    }

    public FundRecordDAO getFundRecordDAO() {
        return fundRecordDAO;
    }

    public void setFundRecordDAO(FundRecordDAO fundRecordDAO) {
        this.fundRecordDAO = fundRecordDAO;
    }
}
