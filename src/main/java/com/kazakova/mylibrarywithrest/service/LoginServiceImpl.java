package com.kazakova.mylibrarywithrest.service;

import com.kazakova.mylibrarywithrest.domain.Token;
import com.kazakova.mylibrarywithrest.domain.User;
import com.kazakova.mylibrarywithrest.repository.TokenRepository;
import com.kazakova.mylibrarywithrest.repository.UserRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.ivmiit.service.forms.LoginForm;
import ru.ivmiit.service.transfer.TokenDto;

import java.util.Optional;

import static ru.ivmiit.service.transfer.TokenDto.from;

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
                return from(token);
            }
        }
        throw new IllegalArgumentException("User not found");
    }
}
