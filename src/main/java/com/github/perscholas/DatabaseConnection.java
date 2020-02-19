package com.github.perscholas;

import java.sql.*;

/**
 * Created by leon on 2/18/2020.
 */
public enum DatabaseConnection {
    MYSQL;

    private String database = "";

    public Connection getConnection() {
        String username = "root";
        String password = "";
        String dbVendor = name().toLowerCase();
        String url = "jdbc:" + dbVendor + "://127.0.0.1/"+database;
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    public void executeStatement(String sqlStatement) {
        try {
            Statement statement = getScrollableStatement();
            System.out.println(sqlStatement);
            statement.executeUpdate(sqlStatement);
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    public ResultSet executeQuery(String sqlQuery) {
        try {
            Statement statement = getScrollableStatement();
//            Statement statement = getConnection().prepareStatement(sqlQuery);
            return statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    private Statement getScrollableStatement() {
        int resultSetType = ResultSet.TYPE_SCROLL_INSENSITIVE;
        int resultSetConcurrency = ResultSet.CONCUR_READ_ONLY;
        try {
            return getConnection().createStatement(resultSetType, resultSetConcurrency);
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    public void setDatabase(String database) {
        System.out.println("set database "+database);
        this.database = database;
    }
}
