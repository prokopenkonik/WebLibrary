package com.company.model.dao.impl;

import com.company.domain.User;
import com.company.model.dao.IUserDao;
import com.company.model.dao.connection.ConnectionFactory;
import com.company.model.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.company.model.dao.constants.SqlQueries.ADD_USER;
import static com.company.model.dao.constants.SqlQueries.GET_USER_BY_LOGIN;
import static com.company.model.util.SqlQueriesManager.getQuery;

public class UserDao extends AbstractDao<User> implements IUserDao {

    UserDao(ConnectionFactory factory) {
        super(factory);
    }

    @Override
    protected String getQueryForCreate() {
        return getQuery(ADD_USER);
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
    protected void prepareStatementForCreate(PreparedStatement statement, User entity) throws SQLException {
        statement.setString(1, entity.getLogin());
        statement.setString(2, entity.getPassword());
        statement.setString(3, entity.getPhoneNumber());
        statement.setString(4, entity.getName());
        statement.setString(5, entity.getMail());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User entity) throws SQLException {

    }

    @Override
    protected List<User> parseResultSet(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        User user;
        while (rs.next()) {
            user = new User();
            user.setId(rs.getInt("User_ID"));
            user.setLogin(rs.getString("Login"));
            user.setPassword(rs.getString("Password"));
            user.setName(rs.getString("Name"));
            user.setPhoneNumber(rs.getString("Phone"));
            user.setMail(rs.getString("Mail"));
            users.add(user);
        }
        return users;
    }

    @Override
    public void create(User entity) throws DaoException {
        User user = getUserByLogin(entity.getLogin());
        if (user != null) {
            throw new DaoException("Such login already exists");
        }
        super.create(entity);
    }

    @Override
    public User getUserByLogin(String login) throws DaoException {
        List<User> users;
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     getQuery(GET_USER_BY_LOGIN))) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            users = parseResultSet(rs);
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        }
        if (users == null || users.size() == 0) {
            return null;
        }
        if (users.size() > 1) {
            throw new DaoException("Received more then 1 parameter");
        }
        return users.iterator().next();
    }
}
