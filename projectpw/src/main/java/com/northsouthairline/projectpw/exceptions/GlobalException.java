package com.northsouthairline.projectpw.exceptions;

import com.northsouthairline.projectpw.exceptions.booking.BookingNotFoundException;
import com.northsouthairline.projectpw.exceptions.booking.CustomerAlreadyExistsException;
import com.northsouthairline.projectpw.exceptions.flight.FlightCreationException;
import com.northsouthairline.projectpw.exceptions.flight.FlightNotFoundException;
import com.northsouthairline.projectpw.exceptions.user.UserCreationException;
import com.northsouthairline.projectpw.exceptions.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<ErrorResponse> bookingNotFoundHandler(BookingNotFoundException exception,
                                                                WebRequest webRequest){
        ErrorResponse error = new ErrorResponse();
        error.setErrroCode(HttpStatus.NOT_FOUND.value());
        error.setLocalDateTime(LocalDateTime.now());
        error.setMessage(exception.getMessage());
        error.setDescription(webRequest.getDescription(false));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException exception,
                                                          WebRequest webRequest){
        ErrorResponse error = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false)
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity<ErrorResponse> flightNotFoundHandler(FlightNotFoundException exception,
                                                                WebRequest webRequest){
        ErrorResponse error = new ErrorResponse();
        error.setErrroCode(HttpStatus.NOT_FOUND.value());
        error.setLocalDateTime(LocalDateTime.now());
        error.setMessage(exception.getMessage());
        error.setDescription(webRequest.getDescription(false));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    @ExceptionHandler(FlightCreationException.class)
    public ResponseEntity<ErrorResponse> handleFlightCreationException(FlightCreationException exception,
                                                               WebRequest webRequest){
        ErrorResponse error = new ErrorResponse();
        error.setErrroCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setLocalDateTime(LocalDateTime.now());
        error.setMessage(exception.getMessage());
        error.setDescription(webRequest.getDescription(false));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundHandler(UserNotFoundException exception,
                                                                WebRequest webRequest){
        ErrorResponse error = new ErrorResponse();
        error.setErrroCode(HttpStatus.NOT_FOUND.value());
        error.setLocalDateTime(LocalDateTime.now());
        error.setMessage(exception.getMessage());
        error.setDescription(webRequest.getDescription(false));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    @ExceptionHandler(UserCreationException.class)
    public ResponseEntity<ErrorResponse> handleUserCreationException(FlightCreationException exception,
                                                                       WebRequest webRequest){
        ErrorResponse error = new ErrorResponse();
        error.setErrroCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setLocalDateTime(LocalDateTime.now());
        error.setMessage(exception.getMessage());
        error.setDescription(webRequest.getDescription(false));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }
}
