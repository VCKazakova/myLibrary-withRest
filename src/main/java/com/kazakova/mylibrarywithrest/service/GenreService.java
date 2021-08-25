package com.kazakova.mylibrarywithrest.service;

import com.kazakova.mylibrarywithrest.domain.Genre;
import com.kazakova.mylibrarywithrest.repository.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }


    @Transactional(rollbackFor = {SQLException.class})
    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Optional<Genre> findGenreById(Long id) {
        return genreRepository.findById(id);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public void deleteGenreById(Long id) {
        genreRepository.deleteById(id);
    }

}
