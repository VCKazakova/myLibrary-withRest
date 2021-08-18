package com.kazakova.mylibrarywithrest.rest;

import com.kazakova.mylibrarywithrest.domain.Comment;
import com.kazakova.mylibrarywithrest.dto.CommentDto;
import com.kazakova.mylibrarywithrest.exception.NotFoundException;
import com.kazakova.mylibrarywithrest.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class CommentController {

    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }


    @RequestMapping(
            value = "/comments",
            method = RequestMethod.GET
    )
    public List<CommentDto> get() {
        log.info(">> CommentController getAllComments");
        List<CommentDto> allComments = service.findAllComments().stream()
                .map(CommentDto::toDto)
                .collect(Collectors.toList());
        log.info(">> CommentController getAllComments allComments={}", allComments);
        return allComments;
    }

    @RequestMapping(
            value = "/comment/{id}",
            method = RequestMethod.GET
    )
    public CommentDto get(
            @PathVariable("id") Long id
    ) {
        log.info(">> CommentController getCommentById id={}", id);
        Comment comment = service.findCommentById(id).orElseThrow(NotFoundException::new);
        CommentDto commentById = CommentDto.toDto(comment);
        log.info(">> CommentController getCommentById commentById={}", commentById);
        return commentById;
    }

    @PostMapping("/createComment")
    public CommentDto createComment(@RequestBody CommentDto dto) {
        log.info(">> CommentController createComment dto={}", dto);
        Comment comment = dto.toDomainObject();
        Comment newComment = service.createComment(comment);
        CommentDto createdComment = CommentDto.toDto(newComment);
        log.info(">> CommentController createComment createdComment={}", createdComment);
        return createdComment;
    }

    @DeleteMapping("/comment/{id}")
    public void delete(@PathVariable("id") Long id) {
        log.info(">> CommentController deleteComment id={}", id);
        service.deleteCommentById(id);
    }

    @PutMapping("/comment/{id}/holder")
    public void changeName(
            @PathVariable("id") Long id,
            @RequestParam("comment") String comment
    ) {
        log.info(">> CommentController changeComment id={}", id);
        Comment commentForChange = service.findCommentById(id).orElseThrow(NotFoundException::new);
        commentForChange.setComment(comment);
        Comment changedComment = service.createComment(commentForChange);
        log.info(">> CommentController changeComment changedComment={}", changedComment);
    }

}
