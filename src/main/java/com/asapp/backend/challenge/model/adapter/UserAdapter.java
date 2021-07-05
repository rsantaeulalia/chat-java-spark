package com.asapp.backend.challenge.model.adapter;

import com.asapp.backend.challenge.controller.model.UserRequest;
import com.asapp.backend.challenge.model.User;
import com.asapp.backend.challenge.resources.UserResource;

import java.util.Date;

public class UserAdapter {
    public static User toDomain(UserRequest userRequest) {
        return new User(userRequest.getUsername(), userRequest.getPassword(), new Date());
    }

    public static UserResource toApi(com.asapp.backend.challenge.model.User user) {
        return new UserResource(user.getId());
    }
}
