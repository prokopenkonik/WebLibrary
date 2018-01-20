package com.company.model.dao.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionFactory {
    Connection getConnection() throws SQLException;
}
