package com.company.model.dao;

import com.company.domain.Author;
import com.company.model.exception.DaoException;

public interface IAuthorDao extends IGenericDao<Author> {
    Author getAuthorByNameAndSurname(Author author) throws DaoException;
}
