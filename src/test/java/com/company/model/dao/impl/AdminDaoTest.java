package com.company.model.dao.impl;

import com.company.domain.Administrator;
import com.company.model.dao.IAdminDao;
import com.company.model.dao.IDaoFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AdminDaoTest {

    private IAdminDao adminDao;

    @Before
    public void setUp() throws Exception {
        IDaoFactory factory = DaoFactory.getInstance();
        adminDao = factory.getAdminDao();
    }

    @Test
    public void getAdminByLogin() throws Exception {
        String login = "admin";
        Administrator admin = adminDao.getAdminByLogin(login);
        Assert.assertNotNull(admin);
        Assert.assertEquals(login, admin.getLogin());
    }
}