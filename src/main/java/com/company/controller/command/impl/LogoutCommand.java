package com.company.controller.command.impl;

import com.company.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return new GetAllBooksCommand().execute(request);
    }
}
