package com.northsouthairline.projectpw.exceptions.user;

public class UserCreationException extends RuntimeException{
    public UserCreationException() {
        super("Failed to create user");
    }
}
