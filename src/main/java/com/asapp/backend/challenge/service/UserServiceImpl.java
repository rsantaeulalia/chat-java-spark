package com.asapp.backend.challenge.service;

import com.asapp.backend.challenge.controller.model.UserRequest;
import com.asapp.backend.challenge.model.User;
import com.asapp.backend.challenge.repository.UserRepository;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TokenValidatorService tokenValidatorService;

    public UserServiceImpl(UserRepository userRepository,
                           TokenValidatorService tokenValidatorService) {
        this.userRepository = userRepository;
        this.tokenValidatorService = tokenValidatorService;
    }

    @Override
    public User registerUser(UserRequest userRequest) {
        return userRepository.addUser(userRequest.getUsername(), tokenValidatorService.hashPassword(userRequest.getPassword()));
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.getByUsername(username);
    }
}
