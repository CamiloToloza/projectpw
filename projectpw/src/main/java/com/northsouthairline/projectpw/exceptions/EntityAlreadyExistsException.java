package com.northsouthairline.projectpw.exceptions;

public class EntityAlreadyExistsException extends RuntimeException{
    public EntityAlreadyExistsException() {
        super("This one already exists");
    }
}
