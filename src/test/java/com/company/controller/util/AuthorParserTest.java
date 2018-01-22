package com.company.controller.util;

import com.company.domain.Author;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AuthorParserTest {
    @Test
    public void parseAuthors() throws Exception {
        String authorsString = "Александр Пушкин, Лев Толстой, Рей Бредбери";
        List<Author> authors = new ArrayList<>();

        Author author = new Author();
        author.setName("Александр");
        author.setSurname("Пушкин");
        authors.add(author);

        author = new Author();
        author.setName("Лев");
        author.setSurname("Толстой");
        authors.add(author);

        author = new Author();
        author.setName("Рей");
        author.setSurname("Бредбери");
        authors.add(author);

        List<Author> expectedAuthors = AuthorParser.parseAuthors(authorsString);
        Assert.assertNotNull(expectedAuthors);
        for (int i = 0; i < authors.size(); i++) {
            Assert.assertEquals(expectedAuthors.get(i).toString(), authors.get(i).toString());
        }
    }
}