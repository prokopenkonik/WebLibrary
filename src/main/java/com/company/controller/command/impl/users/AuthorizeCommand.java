package com.company.controller.command.impl.users;

import com.company.controller.command.Command;
import com.company.controller.command.impl.books.GetAllBooksCommand;
import com.company.controller.util.Encryptor;
import com.company.domain.Administrator;
import com.company.domain.User;
import com.company.model.dao.IAdminDao;
import com.company.model.dao.IDaoFactory;
import com.company.model.dao.IUserDao;
import com.company.model.dao.impl.DaoFactory;
import com.company.model.exception.DaoException;

import javax.servlet.http.HttpServletRequest;

public class AuthorizeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        IDaoFactory factory = DaoFactory.getInstance();
        try {
            String login = request.getParameter("login");
            String password = Encryptor.getHash(request.getParameter("password"));
            IAdminDao adminDao = factory.getAdminDao();
            Administrator admin = adminDao.getAdminByLogin(login);
            if (admin != null && admin.getPassword().equals(password)) {
                request.getSession().setAttribute("admin", admin);
            } else {
                IUserDao userDao = factory.getUserDao();
                User user = userDao.getUserByLogin(login);
                if (user != null && user.getPassword().equals(password)) {
                    request.getSession().setAttribute("user", user);
                }
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return new GetAllBooksCommand().execute(request);
    }
}
