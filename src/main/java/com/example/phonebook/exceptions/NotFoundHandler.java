package com.example.phonebook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class NotFoundHandler {
    @ExceptionHandler(value = {NotFoundRequestException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundRequestException e) {
        HttpStatus notFoundRequest = HttpStatus.NOT_FOUND;
        NotFoundException notFoundException = new NotFoundException(e.getMessage(),
                notFoundRequest,
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(notFoundException, notFoundRequest);
    }
}
