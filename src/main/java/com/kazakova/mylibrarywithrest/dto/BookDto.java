package com.kazakova.mylibrarywithrest.dto;

import com.kazakova.mylibrarywithrest.domain.Author;
import com.kazakova.mylibrarywithrest.domain.Book;
import com.kazakova.mylibrarywithrest.domain.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("all")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private Long id;
    private String bookTitle;
    private Long authorId;
    private Long genreId;

    public static BookDto toDto(Book entity) {
        BookDto dto = new BookDto();
        dto.setId(entity.getId());
        dto.setBookTitle(entity.getBookTitle());
        dto.setAuthorId(dto.getAuthorId());
        dto.setGenreId(dto.getGenreId());
        return dto;
    }

    public Book toDomainObject() {
        return new Book(id, bookTitle, new Author(authorId), new Genre(genreId));
    }

}
