package com.company.model.dao.impl;

import com.company.model.domain.Author;
import com.company.model.domain.Book;
import com.company.model.dao.IAuthorDao;
import com.company.model.dao.IBookDao;
import com.company.model.dao.connection.ConnectionFactory;
import com.company.model.dao.constants.Parameter;
import com.company.model.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.company.model.dao.constants.SqlQueries.*;
import static com.company.model.util.SqlQueriesManager.getQuery;

public class BookDao extends AbstractDao<Book> implements IBookDao {

    BookDao(ConnectionFactory factory) {
        super(factory);
    }

    @Override
    protected String getQueryForCreate() {
        return getQuery(ADD_BOOK);
    }

    @Override
    protected String getQueryForGet() {
        return getQuery(GET_BOOK_BY_ID);
    }

    @Override
    protected String getQueryForGetAll() {
        return getQuery(GET_ALL_BOOKS);
    }

    @Override
    protected String getQueryForUpdate() {
        return getQuery(UPDATE_BOOK);
    }

    @Override
    protected String getQueryForDelete() {
        return getQuery(DELETE_BOOK);
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Book entity)
            throws SQLException {
        statement.setString(1, entity.getTitle());
        statement.setString(2, entity.getPublisher());
        statement.setInt(3, entity.getPublishingYear());
        statement.setString(4, entity.getGenre());
        statement.setString(5, entity.getDescription());
        statement.setInt(6, 0);
        statement.setString(7, entity.getLanguage());
    }

    private void prepareStatementForRelationCreate(PreparedStatement statementForRelation,
                                                   Book entity,
                                                   IAuthorDao authorDao,
                                                   Author author)
            throws SQLException, DaoException {
        statementForRelation.setInt(1, entity.getId());
        statementForRelation.setInt(2, authorDao.getAuthorByNameAndSurname(author).getId());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Book entity)
            throws SQLException {
        statement.setString(1, entity.getTitle());
        statement.setString(2, entity.getPublisher());
        statement.setInt(3, entity.getPublishingYear());
        statement.setString(4, entity.getGenre());
        statement.setString(5, entity.getDescription());
        statement.setInt(6, entity.isTaken() ? 1 : 0);
        statement.setInt(7, entity.getId());
    }

    @Override
    protected List<Book> parseResultSet(ResultSet rs) throws SQLException {
        List<Book> books = new ArrayList<>();
        int id;
        Book book = new Book();
        while (rs.next()) {
            id = rs.getInt("Book_ID");
            if (id != book.getId() ) {
                book = getBook(rs, id);
                books.add(book);
            }
            Author author = new Author();
            author.setId(rs.getInt("Author_ID"));
            author.setName(rs.getString("Name"));
            author.setSurname(rs.getString("Surname"));
            book.addAuthor(author);
        }
        return books;
    }

    @Override
    public void create(Book entity) throws DaoException {
        String queryForBook = getQueryForCreate();
        String queryForRelation = getQuery(ADD_RELATION);
        Connection connection;
        try {
            connection = connectionFactory.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(queryForBook);
                 PreparedStatement statementForRelation = connection.prepareStatement(queryForRelation)) {

                connection.setAutoCommit(false);
                prepareStatementForCreate(statement, entity);
                statement.executeUpdate();
                connection.commit();

                entity.setId(getIdByTitle(entity.getTitle()));
                createAuthorsAndRelations(entity, statementForRelation);

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new DaoException("Request failed", e);
            } finally {
                connection.setAutoCommit(true);
                connection.close();
            }
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        }
    }

    @Override
    public void update(Book entity) throws DaoException {
        String queryForBook = getQueryForUpdate();
        String queryForAddRelation = getQuery(ADD_RELATION);
        String queryForDeleteRelations = getQuery(DELETE_RELATIONS);
        Connection connection;
        try {
            connection = connectionFactory.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(queryForBook);
                 PreparedStatement stAddRelation = connection.prepareStatement(queryForAddRelation);
                 PreparedStatement stDeleteRelation = connection.prepareStatement(queryForDeleteRelations)) {

                connection.setAutoCommit(false);
                prepareStatementForUpdate(statement, entity);
                statement.executeUpdate();

                stDeleteRelation.setInt(1, entity.getId());
                stDeleteRelation.executeUpdate();

                createAuthorsAndRelations(entity, stAddRelation);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new DaoException("Request failed", e);
            } finally {
                connection.setAutoCommit(true);
                connection.close();
            }
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        }
    }

    @Override
    public void delete(Book entity) throws DaoException {
        String queryForBook = getQueryForDelete();
        String queryForDeleteRelations = getQuery(DELETE_RELATIONS);
        Connection connection;
        try {
            connection = connectionFactory.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(queryForBook);
                 PreparedStatement stDeleteRelation = connection.prepareStatement(queryForDeleteRelations)) {

                connection.setAutoCommit(false);
                stDeleteRelation.setInt(1, entity.getId());
                stDeleteRelation.executeUpdate();

                statement.setInt(1, entity.getId());
                statement.executeUpdate();

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new DaoException("Request failed", e);
            } finally {
                connection.setAutoCommit(true);
                connection.close();
            }
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        }
    }

    @Override
    public List<Book> getBooksByClientQuery(String query) throws DaoException {
        Parameter parameter = Parameter.QUERY;
        parameter.setQuery(getQuery(GET_BOOKS_BY_QUERY));
        parameter.setArgument(query);
        return getBooksPyParameter(parameter);
    }

    @Override
    public List<Book> getBooksByAuthor(Author author) throws DaoException {
        Parameter parameter = Parameter.AUTHOR;
        parameter.setQuery(getQuery(GET_BOOKS_BY_AUTHOR));
        parameter.setArgument(author.getId().toString());
        return getBooksPyParameter(parameter);
    }

    @Override
    public List<Book> getBooksByGenre(String genre) throws DaoException {
        Parameter parameter = Parameter.GENRE;
        parameter.setQuery(getQuery(GET_BOOKS_BY_GENRES));
        parameter.setArgument(genre);
        return getBooksPyParameter(parameter);
    }

    @Override
    public List<Book> getByLanguage(String lang) throws DaoException {
        Parameter parameter = Parameter.LANGUAGE;
        parameter.setQuery(getQueryForGetAll());
        parameter.setArgument(lang);
        return getBooksPyParameter(parameter);
    }

    private List<Book> getBooksPyParameter(Parameter parameter) throws DaoException {
        List<Book> books;
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     parameter.getQuery())) {
            switch (parameter) {
                case QUERY:
                    String usersQuery = String.format("%%%s%%", parameter.getArgument());
                    statement.setString(1, usersQuery);
                    statement.setString(2, usersQuery);
                    statement.setString(3, usersQuery);
                    break;
                case AUTHOR:
                    statement.setInt(1, Integer.parseInt(parameter.getArgument()));
                    break;
                case GENRE:
                    statement.setString(1, parameter.getArgument());
                    break;
                case LANGUAGE:
                    statement.setString(1, parameter.getArgument());
                    break;
            }
            ResultSet rs = statement.executeQuery();
            books = parseResultSet(rs);
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        }
        if (books == null || books.size() == 0) {
            return null;
        }
        return books;
    }

    @Override
    public List<String> getGenresByLanguage(String language) throws DaoException {
        List<String> genres;
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     getQuery(GET_GENRES))){
            statement.setString(1, language);
            ResultSet rs = statement.executeQuery();
            genres = parseResultSetForGenres(rs);
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        }
        return genres;
    }

    private List<String> parseResultSetForGenres(ResultSet rs) throws SQLException {
        List<String> result = new ArrayList<>();
        while (rs.next()) {
            result.add(rs.getString("Genre"));
        }
        return result;
    }

    private int getIdByTitle(String title) throws DaoException {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     getQuery(GET_BOOK_ID_BY_TITLE))) {
            statement.setString(1, title);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("Book_ID");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        }
    }

    private void createAuthorsAndRelations(Book entity, PreparedStatement stAddRelation)
            throws DaoException, SQLException {
        IAuthorDao authorDao = DaoFactory.getInstance().getAuthorDao();
        for (Author author : entity.getAuthors()) {
            try {
                author.setLanguage(entity.getLanguage());
                authorDao.create(author);
            } catch (DaoException e) {
                if (!e.getMessage().equals("Such author already exists")) {
                    throw e;
                }
            }
            prepareStatementForRelationCreate(stAddRelation,
                    entity, authorDao, author);
            stAddRelation.executeUpdate();
        }
    }

    private Book getBook(ResultSet rs, int id) throws SQLException {
        Book book;
        book = new Book();
        book.setId(id);
        book.setTitle(rs.getString("Title"));
        book.setPublisher(rs.getString("Publisher"));
        book.setPublishingYear(rs.getInt("Publishing_Year"));
        book.setGenre(rs.getString("Genre"));
        book.setDescription(rs.getString("Description"));
        book.setTaken(rs.getInt("Taken") == 1);
        return book;
    }
}
