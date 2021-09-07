package com.kazakova.mylibrarywithrest.rest;

import com.kazakova.mylibrarywithrest.domain.Genre;
import com.kazakova.mylibrarywithrest.dto.GenreDto;
import com.kazakova.mylibrarywithrest.exception.NotFoundException;
import com.kazakova.mylibrarywithrest.service.GenreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class GenreController {

    private final GenreService service;

    public GenreController(GenreService service) {
        this.service = service;
    }

    @RequestMapping(
            value = "/genres",
            method = RequestMethod.GET
    )
    public List<GenreDto> get() {
        log.info(">> GenreController getAllGenres");
        List<GenreDto> allGenres = service.findAllGenres().stream()
                .map(GenreDto::toDto)
                .collect(Collectors.toList());
        log.info(">> GenreController getAllGenres allGenres={}", allGenres);
        return allGenres;
    }

    @RequestMapping(
            value = "/genre/{id}",
            method = RequestMethod.GET
    )
    public GenreDto get(
            @PathVariable("id") Long id
    ) {
        log.info(">> GenreController getGenreById id={}", id);
        Genre genre = service.findGenreById(id).orElseThrow(NotFoundException::new);
        GenreDto genreById = GenreDto.toDto(genre);
        log.info(">> GenreController getGenreById genreById={}", genreById);
        return genreById;
    }

    @PostMapping("/genre")
    public GenreDto create(@RequestBody GenreDto dto) {
        log.info(">> GenreController createGenre dto={}", dto);
        Genre genre = dto.toDomainObject();
        Genre newGenre = service.createGenre(genre);
        GenreDto createdGenre = GenreDto.toDto(newGenre);
        log.info(">> GenreController createGenre createdGenre={}", createdGenre);
        return createdGenre;
    }

    @DeleteMapping("/genre/{id}")
    public void delete(@PathVariable("id") Long id) {
        log.info(">> GenreController deleteGenre id={}", id);
        service.deleteGenreById(id);
    }

    @PutMapping("/genre/{id}/holder")
    public void changeName(
            @PathVariable("id") Long id,
            @RequestParam("name") String name
    ) {
        log.info(">> GenreController changeGenre id={}", id);
        Genre genre = service.findGenreById(id).orElseThrow(NotFoundException::new);
        genre.setName(name);
        Genre changedGenre = service.createGenre(genre);
        log.info(">> GenreController changeGenre changedGenre={}", changedGenre);
    }

}
