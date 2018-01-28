package com.company.model.dao.impl;

import com.company.model.domain.Entity;
import com.company.model.dao.IGenericDao;
import com.company.model.dao.connection.ConnectionFactory;
import com.company.model.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDao<T extends Entity> implements IGenericDao<T> {
    ConnectionFactory connectionFactory;

    AbstractDao(ConnectionFactory factory) {
        connectionFactory = factory;
    }

    protected abstract String getQueryForCreate();
    protected abstract String getQueryForGet();
    protected abstract String getQueryForGetAll();
    protected abstract String getQueryForUpdate();
    protected abstract String getQueryForDelete();


    protected abstract void prepareStatementForCreate(
            PreparedStatement statement, T entity) throws SQLException;
    protected abstract void prepareStatementForUpdate(
            PreparedStatement statement, T entity) throws SQLException;

    protected abstract List<T> parseResultSet(ResultSet rs) throws SQLException;


    @Override
    public void create(T entity) throws DaoException {
        String query = getQueryForCreate();
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            prepareStatementForCreate(statement, entity);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DaoException("Modified more then 1 record");
            }
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        }
    }

    @Override
    public T get(int id) throws DaoException {
        List<T> list;
        String query = getQueryForGet();
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        }
        if (list == null || list.size() == 0) {
            return null;
        }
        if (list.size() > 1) {
            throw new DaoException("Received more then 1 parameter");
        }
        return list.iterator().next();
    }

    @Override
    public List<T> getAll() throws DaoException {
        List<T> list;
        String query = getQueryForGetAll();
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        }
        return list;
    }

    @Override
    public void update(T entity) throws DaoException {
        String query = getQueryForUpdate();
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            prepareStatementForUpdate(statement, entity);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DaoException("Modified more then 1 record");
            }
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        }
    }

    @Override
    public void delete(T entity) throws DaoException {
        String query = getQueryForDelete();
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, entity.getId());
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DaoException("Modified more then 1 record");
            }
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        }
    }
}
