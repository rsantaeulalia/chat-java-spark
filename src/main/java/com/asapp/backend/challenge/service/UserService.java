package com.asapp.backend.challenge.service;


import com.asapp.backend.challenge.model.User;

import java.util.Optional;

public interface UserService {
    User registerUser(User user);

    Optional<User> getUserByUsername(String username);

    Optional<User> getUserById(Long id);
}
