package com.company.model.dao.impl;

import com.company.model.dao.*;
import com.company.model.dao.connection.ConnectionFactory;
import com.company.model.dao.connection.ConnectionImpl;
import com.company.model.dao.connection.ConnectionPool;
import com.company.model.exception.DaoException;

public class DaoFactory implements IDaoFactory {

    private static DaoFactory instance = new DaoFactory();
    private ConnectionFactory connectionFactory = ConnectionPool.getInstance();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return instance;
    }

    @Override
    public IBookDao getBookDao() throws DaoException {
        return new BookDao(connectionFactory);
    }

    @Override
    public IUserDao getUserDao() throws DaoException {
        return new UserDao(connectionFactory);
    }

    @Override
    public IAdminDao getAdminDao() throws DaoException {
        return new AdminDao(connectionFactory);
    }

    @Override
    public IAuthorDao getAuthorDao() throws DaoException {
        return new AuthorDao(connectionFactory);
    }

    @Override
    public IOrderDao getOrderDao() throws DaoException {
        return new OrderDao(connectionFactory);
    }
}
