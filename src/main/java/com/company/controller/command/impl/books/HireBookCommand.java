package com.company.controller.command.impl.books;

import com.company.controller.command.Command;
import com.company.model.dao.IBookDao;
import com.company.model.dao.IDaoFactory;
import com.company.model.dao.IOrderDao;
import com.company.model.dao.impl.DaoFactory;
import com.company.model.domain.Book;
import com.company.model.domain.Order;
import com.company.model.domain.User;
import com.company.model.exception.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class HireBookCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            return "/pages/registration.jsp";
        }
        IDaoFactory factory = DaoFactory.getInstance();
        Date creationDate = GregorianCalendar.getInstance().getTime();
        try {
            Date endingDate = new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter("date"));
            if (creationDate.after(endingDate)) {
                request.setAttribute("error", "error.borrow");
                return new GetBookCommand().execute(request);
            }

            Order order = new Order();
            order.setCreationDate(creationDate);
            order.setEndingDate(endingDate);

            IBookDao bookDao = factory.getBookDao();
            Book book = bookDao.get(Integer.parseInt(request.getParameter("id")));
            book.setTaken(true);
            bookDao.update(book);

            order.setBook(book);
            order.setUser((User) request.getSession().getAttribute("user"));

            IOrderDao orderDao = factory.getOrderDao();
            orderDao.create(order);
        } catch (ParseException | DaoException e) {
            e.printStackTrace();
        }
        return new GetAllBooksCommand().execute(request);
    }
}
