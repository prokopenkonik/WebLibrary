package com.company.model.dao.impl;

import com.company.domain.Author;
import com.company.model.dao.IAuthorDao;
import com.company.model.dao.IDaoFactory;
import com.company.model.dao.connection.ConnectionPool;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AuthorDaoTest {

    private IAuthorDao authorDao;

    @Before
    public void setUp() throws Exception {
        IDaoFactory factory = DaoFactory.getInstance();
        authorDao = factory.getAuthorDao();
    }

    @Test
    public void create() throws Exception {
        Author author = new Author("zzz", "xxx");
        authorDao.create(author);

        Author otherAuthor = authorDao.getAuthorByNameAndSurname(author);
        assertNotNull(otherAuthor);
        assertEquals(author.getName(), otherAuthor.getName());
    }

    @Test
    public void getAuthorByNameAndSurname() throws Exception {
        Author author = new Author("Михаил", "Булгаков");
        Author otherAuthor = authorDao.getAuthorByNameAndSurname(author);
        assertNotNull(otherAuthor);
        assertEquals(author.getName(), otherAuthor.getName());
    }

    @Test
    public void get() throws Exception {
        Author author = authorDao.get(1);
        assertNotNull(author);
        assertEquals("Пушкин", author.getSurname());
    }

    @Test
    public void getAll() throws Exception {
        List<Author> authors = authorDao.getAll();
        assertNotNull(authors);
        assertTrue(authors.size() > 0);
    }

    @Test
    public void update() throws Exception {
        Author author = authorDao.get(1);
        author.setName("Саша");
        authorDao.update(author);

        Author otherAuthor = authorDao.get(1);
        assertEquals("Саша", otherAuthor.getName());
    }
}