package com.asapp.backend.challenge.repository;

import com.asapp.backend.challenge.model.User;

import java.util.Optional;

public interface UserRepository {
    User addUser(String username, String password);

    Optional<User> getByUsername(String username);
}
