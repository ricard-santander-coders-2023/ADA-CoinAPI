package com.dev.api.adacoinapi.service;

import com.dev.api.adacoinapi.model.User;
import com.dev.api.adacoinapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class UserService
{

    @Autowired
    private UserRepository userRepository;

    public User createUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userRepository.save(user);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

}
