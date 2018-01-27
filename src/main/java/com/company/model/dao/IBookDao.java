package com.company.model.dao;

import com.company.domain.Author;
import com.company.domain.Book;
import com.company.model.exception.DaoException;

import java.util.List;

public interface IBookDao extends IGenericDao<Book> {
    List<Book> getBooksByClientQuery(String query) throws DaoException;
    List<Book> getBooksByAuthor(Author author) throws DaoException;
    List<String> getGenres() throws DaoException;
    List<Book> getBooksByGenre(String genre) throws DaoException;
}
