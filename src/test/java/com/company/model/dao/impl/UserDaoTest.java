package com.company.model.dao.impl;

import com.company.domain.User;
import com.company.model.dao.IDaoFactory;
import com.company.model.dao.IUserDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserDaoTest {

    private IUserDao userDao;

    @Before
    public void setUp() throws Exception {
        IDaoFactory factory = DaoFactory.getInstance();
        userDao = factory.getUserDao();
    }

    @Test
    public void getUserByLogin() throws Exception {
        String login = "user123";
        User user = userDao.getUserByLogin(login);
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getLogin(), login);
    }
}