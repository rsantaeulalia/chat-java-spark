package com.asapp.backend.challenge.resources;

public class UserResource {
    private Long id;

    public UserResource(Long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
