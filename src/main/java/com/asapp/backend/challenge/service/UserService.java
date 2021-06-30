package com.asapp.backend.challenge.service;

import com.asapp.backend.challenge.model.User;

import java.util.Optional;

public interface UserService {
    User registerUser(String username, String password);

    Optional<User> getUserByUsername(String username);
}
