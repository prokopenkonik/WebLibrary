package com.company.model.dao.constants;

public interface SqlQueries {
    String ADD_BOOK = "book.create";
    String ADD_RELATION = "book.add.relation";
    String UPDATE_BOOK = "book.update";
    String DELETE_BOOK = "book.delete";
    String DELETE_RELATIONS = "book.delete.relation";
    String GET_BOOK_ID_BY_TITLE = "book.get.id.by.title";
    String GET_BOOK_BY_ID = "book.get.by.id";
    String GET_ALL_BOOKS = "book.get.all";
    String GET_BOOKS_BY_QUERY = "book.get.by.query";
    String GET_BOOKS_BY_AUTHOR = "book.get.by.author";
    String GET_BOOKS_BY_GENRES = "book.get.by.genre";
    String GET_GENRES = "book.get.genres";

    String ADD_AUTHOR = "author.create";
    String UPDATE_AUTHOR = "author.update";
    String GET_AUTHOR_BY_NAME_AND_SURNAME = "author.get.by.name";
    String GET_AUTHOR_BY_ID = "author.get.by.id";
    String GET_ALL_AUTHORS = "author.get.all";

    String ADD_ORDER = "order.create";
    String UPDATE_ORDER = "order.update";
    String DELETE_ORDER = "order.delete";
    String GET_ALL_ORDERS = "order.get.all";
    String GET_ORDER_BY_USER = "order.get.by.user";
    String GET_ORDER_BY_ID = "order.get.by.id";

    String ADD_USER = "user.create";
    String GET_USER_BY_LOGIN = "user.get.by.login";
    String GET_ADMIN_BY_LOGIN = "admin.get.by.login";
}
