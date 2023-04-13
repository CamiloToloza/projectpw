package com.northsouthairline.projectpw.controllers;

import com.northsouthairline.projectpw.controllers.dto.BookingCreationDto;
import com.northsouthairline.projectpw.controllers.dto.BookingDetailsDto;
import com.northsouthairline.projectpw.controllers.dto.mapping.BookingMapper;
import com.northsouthairline.projectpw.entities.Booking;
import com.northsouthairline.projectpw.entities.BookingStatus;
import com.northsouthairline.projectpw.exceptions.EntityAlreadyExistsException;
import com.northsouthairline.projectpw.exceptions.booking.BookingNotFoundException;
import com.northsouthairline.projectpw.exceptions.flight.FlightNotFoundException;
import com.northsouthairline.projectpw.exceptions.user.UserNotFoundException;
import com.northsouthairline.projectpw.services.BookingServices;
import com.northsouthairline.projectpw.services.FlightServices;
import com.northsouthairline.projectpw.services.UserServices;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/northsouthairline/bookings")
public class BookingController {

    private final BookingServices bookingServices;
    private final UserServices userServices;
    private final FlightServices flightServices;
    private final BookingMapper bookingMapper;

    public BookingController(BookingServices bookingServices, BookingMapper bookingMapper,
                             UserServices userServices, FlightServices flightServices) {
        this.bookingServices = bookingServices;
        this.bookingMapper = bookingMapper;
        this.userServices = userServices;
        this.flightServices = flightServices;
    }

    @PostMapping("/booking/flight/{idFlight}/user/{idUser}")
    public ResponseEntity<BookingCreationDto> create(@PathVariable("idFlight")Long idFlight,
                                                     @PathVariable("idUser")Long idUser,
                                                     @RequestBody BookingCreationDto bookingDto){
        userServices.findById(idUser).orElseThrow(() -> new UserNotFoundException(idUser));
        flightServices.findById(idFlight).orElseThrow(() -> new FlightNotFoundException(idFlight));

        Booking bookingCreated = null;
        try {
            bookingCreated = bookingServices.create(idFlight, idUser, bookingDto);
        } catch (DataIntegrityViolationException exception) {
            throw new EntityAlreadyExistsException();
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(bookingCreated.getId())
                .toUri();

        return ResponseEntity.created(location).body(bookingDto);
    }
    @GetMapping("/booking/")
    public ResponseEntity<List<BookingDetailsDto>> findByStatusAndCustomer(@RequestParam("status") BookingStatus status,
                                                                           @RequestParam("customerName") String customerName){
        try {
           return ResponseEntity.ok()
                   .body(bookingServices.findByStatusAndCustomer(status, customerName));
        } catch (Exception exception) {
            throw new RuntimeException("The list is empty");
        }
    }
    @GetMapping("/booking/{id}")
    public ResponseEntity<BookingDetailsDto> findById(@PathVariable("id") int id) {
        try {
            return ResponseEntity.ok()
                    .body(bookingServices.findById(id));
        } catch (Exception e) {
           throw  new BookingNotFoundException(id);
        }
    }
    @GetMapping("/booking/flight/{idflight}")
    public ResponseEntity<BookingDetailsDto> findById(@PathVariable("idflight") Long idFlight) {
        BookingDetailsDto booking = bookingServices.findByIdFlight(idFlight);
        if (booking != null) {
            BookingDetailsDto bookingDto = new BookingDetailsDto();
            BeanUtils.copyProperties(booking, bookingDto);
            return ResponseEntity.ok().body(bookingDto);
        } else {
            throw new RuntimeException("The Booking not found");
        }
    }

    @DeleteMapping("/catalog/{id}")
    public ResponseEntity<String> deleteByid(@PathVariable int id) {
        bookingServices.delete(id);
        return ResponseEntity.ok(String.format("The flight have been deleted: %d", id));
    }

}



