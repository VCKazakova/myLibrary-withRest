package com.kazakova.mylibrarywithrest.rest;

import com.kazakova.mylibrarywithrest.domain.Comment;
import com.kazakova.mylibrarywithrest.dto.CommentDto;
import com.kazakova.mylibrarywithrest.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
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
        return service.findAllComments().stream()
                .map(CommentDto::toDto)
                .collect(Collectors.toList());
    }

    @RequestMapping(
            value = "/comment/{id}",
            method = RequestMethod.GET
    )
    public CommentDto get(
            @PathVariable("id") Long id
    ) {
        Comment comment = service.findCommentById(id).get();
        return CommentDto.toDto(comment);
    }

    @PostMapping("/createComment")
    public CommentDto createComment(@RequestBody CommentDto dto) {
        Comment comment = dto.toDomainObject();
        Comment newComment = service.createComment(comment);
        return CommentDto.toDto(newComment);
    }

    @DeleteMapping("/comment/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.deleteCommentById(id);
    }

    @PutMapping("/comment/{id}/holder")
    public void changeName(
            @PathVariable("id") Long id,
            @RequestParam("comment") String comment
    ) {
        Comment commentForChange = service.findCommentById(id).get();
        commentForChange.setComment(comment);
        service.createComment(commentForChange);
    }

}
