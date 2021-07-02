package com.asapp.backend.challenge.controller.model;

public class MessageRequest {
    private Long recipient;
    private Long sender;
    private ContentRequest content;

    MessageRequest(){}

    public MessageRequest(Long recipient, Long sender, ContentRequest content) {
        this.recipient = recipient;
        this.sender = sender;
        this.content = content;
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

    public ContentRequest getContent() {
        return content;
    }

    public void setContent(ContentRequest content) {
        this.content = content;
    }
}
