package com.northsouthairline.projectpw.services;

import com.northsouthairline.projectpw.controllers.dto.BookingCreationDto;
import com.northsouthairline.projectpw.controllers.dto.BookingDetailsDto;
import com.northsouthairline.projectpw.controllers.dto.BookingUpdateDto;
import com.northsouthairline.projectpw.entities.Booking;
import com.northsouthairline.projectpw.entities.BookingStatus;
import com.northsouthairline.projectpw.entities.Flight;
import com.northsouthairline.projectpw.entities.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
public interface BookingServices {
    Booking create(Long idFlight, Long idUser,BookingCreationDto bookingCreatedDto);
    Optional<Booking> update(int id, BookingUpdateDto updatedBookingDto);
    List<BookingDetailsDto> findAll();
    List<BookingDetailsDto> findByStatusAndCustomer(BookingStatus bookingStatus, String customerName);
    void delete(int id);
    Booking assignFlight(int id, Flight assignedFlight);
    Booking addCustomer(int id, User newCustomer);
    BookingDetailsDto findByIdFlight(Long id);
    BookingDetailsDto findById(int id);
}
