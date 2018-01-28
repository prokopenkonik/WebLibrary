package com.company.controller.command.impl.users;

import com.company.controller.command.Command;
import com.company.controller.command.impl.books.GetAllBooksCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String language = request.getParameter("language");
        switch (language) {
            case "ua":
                session.setAttribute("language", "uk_UA");
                break;
            case "en":
                session.setAttribute("language", "en_US");
                break;
            case "ru":
                session.setAttribute("language", "ru_RU");
                break;
        }
        return new GetAllBooksCommand().execute(request);
    }
}
