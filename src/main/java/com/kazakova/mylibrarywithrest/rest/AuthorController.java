package com.kazakova.mylibrarywithrest.rest;

import com.kazakova.mylibrarywithrest.domain.Author;
import com.kazakova.mylibrarywithrest.dto.AuthorDto;
import com.kazakova.mylibrarywithrest.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @RequestMapping(
            value = "/authors",
            method = RequestMethod.GET
    )
    public List<AuthorDto> get() {
        log.info(">> AuthorController getAllAuthors");
        List<AuthorDto> allAuthors = service.findAllAuthors().stream()
                .map(AuthorDto::toDto)
                .collect(Collectors.toList());
        log.info(">> AuthorController getAllAuthors allAuthors={}", allAuthors);
        return allAuthors;
    }

    @RequestMapping(
            value = "/author/{id}",
            method = RequestMethod.GET
    )
    public AuthorDto get(
            @PathVariable("id") Long id
    ) {
        log.info(">> AuthorController getAuthorById id={}", id);
        Author author = service.findAuthorById(id);
        AuthorDto authorById = AuthorDto.toDto(author);
        log.info(">> AuthorController getAuthorById authorById={}", authorById);
        return authorById;
    }

    @PostMapping("/createAuthor")
    public AuthorDto createAuthor(@RequestBody AuthorDto dto) {
        log.info(">> AuthorController createAuthor dto={}", dto);
        Author author = dto.toDomainObject();
        Author newAuthor = service.createAuthor(author);
        AuthorDto savedAuthor = AuthorDto.toDto(newAuthor);
        log.info(">> AuthorController createAuthor savedAuthor={}", savedAuthor);
        return savedAuthor;
    }


    @DeleteMapping("/author/{id}")
    public void delete(@PathVariable("id") Long id) {
        log.info(">> AuthorController deleteAuthor id={}", id);
        service.deleteAuthorById(id);
    }

    @PutMapping("/author/{id}/holder")
    public void changeName(
            @PathVariable("id") Long id,
            @RequestParam("name") String name
    ) {
        log.info(">> AuthorController changeAuthorName id={}", id);
        Author author = service.findAuthorById(id);
        author.setName(name);
        Author authorWithNewName = service.createAuthor(author);
        log.info(">> AuthorController changeAuthorName authorWithNewName={}", authorWithNewName);
    }

}
