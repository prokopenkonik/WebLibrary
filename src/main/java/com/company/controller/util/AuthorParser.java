package com.company.controller.util;

import com.company.model.domain.Author;

import java.util.ArrayList;
import java.util.List;

public class AuthorParser {
    public static List<Author> parseAuthors(String authors) {
        List<Author> result = new ArrayList<>();
        String[] splitedAuthors = authors.split(", |,");
        Author author;
        for (String authorSting : splitedAuthors) {
            author = new Author();
            author.setName(authorSting.substring(0, authorSting.indexOf(" ")));
            author.setSurname(authorSting.substring(authorSting.indexOf(" ") + 1, authorSting.length()));
            result.add(author);
        }
        return result;
    }
}
