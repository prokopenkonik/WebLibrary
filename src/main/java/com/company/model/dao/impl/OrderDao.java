package com.company.model.dao.impl;

import com.company.domain.Book;
import com.company.domain.Order;
import com.company.domain.User;
import com.company.model.dao.AbstractDao;
import com.company.model.dao.IOrderDao;
import com.company.model.dao.connection.ConnectionFactory;
import com.company.model.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends AbstractDao<Order> implements IOrderDao {

    private static final String SQL_ADD_ORDER =
            "INSERT INTO orders (User_ID, Creation_Date, End_Date, Accepted, Book_ID) VALUES(?,?,?,?,?)";
    private static final String SQL_GET_ORDERS =
            "SELECT Order_ID, orders.User_ID, Creation_Date, End_Date, Accepted, books.Book_ID, Title, Name, Phone, Mail " +
                    "FROM orders JOIN books ON orders.Book_ID=books.Book_ID JOIN users ON orders.User_ID=users.User_ID";
    private static final String SQL_GET_ORDERS_BY_USERS_ID =
            "SELECT Order_ID, Creation_Date, End_Date, Accepted, books.Book_ID, Title FROM orders JOIN books ON orders.Book_ID=books.Book_ID JOIN users ON orders.User_ID=users.User_ID WHERE users.User_ID=?";
    private static final String SQL_DELETE_ORDER =
            "DELETE FROM orders WHERE Order_ID=?";

    OrderDao(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    @Override
    public void create(Order entity) throws DaoException {
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(SQL_ADD_ORDER);
            statement.setInt(1, entity.getUser().getId());
            statement.setDate(2, new Date(entity.getCreationDate().getTime()));
            statement.setDate(3, new Date(entity.getEndingDate().getTime()));
            statement.setInt(4, 0);
            statement.setInt(5, entity.getBook().getId());
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
    public Order get(int id) throws DaoException {
        return null;
    }

    @Override
    public List<Order> getAll() throws DaoException {
        List<Order> orders;
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(SQL_GET_ORDERS);
            ResultSet rs = statement.executeQuery();
            orders = parseResult(rs, true);
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        } finally {
            this.close(statement);
        }
        if (orders == null || orders.size() == 0) {
            return null;
        }
        return orders;
    }

    private List<Order> parseResult(ResultSet rs, boolean includeUser) throws SQLException {
        List<Order> orders = new ArrayList<>();
        Order order;
        Book book;
        User user;
        while (rs.next()) {
            order = new Order();
            order.setId(rs.getInt("Order_ID"));
            order.setCreationDate(rs.getDate("Creation_Date"));
            order.setEndingDate(rs.getDate("End_Date"));
            order.setAccepted(rs.getInt("Accepted") == 1);

            book = new Book();
            book.setId(rs.getInt("Book_ID"));
            book.setTitle(rs.getString("Title"));
            order.setBook(book);

            if (includeUser) {
                user = new User();
                user.setName(rs.getString("Name"));
                user.setMail(rs.getString("Mail"));
                user.setPhoneNumber(rs.getString("Phone"));
                order.setUser(user);
            }

            orders.add(order);
        }
        return orders;
    }

    @Override
    public void update(Order entity) throws DaoException {

    }

    @Override
    public void delete(Order entity) throws DaoException {
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(SQL_DELETE_ORDER);
            statement.setInt(1, entity.getId());
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DaoException("Deleted more then 1 record");
            }
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        } finally {
            this.close(statement);
        }
    }

    @Override
    public List<Order> getByUsersId(int id) throws DaoException {
        List<Order> orders;
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(SQL_GET_ORDERS_BY_USERS_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            orders = parseResult(rs, false);
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        } finally {
            this.close(statement);
        }
        if (orders == null || orders.size() == 0) {
            return null;
        }
        return orders;
    }
}
