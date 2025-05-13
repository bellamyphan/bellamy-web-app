package com.bellamyphan.spring_backend.exception;

public class BankTypeCreationException extends RuntimeException {
    public BankTypeCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
