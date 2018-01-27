package com.company.model.dao.impl;

import com.company.domain.Book;
import com.company.domain.Order;
import com.company.domain.User;
import com.company.model.dao.IOrderDao;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDaoTest {

    private IOrderDao orderDao;

    @Before
    public void setUp() throws Exception {
        orderDao = DaoFactory.getInstance().getOrderDao();
    }

    @Test
    public void getByUsersId() throws Exception {
        List<Order> orders = orderDao.getByUsersId(10);
        assertNotNull(orders);
        assertTrue(orders.size() > 0);
    }

    @Test
    public void create() throws Exception {
        Order order = new Order();
        order.setEndingDate(GregorianCalendar.getInstance().getTime());
        order.setCreationDate(GregorianCalendar.getInstance().getTime());

        User user = new User();
        user.setId(11);
        order.setUser(user);

        Book book = new Book();
        book.setId(1);
        order.setBook(book);

        orderDao.create(order);

        List<Order> orders = orderDao.getByUsersId(user.getId());
        assertNotNull(orders);
        assertTrue(orders.size() > 0);
    }

    @Test
    public void get() throws Exception {
        Order order = orderDao.get(7);
        assertNotNull(order);
    }

    @Test
    public void getAll() throws Exception {
        List<Order> orders = orderDao.getAll();
        assertNotNull(orders);
        assertTrue(orders.size() > 0);
    }

    @Test
    public void update() throws Exception {
        Order order = orderDao.get(7);
        order.setEndingDate(GregorianCalendar.getInstance().getTime());
        orderDao.update(order);

        Order otherOrder = orderDao.get(7);
        assertNotNull(otherOrder);
    }

    @Test
    public void delete() throws Exception {
        Order order = new Order();
        order.setEndingDate(GregorianCalendar.getInstance().getTime());
        order.setCreationDate(GregorianCalendar.getInstance().getTime());

        User user = new User();
        user.setId(11);
        order.setUser(user);

        Book book = new Book();
        book.setId(1);
        order.setBook(book);

        orderDao.create(order);
        List<Order> orders = orderDao.getByUsersId(user.getId());
        int id = orders.get(orders.size() - 1).getId();
        order.setId(id);
        orderDao.delete(order);

        Order otherOrder = orderDao.get(id);
        assertNull(otherOrder);
    }
}