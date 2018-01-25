package com.company.controller.command.impl.orders;

import com.company.controller.command.Command;
import com.company.domain.Order;
import com.company.model.dao.IDaoFactory;
import com.company.model.dao.IOrderDao;
import com.company.model.dao.impl.DaoFactory;
import com.company.model.exception.DaoException;

import javax.servlet.http.HttpServletRequest;

public class AcceptOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        IDaoFactory factory = DaoFactory.getInstance();
        try {
            IOrderDao orderDao = factory.getOrderDao();
            Order order = orderDao.get(Integer.parseInt(request.getParameter("order_id")));
            order.setAccepted(true);
            orderDao.update(order);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return new GetOrdersForAdminCommand().execute(request);
    }
}
