package com.kazakova.mylibrarywithrest.service;

import com.kazakova.mylibrarywithrest.domain.User;

import java.util.List;

/**
 * 25.04.2018
 * UsersService
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */

public interface UserService {
    void signUp(UserForm userForm);

    List<User> findAll();

    User findOne(Long userId);
}
