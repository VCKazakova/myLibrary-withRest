package com.kazakova.mylibrarywithrest.rest;

import com.kazakova.mylibrarywithrest.domain.Author;

@SuppressWarnings("all")
public class AuthorDto {

    private Long id;
    private String name;

    public AuthorDto() {
    }

    public AuthorDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Author toDomainObject(AuthorDto dto) {
        return new Author(dto.getId(), dto.getName());
    }

    public static AuthorDto toDto(Author account) {
        return new AuthorDto(account.getId(), account.getName());
    }
}
