package com.asapp.backend.challenge.controller;

import com.asapp.backend.challenge.resources.UserResource;
import com.asapp.backend.challenge.service.UserService;
import com.asapp.backend.challenge.utils.JSONUtil;
import org.springframework.stereotype.Component;
import spark.Request;
import spark.Response;
import spark.Route;

@Component
public class UsersController {

    private UserService userService;

    UsersController(final UserService userService) {
        this.userService = userService;
    }

    public static Route createUser = (Request req, Response resp) -> {
        // TODO: Create a New User
        return JSONUtil.dataToJson(new UserResource("", ""));
    };
}
