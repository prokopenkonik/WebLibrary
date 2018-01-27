package com.company.model.dao.impl;

import com.company.domain.Administrator;
import com.company.model.dao.IAdminDao;
import com.company.model.dao.connection.ConnectionFactory;
import com.company.model.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.company.model.dao.constants.SqlQueries.GET_ADMIN_BY_LOGIN;
import static com.company.model.util.SqlQueriesManager.getQuery;

public class AdminDao extends AbstractDao<Administrator> implements IAdminDao {

    AdminDao(ConnectionFactory factory) {
        super(factory);
    }

    @Override
    public Administrator getAdminByLogin(String login) throws DaoException {
        List<Administrator> admins;
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     getQuery(GET_ADMIN_BY_LOGIN))) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            admins = parseResultSet(rs);
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        }
        if (admins == null || admins.size() == 0) {
            return null;
        }
        if (admins.size() > 1) {
            throw new DaoException("Received more then 1 parameter");
        }
        return admins.iterator().next();
    }

    @Override
    protected String getQueryForCreate() {
        return null;
    }

    @Override
    protected String getQueryForGet() {
        return null;
    }

    @Override
    protected String getQueryForGetAll() {
        return null;
    }

    @Override
    protected String getQueryForUpdate() {
        return null;
    }

    @Override
    protected String getQueryForDelete() {
        return null;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Administrator entity) throws SQLException {

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Administrator entity) throws SQLException {

    }

    @Override
    protected List<Administrator> parseResultSet(ResultSet rs) throws SQLException {
        List<Administrator> admins = new ArrayList<>();
        Administrator admin;
        while (rs.next()) {
            admin = new Administrator();
            admin.setId(rs.getInt("Admin_ID"));
            admin.setLogin(rs.getString("Login"));
            admin.setPassword(rs.getString("Password"));
            admins.add(admin);
        }
        return admins;
    }
}
