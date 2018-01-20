package com.company.model.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionImpl implements ConnectionFactory {

    private static ConnectionImpl instance = new ConnectionImpl();
    private Connection connection;

    public static ConnectionImpl getInstance() {
        return instance;
    }

    private ConnectionImpl() {
        ResourceBundle resource = ResourceBundle.getBundle("db");
        String url = resource.getString("url");
        String user = resource.getString("user");
        String pass = resource.getString("password");
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return connection;
    }
}
