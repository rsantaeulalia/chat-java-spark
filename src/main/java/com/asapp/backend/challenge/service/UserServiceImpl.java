package com.asapp.backend.challenge.service;

import com.asapp.backend.challenge.model.User;
import com.asapp.backend.challenge.repository.UserRepository;

public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user) {
        return null;
    }
}
