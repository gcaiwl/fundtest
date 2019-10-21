package com.longxi.data.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.longxi.data.dao.FundBaseDAO;
import com.longxi.data.dao.FundBondPositionDAO;
import com.longxi.data.dao.FundConfigDAO;
import com.longxi.data.dao.FundHolderDAO;
import com.longxi.data.dao.FundIndustryDAO;
import com.longxi.data.dao.FundManagerDAO;
import com.longxi.data.dao.FundScaleDAO;
import com.longxi.data.dao.FundSharesPositionDAO;
import com.longxi.data.dao.FundStyleDAO;
import com.longxi.data.dao.FundTurnoverDAO;
import com.longxi.data.obj.FundBaseDO;
import com.longxi.data.obj.FundBondPositionDO;
import com.longxi.data.obj.FundConfigDO;
import com.longxi.data.obj.FundHolderDO;
import com.longxi.data.obj.FundIndustryDO;
import com.longxi.data.obj.FundManagerDO;
import com.longxi.data.obj.FundScaleDO;
import com.longxi.data.obj.FundSharesPositionDO;
import com.longxi.data.obj.FundStyleDO;
import com.longxi.data.obj.FundTurnoverDO;
import com.longxi.data.obj.NodeBond;
import com.longxi.data.obj.NodeCompany;
import com.longxi.data.obj.NodeFund;
import com.longxi.data.obj.NodeIndustry;
import com.longxi.data.obj.NodeManager;
import com.longxi.data.obj.NodeShares;
import com.longxi.data.obj.RelationCompanyFund;
import com.longxi.data.obj.RelationCompanyManager;
import com.longxi.data.obj.RelationFundBond;
import com.longxi.data.obj.RelationFundIndustry;
import com.longxi.data.obj.RelationFundShares;
import com.longxi.data.obj.RelationManagerFund;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author longxi.cwl
 * @date 2019/10/17
 */
public class FundBuildService {
    private static Logger logger = LoggerFactory.getLogger(FundBaseService.class);

    @Resource
    private FundBaseDAO fundBaseDAO;
    @Resource
    private FundListService fundListService;
    @Resource
    private FundGraphService fundGraphService;
    @Resource
    private FundConfigDAO fundConfigDAO;
    @Resource
    private FundScaleDAO fundScaleDAO;
    @Resource
    private FundHolderDAO fundHolderDAO;
    @Resource
    private FundTurnoverDAO fundTurnoverDAO;
    @Resource
    private FundStyleDAO fundStyleDAO;
    @Resource
    private FundManagerDAO fundManagerDAO;
    @Resource
    private FundIndustryDAO fundIndustryDAO;
    @Resource
    private FundBondPositionDAO fundBondPositionDAO;
    @Resource
    private FundSharesPositionDAO fundSharesPositionDAO;

    private Map<String, Set<Integer>> companyMangerMap = new HashMap<>();

    public void run() {
        List<String> fundCodeList = fundListService.getFundCodeList();
        for (String code : fundCodeList) {
            try {
                FundBaseDO fundBaseDO = fundBaseDAO.queryFundBaseByCode(code);
                if (null == fundBaseDO) {
                    logger.error(code + " queryFundBaseByCode return null");
                    continue;
                }

                // fund base
                NodeFund nodeFund = new NodeFund();
                nodeFund.setType(fundBaseDO.getType());
                nodeFund.setName(fundBaseDO.getName());
                nodeFund.setCode(fundBaseDO.getCode());
                nodeFund.setScale(getDouble(fundBaseDO.getScale()));
                nodeFund.setShare(getDouble(fundBaseDO.getShare()));
                nodeFund.setStatus(fundBaseDO.getStatus());
                nodeFund.setEstablishTime(getLocalDateTime(fundBaseDO.getEstablishTime()));

                // fund config
                FundConfigDO fundConfigDO = fundConfigDAO.queryFundConfigLatestByCode(code);
                if (null != fundConfigDO) {
                    nodeFund.setConfigAssets(getDouble(fundConfigDO.getAssets()));
                    nodeFund.setConfigBondRatio(getDouble(fundConfigDO.getBondRatio()));
                    nodeFund.setConfigCashRatio(getDouble(fundConfigDO.getCashRatio()));
                    nodeFund.setConfigSharesRatio(getDouble(fundConfigDO.getSharesRatio()));
                    nodeFund.setConfigVoucherRatio(getDouble(fundConfigDO.getVoucherRatio()));
                }

                // fund scale
                FundScaleDO fundScaleDO = fundScaleDAO.queryFundScaleLatestByCode(code);
                if (null != fundScaleDO) {
                    nodeFund.setScaleAssets(getDouble(fundScaleDO.getAssets()));
                    nodeFund.setScaleAssetsRate(getDouble(fundScaleDO.getAssetsRate()));
                    nodeFund.setScalePurchase(getDouble(fundScaleDO.getPurchase()));
                    nodeFund.setScaleRedeem(getDouble(fundScaleDO.getRedeem()));
                    nodeFund.setScaleShare(getDouble(fundScaleDO.getShare()));
                }

                // fund holder
                FundHolderDO fundHolderDO = fundHolderDAO.queryFundHolderLatestByCode(code);
                if (null != fundHolderDO) {
                    nodeFund.setHoldInsideRatio(getDouble(fundHolderDO.getInsideRatio()));
                    nodeFund.setHoldMechanismRatio(getDouble(fundHolderDO.getMechanismRatio()));
                    nodeFund.setHoldPersonalRatio(getDouble(fundHolderDO.getPersonalRatio()));
                }

                // fund turnover
                FundTurnoverDO fundTurnoverDO = fundTurnoverDAO.queryFundTurnoverLatestByCode(code);
                if (null != fundTurnoverDO) {
                    nodeFund.setTurnRate(getDouble(fundTurnoverDO.getTurnRate()));
                }

                // fund style
                FundStyleDO fundStyleDO = fundStyleDAO.queryFundStyleLatestByCode(code);
                if (null != fundStyleDO) {
                    nodeFund.setStyle(fundStyleDO.getStyle());
                }
                Integer nodeFundId = fundGraphService.saveNode(nodeFund);

                // company
                NodeCompany nodeCompany = new NodeCompany();
                nodeCompany.setName(fundBaseDO.getCompany());
                Integer nodeCompanyId = fundGraphService.saveNode(nodeCompany);

                RelationCompanyFund relationCompanyFund = new RelationCompanyFund();
                relationCompanyFund.setFromId(nodeCompanyId);
                relationCompanyFund.setToId(nodeFundId);
                fundGraphService.saveRelationship(relationCompanyFund);

                // manage
                Map<String, Integer> managerMap = new HashMap<>();
                List<FundManagerDO> fundManagerDOList = fundManagerDAO.queryFundManagerByCode(code);
                if (null != fundManagerDOList) {
                    for (int i = 0; i < fundManagerDOList.size(); i++) {
                        FundManagerDO manager = fundManagerDOList.get(i);
                        String[] mans = manager.getManager().split(" ");
                        for (String m : mans) {
                            managerMap.put(m, i);
                        }
                    }

                    Set<Integer> nodeManagerIdSet = companyMangerMap.get(fundBaseDO.getCompany());
                    if (null == nodeManagerIdSet) {
                        nodeManagerIdSet = new HashSet<>();
                    }

                    for (String m : managerMap.keySet()) {
                        NodeManager nodeManager = new NodeManager();
                        nodeManager.setName(m);
                        Integer nodeManagerId = fundGraphService.saveNode(nodeManager);

                        if (!nodeManagerIdSet.contains(nodeManagerId)) {
                            RelationCompanyManager relationCompanyManager = new RelationCompanyManager();
                            relationCompanyManager.setFromId(nodeCompanyId);
                            relationCompanyManager.setToId(nodeManagerId);
                            fundGraphService.saveRelationship(relationCompanyManager);

                            // 去重缓存
                            nodeManagerIdSet.add(nodeManagerId);
                            companyMangerMap.put(fundBaseDO.getCompany(), nodeManagerIdSet);
                        }

                        RelationManagerFund relationManagerFund = new RelationManagerFund();
                        relationManagerFund.setStatus(managerMap.get(m).intValue() < 1 ? 1 : 0);
                        relationManagerFund.setRedound(0.0);
                        relationManagerFund.setFromId(nodeManagerId);
                        relationManagerFund.setToId(nodeFundId);
                        fundGraphService.saveRelationship(relationManagerFund);
                    }
                }

                // industry
                List<FundIndustryDO> fundIndustryDOList = fundIndustryDAO.queryFundIndustryLatestByCode(code);
                if (null != fundIndustryDOList) {
                    fundIndustryDOList.forEach(industry -> {
                        try {
                            NodeIndustry nodeIndustry = new NodeIndustry();
                            nodeIndustry.setName(industry.getIndustry());
                            Integer nodeIndustryId = fundGraphService.saveNode(nodeIndustry);

                            RelationFundIndustry relationFundIndustry = new RelationFundIndustry();
                            relationFundIndustry.setMarketRatio(getDouble(industry.getMarketRatio()));
                            relationFundIndustry.setMarketValue(getDouble(industry.getMarketValue()));
                            relationFundIndustry.setFromId(nodeFundId);
                            relationFundIndustry.setToId(nodeIndustryId);
                            fundGraphService.saveRelationship(relationFundIndustry);
                        } catch (Exception e) {
                            logger.error(industry.getCode() + "|" + industry.getIndustry() + " exception", e);
                        }
                    });
                }

                // bond
                List<FundBondPositionDO> fundBondPositionDOList = fundBondPositionDAO.queryFundBondPositionLatestByCode(code);
                if (null != fundBondPositionDOList) {
                    fundBondPositionDOList.forEach(bond -> {
                        try {
                            NodeBond nodeBond = new NodeBond();
                            nodeBond.setBondCode(bond.getBondCode());
                            nodeBond.setBondName(bond.getBondName());
                            Integer nodeBondId = fundGraphService.saveNode(nodeBond);

                            RelationFundBond relationFundBond = new RelationFundBond();
                            relationFundBond.setAssetsRate(getDouble(bond.getAssetsRate()));
                            relationFundBond.setMarketValue(getDouble(bond.getMarketValue()));
                            relationFundBond.setFromId(nodeFundId);
                            relationFundBond.setToId(nodeBondId);
                            fundGraphService.saveRelationship(relationFundBond);
                        } catch (Exception e) {
                            logger.error(bond.getCode() + "|" + bond.getBondCode() + " exception", e);
                        }
                    });
                }

                // shares
                List<FundSharesPositionDO> fundSharesPositionDOList = fundSharesPositionDAO.queryFundSharesPositionLatestByCode(code);
                if (null != fundSharesPositionDOList) {
                    fundSharesPositionDOList.forEach(shares -> {
                        try {
                            NodeShares nodeShares = new NodeShares();
                            nodeShares.setSharesCode(shares.getSharesCode());
                            nodeShares.setSharesName(shares.getSharesName());
                            Integer nodeSharesId = fundGraphService.saveNode(nodeShares);

                            RelationFundShares relationFundShares = new RelationFundShares();
                            relationFundShares.setAssetsRate(getDouble(shares.getAssetsRate()));
                            relationFundShares.setMarketValue(getDouble(shares.getMarketValue()));
                            relationFundShares.setSharesNum(getDouble(shares.getSharesNum()));
                            relationFundShares.setFromId(nodeFundId);
                            relationFundShares.setToId(nodeSharesId);
                            fundGraphService.saveRelationship(relationFundShares);
                        } catch (Exception e) {
                            logger.error(shares.getCode() + "|" + shares.getSharesCode() + " exception", e);
                        }
                    });
                }
                logger.info(code + " build graph done");
            } catch (Exception e) {
                logger.error(code + " build graph exception ", e);
            }
        }
        logger.info("all code build graph done");
    }

    /**
     * @param data
     * @return
     */
    private static Double getDouble(BigDecimal data) {
        return (null == data) ? null : data.doubleValue();
    }

    /**
     * @param date
     * @return
     */
    private static LocalDateTime getLocalDateTime(Date date) {
        if (null == date) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public FundBaseDAO getFundBaseDAO() {
        return fundBaseDAO;
    }

    public void setFundBaseDAO(FundBaseDAO fundBaseDAO) {
        this.fundBaseDAO = fundBaseDAO;
    }

    public FundListService getFundListService() {
        return fundListService;
    }

    public void setFundListService(FundListService fundListService) {
        this.fundListService = fundListService;
    }

    public FundGraphService getFundGraphService() {
        return fundGraphService;
    }

    public void setFundGraphService(FundGraphService fundGraphService) {
        this.fundGraphService = fundGraphService;
    }

    public FundConfigDAO getFundConfigDAO() {
        return fundConfigDAO;
    }

    public void setFundConfigDAO(FundConfigDAO fundConfigDAO) {
        this.fundConfigDAO = fundConfigDAO;
    }

    public FundScaleDAO getFundScaleDAO() {
        return fundScaleDAO;
    }

    public void setFundScaleDAO(FundScaleDAO fundScaleDAO) {
        this.fundScaleDAO = fundScaleDAO;
    }

    public FundHolderDAO getFundHolderDAO() {
        return fundHolderDAO;
    }

    public void setFundHolderDAO(FundHolderDAO fundHolderDAO) {
        this.fundHolderDAO = fundHolderDAO;
    }

    public FundTurnoverDAO getFundTurnoverDAO() {
        return fundTurnoverDAO;
    }

    public void setFundTurnoverDAO(FundTurnoverDAO fundTurnoverDAO) {
        this.fundTurnoverDAO = fundTurnoverDAO;
    }

    public FundStyleDAO getFundStyleDAO() {
        return fundStyleDAO;
    }

    public void setFundStyleDAO(FundStyleDAO fundStyleDAO) {
        this.fundStyleDAO = fundStyleDAO;
    }

    public FundManagerDAO getFundManagerDAO() {
        return fundManagerDAO;
    }

    public void setFundManagerDAO(FundManagerDAO fundManagerDAO) {
        this.fundManagerDAO = fundManagerDAO;
    }

    public FundIndustryDAO getFundIndustryDAO() {
        return fundIndustryDAO;
    }

    public void setFundIndustryDAO(FundIndustryDAO fundIndustryDAO) {
        this.fundIndustryDAO = fundIndustryDAO;
    }

    public FundBondPositionDAO getFundBondPositionDAO() {
        return fundBondPositionDAO;
    }

    public void setFundBondPositionDAO(FundBondPositionDAO fundBondPositionDAO) {
        this.fundBondPositionDAO = fundBondPositionDAO;
    }

    public FundSharesPositionDAO getFundSharesPositionDAO() {
        return fundSharesPositionDAO;
    }

    public void setFundSharesPositionDAO(FundSharesPositionDAO fundSharesPositionDAO) {
        this.fundSharesPositionDAO = fundSharesPositionDAO;
    }
}
