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

import java.util.Optional;

@DisplayName("Service book должен")
@DataJpaTest
@Import(BookService.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookServiceTest {


    private static final String NEW_BOOK_TITLE = "Gold chain";
    private static final Long FIRST_BOOK_ID = 1L;
    private static final Long THIRD_BOOK_ID = 3L;

    @Autowired
    private final BookService bookService;

    @Autowired
    private TestEntityManager tem;

    public BookServiceTest(BookService bookService) {
        this.bookService = bookService;
    }


    @DisplayName("Добавлять книгу")
    @Test
    public void testAddBook() throws Exception {
        Book newBook = new Book();
        Author newAuthor = new Author(1L, "Green");
        Genre newGenre = new Genre(1L, "Roman");

        newBook.setBookTitle(NEW_BOOK_TITLE);
        newBook.setAuthor(newAuthor);
        newBook.setGenre(newGenre);

        bookService.createBook(newBook);

        Optional<Book> bookOptional = bookService.findBookById(newBook.getId());

        Assertions.assertTrue(bookOptional.isPresent(), "Book does not exist");

        Book book1 = bookOptional.get();
        Assertions.assertEquals(newBook, book1);
    }


    @DisplayName("Загружать информацию о нужной книге по ее id")
    @Test
    public void testFindExpectedBookById() {
        Optional<Book> optionalActualBook = bookService.findBookById(FIRST_BOOK_ID);
        Book expectedBook = tem.find(Book.class, FIRST_BOOK_ID);

        AssertionsForClassTypes.assertThat(optionalActualBook)
                .isPresent().get().isEqualTo(expectedBook);
    }

    @DisplayName("Удалять книгу по id")
    @Test
    public void testDeleteBookById() {
        bookService.deleteBookById(THIRD_BOOK_ID);
        Assertions.assertEquals(9L, bookService.findAllBooks().size());
    }

    @DisplayName("Обновлять книгу")
    @Test
    public void testUpdateBook () {
        Optional<Book> optionalBook = bookService.findBookById(FIRST_BOOK_ID);

        Assertions.assertTrue(optionalBook.isPresent(), "Book does not exist");
        Book bookForUpdate = optionalBook.get();
        bookForUpdate.setBookTitle(NEW_BOOK_TITLE);

        Assertions.assertEquals(bookForUpdate.getBookTitle(), NEW_BOOK_TITLE);
    }

}
