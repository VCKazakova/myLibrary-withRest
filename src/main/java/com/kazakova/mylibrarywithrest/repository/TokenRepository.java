package com.kazakova.mylibrarywithrest.repository;

import com.kazakova.mylibrarywithrest.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findOneByValue(String value);
}
