package com.kazakova.mylibrarywithrest.service;

import com.kazakova.mylibrarywithrest.domain.Comment;
import com.kazakova.mylibrarywithrest.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Optional<Comment> findCommentById(Long id) {
        return commentRepository.findById(id);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }

}

