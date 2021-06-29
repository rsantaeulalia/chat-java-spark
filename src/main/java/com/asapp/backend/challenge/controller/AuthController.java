package com.asapp.backend.challenge.controller;

import com.asapp.backend.challenge.resources.LoginResource;
import com.asapp.backend.challenge.utils.JSONUtil;
import spark.*;

public class AuthController {

    public static Route login = (Request req, Response resp) -> {
        // TODO: Login and return a token
        return JSONUtil.dataToJson(new LoginResource());
    };

}
