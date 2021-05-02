package com.example.phonebook.exceptions;

public class NotFoundRequestException extends RuntimeException {

    public NotFoundRequestException(String message) {
        super(message);
    }

    public NotFoundRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
