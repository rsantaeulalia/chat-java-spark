package com.asapp.backend.challenge.service;

import com.asapp.backend.challenge.model.User;

public interface TokenValidatorService {
    String generateToken(User user);

    boolean validateToken(String token);
}
