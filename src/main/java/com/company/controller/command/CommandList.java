package com.company.controller.command;

import com.company.controller.command.impl.*;

public enum CommandList {
    DEFAULT(new DefaultCommand()),
    GET_ALL_BOOKS(new GetAllBooksCommand()),
    GET_BOOK(new GetBookCommand()),
    SEARCH_BOOK(new SearchBookCommand()),
    AUTHORIZATION(new AuthorizeCommand()),
    DELETE_BOOK(new DeleteBookCommand());

    private Command command;

    CommandList(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}
