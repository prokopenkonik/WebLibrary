package com.company.model.dao.impl;

import com.company.model.dao.IAdminDao;
import com.company.model.dao.IBookDao;
import com.company.model.dao.IDaoFactory;
import com.company.model.dao.IUserDao;
import com.company.model.dao.connection.ConnectionImpl;
import com.company.model.exception.DaoException;

public class DaoFactory implements IDaoFactory {

    private static DaoFactory instance = new DaoFactory();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return instance;
    }

    @Override
    public IBookDao getBookDao() throws DaoException {
        return new BookDao(ConnectionImpl.getInstance());
    }

    @Override
    public IUserDao getUserDao() throws DaoException {
        return new UserDao(ConnectionImpl.getInstance());
    }

    @Override
    public IAdminDao getAdminDao() throws DaoException {
        return new AdminDao(ConnectionImpl.getInstance());
    }
}
