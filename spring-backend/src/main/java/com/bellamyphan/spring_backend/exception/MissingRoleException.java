package com.bellamyphan.spring_backend.exception;

public class MissingRoleException extends RuntimeException {
    public MissingRoleException(String message) {
        super(message);
    }
}
