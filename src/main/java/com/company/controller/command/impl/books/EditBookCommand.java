package com.company.controller.command.impl.books;

import com.company.controller.command.Command;
import com.company.controller.util.AuthorParser;
import com.company.domain.Book;
import com.company.model.dao.IBookDao;
import com.company.model.dao.IDaoFactory;
import com.company.model.dao.impl.DaoFactory;
import com.company.model.exception.DaoException;

import javax.servlet.http.HttpServletRequest;

public class EditBookCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        IDaoFactory factory = DaoFactory.getInstance();
        try {
            IBookDao bookDao = factory.getBookDao();
            Book book = new Book();
            book.setId(Integer.parseInt(request.getParameter("id")));
            book.setTitle(request.getParameter("title"));
            book.setAuthors(AuthorParser.parseAuthors(request.getParameter("authors")));
            book.setGenre(request.getParameter("genre"));
            book.setPublisher(request.getParameter("publisher"));
            book.setPublishingYear(Integer.parseInt(request.getParameter("publishingYear")));
            book.setDescription(request.getParameter("description"));
            bookDao.update(book);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return new GetAllBooksCommand().execute(request);
    }
}
