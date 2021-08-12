package com.kazakova.mylibrarywithrest.rest;

import com.kazakova.mylibrarywithrest.domain.Author;
import com.kazakova.mylibrarywithrest.domain.Book;
import com.kazakova.mylibrarywithrest.domain.Genre;
import lombok.Data;

@Data
public class BookDto {

    private Long id;
    private String bookTitle;
    private Author author;
    private Genre genre;
    private Long authorId;
    private Long genreId;

    public BookDto() {
    }

    public BookDto(Long id, String bookTitle, Author author, Genre genre) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.author = author;
        this.genre = genre;
    }

    public BookDto(Long id, String bookTitle, Long authorId, Long genreId) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.authorId = author.getId();
        this.genreId = genre.getId();
    }


    public static Book toDomainObject(BookDto dto) {
        return new Book(dto.getId(), dto.getBookTitle(), dto.getAuthor(), dto.getGenre());
    }

    public static BookDto toDto(Book account) {
        return new BookDto(account.getId(), account.getBookTitle(), account.getAuthor().getId(), account.getGenre().getId());
    }

}
