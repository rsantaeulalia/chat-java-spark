package com.asapp.backend.challenge.resources;

public class LoginResource {

    private Long id;
    private String token;

    public LoginResource(Long id, String token) {
        this.id = id;
        this.token = token;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
