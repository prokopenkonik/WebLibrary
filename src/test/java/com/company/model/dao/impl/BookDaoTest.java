package com.company.model.dao.impl;

import com.company.domain.Book;
import com.company.model.dao.IBookDao;
import com.company.model.dao.IDaoFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public class BookDaoTest {

    private IBookDao bookDao;

    @Before
    public void setUp() throws Exception {
        IDaoFactory factory = DaoFactory.getInstance();
        bookDao = factory.getBookDao();
    }

    @Test
    public void get() throws Exception {
        Book book = bookDao.get(1);
        Assert.assertNotNull(book);
    }

    @Test
    public void getAll() throws Exception {
        List<Book> books;
        books = bookDao.getAll();
        Assert.assertNotNull(books);
        Assert.assertTrue(books.size() > 0);
    }

    @Test
    public void getBooksWithAuthors() throws Exception {
        List<Book> books;
        books = bookDao.getBooksWithAuthors();
        Assert.assertNotNull(books);
        Assert.assertTrue(books.size() > 0);
        Assert.assertNotNull(books.get(0).getAuthors());
    }

    @Test
    public void getBooksByClientQuery() throws Exception {
        List<Book> books = bookDao.getBooksByClientQuery("пушкин");
        Assert.assertNotNull(books);
        Assert.assertTrue(books.size() > 0);
    }

    @Ignore
    @Test
    public void delete() throws Exception {
        Book book = new Book();
        book.setId(12);
        bookDao.delete(book);
        book = bookDao.get(12);
        Assert.assertNull(book);
    }
}