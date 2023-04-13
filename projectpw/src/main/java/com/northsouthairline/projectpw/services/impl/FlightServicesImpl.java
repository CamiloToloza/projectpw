package com.northsouthairline.projectpw.services.impl;

import com.northsouthairline.projectpw.controllers.dto.*;
import com.northsouthairline.projectpw.controllers.dto.mapping.FlightMapper;
import com.northsouthairline.projectpw.entities.Flight;
import com.northsouthairline.projectpw.exceptions.flight.FlightCreationException;
import com.northsouthairline.projectpw.exceptions.flight.FlightNotFoundException;
import com.northsouthairline.projectpw.repositories.FlightRepository;
import com.northsouthairline.projectpw.services.FlightServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightServicesImpl implements FlightServices {
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    @Autowired
    public FlightServicesImpl(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    @Override
    public Flight create(FlightCreationDto flightCreationDto) {
        try {
            return flightRepository.save(flightMapper.toCreationEntity(flightCreationDto));
        } catch (Exception exception) {//DataAccessException
            throw new FlightCreationException();
        }
    }

    @Override
    public Optional<Flight> update(Long id, FlightUpdateDto flightUpdateDto) {
        return flightRepository.findById(id)
                .map(existingFlight -> {
                    BeanUtils.copyProperties(flightUpdateDto, existingFlight);
                    return Optional.of(flightRepository.saveAndFlush(existingFlight));
                })
                .orElseThrow(()  -> new FlightNotFoundException(id));
    }

    @Override
    public List<FlightDetailsDto> findAll() {
        return flightRepository.findAll()
                .stream().map(flightMapper::toDetailsDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        try {
            flightRepository.deleteById(id);
        } catch (Exception exception) {//EmptyResultDataAccessException
            throw new FlightNotFoundException(id);
        }
    }

    @Override
    public Optional<List<FlightDetailsDto>> findByAirportCode(String idDepartureAirportCode) {
        List<FlightDetailsDto> flightsFound = flightRepository.findByFlightDepartureAirportCode(idDepartureAirportCode)
                .stream()
                .map(flightMapper::toDetailsDto)
                .collect(Collectors.toList());

        if (flightsFound.isEmpty()) throw new RuntimeException("No flights found");

        return Optional.of(flightsFound);
    }

    @Override
    public Optional<List<FlightDetailsDto>> findByDepartureAndArrivalAirportAndDate(String departureAirportCode,
                                                                           String arrivalAirportCode,
                                                                           LocalDate departureDate) {
        List<FlightDetailsDto> flightsFound = flightRepository.findFlightByDepartureAndArrivalAirportAndDate
                        (departureAirportCode, arrivalAirportCode, departureDate)
                .stream()
                .map(flightMapper::toDetailsDto)
                .toList();

        if (flightsFound.isEmpty()) throw new RuntimeException("No flights found");

        return Optional.of(flightsFound);
    }

    @Override
    public Optional<FlightDetailsDto> findById(Long id) {
        return Optional.of(flightRepository.findById(id)
                .map(flightMapper::toDetailsDto)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("this flight doesn't exists -> %s", id))));
    }


}
