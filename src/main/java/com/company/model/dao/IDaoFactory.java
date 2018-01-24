package com.company.model.dao;

import com.company.model.exception.DaoException;

public interface IDaoFactory {
    IBookDao getBookDao() throws DaoException;
    IUserDao getUserDao() throws DaoException;
    IAdminDao getAdminDao() throws DaoException;
    IAuthorDao getAuthorDao() throws DaoException;
    IOrderDao getOrderDao() throws DaoException;
}
