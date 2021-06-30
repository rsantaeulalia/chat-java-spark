package com.asapp.backend.challenge.service;

import com.asapp.backend.challenge.model.User;

public interface TokenValidatorService {
    String hashPassword(String password);

    boolean checkPassword(String password, String hashedPassword);

    String generateToken(User user);

    boolean validateToken(String token);
}
