package com.kazakova.mylibrarywithrest.rest;

import com.kazakova.mylibrarywithrest.domain.Book;
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
       Book book = service.findBookById(id).get();
        return BookDto.toDto(book);
    }

    @RequestMapping(
            value = "/book",
            method = RequestMethod.POST
    )
    public @ResponseBody
    BookDto create(@RequestBody BookDto dto) {
        Book book = BookDto.toDomainObject(dto);
        Book bookWithId = service.createBook(book);
        return BookDto.toDto(bookWithId);
    }

    @DeleteMapping("/book/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.deleteBookById(id);
    }

//    @PutMapping("/book/{id}/holder")
//    public void changeName(
//            @PathVariable("id") Long id,
//            @RequestParam("bookTitle") String bookTitle,
//            @RequestParam("authorId") Long authorId,
//            @RequestParam("genreId") Long genreId
//    ) {
//        Book book = service.findBookById(id).get();
//        book.setBookTitle(bookTitle);
//        book.setAuthor();
//        book.setGenre();
//        service.createBook(book);
//    }


}
