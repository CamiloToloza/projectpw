package com.northsouthairline.projectpw.repositories;

import com.northsouthairline.projectpw.entities.Booking;
import com.northsouthairline.projectpw.entities.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query("SELECT b FROM Booking b INNER JOIN b.customers c WHERE b.bookingStatus = :bookingStatus AND c.username IN :customerNames")
    List<Booking> findByStatusAndCustomer(BookingStatus bookingStatus,  String customerName);
    List<Booking> findByStatus(BookingStatus bookingStatus);
    Optional<Booking> findByBookingReference(String bookingReference);
    @Query("SELECT b FROM Booking b WHERE b.bookingStatus = :bookingStatus AND b.outboundFlight.id = :idFlight")
    Booking findBookingByIdFlight(Long idFlight);


}
