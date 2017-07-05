package com.shengdiyage.dao.implement;

import java.sql.*;

/**
 * Created by Akari on 2017/6/26.
 */
public class BaseDao {

    private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String URL = "jdbc:mariadb://localhost:2233/shengdiyage";
    private static final String USER = "root";
    private static final String PASSWORD = "1125";
    private Connection c = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public BaseDao() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }

    /**
     * 通用的增删改功能
     * String sql填入要执行的sql语句，Object[] values按sql语句中的？位置依次输入
     * 返回-1表示失败
     * @param sql
     * @param values
     * @return
     */
    public int executeUpdate(String sql,Object[] values) {
        int result = 0;
        try {
            c = getConnection();
            ps = c.prepareStatement(sql);
            if (values != null) {
                for (int i = 0; i < values.length; i++) {
                    ps.setObject(i+1, values[i]);
                }
            }
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return result;
    }

    /**
     * 查询功能
     * String sql填入要执行的sql语句，Object[] values按sql语句中的？位置依次输入
     * 返回-1表示失败
     * @param sql
     * @return
     */
    public ResultSet executeQuery(String sql,Object[] values) {
        try {
            c = getConnection();
            ps = c.prepareStatement(sql);
            if(values != null) {
                for(int i = 0; i < values.length; i++) {
                    ps.setObject(i+1, values[i]);
                }
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 用来关闭所有数据库链接
     */
    public void closeAll() {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(c != null) {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
