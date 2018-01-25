package com.company.controller.command.impl.books;

import com.company.controller.command.Command;
import com.company.controller.util.BooksCrud;
import com.company.domain.Author;
import com.company.domain.Book;
import com.company.model.dao.IAuthorDao;
import com.company.model.dao.IBookDao;
import com.company.model.dao.IDaoFactory;
import com.company.model.dao.impl.DaoFactory;
import com.company.model.exception.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetAllBooksCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        IDaoFactory factory = DaoFactory.getInstance();
        try {
            IBookDao bookDao = factory.getBookDao();
            List<Book> books = bookDao.getBooksWithAuthors();
            request.setAttribute("list", books);

            List<String> genres = bookDao.getGenres();
            request.setAttribute("genres", genres);

            IAuthorDao authorDao = factory.getAuthorDao();
            List<Author>  authors = authorDao.getAll();
            request.setAttribute("authors", authors);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return BooksCrud.LIST_ALL_BOOKS_JSP;
    }
}
