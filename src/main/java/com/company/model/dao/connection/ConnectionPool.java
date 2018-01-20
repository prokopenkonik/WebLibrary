package com.company.model.dao.connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool implements ConnectionFactory {
    private static ConnectionPool instance = new ConnectionPool();
    private static DataSource dataSource;

    static {
        try {
            Context context = (Context) new InitialContext().lookup("java:/comp/env");
            dataSource = (DataSource) context.lookup("jdbc/librarydb");
            System.out.println("yes");
        } catch (NamingException e) {
            System.out.println("no");
            e.printStackTrace();
        }
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public ConnectionPool() {

    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
