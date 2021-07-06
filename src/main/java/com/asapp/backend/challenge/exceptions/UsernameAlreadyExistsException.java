package com.asapp.backend.challenge.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
