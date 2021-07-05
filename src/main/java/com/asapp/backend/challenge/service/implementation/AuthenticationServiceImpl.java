package com.asapp.backend.challenge.service.implementation;

import com.asapp.backend.challenge.model.User;
import com.asapp.backend.challenge.resources.LoginResource;
import com.asapp.backend.challenge.service.AuthenticationService;
import com.asapp.backend.challenge.service.TokenValidatorService;
import com.asapp.backend.challenge.service.UserService;
import com.asapp.backend.challenge.utils.PasswordUtil;

import java.util.Optional;

public class AuthenticationServiceImpl implements AuthenticationService {

    private final TokenValidatorService tokenValidatorService;
    private final UserService userService;

    public AuthenticationServiceImpl(UserService userService, TokenValidatorService tokenValidatorService) {
        this.tokenValidatorService = tokenValidatorService;
        this.userService = userService;
    }

    @Override
    public LoginResource login(User userToLogin) {
        Optional<User> user = userService.getUserByUsername(userToLogin.getUsername());
        if (user.isPresent() && PasswordUtil.checkPassword(userToLogin.getPassword(), user.get().getPassword())) {
            String token = tokenValidatorService.generateToken(user.get());
            return new LoginResource(user.get().getId(), token);
        } else {
            return null;
        }
    }
}
