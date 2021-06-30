package com.asapp.backend.challenge.controller;

import com.asapp.backend.challenge.resources.LoginResource;
import com.asapp.backend.challenge.service.AuthenticationService;
import com.asapp.backend.challenge.utils.JSONUtil;
import spark.Request;
import spark.Response;
import spark.Route;

public class AuthController {
    private AuthenticationService authenticationService;

    public AuthController(final AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public Route login = (Request req, Response resp) -> {
        // TODO: Login and return a token
        return JSONUtil.dataToJson(new LoginResource("", ""));
    };

}
