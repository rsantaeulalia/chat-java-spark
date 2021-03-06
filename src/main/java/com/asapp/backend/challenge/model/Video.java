package com.asapp.backend.challenge.model;

import com.asapp.backend.challenge.model.enums.SourceEnum;

public class Video implements Content {
    private String type;
    private String url;
    private SourceEnum source;
    private byte[] metadata;

    public Video(String type, String url, SourceEnum source, byte[] metadata) {
        this.type = type;
        this.url = url;
        this.source = source;
        this.metadata = metadata;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public SourceEnum getSource() {
        return source;
    }

    public void setSource(SourceEnum source) {
        this.source = source;
    }

    public byte[] getMetadata() {
        return metadata;
    }

    public void setMetadata(byte[] metadata) {
        this.metadata = metadata;
    }
}
