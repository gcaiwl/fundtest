package com.longxi.data.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author longxi.cwl
 * @date 2017/11/14
 */
public class FundDetailService {
    private static Logger logger = LoggerFactory.getLogger(FundDetailService.class);

    @Resource
    private FundBaseService fundBaseService;
    @Resource
    private FundBondPositionService fundBondPositionService;
    @Resource
    private FundConfigService fundConfigService;
    @Resource
    private FundFeatureService fundFeatureService;
    @Resource
    private FundHolderService fundHolderService;
    @Resource
    private FundIndustryService fundIndustryService;
    @Resource
    private FundListService fundListService;
    @Resource
    private FundManagerService fundManagerService;
    @Resource
    private FundScaleService fundScaleService;
    @Resource
    private FundSharesPositionService fundSharesPositionService;
    @Resource
    private FundTurnoverService fundTurnoverService;
    @Resource
    private FundValueService fundValueService;

    private static final int FETCH_EACH_DATA_SLEEP = 1000;
    private static final int FETCH_EACH_CODE_SLEEP = 2000;

    /**
     *
     */
    public void run() {
        if (null == fundListService) {
            logger.error("fundListService is null");
            return;
        }

        List<String> codeList = fundListService.getFundList();
        if (null == codeList || codeList.isEmpty()) {
            logger.error("codeList is null or empty");
            return;
        }
        logger.info("codeList size is " + codeList.size());

        // fetchData
        List<String> failList = new ArrayList<>();
        for (int i = 0; i < codeList.size(); i++) {
            boolean result = fetchData(codeList.get(i));
            if (!result) {
                failList.add(codeList.get(i));
            }
        }

        // fetchData fail retry
        for (int i = 0; i < failList.size(); i++) {
            fetchData(failList.get(i));
        }
    }

    /**
     * @param code
     * @return
     */
    private boolean fetchData(String code) {
        boolean result = true;
        try {
            // 更新基础数据
            result = result && fundBaseService.insertOrUpdate(code);
            Thread.sleep(FETCH_EACH_DATA_SLEEP);

            // 更新净值数据
            result = result && fundValueService.insertOrUpdate(code);
            Thread.sleep(FETCH_EACH_DATA_SLEEP);

            // 更新经纪人数据
            result = result && fundManagerService.insertOrUpdate(code);
            Thread.sleep(FETCH_EACH_DATA_SLEEP);

            // 更新特征数据
            result = result && fundFeatureService.insertOrUpdate(code);
            Thread.sleep(FETCH_EACH_DATA_SLEEP);

            // 更新股票持仓数据
            result = result && fundSharesPositionService.insertOrUpdate(code);
            Thread.sleep(FETCH_EACH_DATA_SLEEP);

            // 更新债券持仓数据
            result = result && fundBondPositionService.insertOrUpdate(code);
            Thread.sleep(FETCH_EACH_DATA_SLEEP);

            // 更新资产配置数据
            result = result && fundConfigService.insertOrUpdate(code);
            Thread.sleep(FETCH_EACH_DATA_SLEEP);

            // 更新持有人结构数据
            result = result && fundHolderService.insertOrUpdate(code);
            Thread.sleep(FETCH_EACH_DATA_SLEEP);

            // 更新规模数据
            result = result && fundScaleService.insertOrUpdate(code);
            Thread.sleep(FETCH_EACH_DATA_SLEEP);

            // 更新换手率数据
            result = result && fundTurnoverService.insertOrUpdate(code);
            Thread.sleep(FETCH_EACH_DATA_SLEEP);

            // 更新行业数据
            result = result && fundIndustryService.insertOrUpdate(code);
            Thread.sleep(FETCH_EACH_CODE_SLEEP);
        } catch (Exception e) {
            result = false;
            logger.error(code + " fetchData exception ", e);
        }
        return result;
    }

    public FundBaseService getFundBaseService() {
        return fundBaseService;
    }

    public void setFundBaseService(FundBaseService fundBaseService) {
        this.fundBaseService = fundBaseService;
    }

    public FundBondPositionService getFundBondPositionService() {
        return fundBondPositionService;
    }

    public void setFundBondPositionService(FundBondPositionService fundBondPositionService) {
        this.fundBondPositionService = fundBondPositionService;
    }

    public FundConfigService getFundConfigService() {
        return fundConfigService;
    }

    public void setFundConfigService(FundConfigService fundConfigService) {
        this.fundConfigService = fundConfigService;
    }

    public FundFeatureService getFundFeatureService() {
        return fundFeatureService;
    }

    public void setFundFeatureService(FundFeatureService fundFeatureService) {
        this.fundFeatureService = fundFeatureService;
    }

    public FundHolderService getFundHolderService() {
        return fundHolderService;
    }

    public void setFundHolderService(FundHolderService fundHolderService) {
        this.fundHolderService = fundHolderService;
    }

    public FundIndustryService getFundIndustryService() {
        return fundIndustryService;
    }

    public void setFundIndustryService(FundIndustryService fundIndustryService) {
        this.fundIndustryService = fundIndustryService;
    }

    public FundListService getFundListService() {
        return fundListService;
    }

    public void setFundListService(FundListService fundListService) {
        this.fundListService = fundListService;
    }

    public FundManagerService getFundManagerService() {
        return fundManagerService;
    }

    public void setFundManagerService(FundManagerService fundManagerService) {
        this.fundManagerService = fundManagerService;
    }

    public FundScaleService getFundScaleService() {
        return fundScaleService;
    }

    public void setFundScaleService(FundScaleService fundScaleService) {
        this.fundScaleService = fundScaleService;
    }

    public FundSharesPositionService getFundSharesPositionService() {
        return fundSharesPositionService;
    }

    public void setFundSharesPositionService(FundSharesPositionService fundSharesPositionService) {
        this.fundSharesPositionService = fundSharesPositionService;
    }

    public FundTurnoverService getFundTurnoverService() {
        return fundTurnoverService;
    }

    public void setFundTurnoverService(FundTurnoverService fundTurnoverService) {
        this.fundTurnoverService = fundTurnoverService;
    }

    public FundValueService getFundValueService() {
        return fundValueService;
    }

    public void setFundValueService(FundValueService fundValueService) {
        this.fundValueService = fundValueService;
    }
}
