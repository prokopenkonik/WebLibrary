package com.company.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Book extends Entity{
    private String title;
    private int copiesCount;
    private String genre;
    private String publisher;
    private int publishingYear;
    private String description;
    private List<Author> authors = new ArrayList<>();

    public Book() {
    }

    public Book(int id,String title, int copiesCount,
                String genre, String publisher, int publishingYear, String description) {
        super(id);
        this.title = title;
        this.copiesCount = copiesCount;
        this.genre = genre;
        this.publisher = publisher;
        this.publishingYear = publishingYear;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCopiesCount() {
        return copiesCount;
    }

    public void setCopiesCount(int copiesCount) {
        this.copiesCount = copiesCount;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Author> getAuthors() {
        return new ArrayList<>(authors);
    }

    public void setAuthors(Collection<Author> authors) {
        this.authors = new ArrayList<>(authors);
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void addAuthors(Collection<Author> authors) {
        this.authors.addAll(authors);
    }

    @Override
    public String toString() {
        return title;
    }

}
