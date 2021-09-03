package com.kazakova.mylibrarywithrest.security.details;

import com.kazakova.mylibrarywithrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        return new
                UserDetailsImpl(userRepository.findOneByLogin(login)
                .orElseThrow(IllegalArgumentException::new));
    }
}
