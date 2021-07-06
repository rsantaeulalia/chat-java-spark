package com.asapp.backend.challenge.service.implementation;

import com.asapp.backend.challenge.exceptions.UsernameAlreadyExistsException;
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
        validateExistenceOfUser(user.getUsername());
        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
        return userRepository.addUser(user);
    }

    private void validateExistenceOfUser(String username) {
        if (userRepository.getByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException(String.format("Username %s already exists", username));
        }
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
