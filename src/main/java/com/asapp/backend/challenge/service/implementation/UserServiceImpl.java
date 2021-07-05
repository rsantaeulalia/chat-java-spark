package com.asapp.backend.challenge.service.implementation;

import com.asapp.backend.challenge.model.User;
import com.asapp.backend.challenge.repository.UserRepository;
import com.asapp.backend.challenge.service.UserService;
import com.asapp.backend.challenge.utils.PasswordUtil;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user) {
        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
        return userRepository.addUser(user);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.getById(id);
    }
}
