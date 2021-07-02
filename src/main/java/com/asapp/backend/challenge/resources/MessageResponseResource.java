package com.asapp.backend.challenge.resources;

import java.util.Date;

public class MessageResponseResource {

    private Long id;
    private Date timestamp;

    public MessageResponseResource(Long id, Date timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}
