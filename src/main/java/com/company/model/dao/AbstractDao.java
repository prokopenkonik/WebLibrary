package com.company.model.dao;

import com.company.domain.Entity;
import com.company.model.dao.connection.ConnectionFactory;

import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDao<T extends Entity> implements IGenericDao<T> {
    protected ConnectionFactory connectionFactory;

    public AbstractDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void close(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
