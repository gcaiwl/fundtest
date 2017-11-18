package com.longxi.data.dao.impl;

import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author longxi.cwl
 * @date 2017/11/17
 */
public class SqlMapBaseDAO {
    private static Logger logger = LoggerFactory.getLogger(SqlMapBaseDAO.class);

    protected static SqlMapClient sqlMapClient = null;

    static {
        try {
            Reader reader = Resources.getResourceAsReader("ibatis/spring_sqlmap_config.xml");
            sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
            reader.close();
        } catch (IOException e) {
            logger.error("sqlMapClient exception ", e);
        }
    }
}
