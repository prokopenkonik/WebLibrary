package com.company.controller.command.impl.orders;

import com.company.controller.command.Command;
import com.company.domain.Book;
import com.company.domain.Order;
import com.company.model.dao.IBookDao;
import com.company.model.dao.IDaoFactory;
import com.company.model.dao.IOrderDao;
import com.company.model.dao.impl.DaoFactory;
import com.company.model.exception.DaoException;

import javax.servlet.http.HttpServletRequest;

public class DenyOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        IDaoFactory factory = DaoFactory.getInstance();
        try {
            IOrderDao orderDao = factory.getOrderDao();
            Order order = new Order();
            order.setId(Integer.parseInt(request.getParameter("order_id")));
            orderDao.delete(order);

            IBookDao bookDao = factory.getBookDao();
            Book book = bookDao.get(Integer.parseInt(request.getParameter("book_id")));
            book.setTaken(false);
            bookDao.update(book);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return new GetOrdersForAdminCommand().execute(request);
    }
}
