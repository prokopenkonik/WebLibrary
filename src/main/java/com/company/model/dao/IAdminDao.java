package com.company.model.dao;

import com.company.model.domain.Administrator;
import com.company.model.exception.DaoException;

public interface IAdminDao extends IGenericDao<Administrator> {
    Administrator getAdminByLogin(String login) throws DaoException;
}
