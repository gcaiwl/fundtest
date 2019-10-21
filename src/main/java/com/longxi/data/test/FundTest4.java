package com.longxi.data.test;

import com.longxi.data.obj.NodeFund;
import com.longxi.data.obj.RelationManagerFund;
import com.longxi.data.service.FundGraphService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author longxi.cwl
 * @date 2017/11/28
 */
public class FundTest4 {
    private static Logger logger = LoggerFactory.getLogger(FundTest4.class);

    public static void main(String[] args) {
        NodeFund nodeFund1 = new NodeFund();
        nodeFund1.setCode("123123");
        nodeFund1.setName("fund name");
        nodeFund1.setScale(123456.00);
        nodeFund1.setType("测试type");
        nodeFund1.setShare(444.99);

        NodeFund nodeFund2 = new NodeFund();
        nodeFund2.setCode("123123444");
        nodeFund2.setName("fund name");
        nodeFund2.setScale(123456.00);
        nodeFund2.setType("测试type");
        nodeFund2.setShare(444.99);

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-fund.xml");
        applicationContext.start();
        logger.info("context is start... ");

        FundGraphService fundGraphService = (FundGraphService)applicationContext.getBean("fundGraphService");
        Integer nodeId1 = fundGraphService.saveNode(nodeFund1);
        Integer nodeId2 = fundGraphService.saveNode(nodeFund2);

        System.out.println(nodeId1);
        System.out.println(nodeId2);

        RelationManagerFund relationManagerFund = new RelationManagerFund();
        relationManagerFund.setFromId(nodeId1.intValue());
        relationManagerFund.setToId(nodeId2.intValue());
        relationManagerFund.setRedound(444.55);
        relationManagerFund.setStatus(1);

        fundGraphService.saveRelationship(relationManagerFund);
    }
}
