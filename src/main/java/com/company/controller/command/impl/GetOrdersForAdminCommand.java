package com.company.controller.command.impl;

import com.company.controller.command.Command;
import com.company.controller.util.BooksCrud;
import com.company.domain.Order;
import com.company.model.dao.IDaoFactory;
import com.company.model.dao.IOrderDao;
import com.company.model.dao.impl.DaoFactory;
import com.company.model.exception.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetOrdersForAdminCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        IDaoFactory factory = DaoFactory.getInstance();
        try {
            IOrderDao orderDao = factory.getOrderDao();
            List<Order> orders = orderDao.getAll();
            request.setAttribute("list", orders);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return BooksCrud.GET_ORDERS_FOR_ADMIN_JSP;
    }
}
