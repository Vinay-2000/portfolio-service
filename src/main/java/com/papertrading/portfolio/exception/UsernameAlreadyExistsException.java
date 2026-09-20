package com.papertrading.portfolio.exception;

public class UsernameAlreadyExistsException extends RuntimeException{

    public UsernameAlreadyExistsException(String username) {
        super("Username already exists: " + username);
    }
}
