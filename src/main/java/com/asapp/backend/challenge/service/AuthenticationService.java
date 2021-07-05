package com.asapp.backend.challenge.service;

import com.asapp.backend.challenge.model.User;
import com.asapp.backend.challenge.resources.LoginResource;

public interface AuthenticationService {
    LoginResource login(User User);
}
