package com.kazakova.mylibrarywithrest.dto;


import com.kazakova.mylibrarywithrest.domain.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("all")
public class GenreDto {

    private Long id;
    private String name;

    public GenreDto() {}

    public GenreDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Genre toDomainObject(GenreDto dto) {
        return new Genre(dto.getId(), dto.getName());
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

    public static GenreDto toDto(Genre account) {
        return new GenreDto(account.getId(), account.getName());
    }

}
