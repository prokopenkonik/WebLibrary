package com.company.other;

import com.company.model.dao.connection.ConnectionFactory;
import com.company.model.dao.connection.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        /*ConnectionFactory connectionFactory = new ConnectionPool();
        try {
            connectionFactory.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        req.getRequestDispatcher("pages/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("pass");
        //Repository.getInstance().addUser(new User(login, password));
        req.setAttribute("userLogin", login);
        doGet(req, resp);
    }
}
