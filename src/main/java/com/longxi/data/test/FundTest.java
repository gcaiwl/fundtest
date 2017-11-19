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
        fundBaseService.insertOrUpdate("169101");
    }
}
