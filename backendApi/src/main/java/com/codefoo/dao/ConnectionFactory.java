package com.codefoo.dao;

import com.codefoo.exception.AppException;
import com.codefoo.mappings.ConfLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static ConnectionFactory connectionFactory = null;

    private ConnectionFactory() throws AppException {
        try {
            Class.forName(ConfLoader.getConf().getDriverClassName());
        } catch (ClassNotFoundException e) {
            throw new AppException(e);
        }
    }

    public Connection getConnection() throws AppException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(ConfLoader.getConf().getConnectionUrl(), ConfLoader.getConf().getUserName(), ConfLoader.getConf().getPassword());
        } catch (SQLException e) {
            throw new AppException(e);
        }
        return conn;
    }

    public static ConnectionFactory getInstance() throws AppException {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }

}
