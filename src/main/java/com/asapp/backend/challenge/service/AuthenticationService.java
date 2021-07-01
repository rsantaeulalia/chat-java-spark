package com.asapp.backend.challenge.service;

import com.asapp.backend.challenge.controller.model.UserRequest;
import com.asapp.backend.challenge.resources.LoginResource;

public interface AuthenticationService {
    LoginResource login(UserRequest userRequest);
}
