package com.asapp.backend.challenge.controller;

import com.asapp.backend.challenge.resources.LoginResource;
import com.asapp.backend.challenge.service.TokenValidatorService;
import com.asapp.backend.challenge.service.UserService;
import com.asapp.backend.challenge.utils.JSONUtil;
import spark.Request;
import spark.Response;
import spark.Route;

public class AuthController {
    private final TokenValidatorService tokenValidatorService;
    private UserService userService;

    public AuthController(final UserService userService,
                          final TokenValidatorService tokenValidatorService) {
        this.userService = userService;
        this.tokenValidatorService = tokenValidatorService;
    }

    public Route login = (Request req, Response resp) -> {
        // TODO: Login and return a token
        return JSONUtil.dataToJson(new LoginResource("", ""));
    };

}
