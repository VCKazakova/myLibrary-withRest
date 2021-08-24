package com.kazakova.mylibrarywithrest.dto;


import com.kazakova.mylibrarywithrest.domain.Author;
import com.kazakova.mylibrarywithrest.domain.Genre;
import lombok.*;

@SuppressWarnings("all")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GenreDto {

    private Long id;
    private String name;

    public static GenreDto toDto(Genre entity) {
        GenreDto dto = new GenreDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public Genre toDomainObject() {
        return new Genre(id, name);
    }

    @Override
    public String toString() {
        return "GenreDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
