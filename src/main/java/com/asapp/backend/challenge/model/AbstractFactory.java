package com.asapp.backend.challenge.model;

import com.asapp.backend.challenge.model.enums.SourceEnum;

public interface AbstractFactory<T> {
    T create(String contentType, String url, int height, int width, String text, SourceEnum source);
}