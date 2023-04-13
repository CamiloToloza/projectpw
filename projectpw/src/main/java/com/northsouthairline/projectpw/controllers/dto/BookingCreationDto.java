package com.northsouthairline.projectpw.controllers.dto;

import com.northsouthairline.projectpw.entities.BookingStatus;
import com.northsouthairline.projectpw.entities.Flight;
import lombok.Data;


@Data
public class BookingCreationDto {

    private BookingStatus bookingStatus;
    private Flight outboundFlight;
    private Boolean checkedIn;
    private String bookingReference;
}
