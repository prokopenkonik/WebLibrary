package com.company.model.dao;

import com.company.domain.Book;
import com.company.model.exception.DaoException;

import java.util.List;

public interface IBookDao extends IGenericDao<Book> {
    List<Book> getBooksWithAuthors() throws DaoException;
    List<Book> getBooksByClientQuery(String query) throws DaoException;
}
