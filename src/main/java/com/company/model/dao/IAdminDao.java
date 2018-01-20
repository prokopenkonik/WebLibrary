package com.company.model.dao;

import com.company.domain.Administrator;
import com.company.model.exception.DaoException;

public interface IAdminDao extends IGenericDao<Administrator> {
    Administrator getAdminByLogin(String login) throws DaoException;
}
