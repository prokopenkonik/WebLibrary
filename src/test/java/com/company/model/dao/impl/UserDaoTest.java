package com.company.model.dao.impl;

import com.company.model.domain.User;
import com.company.model.dao.IDaoFactory;
import com.company.model.dao.IUserDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
@Ignore
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

    @Ignore
    @Test
    public void create() throws Exception {
        User user = new User();
        user.setLogin("useruser");
        user.setPassword("123");
        user.setPhoneNumber("123456789");
        user.setName("Name Surname");
        user.setMail("qwe@gmail.com");
        userDao.create(user);
        User otherUser = userDao.getUserByLogin(user.getLogin());
        user.setId(otherUser.getId());
        Assert.assertNotNull(otherUser);
        Assert.assertEquals(user, otherUser);
    }
}