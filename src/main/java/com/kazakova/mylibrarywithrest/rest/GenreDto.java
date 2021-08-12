package com.kazakova.mylibrarywithrest.rest;


import com.kazakova.mylibrarywithrest.domain.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("all")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreDto {

    private Long id;
    private String name;


    public static Genre toDomainObject(GenreDto dto) {
        return new Genre(dto.getId(), dto.getName());
    }

    public static GenreDto toDto(Genre account) {
        return new GenreDto(account.getId(), account.getName());
    }

}
