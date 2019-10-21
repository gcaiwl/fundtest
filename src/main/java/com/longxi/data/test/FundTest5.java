package com.longxi.data.test;

import com.longxi.data.service.FundBuildService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author longxi.cwl
 * @date 2017/11/28
 */
public class FundTest5 {
    private static Logger logger = LoggerFactory.getLogger(FundTest5.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-fund.xml");
        applicationContext.start();
        logger.info("context is start... ");

        FundBuildService fundBuildService = (FundBuildService)applicationContext.getBean("fundBuildService");
        if (null == fundBuildService) {
            logger.error("fundBuildService is null");
            return;
        }
        fundBuildService.run();
        System.exit(0);
    }
}
