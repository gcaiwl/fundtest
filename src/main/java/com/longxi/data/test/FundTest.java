package com.longxi.data.test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;

import com.longxi.data.dao.FundTurnoverDAO;
import com.longxi.data.dao.impl.FundTurnoverDAOImpl;
import com.longxi.data.obj.FundTurnoverDO;
import com.longxi.data.service.FundBaseService;
import com.longxi.data.service.FundBondPositionService;
import com.longxi.data.service.FundConfigService;
import com.longxi.data.service.FundFeatureService;
import com.longxi.data.service.FundHolderService;
import com.longxi.data.service.FundIndustryService;
import com.longxi.data.service.FundManagerService;
import com.longxi.data.service.FundScaleService;
import com.longxi.data.service.FundSharesPositionService;
import com.longxi.data.service.FundTurnoverService;
import com.longxi.data.service.FundValueService;
import com.longxi.data.utils.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author longxi.cwl
 * @date 2017/11/15
 */
public class FundTest {
    private static Logger logger = LoggerFactory.getLogger(FundTest.class);

    public static void main(String[] args) {
        //String result = HttpUtils.get("http://www.baidu.com");
        //System.out.println(result);
        //DBUtils.insert("create table test2(id int not null auto_increment, name varchar(20), primary key (id));");

        //FundListService fundListService = new FundListService();
        //List<String> fundIds = fundListService.queryFundList();
        //if (null != fundIds) {
        //    FundDetailService fundDetailService = new FundDetailService();
        //    fundDetailService.queryFundDetail(fundIds.get(0));
        //}

        //FundDetailService fundDetailService = new FundDetailService();
        //fundDetailService.queryFundDetail("169101");

        //FundValueService fundValueService = new FundValueService();
        //fundValueService.queryFundValue("169101");

        //String result = HttpUtils.get("http://fund.eastmoney.com/f10/jbgk_169101.html");
        //Document doc = Jsoup.parse(result);
        //logger.info(result);
        //System.out.println(result);
        //logger.info(result);

        //Elements elem = doc.select("table[class='info w790'] td");
        //logger.info(elem.get(8).text().toString());

        //String t1 = "单日投资上限10000元";
        //t1 = t1.replaceAll("\\D*(\\d*)\\D*", "$1");
        //System.out.println(t1); http://fund.eastmoney.com/f10/ccmx_160420.html


    //    单日投资上限10000元

        //FundTurnoverDO fundTurnoverDO = new FundTurnoverDO();
        //fundTurnoverDO.setCode("123");
        //fundTurnoverDO.setTurnRate(new BigDecimal(123.61));
        //
        //FundTurnoverDAOImpl fundTurnoverDAO = new FundTurnoverDAOImpl();
        ////fundTurnoverDAO.insertFundTurnover(fundTurnoverDO);
        //
        //FundTurnoverDO fundTurnoverDO = fundTurnoverDAO.queryFundTurnoverById(1L);
        //logger.error(JSONObject.toJSONString(fundTurnoverDO));

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-fund.xml");
        applicationContext.start();
        logger.info("context is start... ");

        FundBaseService fundBaseService = (FundBaseService)applicationContext.getBean("fundBaseService");
        FundBondPositionService fundBondPositionService = (FundBondPositionService)applicationContext.getBean("fundBondPositionService");
        FundConfigService fundConfigService = (FundConfigService)applicationContext.getBean("fundConfigService");
        FundFeatureService fundFeatureService = (FundFeatureService)applicationContext.getBean("fundFeatureService");
        FundHolderService fundHolderService = (FundHolderService)applicationContext.getBean("fundHolderService");
        FundIndustryService fundIndustryService = (FundIndustryService)applicationContext.getBean("fundIndustryService");
        FundManagerService fundManagerService = (FundManagerService)applicationContext.getBean("fundManagerService");
        FundScaleService fundScaleService = (FundScaleService)applicationContext.getBean("fundScaleService");
        FundSharesPositionService fundSharesPositionService = (FundSharesPositionService)applicationContext.getBean("fundSharesPositionService");
        FundTurnoverService fundTurnoverService = (FundTurnoverService)applicationContext.getBean("fundTurnoverService");
        FundValueService fundValueService = (FundValueService)applicationContext.getBean("fundValueService");

        String code = "169101";
        //logger.info("fundBase insertOrUpdate...");
        //fundBaseService.insertOrUpdate(code);
        //logger.info("fundValue insertOrUpdate...");
        //fundValueService.insertOrUpdate(code);
        //
        //logger.info("fundManager insertOrUpdate...");
        //fundManagerService.insertOrUpdate(code);
        //logger.info("fundFeature insertOrUpdate...");
        //fundFeatureService.insertOrUpdate(code);
        //
        //logger.info("fundShares insertOrUpdate...");
        //fundSharesPositionService.insertOrUpdate(code);
        //logger.info("fundBond insertOrUpdate...");
        //fundBondPositionService.insertOrUpdate(code);
        //
        //logger.info("fundConfig insertOrUpdate...");
        //fundConfigService.insertOrUpdate(code);
        //logger.info("fundHolder insertOrUpdate...");
        //fundHolderService.insertOrUpdate(code);
        //logger.info("fundScale insertOrUpdate...");
        //fundScaleService.insertOrUpdate(code);
        logger.info("fundTurnover insertOrUpdate...");
        fundTurnoverService.insertOrUpdate(code);
        logger.info("fundIndustry insertOrUpdate...");
        fundIndustryService.insertOrUpdate(code);
    }
}
