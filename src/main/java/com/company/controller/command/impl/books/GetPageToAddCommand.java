package com.company.controller.command.impl.books;

import com.company.controller.command.Command;
import com.company.controller.util.BooksCrud;

import javax.servlet.http.HttpServletRequest;

public class GetPageToAddCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return BooksCrud.ADD_BOOK_JSP;
    }
}
