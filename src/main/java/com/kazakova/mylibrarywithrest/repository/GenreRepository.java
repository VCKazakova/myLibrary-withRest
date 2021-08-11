package com.kazakova.mylibrarywithrest.repository;

import com.kazakova.mylibrarywithrest.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
