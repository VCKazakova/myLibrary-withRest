package com.kazakova.mylibrarywithrest.service;

import com.kazakova.mylibrarywithrest.domain.Book;
import com.kazakova.mylibrarywithrest.domain.Comment;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

@DisplayName("Service Comment должен")
@DataJpaTest
@Import(CommentService.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CommentServiceTest {

    private static final String FIRST_UPDATE_COMMENT = "It's the best book";
    private static final Long FIRST_COMMENT_ID = 1L;
    private static final Long THIRD_COMMENT_ID = 3L;
    private static final Long EXPECTED_NUMBER_OF_COMMENTS = 10L;
    private static final Long SECOND_COMMENT_ID = 2L;


    @Autowired
    private CommentService commentService;

    @Autowired
    private TestEntityManager tem;

    @DisplayName("Добавлять комментарий")
    @Test
    public void testAddComment() throws Exception {
        Book book = commentService.findCommentById(3).get().getBook();
        Comment comment = new Comment();
        comment.setComment(FIRST_UPDATE_COMMENT);
        comment.setBook(book);
        commentService.createComment(comment);

        Optional<Comment> commentOptional = commentService.findCommentById(comment.getId());

        Assertions.assertTrue(commentOptional.isPresent(), "Comment does not exist");

        Comment comment1 = commentOptional.get();
        Assertions.assertEquals(comment, comment1);
    }

    @DisplayName("Загружать информацию о нужном комментарии по его id")
    @Test
    void testFindExpectedCommentById() {
        Optional<Comment> optionalActualComment = commentService.findCommentById(FIRST_COMMENT_ID);
        Comment expectedComment = tem.find(Comment.class, FIRST_COMMENT_ID);
        AssertionsForClassTypes.assertThat(optionalActualComment).isPresent().get()
                .isEqualTo(expectedComment);
    }

    @DisplayName("Загружать список всех комментариев")
    @Test
    void testFindAllComments() {
        List<Comment> listComment = commentService.findAllComments();
        Assertions.assertEquals(EXPECTED_NUMBER_OF_COMMENTS, listComment.size());
    }

    @DisplayName("Удалять комментарий по id")
    @Test
    public void testDeleteCommentById() {
        commentService.deleteCommentById(THIRD_COMMENT_ID);
        Assertions.assertEquals(9L, commentService.findAllComments().size());
    }

    @DisplayName("Обновлять комментарий")
    @Test
    public void testUpdateComment() {
        Optional<Comment> comment = commentService.findCommentById(SECOND_COMMENT_ID);
        Assertions.assertTrue(comment.isPresent(), "Comment does not exist");

        Comment comment1 = comment.get();
        comment1.setComment(FIRST_UPDATE_COMMENT);
        commentService.createComment(comment1);

        Optional<Comment> newComment = commentService.findCommentById(SECOND_COMMENT_ID);
        Assertions.assertTrue(newComment.isPresent(), "Comment does not exist");

        Comment result = newComment.get();

        Assertions.assertEquals(result.getComment(), FIRST_UPDATE_COMMENT);
    }


}
