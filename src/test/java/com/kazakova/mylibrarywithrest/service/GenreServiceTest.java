package com.kazakova.mylibrarywithrest.service;


import com.kazakova.mylibrarywithrest.domain.Genre;
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
import java.util.Optional;

@DisplayName("Service Genre должен")
@DataJpaTest
@Import(GenreService.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GenreServiceTest {

    private static final long FIRST_GENRE_ID = 1L;
    private static final long SECOND_GENRE_ID = 2L;
    private static final long SEVENTH_GENRE_ID = 7L;
    private static final long THIRD_GENRE_ID = 3L;
    private static final long EXPECTED_NUMBER_OF_GENRES = 6L;
    private static final String FIRST_UPDATE_GENRE = "Povest";

    @Autowired
    private GenreService genreService;

    @Autowired
    private TestEntityManager tem;

    @DisplayName("Добавлять жанр")
    @Test
    public void testAddGenre() throws Exception {
        Genre genre = new Genre();
        genre.setName(FIRST_UPDATE_GENRE);
        genreService.createGenre(genre);

        Optional<Genre> genreOptional = genreService.findGenreById(genre.getId());

        Assertions.assertTrue(genreOptional.isPresent(), "Genre does not exist");

        Genre genre1 = genreOptional.get();
        Assertions.assertEquals(genre, genre1);
    }

    @DisplayName("Загружать информацию о нужном жанре по его id")
    @Test
    void testFindExpectedGenreById() {
        Optional<Genre> optionalActualGenre = genreService.findGenreById(FIRST_GENRE_ID);
        Genre expectedGenre = tem.find(Genre.class, FIRST_GENRE_ID);
        AssertionsForClassTypes.assertThat(optionalActualGenre).isPresent().get()
                .isEqualTo(expectedGenre);
    }

    @DisplayName("Загружать список всех жанров")
    @Test
    void testFindAllGenres() {
        List<Genre> listGenre = genreService.findAllGenres();
        Assertions.assertEquals(EXPECTED_NUMBER_OF_GENRES, listGenre.size());
    }

    @DisplayName("Удалять жанр по id")
    @Test
    public void testDeleteGenreById() {
        genreService.deleteGenreById(FIRST_GENRE_ID);
        Assertions.assertEquals(5L, genreService.findAllGenres().size());
    }

    @DisplayName("Обновлять жанр")
    @Test
    public void testUpdateGenre() {
        Optional<Genre> genre = genreService.findGenreById(SECOND_GENRE_ID);
        Assertions.assertTrue(genre.isPresent(), "Genre does not exist");

        Genre genre1 = genre.get();
        genre1.setName(FIRST_UPDATE_GENRE);
        genreService.createGenre(genre1);

        Optional<Genre> newGenre = genreService.findGenreById(SECOND_GENRE_ID);
        Assertions.assertTrue(newGenre.isPresent(), "GENRE does not exist");

        Genre result = newGenre.get();

        Assertions.assertEquals(result.getName(), FIRST_UPDATE_GENRE);
    }

}
