package com.longxi.data.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author longxi.cwl
 * @date 2017/11/04
 */
public class DBUtils {

    private static final String url = "jdbc:mysql://localhost:3306/service";
    private static final String user = "service";
    private static final String password = "fund_longxi";


    public static void insert(String sql) {
        Connection conn = create();
        if (null == conn) {
            return;
        }

        try {
            Statement stmt = conn.createStatement(); //创建Statement对象
            int num = stmt.executeUpdate(sql);//创建数据对象
            stmt.close();
        } catch (Exception e) {
            System.out.println("exception " + e.getMessage());
        } finally {
            close(conn);
        }
    }


    public static void query(String sql) {
        Connection conn = create();
        if (null == conn) {
            return;
        }

        try {
            Statement stmt = conn.createStatement(); //创建Statement对象
            ResultSet rs = stmt.executeQuery(sql);//创建数据对象
            System.out.println("编号" + "\t" + "姓名" + "\t" + "年龄");
            while (rs.next()) {
                System.out.print(rs.getInt(1) + "\t");
                System.out.print(rs.getString(2) + "\t");
                System.out.print(rs.getInt(3) + "\t");
                System.out.println();
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("exception " + e.getMessage());
        } finally {
            close(conn);
        }
    }

    /**
     * @return
     */
    private static Connection create() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("create exception " + e.getMessage());
        }
        return conn;
    }

    /**
     *
     * @param conn
     */
    private static void close(Connection conn) {
        try {
            if (null != conn) {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("close exception " + e.getMessage());
        }
    }
}
