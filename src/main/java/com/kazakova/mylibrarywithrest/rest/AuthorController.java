package com.kazakova.mylibrarywithrest.rest;

import com.kazakova.mylibrarywithrest.domain.Author;
import com.kazakova.mylibrarywithrest.dto.AuthorDto;
import com.kazakova.mylibrarywithrest.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
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
        return service.findAllAuthors().stream()
                .map(AuthorDto::toDto)
                .collect(Collectors.toList());
    }

    @RequestMapping(
            value = "/author/{id}",
            method = RequestMethod.GET
    )
    public AuthorDto get(
            @PathVariable("id") Long id
    ) {
        Author author = service.findAuthorById(id);
        return AuthorDto.toDto(author);
    }

    @PostMapping("/createAuthor")
    public AuthorDto createPerson(@RequestBody AuthorDto dto) {
        Author author = dto.toDomainObject();
        Author newAuthor = service.createAuthor(author);
        return AuthorDto.toDto(newAuthor);
    }


    @DeleteMapping("/author/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.deleteAuthorById(id);
    }

    @PutMapping("/author/{id}/holder")
    public void changeName(
            @PathVariable("id") Long id,
            @RequestParam("name") String name
    ) {
        Author author = service.findAuthorById(id);
        author.setName(name);
        service.createAuthor(author);
    }

}
