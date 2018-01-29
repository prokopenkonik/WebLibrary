package com.company.model.dao.impl;

import com.company.model.domain.Author;
import com.company.model.dao.IAuthorDao;
import com.company.model.dao.connection.ConnectionFactory;
import com.company.model.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.company.model.dao.constants.SqlQueries.*;
import static com.company.model.dao.constants.SqlQueries.GET_AUTHOR_BY_NAME_AND_SURNAME;
import static com.company.model.util.SqlQueriesManager.getQuery;

public class AuthorDao extends AbstractDao<Author> implements IAuthorDao {

    AuthorDao(ConnectionFactory factory) {
        super(factory);
    }

    @Override
    protected String getQueryForCreate() {
        return getQuery(ADD_AUTHOR);
    }

    @Override
    protected String getQueryForGet() {
        return getQuery(GET_AUTHOR_BY_ID);
    }

    @Override
    protected String getQueryForGetAll() {
        return getQuery(GET_ALL_AUTHORS);
    }

    @Override
    protected String getQueryForUpdate() {
        return getQuery(UPDATE_AUTHOR);
    }

    @Override
    protected String getQueryForDelete() {
        return null;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Author entity)
            throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getSurname());
        statement.setString(3, entity.getLanguage());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Author entity)
            throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getSurname());
        statement.setInt(3, entity.getId());
    }

    @Override
    protected List<Author> parseResultSet(ResultSet rs) throws SQLException {
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

    @Override
    public void create(Author entity) throws DaoException {
        Author author = getAuthorByNameAndSurname(entity);
        if (author != null) {
            throw new DaoException("Such author already exists");
        }
        super.create(entity);
    }

    @Override
    public Author getAuthorByNameAndSurname(Author author) throws DaoException {
        List<Author> authors;
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     getQuery(GET_AUTHOR_BY_NAME_AND_SURNAME))) {
            statement.setString(1, author.getName());
            statement.setString(2, author.getSurname());
            ResultSet rs = statement.executeQuery();
            authors = parseResultSet(rs);
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
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
    public List<Author> getByLanguage(String language) throws DaoException {
        List<Author> authors;
        String query = getQueryForGetAll();
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, language);
            ResultSet rs = statement.executeQuery();
            authors = parseResultSet(rs);
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        }
        return authors;
    }
}
