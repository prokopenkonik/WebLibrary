package com.company.model.dao.impl;

import com.company.domain.Author;
import com.company.model.dao.AbstractDao;
import com.company.model.dao.IAuthorDao;
import com.company.model.dao.connection.ConnectionFactory;
import com.company.model.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao extends AbstractDao<Author> implements IAuthorDao {

    public AuthorDao(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    @Override
    public void create(Author entity) throws DaoException {

    }

    @Override
    public Author get(int id) throws DaoException {
        return null;
    }

    @Override
    public List<Author> getAll() throws DaoException {
        List<Author> authors = new ArrayList<>();
        Connection connection;
        PreparedStatement statement;
        return null;
    }

    @Override
    public void update(Author entity) throws DaoException {

    }

    @Override
    public void delete(Author entity) throws DaoException {

    }
}
