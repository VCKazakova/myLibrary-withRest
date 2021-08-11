package com.kazakova.mylibrarywithrest.service;

import com.kazakova.mylibrarywithrest.domain.Book;
import com.kazakova.mylibrarywithrest.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Transactional(rollbackFor = {SQLException.class})
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Optional<Book> findBookById(long id) {
        return bookRepository.findById(id);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }

}

