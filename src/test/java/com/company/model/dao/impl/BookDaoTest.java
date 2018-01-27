package com.company.model.dao.impl;

import com.company.domain.Author;
import com.company.domain.Book;
import com.company.model.dao.IBookDao;
import com.company.model.dao.IDaoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {

    private IBookDao bookDao;
    private int id;

    @Before
    public void setUp() throws Exception {
        IDaoFactory factory = DaoFactory.getInstance();
        bookDao = factory.getBookDao();
    }

    @After
    public void tearDown() throws Exception {
        Book book = new Book();
        book.setId(id);
        bookDao.delete(book);
    }

    @Test
    public void create() throws Exception {
        Book book = new Book();
        book.setTitle("New Book");
        book.setPublisher("New publisher");
        book.setPublishingYear(1230);
        book.setGenre("New Genre");
        book.setDescription("Some description");

        Author author = new Author("New", "Author");
        book.addAuthor(author);
        author = new Author("Александр", "Пушкин");
        book.addAuthor(author);

        bookDao.create(book);

        List<Book> books = bookDao.getBooksByClientQuery("new book");
        assertNotNull(books);
        assertTrue(books.size() == 1);
        assertEquals(book.getTitle(), books.get(0).getTitle());
        id = books.get(0).getId();
    }

    @Test
    public void get() throws Exception {
        Book otherBook = bookDao.get(1);
        assertNotNull(otherBook);
        assertNotNull(otherBook.getAuthors());
    }

    @Test
    public void getAll() throws Exception {
        List<Book> books = bookDao.getAll();
        assertNotNull(books);
        assertTrue(books.size() > 1);
    }

    @Test
    public void getBooksByClientQuery() throws Exception {
        String authorSurname = "Пушкин";
        List<Book> books = bookDao.getBooksByClientQuery(authorSurname);
        assertNotNull(books);
        assertTrue(books.size() > 0);
        assertEquals(books.get(0).getAuthors().get(0).getSurname(), authorSurname);
    }

    @Test
    public void getBooksByAuthor() throws Exception {
        Author author = new Author();
        author.setId(1);
        List<Book> books = bookDao.getBooksByAuthor(author);
        assertNotNull(books);
        assertTrue(books.size() >= 1);
    }

    @Test
    public void getBooksByGenre() throws Exception {
        List<Book> books = bookDao.getBooksByGenre("Роман");
        assertNotNull(books);
        assertTrue(books.size() >= 1);
    }

    @Test
    public void getGenres() throws Exception {
        List<String> genres = bookDao.getGenres();
        assertNotNull(genres);
        assertEquals(genres.get(0), "Роман");
    }

    @Test
    public void update() throws Exception {
        Book book = bookDao.get(1);
        book.setPublishingYear(2015);
        bookDao.update(book);

        Book modifiedBook = bookDao.get(book.getId());
        assertEquals(modifiedBook.getTitle(), book.getTitle());
        assertEquals(modifiedBook.getPublishingYear(), 2015);
        assertTrue(book.getAuthors().size() > 0);
    }

    @Test
    public void delete() throws Exception {
        Book book = new Book();
        book.setTitle("Q");
        book.setGenre("W");
        book.addAuthor(new Author("E", "R"));
        bookDao.create(book);

        List<Book> books = bookDao.getBooksByClientQuery("Q");
        bookDao.delete(books.get(0));
        book = bookDao.get(books.get(0).getId());
        assertNull(book);
    }
}