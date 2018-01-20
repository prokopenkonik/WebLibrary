package com.company.controller.command.impl;

import com.company.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}
