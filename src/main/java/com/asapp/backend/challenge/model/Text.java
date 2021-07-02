package com.asapp.backend.challenge.model;


public class Text implements Content {
    private String type;
    private String text;

    public Text(String type, String text) {
        this.text = text;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
