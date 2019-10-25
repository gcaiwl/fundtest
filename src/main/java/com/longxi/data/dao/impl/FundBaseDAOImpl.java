package com.longxi.data.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.longxi.data.dao.FundBaseDAO;
import com.longxi.data.obj.FundBaseDO;
import com.longxi.data.obj.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * fund_base表的DAO层实现类
 * Date 2017-11-17 21:38:15
 */
public class FundBaseDAOImpl extends SqlMapBaseDAO implements FundBaseDAO {
    private static final Logger logger = LoggerFactory.getLogger(FundBaseDAOImpl.class);

    @Override
    public FundBaseDO queryFundBaseById(Long id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        List<FundBaseDO> list = queryFundBase(params);
        if (list == null || list.isEmpty()) {
            return null;
        } else if (list.size() != 1) {
            String errMsg = "queryFundBaseById return too many records,the param=" + params;
            logger.error(errMsg);
            throw new RuntimeException(errMsg);
        }
        return list.get(0);
    }

    @SuppressWarnings("unchecked")
    private List<FundBaseDO> queryFundBase(Map<String, Object> params) {
        List<FundBaseDO> list = Collections.emptyList();
        try {
            list = (List<FundBaseDO>) sqlMapClient.queryForList("FundBaseDAO.queryFundBase", params);
        } catch (Exception e) {
            logger.error("queryFundBase ERROR", e);
        }
        return list;
    }

    @Override
    public int updateFundBase(FundBaseDO instance) {
        int num = -1;
        try {
            num = sqlMapClient.update("FundBaseDAO.updateFundBase", instance);
        } catch (Exception e) {
            logger.error("updateFundBase error", e);
        }
        return num;
    }

    @Override
    public Long insertFundBase(FundBaseDO instance) {
        Long id = null;
        try {
            id = (Long) sqlMapClient.insert("FundBaseDAO.insertFundBase", instance);
        } catch (Exception e) {
            logger.error("insertFundBase error", e);
        }
        return id;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<FundBaseDO> queryFundBaseByPage(Query<FundBaseDO> query) {
        Map<String, Object> params = convertQuery2Param(query);
        int count = countFundBaseForPage(query);
        query.setTotalSize(count);
        List<FundBaseDO> list = Collections.emptyList();
        try {
            list = (List<FundBaseDO>) sqlMapClient.queryForList("FundBaseDAO.queryFundBaseByPage", params);
        } catch (Exception e) {
            logger.error("queryFundBaseByPage error", e);
        }
        return list;
    }

    @Override
    public int countFundBaseForPage(Query<FundBaseDO> query) {
        int count = 0;
        Map<String, Object> params = convertQuery2Param(query);
        try {
            count = (Integer) sqlMapClient.queryForObject("FundBaseDAO.countFundBase", params);
        } catch (Exception e) {
            logger.error("countFundBaseForPage error", e);
        }
        return count;
    }

    private Map<String, Object> convertQuery2Param(Query<FundBaseDO> query) {
        FundBaseDO instance = query.getModule();
        Map<String, Object> params = new HashMap<String, Object>();

        // TODO 自行添加分页查询可能的条件
        //params.put("id", instance.getId());
        //params.put("isDel", instance.getIsDel()==null?0:instance.getIsDel());//默认只查询未被删除的

        params.put("_order", query.getOrderBy());

        Map<String, Object> _limit = new HashMap<String, Object>();
        _limit.put("start", query.getStart());
        _limit.put("pageSize", query.getPageSize());
        params.put("_limit", _limit);

        return params;
    }

    @Override
    public FundBaseDO queryFundBaseByCode(String code) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("code", code);
        List<FundBaseDO> list = queryFundBase(params);
        if (list == null || list.isEmpty()) {
            return null;
        } else if (list.size() != 1) {
            String errMsg = "queryFundBaseByCode return too many records,the param=" + params;
            throw new RuntimeException(errMsg);
        }
        return list.get(0);
    }

    @Override
    public int deleteFundBaseByCode(String code) {
        int num = -1;
        try {
            num = sqlMapClient.update("FundBaseDAO.deleteFundBaseByCode", code);
        } catch (Exception e) {
            logger.error("deleteFundBaseByCode error", e);
        }
        return num;
    }
}
