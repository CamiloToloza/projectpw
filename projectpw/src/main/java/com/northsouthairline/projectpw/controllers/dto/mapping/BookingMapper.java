package com.northsouthairline.projectpw.controllers.dto.mapping;

import com.northsouthairline.projectpw.controllers.dto.BookingCreationDto;
import com.northsouthairline.projectpw.controllers.dto.BookingDetailsDto;
import com.northsouthairline.projectpw.controllers.dto.BookingUpdateDto;
import com.northsouthairline.projectpw.entities.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public Booking toUpdateEntity(BookingUpdateDto bookingUpdateDto){

        Booking booking = new Booking();
        booking.setId(bookingUpdateDto.getId());
        booking.setBookingStatus(bookingUpdateDto.getBookingStatus());
        booking.setCheckedIn(bookingUpdateDto.getCheckedIn());
        booking.setCustomers(bookingUpdateDto.getCustomers());

        return booking;
    }

    public BookingUpdateDto toUpdateDto(Booking booking){

        BookingUpdateDto bookingUpdateDto = new BookingUpdateDto();
        bookingUpdateDto.setId(booking.getId());
        bookingUpdateDto.setBookingStatus(booking.getBookingStatus());
        bookingUpdateDto.setCheckedIn(booking.getCheckedIn());
        bookingUpdateDto.setCustomers(booking.getCustomers());

        return bookingUpdateDto;
    }

    public Booking toDetailsEntity(BookingDetailsDto bookingDetailsDto){

        Booking booking = new Booking();
        booking.setBookingStatus(bookingDetailsDto.getBookingStatus());
        booking.setCheckedIn(bookingDetailsDto.getCheckedIn());
        booking.setBookingReference(bookingDetailsDto.getBookingReference());

        return booking;
    }

    public BookingDetailsDto toDetailsDto(Booking booking){

        BookingDetailsDto bookingDetailsDto = new BookingDetailsDto();
        bookingDetailsDto.setBookingStatus(booking.getBookingStatus());
        bookingDetailsDto.setCheckedIn(bookingDetailsDto.getCheckedIn());
        bookingDetailsDto.setBookingReference(booking.getBookingReference());

        return bookingDetailsDto;
    }

    public Booking toCreationEntity(BookingCreationDto bookingCreationDto){

        Booking booking = new Booking();
        booking.setBookingStatus(bookingCreationDto.getBookingStatus());
        booking.setOutboundFlight(bookingCreationDto.getOutboundFlight());
        booking.setBookingReference(bookingCreationDto.getBookingReference());

        return booking;
    }

    public BookingCreationDto toCreationDto(Booking booking){

        BookingCreationDto bookingCreationDto = new BookingCreationDto();
        bookingCreationDto.setBookingStatus(booking.getBookingStatus());
        bookingCreationDto.setOutboundFlight(booking.getOutboundFlight());
        bookingCreationDto.setBookingReference(booking.getBookingReference());

        return bookingCreationDto;
    }


}
