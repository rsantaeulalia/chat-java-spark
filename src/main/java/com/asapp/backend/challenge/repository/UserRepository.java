package com.asapp.backend.challenge.repository;

import com.asapp.backend.challenge.model.User;

public interface UserRepository {
    User addUser(String username, String password);
}
