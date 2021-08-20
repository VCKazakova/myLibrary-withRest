package com.kazakova.mylibrarywithrest.service;


import com.kazakova.mylibrarywithrest.domain.Author;
import com.kazakova.mylibrarywithrest.domain.Book;
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

@DisplayName("Service Book должен")
@DataJpaTest
@Import(BookService.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookServiceTest {

    private static final long FIRST_BOOK_ID = 1L;
    private static final long SECOND_BOOK_ID = 2L;
    private static final long FIFTH_BOOK_ID = 5L;
    private static final long THIRD_BOOK_ID = 3L;
    private static final long EXPECTED_NUMBER_OF_BOOKS = 10L;
    private static final String FIRST_UPDATE_BOOK = "Fight club";

    @Autowired
    private BookService bookService;

    @Autowired
    private TestEntityManager tem;

    @DisplayName("Загружать информацию о нужной книге по его id")
    @Test
    void testFindExpectedBookById() {
        Optional<Book> optionalActualBook = bookService.findBookById(FIRST_BOOK_ID);
        Book expectedBook = tem.find(Book.class, FIRST_BOOK_ID);
        AssertionsForClassTypes.assertThat(optionalActualBook).isPresent().get()
                .isEqualTo(expectedBook);
    }

    @DisplayName("Загружать список всех книг")
    @Test
    void testFindAllBooks() {
        List<Book> listBook = bookService.findAllBooks();
        Assertions.assertEquals(EXPECTED_NUMBER_OF_BOOKS, listBook.size());
    }

    @DisplayName("Добавлять книгу")
    @Test
    public void testAddBook() {
        Author author = new Author();
        author.setName("Pushkin");
        Genre genre = new Genre();
        genre.setName("Roman");
        Book book = new Book();
        book.setBookTitle(FIRST_UPDATE_BOOK);
        book.setAuthor(author);
        book.setGenre(genre);

        bookService.createBook(book);

        Optional<Book> bookOptional = bookService.findBookById(book.getId());

        Assertions.assertTrue(bookOptional.isPresent(), "Book does not exist");

        Book book1 = bookOptional.get();
        Assertions.assertEquals(book, book1);
    }

    @DisplayName("Удалять книгу по id")
    @Test
    public void testDeleteBookById() {
        bookService.deleteBookById(THIRD_BOOK_ID);
        Assertions.assertEquals(9L, bookService.findAllBooks().size());
    }

    @DisplayName("Обновлять книгу")
    @Test
    public void shouldUpdateBook() {
        Optional<Book> book = bookService.findBookById(SECOND_BOOK_ID);

        Assertions.assertTrue(book.isPresent(), "Book does not exist");
        Book book1 = book.get();
        book1.setBookTitle(FIRST_UPDATE_BOOK);
        bookService.createBook(book1);

        Optional<Book> newBook = bookService.findBookById(SECOND_BOOK_ID);
        Assertions.assertTrue(newBook.isPresent(), "Book does not exist");

        Book result = newBook.get();

        Assertions.assertEquals(result, book1);
        Assertions.assertEquals(result.getBookTitle(), FIRST_UPDATE_BOOK);
    }

}
