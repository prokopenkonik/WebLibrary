package com.company.model.dao;

import com.company.domain.Order;
import com.company.model.exception.DaoException;

import java.util.List;

public interface IOrderDao extends IGenericDao<Order> {
    List<Order> getByUsersId(int id) throws DaoException;
}
