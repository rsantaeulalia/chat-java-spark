package com.asapp.backend.challenge.controller;

import com.asapp.backend.challenge.resources.UserResource;
import com.asapp.backend.challenge.service.TokenValidatorService;
import com.asapp.backend.challenge.service.UserService;
import com.asapp.backend.challenge.utils.JSONUtil;
import spark.Request;
import spark.Response;
import spark.Route;

public class UsersController {

    private UserService userService;

    public UsersController(final UserService userService) {
        this.userService = userService;
    }

    public Route createUser = (Request req, Response resp) -> {
        // TODO: Create a New User
        return JSONUtil.dataToJson(new UserResource("", ""));
    };
}
