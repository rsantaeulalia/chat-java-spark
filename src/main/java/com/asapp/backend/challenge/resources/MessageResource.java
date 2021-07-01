package com.asapp.backend.challenge.resources;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MessageResource {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ssZ")
    private Date timestamp;
    private Long sender;
    private Long recipient;
    private ContentResource content;

    public MessageResource(){}

    public MessageResource(Long id, Date timestamp, Long sender, Long recipient, ContentResource content) {
        this.id = id;
        this.timestamp = timestamp;
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public Long getRecipient() {
        return recipient;
    }

    public void setRecipient(Long recipient) {
        this.recipient = recipient;
    }

    public ContentResource getContent() {
        return content;
    }

    public void setContent(ContentResource content) {
        this.content = content;
    }
}
