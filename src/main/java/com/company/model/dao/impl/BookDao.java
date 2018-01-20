package com.company.model.dao.impl;

import com.company.domain.Author;
import com.company.domain.Book;
import com.company.model.dao.AbstractDao;
import com.company.model.dao.IBookDao;
import com.company.model.dao.connection.ConnectionFactory;
import com.company.model.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao extends AbstractDao<Book> implements IBookDao {

    private static final String SQL_DELETE_BOOK = "delete from books where Book_ID=?";
    private static final String SQL_DELETE_BOOKS_AND_AUTHORS = "delete from books_and_authors where Book_ID=?";
    private static final String SQL_GET_ALL_BOOKS = "SELECT * FROM books";
    private static final String SQL_GET_BY_ID = "SELECT * FROM books WHERE Book_ID = ?";
    private static final String SQL_SORT_BY_ID_PATTERN = " order by books.Book_ID";
    private static final String SQL_GET_ALL_BOOKS_WITH_AUTHORS_PATTERN = "select * from books join books_and_authors on books.Book_ID=books_and_authors.Book_ID\n" +
            "join authors on authors.Authors_ID=books_and_authors.Authors_ID";
    private static final String SQL_GET_ALL_BOOKS_WITH_AUTHORS =
            SQL_GET_ALL_BOOKS_WITH_AUTHORS_PATTERN + SQL_SORT_BY_ID_PATTERN;
    private static final String SQL_SEARCH_BOOK = SQL_GET_ALL_BOOKS_WITH_AUTHORS_PATTERN
            + " where books.Title like ? or authors.Surname like ?"
            + SQL_SORT_BY_ID_PATTERN;

    BookDao(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    @Override
    public void create(Book entity) throws DaoException {

    }

    @Override
    public Book get(int id) throws DaoException {
        List<Book> books;
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(SQL_GET_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            books = parseResultSet(rs, false);
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        } finally {
            this.close(statement);
        }
        if (books == null || books.size() == 0) {
            return null;
        }
        if (books.size() > 1) {
            throw new DaoException("Received more then 1 parameter");
        }
        return books.iterator().next();
    }

    @Override
    public List<Book> getAll() throws DaoException {
        List<Book> books;
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(SQL_GET_ALL_BOOKS);
            ResultSet rs = statement.executeQuery();
            books = parseResultSet(rs, false);
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        } finally {
            this.close(statement);
        }
        if (books == null || books.size() == 0) {
            return null;
        }
        return books;
    }

    @Override
    public void update(Book entity) throws DaoException {

    }

    @Override
    public void delete(Book entity) throws DaoException {
        Connection connection;
        try {
            connection = connectionFactory.getConnection();
            PreparedStatement statement = null;
            try {
                connection.setAutoCommit(false);
                statement = connection.prepareStatement(SQL_DELETE_BOOKS_AND_AUTHORS);
                statement.setInt(1, entity.getId());
                statement.executeUpdate();
                statement = connection.prepareStatement(SQL_DELETE_BOOK);
                statement.setInt(1, entity.getId());
                statement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new DaoException("Request failed", e);
            } finally {
                connection.setAutoCommit(true);
                this.close(statement);
            }
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        }

    }

    @Override
    public List<Book> getBooksWithAuthors() throws DaoException {
        List<Book> books;
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(SQL_GET_ALL_BOOKS_WITH_AUTHORS);
            ResultSet rs = statement.executeQuery();
            books = parseResultSet(rs, true);
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        } finally {
            this.close(statement);
        }
        if (books == null || books.size() == 0) {
            return null;
        }
        return books;
    }

    @Override
    public List<Book> getBooksByClientQuery(String query) throws DaoException {
        List<Book> books;
        Connection connection;
        PreparedStatement statement = null;
        StringBuilder modifiedQuery = new StringBuilder("%").append(query).append("%");
        try {
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(SQL_SEARCH_BOOK);
            statement.setString(1, modifiedQuery.toString());
            statement.setString(2, modifiedQuery.toString());
            ResultSet rs = statement.executeQuery();
            books = parseResultSet(rs, true);
        } catch (SQLException e) {
            throw new DaoException("Request failed", e);
        } finally {
            this.close(statement);
        }
        if (books == null || books.size() == 0) {
            return null;
        }
        return books;
    }

    private List<Book> parseResultSet(ResultSet rs, boolean includeAuthors)
            throws SQLException {
        List<Book> books = new ArrayList<>();
        int id;
        Book book = new Book();
        while (rs.next()) {
            id = rs.getInt("Book_ID");
            if (id != book.getId() ) {
                book = getBook(rs, id);
                books.add(book);
            }
            if (includeAuthors) {
                Author author = new Author();
                author.setId(rs.getInt("Authors_ID"));
                author.setName(rs.getString("Name"));
                author.setSurname(rs.getString("Surname"));
                book.addAuthor(author);
            }
        }
        return books;
    }

    private Book getBook(ResultSet rs, int id) throws SQLException {
        Book book;
        book = new Book();
        book.setId(id);
        book.setTitle(rs.getString("Title"));
        book.setCopiesCount(rs.getInt("Copies_Count"));
        book.setPublisher(rs.getString("Publisher"));
        book.setPublishingYear(rs.getInt("Publishing_Year"));
        book.setGenre(rs.getString("Genre"));
        book.setDescription(rs.getString("Description"));
        return book;
    }
}
