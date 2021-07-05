package com.asapp.backend.challenge.repository;

import com.asapp.backend.challenge.model.User;

import java.util.Optional;

public interface UserRepository {
    User addUser(User user);

    Optional<User> getByUsername(String username);
}
