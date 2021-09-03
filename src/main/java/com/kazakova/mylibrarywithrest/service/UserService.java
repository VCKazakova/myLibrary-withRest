package com.kazakova.mylibrarywithrest.service;

import com.kazakova.mylibrarywithrest.domain.User;
import com.kazakova.mylibrarywithrest.forms.UserForm;

import java.util.List;

public interface UserService {
    void signUp(UserForm userForm);

    List<User> findAll();

    User findOne(Long userId);
}
