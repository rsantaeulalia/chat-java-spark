package com.asapp.backend.challenge.resources;

public class ContentResource {
    private String type;
    private String text;
    private String url;
    private int width;
    private int height;
    private String source;

    ContentResource() {
    }

    public ContentResource(String type, String text, String url, int width, int height, String source) {
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
