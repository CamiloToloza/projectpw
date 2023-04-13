package com.northsouthairline.projectpw.controllers.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Currency;
@Data
public class FlightUpdateDto {

    private LocalDate departureDate;
    private String departureAirportCode;
    private String arrivalAirportCode;
    private LocalDate arrivalDate;
    private int ticketPrice;
    private Currency ticketCurrency;
}
