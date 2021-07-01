package com.asapp.backend.challenge.controller.model;

public class MessageRequest {
    private Long recipient;
    private Long sender;
    private ContentRequest contentRequest;

    MessageRequest(){}

    public MessageRequest(Long recipient, Long sender, ContentRequest contentRequest) {
        this.recipient = recipient;
        this.sender = sender;
        this.contentRequest = contentRequest;
    }

    public Long getRecipient() {
        return recipient;
    }

    public void setRecipient(Long recipient) {
        this.recipient = recipient;
    }

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public ContentRequest getContentRequest() {
        return contentRequest;
    }

    public void setContentRequest(ContentRequest contentRequest) {
        this.contentRequest = contentRequest;
    }
}
