package com.northsouthairline.projectpw.services;

import com.northsouthairline.projectpw.controllers.dto.FlightCreationDto;
import com.northsouthairline.projectpw.controllers.dto.FlightDetailsDto;
import com.northsouthairline.projectpw.controllers.dto.FlightUpdateDto;
import com.northsouthairline.projectpw.entities.Booking;
import com.northsouthairline.projectpw.entities.Flight;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
public interface FlightServices {
    Flight create(FlightCreationDto flightCreationDto);
    Optional<Flight> update(Long id, FlightUpdateDto flightUpdateDto);
    List<FlightDetailsDto> findAll();
    void delete(Long id);
    Optional<List<FlightDetailsDto>> findByAirportCode(String idDepartureAirportCode);
    Optional<List<FlightDetailsDto>> findByDepartureAndArrivalAirportAndDate(String departureAirportCode,
                                                                    String arrivalAirportCode,
                                                                    LocalDate departureDate);
    Optional<FlightDetailsDto> findById(Long id);

}
