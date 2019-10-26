package com.longxi.data.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import javax.annotation.Resource;

import com.longxi.data.utils.ThreadUtil;
import org.apache.commons.lang3.StringUtils;
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
            String code = codeList.get(i);
            boolean result = fetchData(code);
            if (!result) {
                failList.add(code);
            }
            fundListService.updateFundRecord(code, result);
        }
        logger.info("enter retry failList size is " + failList.size());

        // fetchData fail retry
        Iterator<String> iterator = failList.iterator();
        while (iterator.hasNext()) {
            String code = iterator.next();
            boolean result = fetchData(code);
            fundListService.updateFundRecord(code, result);
            if (result) {
                iterator.remove();
            }
        }
        logger.info("over retry failList size is " + failList.size());
    }

    /**
     * @param code
     * @return
     */
    public boolean run(String code) {
        if (null == fundListService) {
            logger.error("fundListService is null");
            return false;
        }

        if (StringUtils.isBlank(code)) {
            logger.error("code is blank");
            return false;
        }
        return fetchData(code);
    }

    /**
     * @param code
     * @param service
     */
    public void debug(String code, IFundService service) {
        boolean result = (null == service) ? fetchData(code) : service.insertOrUpdate(code);
        logger.info("debug result is " + result);
    }

    /**
     * @param code
     * @return
     */
    private boolean fetchData(String code) {
        boolean result = true;
        try {
            List<FutureTask<Object>> futureTaskList = new ArrayList<>();
            futureTaskList.add(fetchFundTask(fundBaseService, code));
            futureTaskList.add(fetchFundTask(fundValueService, code));
            futureTaskList.add(fetchFundTask(fundManagerService, code));
            futureTaskList.add(fetchFundTask(fundFeatureService, code));
            futureTaskList.add(fetchFundTask(fundSharesPositionService, code));
            futureTaskList.add(fetchFundTask(fundBondPositionService, code));
            futureTaskList.add(fetchFundTask(fundConfigService, code));
            futureTaskList.add(fetchFundTask(fundHolderService, code));
            futureTaskList.add(fetchFundTask(fundScaleService, code));
            futureTaskList.add(fetchFundTask(fundTurnoverService, code));
            futureTaskList.add(fetchFundTask(fundIndustryService, code));

            for (FutureTask<Object> futureTask : futureTaskList) {
                result = result && (Boolean)futureTask.get();
            }

            int rand = (int)(Math.random() * 200) + 100;
            Thread.sleep(rand);
        } catch (Exception e) {
            result = false;
            logger.error(code + " fetchData exception ", e);
        }
        return result;
    }

    /**
     * @param service
     * @param code
     * @return
     */
    private FutureTask<Object> fetchFundTask(IFundService service, String code) {
        FutureTask<Object> futureTask = new FutureTask<Object>(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                boolean result = false;
                try {
                    result = service.insertOrUpdate(code);
                } catch (Exception e) {
                    logger.error(code + "|" + fetchFundServiceType(service) + " fund task exception ", e);
                }
                return result;
            }
        });
        ThreadUtil.submit(futureTask);
        return futureTask;
    }

    /**
     * @param service
     * @return
     */
    private String fetchFundServiceType(IFundService service) {
        String fundServiceType = "";
        if (service instanceof FundBaseService) {
            fundServiceType = "fundBaseService";
        } else if (service instanceof FundValueService) {
            fundServiceType = "fundValueService";
        } else if (service instanceof FundManagerService) {
            fundServiceType = "fundManagerService";
        } else if (service instanceof FundFeatureService) {
            fundServiceType = "fundFeatureService";
        } else if (service instanceof FundSharesPositionService) {
            fundServiceType = "fundSharesPositionService";
        } else if (service instanceof FundBondPositionService) {
            fundServiceType = "fundBondPositionService";
        } else if (service instanceof FundConfigService) {
            fundServiceType = "fundConfigService";
        } else if (service instanceof FundHolderService) {
            fundServiceType = "fundHolderService";
        } else if (service instanceof FundScaleService) {
            fundServiceType = "fundScaleService";
        } else if (service instanceof FundTurnoverService) {
            fundServiceType = "fundTurnoverService";
        } else if (service instanceof FundIndustryService) {
            fundServiceType = "fundIndustryService";
        }
        return fundServiceType;
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
