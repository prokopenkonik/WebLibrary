package com.company.model.dao.impl;

import com.company.domain.Author;
import com.company.model.dao.AbstractDao;
import com.company.model.dao.IAuthorDao;
import com.company.model.dao.connection.ConnectionFactory;
import com.company.model.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao extends AbstractDao<Author> implements IAuthorDao {

    private static final String SQL_GET_BY_NAME_AND_SURNAME =
            "SELECT * FROM authors WHERE name=? AND surname=?";
    private static final String SQL_ADD_AUTHOR =
            "INSERT INTO authors (Name, Surname) VALUES(?,?)";
    private static final String SQL_UPDATE_AUTHOR =
            "UPDATE authors SET Name=?, Surname=? WHERE Author_ID=?";
    private static final String SQL_GET_BY_ID =
            "SELECT * FROM authors WHERE Author_ID=?";

    AuthorDao(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    @Override
    public void create(Author entity) throws DaoException {
        Author author = getAuthorByNameAndSurname(entity);
        if (author != null) {
            throw new DaoException("Such author already exists");
        }
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(SQL_ADD_AUTHOR);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DaoException("Modified more then 1 record");
            }
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        } finally {
            this.close(statement);
        }
    }

    @Override
    public Author get(int id) throws DaoException {
        List<Author> authors;
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(SQL_GET_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            authors = parseResult(rs);
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        } finally {
            this.close(statement);
        }
        if (authors == null || authors.size() == 0) {
            return null;
        }
        if (authors.size() > 1) {
            throw new DaoException("Received more then 1 parameter");
        }
        return authors.iterator().next();
    }

    @Override
    public List<Author> getAll() throws DaoException {
        return null;
    }

    @Override
    public void update(Author entity) throws DaoException {
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_AUTHOR);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getName());
            statement.setString(4, entity.getSurname());
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DaoException("Modified more then 1 record");
            }
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        } finally {
            this.close(statement);
        }
    }

    @Override
    public void delete(Author entity) throws DaoException {

    }

    @Override
    public Author getAuthorByNameAndSurname(Author author) throws DaoException {
        List<Author> authors;
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(SQL_GET_BY_NAME_AND_SURNAME);
            statement.setString(1, author.getName());
            statement.setString(2, author.getSurname());
            ResultSet rs = statement.executeQuery();
            authors = parseResult(rs);
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        } finally {
            this.close(statement);
        }
        if (authors == null || authors.size() == 0) {
            return null;
        }
        if (authors.size() > 1) {
            throw new DaoException("Received more then 1 parameter");
        }
        return authors.iterator().next();
    }

    private List<Author> parseResult(ResultSet rs) throws SQLException {
        List<Author> authors = new ArrayList<>();
        Author author;
        while (rs.next()) {
            author = new Author();
            author.setName(rs.getString("Name"));
            author.setSurname(rs.getString("Surname"));
            author.setId(rs.getInt("Author_ID"));
            authors.add(author);
        }
        return authors;
    }
}
