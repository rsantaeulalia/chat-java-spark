package com.asapp.backend.challenge.controller;

import com.asapp.backend.challenge.controller.model.UserRequest;
import com.asapp.backend.challenge.model.User;
import com.asapp.backend.challenge.model.adapter.UserAdapterTest;
import com.asapp.backend.challenge.service.UserService;
import com.asapp.backend.challenge.utils.JSONUtil;
import spark.Request;
import spark.Response;
import spark.Route;

public class UsersControllerTest {

    private UserService userService;

    public UsersControllerTest(final UserService userService) {
        this.userService = userService;
    }

    public Route createUser = (Request req, Response resp) -> {
        UserRequest userRequest = JSONUtil.jsonToData(req.body(), UserRequest.class);
        User user = userService.registerUser(UserAdapterTest.toDomain(userRequest));
        resp.status(201);
        return JSONUtil.dataToJson(UserAdapterTest.toApi(user));
    };
}
