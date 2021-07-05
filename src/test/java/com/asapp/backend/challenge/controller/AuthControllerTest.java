package com.asapp.backend.challenge.controller;

import com.asapp.backend.challenge.controller.model.UserRequest;
import com.asapp.backend.challenge.model.adapter.UserAdapterTest;
import com.asapp.backend.challenge.service.AuthenticationService;
import com.asapp.backend.challenge.utils.JSONUtil;
import spark.Request;
import spark.Response;
import spark.Route;

public class AuthControllerTest {
    private AuthenticationService authenticationService;

    public AuthControllerTest(final AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public Route login = (Request req, Response resp) -> {
        UserRequest userRequest = JSONUtil.jsonToData(req.body(), UserRequest.class);
        return JSONUtil.dataToJson(authenticationService.login(UserAdapterTest.toDomain(userRequest)));
    };

}
