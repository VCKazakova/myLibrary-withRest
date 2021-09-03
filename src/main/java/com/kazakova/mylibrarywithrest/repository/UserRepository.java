package com.kazakova.mylibrarywithrest.repository;

import com.kazakova.mylibrarywithrest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByFirstName(String firstName);

    Optional<User> findOneByLogin(String login);
}
