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

    public static CommentDto toDto(Comment entity) {
        CommentDto dto = new CommentDto();
        dto.setId(entity.getId());
        dto.setComment(entity.getComment());
        dto.setBookId(entity.getBook().getId());
        return dto;
    }

    public Comment toDomainObject() {
        return new Comment(id, comment, new Book(getBookId()));
    }

}
