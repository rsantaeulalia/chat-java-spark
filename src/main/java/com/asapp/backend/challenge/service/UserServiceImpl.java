package com.asapp.backend.challenge.service;

import com.asapp.backend.challenge.controller.model.UserRequest;
import com.asapp.backend.challenge.model.User;
import com.asapp.backend.challenge.repository.UserRepository;
import com.asapp.backend.challenge.utils.PasswordUtil;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(UserRequest userRequest) {
        return userRepository.addUser(userRequest.getUsername(), PasswordUtil.hashPassword(userRequest.getPassword()));
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.getByUsername(username);
    }
}
