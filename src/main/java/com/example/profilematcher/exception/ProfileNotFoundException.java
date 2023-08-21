package com.example.profilematcher.exception;

public class ProfileNotFoundException extends RuntimeException{
    public ProfileNotFoundException(String message) {
        super(message);
    }
}
