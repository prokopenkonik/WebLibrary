package com.company.model.dao.impl;

import com.company.domain.Author;
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

    @Ignore
    @Test
    public void create() throws Exception {
        Book book = new Book();
        book.setTitle("Mybook");
        book.setPublisher("New publisher");
        book.setPublishingYear(1230);
        book.setGenre("Genre");
        book.setDescription("eeeeeeeeee");
        Author author = new Author("New", "Author");
        book.addAuthor(author);
        author = new Author("Александр", "Пушкин");
        book.addAuthor(author);
        bookDao.create(book);

        List<Book> books = bookDao.getBooksByClientQuery("Mybook");
        Assert.assertNotNull(books);
        Assert.assertTrue(books.size() == 1);
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

    @Test
    public void update() throws Exception {
        String title = "New title";
        Book book = bookDao.get(11);
        book.setTitle(title);
        Author author = new Author("www","www");
        book.addAuthor(author);
        bookDao.update(book);
        Book modifiedBook = bookDao.get(11);
        Assert.assertEquals(modifiedBook.getTitle(), title);
        Assert.assertTrue(book.getAuthors().size() > 1);
        Assert.assertEquals(book.getAuthors().get(1).getName(), author.getName());
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