package com.kazakova.mylibrarywithrest.service;

import com.kazakova.mylibrarywithrest.domain.Author;
import com.kazakova.mylibrarywithrest.domain.Genre;
import com.kazakova.mylibrarywithrest.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

//    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
//    public Author findAuthorById(Long id) {
//        return authorRepository.getById(id);
//    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Optional<Author> findAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

}