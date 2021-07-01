package com.asapp.backend.challenge.model;

import java.util.Date;

public class Message {
    private Long id;
    private User senderId;
    private User receiverId;
    private Content content;
    private Date creationDate;

    public Message(Long id, User senderId, User receiverId, Content content, Date creationDate) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSenderId() {
        return senderId;
    }

    public void setSenderId(User senderId) {
        this.senderId = senderId;
    }

    public User getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(User receiverId) {
        this.receiverId = receiverId;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
