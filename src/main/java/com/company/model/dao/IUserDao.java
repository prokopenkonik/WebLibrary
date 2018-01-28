package com.company.model.dao;

import com.company.model.domain.User;
import com.company.model.exception.DaoException;

public interface IUserDao extends IGenericDao<User> {
    User getUserByLogin(String login) throws DaoException;
}
