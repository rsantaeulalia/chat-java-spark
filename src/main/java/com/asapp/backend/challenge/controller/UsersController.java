package com.asapp.backend.challenge.controller;

import com.asapp.backend.challenge.controller.model.UserRequest;
import com.asapp.backend.challenge.model.User;
import com.asapp.backend.challenge.model.adapter.UserAdapter;
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
        UserRequest userRequest = JSONUtil.jsonToData(req.body(), UserRequest.class);
        User user = userService.registerUser(UserAdapter.toDomain(userRequest));
        resp.status(201);
        resp.type("application/json");
        return JSONUtil.dataToJson(UserAdapter.toApi(user));
    };
}
