package com.northsouthairline.projectpw.exceptions.booking;

public class CustomerAlreadyExistsException extends RuntimeException{
    public CustomerAlreadyExistsException() {
        super("This user already exists in the booking");
    }
}
