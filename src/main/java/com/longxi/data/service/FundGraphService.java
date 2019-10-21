package com.longxi.data.service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import com.alibaba.fastjson.JSONObject;

import com.longxi.data.annotation.Node;
import com.longxi.data.annotation.Property;
import com.longxi.data.annotation.Relation;
import com.longxi.data.obj.RelationBase;
import com.longxi.data.obj.RelationCompanyFund;
import com.longxi.data.obj.RelationCompanyManager;
import com.longxi.data.obj.RelationFundBond;
import com.longxi.data.obj.RelationFundIndustry;
import com.longxi.data.obj.RelationFundShares;
import com.longxi.data.obj.RelationManagerFund;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;
import org.neo4j.driver.v1.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.neo4j.driver.v1.Values.parameters;

/**
 * @author longxi.cwl
 * @date 2019/10/17
 */
public class FundGraphService implements AutoCloseable {
    private static Logger logger = LoggerFactory.getLogger(FundGraphService.class);

    /**
     *
     */
    private String graphUrl;
    /**
     *
     */
    private String graphName;
    /**
     *
     */
    private String graphSign;
    /**
     *
     */
    private Driver driver;

    public void init() {
        try {
            this.driver = GraphDatabase.driver(graphUrl, AuthTokens.basic(graphName, graphSign));
        } catch (Exception e) {
            logger.error("init exception ", e);
        }
    }

    @Override
    public void close() throws Exception {
        try {
            if (null != driver) {
                driver.close();
            }
        } catch (Exception e) {
            logger.error("close exception ", e);
        }
    }

    /**
     * @param obj
     * @return
     */
    public Integer saveNode(Object obj) {
        Integer nodeId = null;
        Node node = obj.getClass().getAnnotation(Node.class);
        if (null != node) {
            JSONObject dataJson = new JSONObject();
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field f : fields) {
                if (f.isAnnotationPresent(Property.class)) {
                    try {
                        String pName = f.getName();
                        char[] cs = pName.toCharArray();
                        cs[0] -= 32;
                        String fName = new String(cs);
                        String mName = "get" + fName;

                        Object value = obj.getClass().getMethod(mName).invoke(obj);
                        dataJson.put(pName, value);
                    } catch (IllegalAccessException e) {
                        logger.error(JSONObject.toJSONString(obj) + " save illegalAccessException ", e);
                    } catch (InvocationTargetException e) {
                        logger.error(JSONObject.toJSONString(obj) + " save invocationTargetException ", e);
                    } catch (NoSuchMethodException e) {
                        logger.error(JSONObject.toJSONString(obj) + " save noSuchMethodException ", e);
                    } catch (Exception e) {
                        logger.error(JSONObject.toJSONString(obj) + " save illegalAccessException ", e);
                    }
                }
            }

            StatementResult statementResult = save(String.format("Merge(n:%s%s) return id(n)", node.label(), transformParamJson(dataJson)
            ), null);
            if (null != statementResult && statementResult.hasNext()) {
                nodeId = statementResult.next().get(0).asInt();
            }
        }
        return nodeId;
    }

    /**
     * @param obj
     */
    public StatementResult saveRelationship(Object obj) {
        StatementResult statementResult = null;
        JSONObject dataJson = new JSONObject();
        Relation relation = obj.getClass().getAnnotation(Relation.class);
        if (null != relation) {
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field f : fields) {
                if (f.isAnnotationPresent(Property.class)) {
                    try {
                        String pName = f.getName();
                        char[] cs = pName.toCharArray();
                        cs[0] -= 32;
                        String fName = new String(cs);
                        String mName = "get" + fName;

                        Object value = obj.getClass().getMethod(mName).invoke(obj);
                        dataJson.put(pName, value);
                    } catch (IllegalAccessException e) {
                        logger.error(JSONObject.toJSONString(obj) + " save illegalAccessException ", e);
                    } catch (InvocationTargetException e) {
                        logger.error(JSONObject.toJSONString(obj) + " save invocationTargetException ", e);
                    } catch (NoSuchMethodException e) {
                        logger.error(JSONObject.toJSONString(obj) + " save noSuchMethodException ", e);
                    } catch (Exception e) {
                        logger.error(JSONObject.toJSONString(obj) + " save illegalAccessException ", e);
                    }
                }
            }

            RelationBase relationBase = (RelationBase)obj;
            if (relationBase.getFromId() < 0 || relationBase.getToId() < 0) {
                logger.error(getRelationshipType(relationBase) + " " + relationBase.getFromId() + "|" + relationBase.getToId() + " is zero in relationship");
                return statementResult;
            }

            if (dataJson.isEmpty()) {
                return save(String.format("Match(fn), (tn) Where id(fn) = %d and id(tn) = %d Create (fn)-[:%s]->(tn)", relationBase.getFromId(), relationBase.getToId(), relation
                    .type()), null);
            } else {
                return save(String
                    .format("Match(fn), (tn) Where id(fn) = %d and id(tn) = %d Create (fn)-[:%s $props]->(tn)", relationBase.getFromId(), relationBase.getToId(), relation
                        .type()), parameters("props", dataJson));
            }
        }
        return statementResult;
    }

    /**
     * @param statementTemplate
     * @param params
     */
    public StatementResult save(String statementTemplate, Value params) {
        StatementResult statementResult = null;
        if (null == driver) {
            logger.error("driver is null");
            return statementResult;
        }

        try {
            Session session = driver.session();
            if (null != session) {
                statementResult = session.writeTransaction(new TransactionWork<StatementResult>() {
                    @Override
                    public StatementResult execute(Transaction tx) {
                        StatementResult result = null;
                        if (null == params) {
                            result = tx.run(statementTemplate);
                        } else {
                            result = tx.run(statementTemplate, params);
                        }
                        return result;
                    }
                });
            }
        } catch (Exception e) {
            logger.error("build graph exception ", e);
        }
        return statementResult;
    }

    /**
     * @param paramJson
     * @return
     */
    private String transformParamJson(JSONObject paramJson) {
        if (null == paramJson) {
            return "";
        }

        return paramJson.toJSONString().replaceAll("\\\":", ":").replaceAll(",\\\"", ",")
            .replaceFirst("\\\"", "");
    }

    /**
     * @param relationBase
     * @return
     */
    private String getRelationshipType(RelationBase relationBase) {
        String type = "";
        if (relationBase instanceof RelationCompanyFund) {
            type = "relationCompanyFund";
        } else if (relationBase instanceof RelationCompanyManager) {
            type = "relationCompanyManager";
        } else if (relationBase instanceof RelationFundBond) {
            type = "relationFundBond";
        } else if (relationBase instanceof RelationFundIndustry) {
            type = "relationFundIndustry";
        } else if (relationBase instanceof RelationFundShares) {
            type = "relationFundShares";
        } else if (relationBase instanceof RelationManagerFund) {
            type = "relationManagerFund";
        }
        return type;
    }

    public String getGraphUrl() {
        return graphUrl;
    }

    public void setGraphUrl(String graphUrl) {
        this.graphUrl = graphUrl;
    }

    public String getGraphName() {
        return graphName;
    }

    public void setGraphName(String graphName) {
        this.graphName = graphName;
    }

    public String getGraphSign() {
        return graphSign;
    }

    public void setGraphSign(String graphSign) {
        this.graphSign = graphSign;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
