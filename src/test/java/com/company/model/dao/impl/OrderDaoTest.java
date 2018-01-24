package com.company.model.dao.impl;

import com.company.domain.Order;
import com.company.model.dao.IOrderDao;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class OrderDaoTest {

    private IOrderDao orderDao;

    @Before
    public void setUp() throws Exception {
        orderDao = DaoFactory.getInstance().getOrderDao();
    }

    @Ignore
    @Test
    public void create() throws Exception {
    }

    @Test
    public void getAll() throws Exception {
        List<Order> orders = orderDao.getAll();
        assertNotNull(orders);
        assertTrue(orders.size() > 0);
        assertNotNull(orders.get(0).getBook());
        assertNotNull(orders.get(0).getUser());
    }

    @Test
    public void getByUsersId() throws Exception {
        List<Order> orders = orderDao.getByUsersId(10);
        assertNotNull(orders);
        assertTrue(orders.size() > 0);
        assertNotNull(orders.get(0).getBook());
    }
}