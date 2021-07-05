package com.asapp.backend.challenge.model;

import com.asapp.backend.challenge.exceptions.ContentTypeNotSupportedException;
import com.asapp.backend.challenge.model.enums.SourceEnum;

public class ContentFactory implements AbstractFactory<Content> {

    public static final String VIDEO = "video";
    public static final String TEXT = "text";
    public static final String IMAGE = "image";

    @Override
    public Content create(String contentType, String url, int height, int width, String text, String source) {
        if (VIDEO.equalsIgnoreCase(contentType)) {
            return new Video(contentType, url, SourceEnum.valueOf(source.toUpperCase()));
        } else if (TEXT.equalsIgnoreCase(contentType)) {
            return new Text(contentType, text);
        } else if (IMAGE.equalsIgnoreCase(contentType)) {
            return new Image(contentType, url, height, width);
        }

        throw new ContentTypeNotSupportedException(String.format("Content type %s not supported", contentType));
    }
}
