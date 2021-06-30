package com.asapp.backend.challenge.filter;

import static spark.Spark.halt;

import com.asapp.backend.challenge.service.TokenValidatorService;
import spark.Filter;
import spark.Request;
import spark.Response;

public class TokenValidatorFilter {

    private TokenValidatorService tokenValidatorService;

    public TokenValidatorFilter(TokenValidatorService tokenValidatorService) {
        this.tokenValidatorService = tokenValidatorService;
    }

    private static final String TOKEN_PREFIX = "Bearer";

    public Filter validateUser = (Request req, Response resp) -> {
        String authorizationHeader = req.headers("Authorization");
        if (authorizationHeader == null) {
            halt(401, "Invalid Token");
        } else if (!tokenValidatorService.validateToken(authorizationHeader.replace(TOKEN_PREFIX, ""))) {
            halt(401, "Invalid Token");
        }
    };
}
