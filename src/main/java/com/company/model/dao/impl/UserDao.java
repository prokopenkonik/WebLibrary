package com.company.model.dao.impl;

import com.company.domain.User;
import com.company.model.dao.AbstractDao;
import com.company.model.dao.IUserDao;
import com.company.model.dao.connection.ConnectionFactory;
import com.company.model.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<User> implements IUserDao {

    private static final String SQL_GET_USER_BY_LOGIN =
            "SELECT * FROM users where Login = ?";
    private static final String SQL_ADD_USER =
            "INSERT INTO users (Login, Password, Phone, Name, Mail) VALUES(?,?,?,?,?)";

    UserDao(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    @Override
    public void create(User entity) throws DaoException {
        User user = getUserByLogin(entity.getLogin());
        if (user != null) {
            throw new DaoException("Such login already exists");
        }
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(SQL_ADD_USER);
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getPhoneNumber());
            statement.setString(4, entity.getName());
            statement.setString(5, entity.getMail());
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DaoException("Modified more then 1 record");
            }
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        } finally {
            this.close(statement);
        }
    }

    @Override
    public User get(int id) throws DaoException {
        return null;
    }

    @Override
    public List<User> getAll() throws DaoException {
        return null;
    }

    @Override
    public void update(User entity) throws DaoException {

    }

    @Override
    public void delete(User entity) throws DaoException {

    }

    @Override
    public User getUserByLogin(String login) throws DaoException {
        List<User> users;
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(SQL_GET_USER_BY_LOGIN);
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            users = parseResultSet(rs);
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        } finally {
            this.close(statement);
        }
        if (users == null || users.size() == 0) {
            return null;
        }
        if (users.size() > 1) {
            throw new DaoException("Received more then 1 parameter");
        }
        return users.iterator().next();
    }

    private List<User> parseResultSet(ResultSet rs) throws SQLException {
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
}
