package com.kazakova.mylibrarywithrest.service;


import com.kazakova.mylibrarywithrest.domain.Author;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;

@DisplayName("Service Author должен")
@DataJpaTest
@Import(AuthorService.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
public class AuthorServiceTest {

    private static final long FIRST_AUTHOR_ID = 1L;
    private static final long SECOND_AUTHOR_ID = 2L;
    private static final long FIFTH_AUTHOR_ID = 5L;
    private static final long THIRD_AUTHOR_ID = 3L;
    private static final long EXPECTED_NUMBER_OF_AUTHORS = 8L;
    private static final String FIRST_UPDATE_AUTHOR = "Duma";

    @Autowired
    private AuthorService authorService;

    @Autowired
    private TestEntityManager tem;

    @DisplayName("Добавлять автора")
    @Test
    public void testAddAuthor() throws Exception {
        Author author = new Author();
        author.setName(FIRST_UPDATE_AUTHOR);
        authorService.createAuthor(author);

        Author authorById = authorService.findAuthorById(author.getId());
        Assertions.assertEquals(author, authorById);
    }

    @DisplayName("Загружать информацию о нужном авторе по его id")
    @Test
    void testFindExpectedAuthorById() {
        Author actualAuthor = authorService.findAuthorById(FIRST_AUTHOR_ID);
        Author expectedAuthor = tem.find(Author.class, FIRST_AUTHOR_ID);
        AssertionsForClassTypes.assertThat(actualAuthor)
                .isEqualTo(expectedAuthor);
    }

    @DisplayName("Загружать список всех авторов")
    @Test
    void testFindAllAuthors() {
        List<Author> listAuthor = authorService.findAllAuthors();
        Assertions.assertEquals(EXPECTED_NUMBER_OF_AUTHORS, listAuthor.size());
    }

    @DisplayName("Удалять автора по id")
    @Test
    public void testDeleteAuthorById() {
        authorService.deleteAuthorById(THIRD_AUTHOR_ID);
        Assertions.assertEquals(7L, authorService.findAllAuthors().size());
    }

    @DisplayName("Обновлять автора")
    @Test
    public void testUpdateAuthor() {
        Author authorById = authorService.findAuthorById(FIRST_AUTHOR_ID);
        authorById.setName("I.Green");
        Assertions.assertEquals(authorById.getName(), "I.Green");
    }


}
