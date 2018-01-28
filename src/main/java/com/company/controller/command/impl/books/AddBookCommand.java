package com.company.controller.command.impl.books;

import com.company.controller.command.Command;
import com.company.controller.util.AuthorParser;
import com.company.model.domain.Book;
import com.company.model.dao.IBookDao;
import com.company.model.dao.IDaoFactory;
import com.company.model.dao.impl.DaoFactory;
import com.company.model.exception.DaoException;

import javax.servlet.http.HttpServletRequest;

public class AddBookCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        IDaoFactory factory = DaoFactory.getInstance();
        try {
            IBookDao bookDao = factory.getBookDao();
            Book book = new Book();
            book.setTitle(request.getParameter("title"));
            book.setAuthors(AuthorParser.parseAuthors(request.getParameter("authors")));
            book.setGenre(request.getParameter("genre"));
            book.setPublisher(request.getParameter("publisher"));
            book.setPublishingYear(Integer.parseInt(request.getParameter("publishingYear")));
            book.setDescription(request.getParameter("description"));
            bookDao.create(book);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return new GetAllBooksCommand().execute(request);
    }
}
