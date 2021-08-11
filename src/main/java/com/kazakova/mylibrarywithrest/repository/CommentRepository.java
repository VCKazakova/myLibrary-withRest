package com.kazakova.mylibrarywithrest.repository;

import com.kazakova.mylibrarywithrest.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
