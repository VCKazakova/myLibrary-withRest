package com.kazakova.mylibrarywithrest.rest;

import com.kazakova.mylibrarywithrest.domain.Book;
import com.kazakova.mylibrarywithrest.dto.BookDto;
import com.kazakova.mylibrarywithrest.exception.NotFoundException;
import com.kazakova.mylibrarywithrest.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }


    @RequestMapping(
            value = "/books",
            method = RequestMethod.GET
    )
    public List<BookDto> get() {
        log.info(">> BookController getAllBooks");
        List<BookDto> allBooks = service.findAllBooks().stream()
                .map(BookDto::toDto)
                .collect(Collectors.toList());
        log.info(">> BookController getAllBooks allBooks={}", allBooks);
        return allBooks;
    }

    @RequestMapping(
            value = "/book/{id}",
            method = RequestMethod.GET
    )
    public BookDto get(
            @PathVariable("id") Long id
    ) {
        log.info(">> BookController getBookById id={}", id);
        Book book = service.findBookById(id)
                .orElseThrow(() -> new NotFoundException("Book not found", id));
        BookDto bookById = BookDto.toDto(book);
        log.info(">> BookController getBookById bookById={}", bookById);
        return bookById;
    }

    @PostMapping("/createBook")
    public BookDto createBook(@RequestBody BookDto dto) {
        log.info(">> BookController createBook dto={}", dto);
        Book book = dto.toDomainObject();
        Book newBook = service.createBook(book);
        BookDto savedBook = BookDto.toDto(newBook);
        log.info(">> BookController createBook savedBook={}", savedBook);
        return savedBook;
    }

    @DeleteMapping("/book/{id}")
    public void delete(@PathVariable("id") Long id) {
        log.info(">> BookController deleteBook id={}", id);
        service.deleteBookById(id);
    }

    @PutMapping("/book/{id}/holder")
    public void changeBookTitle(
            @PathVariable("id") Long id,
            @RequestParam("bookTitle") String bookTitle
    ) {
        log.info(">> BookController changeBookTitle id={}", id);
        Book book = service.findBookById(id).orElseThrow(NotFoundException::new);
        book.setBookTitle(bookTitle);
        Book bookWithNewTitle = service.createBook(book);
        log.info(">> BookController changeBookTitle bookWithNewTitle={}", bookWithNewTitle);
    }

}
