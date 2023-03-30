package com.andy.quizengine.exception;

public class UserWithEmailAlreadyExistsException extends RuntimeException {
    public UserWithEmailAlreadyExistsException(String email) {
        super("User with email " + email + " already exists");
    }
}