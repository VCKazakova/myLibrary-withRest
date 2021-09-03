package com.kazakova.mylibrarywithrest.service;

import com.kazakova.mylibrarywithrest.forms.LoginForm;

public interface LoginService {
    TokenDto login(LoginForm loginForm);
}
