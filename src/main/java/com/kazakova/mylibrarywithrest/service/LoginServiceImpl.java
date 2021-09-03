package com.kazakova.mylibrarywithrest.service;

import com.kazakova.mylibrarywithrest.domain.Token;
import com.kazakova.mylibrarywithrest.domain.User;
import com.kazakova.mylibrarywithrest.dto.TokenDto;
import com.kazakova.mylibrarywithrest.forms.LoginForm;
import com.kazakova.mylibrarywithrest.repository.TokenRepository;
import com.kazakova.mylibrarywithrest.repository.UserRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public TokenDto login(LoginForm loginForm) {
        Optional<User> userCandidate = userRepository.findOneByLogin(loginForm.getLogin());

        if (userCandidate.isPresent()) {
            User user = userCandidate.get();

            if (passwordEncoder.matches(loginForm.getPassword(), user.getHashPassword())) {
                Token token = Token.builder()
                        .user(user)
                        .value(RandomStringUtils.random(10, true, true))
                        .build();

                tokenRepository.save(token);
                return TokenDto.from(token);
            }
        }
        throw new IllegalArgumentException("User not found");
    }
}
