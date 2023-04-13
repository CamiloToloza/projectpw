package com.northsouthairline.projectpw.exceptions.flight;

public class FlightCreationException extends RuntimeException{
    public FlightCreationException() {
        super("Failed to create flight");
    }
}
