package com.company.controller.command;

import com.company.controller.command.impl.*;

public enum CommandList {
    GET_ALL_BOOKS(new GetAllBooksCommand()),
    GET_BOOK(new GetBookCommand()),
    SEARCH_BOOK(new SearchBookCommand()),
    AUTHORIZATION(new AuthorizeCommand()),
    DELETE_BOOK(new DeleteBookCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand()),
    GET_BOOK_TO_UPDATE(new GetBookToUpdateCommand()),
    EDIT_BOOK(new EditBookCommand()),
    ADD_BOOK(new AddBookCommand()),
    HIRE_BOOK(new HireBookCommand()),
    GET_ORDERS_FOR_ADMIN(new GetOrdersForAdminCommand()),
    GET_ORDERS(new GetOrdersCommand()),
    RETURN_BOOK(new ReturnBookCommand());

    private Command command;

    CommandList(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}
