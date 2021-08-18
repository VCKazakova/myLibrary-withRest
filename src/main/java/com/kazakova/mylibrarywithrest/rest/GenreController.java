package com.kazakova.mylibrarywithrest.rest;

import com.kazakova.mylibrarywithrest.domain.Author;
import com.kazakova.mylibrarywithrest.domain.Genre;
import com.kazakova.mylibrarywithrest.dto.AuthorDto;
import com.kazakova.mylibrarywithrest.dto.GenreDto;
import com.kazakova.mylibrarywithrest.service.GenreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
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
        return service.findAllGenres().stream()
                .map(GenreDto::toDto)
                .collect(Collectors.toList());
    }

    @RequestMapping(
            value = "/genre/{id}",
            method = RequestMethod.GET
    )
    public GenreDto get(
            @PathVariable("id") Long id
    ) {
        Genre genre = service.findGenreById(id).get();
        return GenreDto.toDto(genre);
    }

    @PostMapping("/genre")
    public GenreDto create(GenreDto dto) {
        Genre genre = dto.toDomainObject();
        Genre newGenre = service.createGenre(genre);
        return GenreDto.toDto(newGenre);
    }

    @DeleteMapping("/genre/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.deleteGenreById(id);
    }

    @PutMapping("/genre/{id}/holder")
    public void changeName(
            @PathVariable("id") Long id,
            @RequestParam("name") String name
    ) {
        Genre genre = service.findGenreById(id).get();
        genre.setName(name);
        service.createGenre(genre);
    }

}
