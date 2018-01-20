package com.company.model.dao.impl;

import com.company.domain.Administrator;
import com.company.model.dao.AbstractDao;
import com.company.model.dao.IAdminDao;
import com.company.model.dao.connection.ConnectionFactory;
import com.company.model.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao extends AbstractDao<Administrator> implements IAdminDao {

    private static final String SQL_GET_ADMIN_BY_LOGIN =
            "SELECT * FROM librarydb.admins where Login = ?";

    AdminDao(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    @Override
    public Administrator getAdminByLogin(String login) throws DaoException {
        List<Administrator> admins;
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(SQL_GET_ADMIN_BY_LOGIN);
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            admins = parseResultSet(rs);
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        } finally {
            this.close(statement);
        }
        if (admins == null || admins.size() == 0) {
            return null;
        }
        if (admins.size() > 1) {
            throw new DaoException("Received more then 1 parameter");
        }
        return admins.iterator().next();
    }

    private List<Administrator> parseResultSet(ResultSet rs) throws SQLException {
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

    @Override
    public void create(Administrator entity) throws DaoException {

    }

    @Override
    public Administrator get(int id) throws DaoException {
        return null;
    }

    @Override
    public List<Administrator> getAll() throws DaoException {
        return null;
    }

    @Override
    public void update(Administrator entity) throws DaoException {

    }

    @Override
    public void delete(Administrator entity) throws DaoException {

    }
}
