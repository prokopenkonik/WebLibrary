package com.company.model.dao;

import com.company.model.exception.DaoException;

public interface IDaoFactory {
    IBookDao getBookDao() throws DaoException;
    IUserDao getUserDao() throws DaoException;
    IAdminDao getAdminDao() throws DaoException;
}
