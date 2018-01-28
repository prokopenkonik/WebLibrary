package com.company.model.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Author extends Entity {
    private String name;
    private String surname;
    private List<Book> books;

    public Author() {
    }

    public Author(int id, String name, String surname) {
        super(id);
        this.name = name;
        this.surname = surname;
    }

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public void setBooks(Collection<Book> books) {
        this.books = new ArrayList<>(books);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addBooks(Collection<Book> books) {
        this.books.addAll(books);
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
