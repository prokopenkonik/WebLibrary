package com.company.model.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Book extends Entity{
    private String title;
    private String genre;
    private String publisher;
    private int publishingYear;
    private String description;
    private boolean taken;
    private List<Author> authors = new ArrayList<>();

    public Book() {
    }

    public Book(int id,String title, String genre,
                String publisher, int publishingYear, String description, boolean taken) {
        super(id);
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        this.publishingYear = publishingYear;
        this.description = description;
        this.taken = taken;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getAuthorsAsString() {
        StringBuilder result = new StringBuilder();
        Iterator iterator = authors.iterator();
        while (iterator.hasNext()) {
            result.append(iterator.next().toString());
            if (iterator.hasNext()) {
                result.append(", ");
            }
        }
        return result.toString();
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

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    @Override
    public String toString() {
        return title;
    }
}
