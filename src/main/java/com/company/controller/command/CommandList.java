package com.company.controller.command;

import com.company.controller.command.impl.books.*;
import com.company.controller.command.impl.orders.GetOrdersCommand;
import com.company.controller.command.impl.orders.GetOrdersForAdminCommand;
import com.company.controller.command.impl.users.AuthorizeCommand;
import com.company.controller.command.impl.users.LogoutCommand;
import com.company.controller.command.impl.users.RegistrationCommand;

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
    RETURN_BOOK(new ReturnBookCommand()),
    GET_BOOKS_BY_AUTHOR(new GetBooksByAuthorCommand()),
    GET_BOOKS_BY_GENRE(new GetBooksByGenreCommand());

    private Command command;

    CommandList(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}
