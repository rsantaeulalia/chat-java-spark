package com.asapp.backend.challenge.model;

public class Image implements Content {
    private String type;
    private String url;
    private int height;
    private int width;
    private byte[] metadata;

    public Image(String type, String url, int height, int width, byte[] metadata) {
        this.type = type;
        this.url = url;
        this.height = height;
        this.width = width;
        this.metadata = metadata;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getMetadata() {
        return metadata;
    }

    public void setMetadata(byte[] metadata) {
        this.metadata = metadata;
    }
}
