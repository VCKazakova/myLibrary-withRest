package com.kazakova.mylibrarywithrest.dto;

import com.kazakova.mylibrarywithrest.domain.Book;
import com.kazakova.mylibrarywithrest.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("all")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private Long id;
    private String comment;
    private Long bookId;

    public CommentDto() {
    }

    public static Comment toDomainObject(CommentDto dto) {
        return new Comment(dto.getId(), dto.getComment(), new Book(dto.getBookId()));
    }

    public static CommentDto toDto(Comment account) {
        return new CommentDto(account.getId(), account.getComment(), account.getBook().getId());
    }
}
