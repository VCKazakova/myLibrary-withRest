package com.kazakova.mylibrarywithrest.service;

import com.kazakova.mylibrarywithrest.domain.Role;
import com.kazakova.mylibrarywithrest.domain.State;
import com.kazakova.mylibrarywithrest.domain.User;
import com.kazakova.mylibrarywithrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ivmiit.service.forms.UserForm;
import ru.ivmiit.service.models.Role;
import ru.ivmiit.service.models.State;
import ru.ivmiit.service.models.User;
import ru.ivmiit.service.repositories.UsersRepository;

import java.util.List;

/**
 * 25.04.2018
 * UsersServiceImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(UserForm userForm) {
        String hashPassword = passwordEncoder.encode(userForm.getPassword());

        User user = User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .hashPassword(hashPassword)
                .login(userForm.getLogin())
                .role(Role.USER)
                .state(State.ACTIVE)
                .build();

        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(Long userId) {
        return userRepository.findOne(userId);
    }
}
