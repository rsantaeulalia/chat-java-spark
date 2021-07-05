package com.asapp.backend.challenge.exceptions;

public class MissingParametersException extends Exception {
    public MissingParametersException(String errorMessage) {
        super(errorMessage);
    }
}
