package com.northsouthairline.projectpw.controllers.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Currency;

@Data
public class FlightCreationDto {

    private Long id;
    private LocalDate departureDate;
    private String departureAirportCode;
    private String departureAirportName;
    private String departureCity;
    private String departureLocale;
    private String arrivalAirportCode;
    private String arrivalAirportName;
    private String arrivalCity;
    private String arrivalLocale;
    private LocalDate arrivalDate;
    private int ticketPrice;
    private Currency ticketCurrency;
    private int flightNumber;
    private int seatCapacity;
}
