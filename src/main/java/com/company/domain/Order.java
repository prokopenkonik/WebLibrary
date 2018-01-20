package com.company.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Order extends Entity {
    private String creationDate;
    private String endingDate;
    private String status;
    private List<Book> books = new ArrayList<>();

    public Order() {
    }

    public Order(int id, String creationDate, String endingDate,
                 String status) {
        super(id);
        this.creationDate = creationDate;
        this.endingDate = endingDate;
        this.status = status;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return status;
    }
}
