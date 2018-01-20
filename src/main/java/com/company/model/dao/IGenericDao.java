package com.company.model.dao;

import com.company.domain.Entity;
import com.company.model.exception.DaoException;

import java.util.List;

public interface IGenericDao<T extends Entity> {
    void create(T entity) throws DaoException;
    T get(int id) throws DaoException;
    List<T> getAll() throws DaoException;
    void update(T entity) throws DaoException;
    void delete(T entity) throws DaoException;
}
