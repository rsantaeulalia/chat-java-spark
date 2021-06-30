package com.asapp.backend.challenge.service;

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
    public User registerUser(String username, String password) {
        return userRepository.addUser(username, tokenValidatorService.hashPassword(password));
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return null;
    }
}
