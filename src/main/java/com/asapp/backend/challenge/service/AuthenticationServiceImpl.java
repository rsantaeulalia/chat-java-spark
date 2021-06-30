package com.asapp.backend.challenge.service;

import com.asapp.backend.challenge.model.User;
import com.asapp.backend.challenge.resources.LoginResource;

import java.util.Optional;

public class AuthenticationServiceImpl implements AuthenticationService {

    private TokenValidatorService tokenValidatorService;
    private UserService userService;

    public AuthenticationServiceImpl(UserService userService, TokenValidatorService tokenValidatorService) {
        this.tokenValidatorService = tokenValidatorService;
        this.userService = userService;
    }

    @Override
    public LoginResource login(String userName, String password) {
        Optional<User> user = userService.getUserByUsername(userName);
        if (user.isPresent() && tokenValidatorService.checkPassword(password, user.get().getPassword())) {
            String token = tokenValidatorService.generateToken(user.get());
            return new LoginResource(user.get().getUsername(), token);
        } else {
            return null;
        }
    }
}
