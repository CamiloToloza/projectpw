package com.northsouthairline.projectpw.exceptions.booking;

import com.northsouthairline.projectpw.exceptions.EntityNotFoundException;

public class BookingNotFoundException extends EntityNotFoundException {
    public BookingNotFoundException(int id) {
        super(String.format("Booking not found with id: %d", id));
    }
}
