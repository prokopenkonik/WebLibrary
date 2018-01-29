package com.company.controller.command.impl.books;

import com.company.controller.command.Command;
import com.company.controller.util.BookUtil;
import com.company.controller.util.PagesPaths;
import com.company.model.domain.Author;
import com.company.model.domain.Book;
import com.company.model.dao.IBookDao;
import com.company.model.dao.IDaoFactory;
import com.company.model.dao.impl.DaoFactory;
import com.company.model.exception.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetBooksByAuthorCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        IDaoFactory factory = DaoFactory.getInstance();
        try {
            IBookDao bookDao = factory.getBookDao();
            String language;
            if (request.getSession(true).getAttribute("language") == null) {
                language = "ru_RU";
            } else {
                language = request.getSession(true).getAttribute("language").toString();
            }
            Author author = new Author();
            author.setId(Integer.parseInt(request.getParameter("author_id")));
            List<Book> books = bookDao.getBooksByAuthor(author);
            request.setAttribute("list", books);

            BookUtil.setSortingParameters(request, factory, language);

        } catch (DaoException e) {
            e.printStackTrace();
        }
        return PagesPaths.LIST_ALL_BOOKS_JSP;
    }
}
