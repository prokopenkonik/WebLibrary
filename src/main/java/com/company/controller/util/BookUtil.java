package com.company.controller.util;

import com.company.model.dao.IAuthorDao;
import com.company.model.dao.IBookDao;
import com.company.model.dao.IDaoFactory;
import com.company.model.domain.Author;
import com.company.model.exception.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BookUtil {
    public static void setSortingParameters(
            HttpServletRequest request, IDaoFactory factory, String language)
            throws DaoException {
        IBookDao bookDao = factory.getBookDao();
        List<String> genres = bookDao.getGenresByLanguage(language);
        request.setAttribute("genres", genres);

        IAuthorDao authorDao = factory.getAuthorDao();
        List<Author>  authors = authorDao.getByLanguage(language);
        request.setAttribute("authors", authors);
    }
}
