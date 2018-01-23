package com.company.controller;

import com.company.controller.command.Command;
import com.company.controller.command.CommandList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class MainController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String commandName = req.getParameter("command");
        if (commandName == null) {
            commandName = "Get_All_Books";
        }
        Command command = CommandList.valueOf(commandName.toUpperCase()).getCommand();
        String page = command.execute(req);
        req.getRequestDispatcher(page).forward(req, resp);
    }
}
