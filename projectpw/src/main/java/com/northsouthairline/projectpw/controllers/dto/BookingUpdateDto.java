package com.northsouthairline.projectpw.controllers.dto;

import com.northsouthairline.projectpw.entities.BookingStatus;
import com.northsouthairline.projectpw.entities.User;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class BookingUpdateDto {

    private int id;
    private BookingStatus bookingStatus;
    private Boolean checkedIn;
    private Set<User> customers = new HashSet<>();

}
