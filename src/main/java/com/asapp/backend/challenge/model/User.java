package com.asapp.backend.challenge.model;

import java.util.Date;

public class User {
    private Long id;
    private String username;
    private String password;
    private Date creationDate;

    public User(String username, String password, Date creationDate) {
        this.username = username;
        this.password = password;
        this.creationDate = creationDate;
    }

    public User(Long id, String username, String password, Date creationDate) {
        this.id = id;
        this.username = username;
        this.creationDate = creationDate;
        this.password = password;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
