package com.company.model.dao.impl;

import com.company.model.domain.Book;
import com.company.model.domain.Order;
import com.company.model.domain.User;
import com.company.model.dao.IOrderDao;
import com.company.model.dao.connection.ConnectionFactory;
import com.company.model.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.company.model.dao.constants.SqlQueries.*;
import static com.company.model.dao.constants.SqlQueries.DELETE_ORDER;
import static com.company.model.dao.constants.SqlQueries.GET_ORDER_BY_USER;
import static com.company.model.util.SqlQueriesManager.getQuery;

public class OrderDao extends AbstractDao<Order> implements IOrderDao {

    OrderDao(ConnectionFactory factory) {
        super(factory);
    }

    @Override
    protected String getQueryForCreate() {
        return getQuery(ADD_ORDER);
    }

    @Override
    protected String getQueryForGet() {
        return getQuery(GET_ORDER_BY_ID);
    }

    @Override
    protected String getQueryForGetAll() {
        return getQuery(GET_ALL_ORDERS);
    }

    @Override
    protected String getQueryForUpdate() {
        return getQuery(UPDATE_ORDER);
    }

    @Override
    protected String getQueryForDelete() {
        return getQuery(DELETE_ORDER);
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Order entity)
            throws SQLException {
        statement.setInt(1, entity.getUser().getId());
        statement.setDate(2, new Date(entity.getCreationDate().getTime()));
        statement.setDate(3, new Date(entity.getEndingDate().getTime()));
        statement.setInt(4, 0);
        statement.setInt(5, entity.getBook().getId());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Order entity) throws SQLException {
        statement.setDate(1, new Date(entity.getCreationDate().getTime()));
        statement.setDate(2, new Date(entity.getEndingDate().getTime()));
        statement.setInt(3, entity.isAccepted() ? 1 : 0);
        statement.setInt(4, entity.getId());
    }

    @Override
    protected List<Order> parseResultSet(ResultSet rs) throws SQLException {
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

            user = new User();
            user.setName(rs.getString("Name"));
            user.setMail(rs.getString("Mail"));
            user.setPhoneNumber(rs.getString("Phone"));
            order.setUser(user);

            orders.add(order);
        }
        return orders;
    }

    @Override
    public List<Order> getByUsersId(int id) throws DaoException {
        List<Order> orders;
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     getQuery(GET_ORDER_BY_USER))) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            orders = parseResultSet(rs);
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        }
        if (orders == null || orders.size() == 0) {
            return null;
        }
        return orders;
    }
}
