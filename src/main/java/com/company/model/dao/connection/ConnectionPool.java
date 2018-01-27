package com.company.model.dao.connection;

import com.company.model.dao.constants.Config;
import com.company.model.util.DataBaseManager;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool implements ConnectionFactory {
    private static ConnectionPool instance = new ConnectionPool();
    private static DataSource dataSource;

    static {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(DataBaseManager.getString(Config.URL));
        ds.setUsername(DataBaseManager.getString(Config.USER));
        ds.setPassword(DataBaseManager.getString(Config.PASSWORD));
        ds.setMaxIdle(Integer.parseInt(DataBaseManager.getString(Config.MAX_IDLE)));
        ds.setMaxActive(Integer.parseInt(DataBaseManager.getString(Config.MAX_ACTIVE)));
        dataSource = ds;
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    private ConnectionPool() {

    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
