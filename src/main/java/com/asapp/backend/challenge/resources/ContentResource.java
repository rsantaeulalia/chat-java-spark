package com.asapp.backend.challenge.resources;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContentResource {
    private String type;
    private String text;
    private String url;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int width;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int height;
    private String source;

    ContentResource() {
    }

    public ContentResource(String type, String text) {
        this.type = type;
        this.text = text;
    }

    public ContentResource(String type, String url, int width, int height) {
        this.type = type;
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public ContentResource(String type, String url, String source) {
        this.type = type;
        this.url = url;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
