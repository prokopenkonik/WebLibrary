package com.company.controller.command.impl.books;

import com.company.controller.command.Command;
import com.company.model.domain.Book;
import com.company.model.dao.IBookDao;
import com.company.model.dao.IDaoFactory;
import com.company.model.dao.impl.DaoFactory;
import com.company.model.exception.DaoException;

import javax.servlet.http.HttpServletRequest;

public class DeleteBookCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        IDaoFactory factory = DaoFactory.getInstance();
        try {
            IBookDao bookDao = factory.getBookDao();
            Book book = new Book();
            book.setId(Integer.parseInt(request.getParameter("id")));
            bookDao.delete(book);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return new GetAllBooksCommand().execute(request);
    }
}
