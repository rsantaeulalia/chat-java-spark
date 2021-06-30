package com.asapp.backend.challenge.model;

import java.time.LocalDate;

public class User {
    private Long id;
    private String username;
    private String password;
    private LocalDate creationDate;

    public User(String username, String password, LocalDate creationDate) {
        this.username = username;
        this.password = password;
        this.creationDate = creationDate;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
