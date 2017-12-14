package com.longxi.data.test;

import com.longxi.data.dao.FundRecordDAO;
import com.longxi.data.service.FundBaseService;
import com.longxi.data.service.FundConfigService;
import com.longxi.data.service.FundDetailService;
import com.longxi.data.service.FundFeatureService;
import com.longxi.data.service.FundScaleService;
import com.longxi.data.service.FundValueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author longxi.cwl
 * @date 2017/11/24
 */
public class FundTest2 {
    private static Logger logger = LoggerFactory.getLogger(FundTest.class);

    public static void main(String[] args) {
        String code = "169101";

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-fund.xml");
        applicationContext.start();
        logger.info("context is start... ");

        FundDetailService fundDetailService = (FundDetailService)applicationContext.getBean("fundDetailService");
        if (null == fundDetailService) {
            logger.error("fundDetailService is null");
            return;
        }

        FundFeatureService fundFeatureService = (FundFeatureService)applicationContext.getBean("fundFeatureService");

        fundDetailService.debug(code, fundFeatureService);
    }
}