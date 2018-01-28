package com.company.controller.command.impl.users;

import com.company.controller.command.Command;
import com.company.controller.command.impl.books.GetAllBooksCommand;
import com.company.controller.util.Encryptor;
import com.company.model.domain.User;
import com.company.model.dao.IDaoFactory;
import com.company.model.dao.IUserDao;
import com.company.model.dao.impl.DaoFactory;
import com.company.model.exception.DaoException;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        IDaoFactory factory = DaoFactory.getInstance();
        try {
            IUserDao userDao = factory.getUserDao();
            User user = new User();
            user.setLogin(request.getParameter("login"));
            user.setPassword(Encryptor.getHash(request.getParameter("pass")));
            user.setName(String.format("%s %s",
                    request.getParameter("name"),
                    request.getParameter("surname")));
            user.setMail(request.getParameter("mail"));
            user.setPhoneNumber(request.getParameter("phone"));
            userDao.create(user);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return new GetAllBooksCommand().execute(request);
    }
}
