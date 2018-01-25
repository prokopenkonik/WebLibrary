package com.company.controller.command.impl.books;

import com.company.controller.command.Command;
import com.company.controller.util.BooksCrud;
import com.company.domain.Book;
import com.company.model.dao.IBookDao;
import com.company.model.dao.IDaoFactory;
import com.company.model.dao.impl.DaoFactory;
import com.company.model.exception.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SearchBookCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        IDaoFactory factory = DaoFactory.getInstance();
        try {
            IBookDao bookDao = factory.getBookDao();
            String query = request.getParameter("query");
            List<Book> books = bookDao.getBooksByClientQuery(query);
            request.setAttribute("list", books);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return BooksCrud.SEARCH_BOOK_JSP;
    }
}
