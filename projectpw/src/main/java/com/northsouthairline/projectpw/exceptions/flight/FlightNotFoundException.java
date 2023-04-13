package com.northsouthairline.projectpw.exceptions.flight;

import com.northsouthairline.projectpw.exceptions.EntityNotFoundException;

public class FlightNotFoundException extends EntityNotFoundException {
    public FlightNotFoundException(Long id) {
        super(String.format("Flight not found with id: %d", id));
    }
}
/*
* exceptions
├── database
│   ├── FlightNotFoundException.java
│   ├── BookingNotFoundException.java
│   ├── UserNotFoundException.java
│   └── PersistenceException.java
├── validation
│   ├── InvalidDataException.java
│   ├── InvalidDateFormatException.java
│   ├── InvalidFlightNumberException.java
│   ├── InvalidBookingStatusException.java
│   ├── InvalidEmailFormatException.java
│   ├── RequiredFieldMissingException.java
│   └── UniqueConstraintViolationException.java
├── security
│   ├── AccessDeniedException.java
│   ├── AuthenticationException.java
│   ├── AuthorizationException.java
│   └── InvalidCredentialsException.java
└── business
    ├── InsufficientFundsException.java
    ├── InvalidStateException.java
    ├── UnsupportedUserTypeException.java
    └── MaximumNumberOfBookingsException.java
*/