package com.northsouthairline.projectpw.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Currency;

@Entity
@Table(name = "Flight")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate departureDate;

    @Column (nullable = false, unique=true)
    private String departureAirportCode;

    @Column(nullable = false)
    private String departureAirportName;

    @Column(nullable = false)
    private String departureCity;

    @Column(nullable = false)
    private String departureLocale;

    @Column(nullable = false, unique=true)
    private String arrivalAirportCode;

    @Column(nullable = false)
    private String arrivalAirportName;

    @Column(nullable = false)
    private String arrivalCity;

    @Column(nullable = false)
    private String arrivalLocale;

    @Column(nullable = false)
    private LocalDate arrivalDate;

    @Column(nullable = false)
    private int ticketPrice;

    @Column(nullable = false)
    private Currency ticketCurrency;

    @Column (nullable = false, unique=true)
    private int flightNumber;

    @Column(nullable = false)
    private int seatCapacity;

}
/*
* Flight

id: int
departure_date: Date
departure_airport_id: int (FK to Airports table)
arrival_date: Date
arrival_airport_id: int (FK to Airports table)
ticket_price: decimal
flight_number: string
seat_capacity: int
Airports

id: int
airport_code: string
airport_name: string
city: string
country: string
timezone: string
Booking

id: int
status: BookingStatus
outbound_flight_id: int (FK to Flights table)
payment_id: int (FK to Payments table)
checked_in: boolean
customer_id: int (FK to Users table)
created_at: datetime
booking_reference: string
BookingStatus (enum)

UNCONFIRMED
CONFIRMED
CANCELLED
User

id: int
full_name: string
email: string
password: string
created_at: datetime
Payment

id: int
token: string
currency: string
amount: decimal
Seat

id: int
flight_id: int (FK to Flights table)
seat_type: string
available: boolean
seat_number: string*/