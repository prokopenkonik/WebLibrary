package com.company.controller.command.impl.books;

import com.company.controller.command.Command;
import com.company.controller.util.PagesPaths;

import javax.servlet.http.HttpServletRequest;

public class GetPageToAddCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return PagesPaths.ADD_BOOK_JSP;
    }
}
