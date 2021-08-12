package com.kazakova.mylibrarywithrest.rest;

import com.kazakova.mylibrarywithrest.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("all")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    private Long id;
    private String name;

    public static Author toDomainObject(AuthorDto dto) {
        return new Author(dto.getId(), dto.getName());
    }

    public static AuthorDto toDto(Author account) {
        return new AuthorDto(account.getId(), account.getName());
    }
}
