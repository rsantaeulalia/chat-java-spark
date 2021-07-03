package com.asapp.backend.challenge.model;

public interface AbstractFactory<T> {
    T create(String contentType, String url, int height, int width, String text, String source);
}