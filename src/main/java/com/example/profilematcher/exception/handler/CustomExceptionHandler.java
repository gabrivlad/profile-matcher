package com.example.profilematcher.exception.handler;

import com.example.profilematcher.exception.ProfileNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class CustomExceptionHandler {
    @ExceptionHandler(ProfileNotFoundException.class)
    public ResponseEntity<String> handleProfileMatcherException(ProfileNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
