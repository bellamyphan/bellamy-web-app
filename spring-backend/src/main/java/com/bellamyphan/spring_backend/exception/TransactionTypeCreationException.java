package com.bellamyphan.spring_backend.exception;

public class TransactionTypeCreationException extends RuntimeException {
    public TransactionTypeCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
