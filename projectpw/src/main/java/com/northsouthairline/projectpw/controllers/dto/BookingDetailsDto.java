package com.northsouthairline.projectpw.controllers.dto;

import com.northsouthairline.projectpw.entities.BookingStatus;
import lombok.Data;

@Data
public class BookingDetailsDto {

    private String bookingReference;
    private BookingStatus bookingStatus;
    private Boolean checkedIn;
    private String createdAt;

}
