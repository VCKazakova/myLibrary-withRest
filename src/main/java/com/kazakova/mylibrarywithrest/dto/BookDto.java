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


    public static Book toDomainObject(BookDto dto) {
        return new Book(dto.getId(), dto.getBookTitle(), new Author(dto.getAuthorId()), new Genre(dto.getGenreId()));
    }

    public static BookDto toDto(Book account) {
        return new BookDto(account.getId(), account.getBookTitle(), account.getAuthor().getId(), account.getGenre().getId());
    }

}
