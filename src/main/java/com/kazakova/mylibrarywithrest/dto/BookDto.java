package com.kazakova.mylibrarywithrest.dto;

import com.kazakova.mylibrarywithrest.domain.Author;
import com.kazakova.mylibrarywithrest.domain.Book;
import com.kazakova.mylibrarywithrest.domain.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("all")
@Getter
@Setter
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
        dto.setAuthorId(entity.getAuthor().getId());
        dto.setGenreId(entity.getGenre().getId());
        return dto;
    }

    public Book toDomainObject() {
        return new Book(id, bookTitle, new Author(getAuthorId()), new Genre(getGenreId()));
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", bookTitle='" + bookTitle + '\'' +
                ", authorId=" + authorId +
                ", genreId=" + genreId +
                '}';
    }
}
