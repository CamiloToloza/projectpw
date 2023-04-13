package com.northsouthairline.projectpw.services.impl;

import com.northsouthairline.projectpw.controllers.dto.BookingCreationDto;
import com.northsouthairline.projectpw.controllers.dto.BookingDetailsDto;
import com.northsouthairline.projectpw.controllers.dto.BookingUpdateDto;
import com.northsouthairline.projectpw.controllers.dto.mapping.BookingMapper;
import com.northsouthairline.projectpw.entities.Booking;
import com.northsouthairline.projectpw.entities.BookingStatus;
import com.northsouthairline.projectpw.entities.Flight;
import com.northsouthairline.projectpw.entities.User;
import com.northsouthairline.projectpw.exceptions.booking.BookingNotFoundException;
import com.northsouthairline.projectpw.exceptions.booking.CustomerAlreadyExistsException;
import com.northsouthairline.projectpw.repositories.BookingRepository;
import com.northsouthairline.projectpw.repositories.FlightRepository;
import com.northsouthairline.projectpw.repositories.UserRepository;
import com.northsouthairline.projectpw.services.BookingServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServicesImpl implements BookingServices {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookingServicesImpl(BookingRepository bookingRepository, BookingMapper bookingMapper,
                               FlightRepository flightRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Booking create(Long idFlight, Long idUser, BookingCreationDto bookingCreatedDto) {
        Booking bookingCreated = bookingMapper.toCreationEntity(bookingCreatedDto);
        if (flightRepository.findById(idFlight).isPresent())
            assignFlight(bookingCreated.getId(), flightRepository.findById(idFlight).get());
        if (userRepository.findById(idUser).isPresent())
            addCustomer(bookingCreated.getId(), userRepository.findById(idUser).get());

        return bookingRepository.save(bookingCreated);
    }

    public Optional<Booking> update(int id, BookingUpdateDto updatedBookingDto) {
        return bookingRepository.findById(id)
                .map(existingBooking -> {
                    BeanUtils.copyProperties(updatedBookingDto, existingBooking);
                    return Optional.of(bookingRepository.saveAndFlush(existingBooking));
                })
                .orElseThrow(() -> new BookingNotFoundException(id));
    }

    @Override
    public List<BookingDetailsDto> findAll() {
        return bookingRepository.findAll()
                .stream().map(bookingMapper::toDetailsDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingDetailsDto> findByStatusAndCustomer(BookingStatus bookingStatus, String customerName) {
        return bookingRepository.findByStatusAndCustomer(bookingStatus, customerName)
                .stream().map(bookingMapper::toDetailsDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(int id) {
        try {
            bookingRepository.deleteById(id);
        } catch (Exception exception) { //EmptyResultDataAccessException
            throw new BookingNotFoundException(id);
        }
    }

    @Override
    public Booking assignFlight(int id, Flight assignedFlight) {
        return bookingRepository.findById(id)
                .map(bookingInDB -> {
                    Flight flightInDB = flightRepository.findById(assignedFlight.getId())
                            .orElseGet(() -> flightRepository.save(assignedFlight));
                    bookingInDB.setOutboundFlight(flightInDB);
                    return bookingRepository.saveAndFlush(bookingInDB);
                })
                .orElseThrow(() -> new BookingNotFoundException(id));
    }

    @Override
    public Booking addCustomer(int id, User newCustomer) {
        return bookingRepository.findById(id)
                .map(bookingInDB -> {
                    User CustomerInDB = userRepository.findById(newCustomer.getId())
                            .orElseGet(() -> userRepository.save(newCustomer));
                    if (bookingInDB.getCustomers().contains(newCustomer)) {
                        throw new CustomerAlreadyExistsException();
                    }
                    bookingInDB.addCustomer(CustomerInDB);
                    return bookingRepository.saveAndFlush(bookingInDB);
                })
                .orElseThrow(() -> new BookingNotFoundException(id));
    }

    @Override
    public BookingDetailsDto findByIdFlight(Long id){
        Booking booking = bookingRepository.findBookingByIdFlight(id);
        return bookingMapper.toDetailsDto(booking);
    }

    @Override
    public BookingDetailsDto findById(int id) {
        return bookingRepository.findById(id)
                .map(bookingMapper::toDetailsDto)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }


}

