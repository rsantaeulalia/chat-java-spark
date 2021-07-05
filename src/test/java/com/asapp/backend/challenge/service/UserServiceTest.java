package com.asapp.backend.challenge.service;

import com.asapp.backend.challenge.model.User;
import com.asapp.backend.challenge.repository.UserRepository;
import com.asapp.backend.challenge.utils.PasswordUtilTest;

import java.util.Optional;

public class UserServiceTest implements UserService {

    private final UserRepository userRepository;

    public UserServiceTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user) {
        user.setPassword(PasswordUtilTest.hashPassword(user.getPassword()));
        return userRepository.addUser(user);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.getByUsername(username);
    }
}
