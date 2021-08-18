package com.kazakova.mylibrarywithrest.rest;

import com.kazakova.mylibrarywithrest.domain.Book;
import com.kazakova.mylibrarywithrest.dto.BookDto;
import com.kazakova.mylibrarywithrest.exception.NotFoundException;
import com.kazakova.mylibrarywithrest.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
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
        return service.findAllBooks().stream()
                .map(BookDto::toDto)
                .collect(Collectors.toList());
    }

    @RequestMapping(
            value = "/book/{id}",
            method = RequestMethod.GET
    )
    public BookDto get(
            @PathVariable("id") Long id
    ) {
        Book book = service.findBookById(id)
                .orElseThrow(() -> new NotFoundException("Book not found", id));
        return BookDto.toDto(book);
    }

    @PostMapping("/createBook")
    public BookDto createBook(@RequestBody BookDto dto) {
        Book book = dto.toDomainObject();
        Book newBook = service.createBook(book);
        return BookDto.toDto(newBook);
    }

    @DeleteMapping("/book/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.deleteBookById(id);
    }

    @PutMapping("/book/{id}/holder")
    public void changeBookTitle(
            @PathVariable("id") Long id,
            @RequestParam("bookTitle") String bookTitle
    ) {
        Book book = service.findBookById(id).orElseThrow(NotFoundException::new);
        book.setBookTitle(bookTitle);
        service.createBook(book);
    }

}
