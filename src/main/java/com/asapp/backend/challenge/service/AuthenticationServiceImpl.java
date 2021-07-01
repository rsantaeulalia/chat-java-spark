package com.asapp.backend.challenge.service;

import com.asapp.backend.challenge.controller.model.UserRequest;
import com.asapp.backend.challenge.model.User;
import com.asapp.backend.challenge.resources.LoginResource;

import java.util.Optional;

public class AuthenticationServiceImpl implements AuthenticationService {

    private final TokenValidatorService tokenValidatorService;
    private final UserService userService;

    public AuthenticationServiceImpl(UserService userService, TokenValidatorService tokenValidatorService) {
        this.tokenValidatorService = tokenValidatorService;
        this.userService = userService;
    }

    @Override
    public LoginResource login(UserRequest userRequest) {
        Optional<User> user = userService.getUserByUsername(userRequest.getUsername());
        if (user.isPresent() && tokenValidatorService.checkPassword(userRequest.getPassword(), user.get().getPassword())) {
            String token = tokenValidatorService.generateToken(user.get());
            return new LoginResource(user.get().getId(), token);
        } else {
            return null;
        }
    }
}
