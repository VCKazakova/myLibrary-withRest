package com.kazakova.mylibrarywithrest.rest;

import com.kazakova.mylibrarywithrest.domain.User;
import com.kazakova.mylibrarywithrest.dto.UserDto;
import com.kazakova.mylibrarywithrest.forms.UserForm;
import com.kazakova.mylibrarywithrest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return UserDto.from(userService.findAll());
    }

    @GetMapping("/users/{user-id}")
    public User getUser(@PathVariable("user-id") Long userId) {
        return userService.findOne(userId);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> addUser(@RequestBody UserForm userForm) {
        userService.signUp(userForm);
        return ResponseEntity.ok().build();
    }
}
