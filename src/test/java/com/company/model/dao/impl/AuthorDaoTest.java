package com.company.model.dao.impl;

import com.company.domain.Author;
import com.company.model.dao.IAuthorDao;
import com.company.model.dao.IDaoFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuthorDaoTest {

    private IAuthorDao authorDao;

    @Before
    public void setUp() throws Exception {
        IDaoFactory factory = DaoFactory.getInstance();
        authorDao = factory.getAuthorDao();
    }

    @Ignore
    @Test
    public void create() throws Exception {
        Author author = new Author("Name", "Surname");
        authorDao.create(author);
        Author otherAuthor = authorDao.getAuthorByNameAndSurname(author);
        Assert.assertEquals(otherAuthor.getName(), author.getName());
    }
}