package com.northsouthairline.projectpw.repositories;

import com.northsouthairline.projectpw.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query("SELECT f FROM Flight f WHERE f.departureAirportCode = :departureAirportCode")
    List<Flight> findByFlightDepartureAirportCode(String departureAirportCode);

    @Query("SELECT f FROM Flight f WHERE f.departureAirportCode = :departureAirportCode " +
            "AND f.arrivalAirportCode = :arrivalAirportCode and f.departureDate =:departureDate")
    List<Flight> findFlightByDepartureAndArrivalAirportAndDate(String departureAirportCode,
                                                                String arrivalAirportCode,
                                                                LocalDate departureDate);

}
