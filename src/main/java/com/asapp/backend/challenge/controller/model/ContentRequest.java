package com.asapp.backend.challenge.controller.model;

public class ContentRequest {
    private String type;
    private String text;
    private String url;
    private Long width;
    private Long height;
    private String source;

    ContentRequest() {
    }

    public ContentRequest(String type, String text, String url, Long width, Long height, String source) {
        this.type = type;
        this.text = text;
        this.url = url;
        this.width = width;
        this.height = height;
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
