package org.nya.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.nya.exceptions.InvalidUuidException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidUuidException.class)
    public ResponseEntity<?> handleInvalidUuidException(InvalidUuidException ex) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("\"" + ex.getMessage() + "\"");
    }
}
