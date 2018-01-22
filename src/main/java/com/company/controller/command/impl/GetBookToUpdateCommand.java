package com.company.controller.command.impl;

import com.company.controller.command.Command;
import com.company.controller.util.BooksCrud;
import com.company.domain.Book;
import com.company.model.dao.IBookDao;
import com.company.model.dao.IDaoFactory;
import com.company.model.dao.impl.DaoFactory;
import com.company.model.exception.DaoException;

import javax.servlet.http.HttpServletRequest;

public class GetBookToUpdateCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        IDaoFactory factory = DaoFactory.getInstance();
        try {
            IBookDao bookDao = factory.getBookDao();
            Book book = bookDao.get(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("book", book);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return BooksCrud.GET_BOOK_TO_UPDATE_JSP;
    }
}
