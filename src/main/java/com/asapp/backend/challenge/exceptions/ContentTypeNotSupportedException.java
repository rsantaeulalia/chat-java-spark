package com.asapp.backend.challenge.exceptions;

public class ContentTypeNotSupportedException extends RuntimeException {
    public ContentTypeNotSupportedException(String errorMessage) {
        super(errorMessage);
    }
}
