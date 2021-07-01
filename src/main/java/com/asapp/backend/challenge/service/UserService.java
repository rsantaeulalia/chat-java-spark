package com.asapp.backend.challenge.service;

import com.asapp.backend.challenge.controller.model.UserRequest;
import com.asapp.backend.challenge.model.User;

import java.util.Optional;

public interface UserService {
    User registerUser(UserRequest userRequest);

    Optional<User> getUserByUsername(String username);
}
