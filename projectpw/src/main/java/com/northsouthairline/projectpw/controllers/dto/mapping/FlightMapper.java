package com.northsouthairline.projectpw.controllers.dto.mapping;


import com.northsouthairline.projectpw.controllers.dto.FlightCreationDto;
import com.northsouthairline.projectpw.controllers.dto.FlightDetailsDto;
import com.northsouthairline.projectpw.controllers.dto.FlightUpdateDto;
import com.northsouthairline.projectpw.entities.Flight;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {

    public Flight toCreationEntity(FlightCreationDto flightCreationDto){

        Flight flight = new Flight();
        //BeanUtils.copyProperties(flightCreationDto, flight);
        flight.setId(flightCreationDto.getId());
        flight.setDepartureDate(flightCreationDto.getDepartureDate());
        flight.setDepartureAirportCode(flightCreationDto.getDepartureAirportCode());
        flight.setDepartureAirportName(flightCreationDto.getDepartureAirportName());
        flight.setDepartureCity(flightCreationDto.getDepartureCity());
        flight.setDepartureLocale(flightCreationDto.getDepartureLocale());
        flight.setArrivalAirportCode(flightCreationDto.getArrivalAirportCode());
        flight.setArrivalAirportName(flightCreationDto.getArrivalAirportName());
        flight.setArrivalCity(flightCreationDto.getArrivalCity());
        flight.setArrivalLocale(flightCreationDto.getArrivalLocale());
        flight.setArrivalDate(flightCreationDto.getArrivalDate());
        flight.setTicketPrice(flightCreationDto.getTicketPrice());
        flight.setTicketCurrency(flightCreationDto.getTicketCurrency());
        flight.setFlightNumber(flightCreationDto.getFlightNumber());
        flight.setSeatCapacity(flightCreationDto.getSeatCapacity());

        return flight;
    }

    public FlightCreationDto toCreationDto(Flight flight){

        FlightCreationDto flightCreationDto = new FlightCreationDto();
        //BeanUtils.copyProperties(flight, flightCreationDto);
        flightCreationDto.setId(flight.getId());
        flightCreationDto.setDepartureDate(flight.getDepartureDate());
        flightCreationDto.setDepartureAirportCode(flight.getDepartureAirportCode());
        flightCreationDto.setDepartureAirportName(flight.getDepartureAirportName());
        flightCreationDto.setDepartureCity(flight.getDepartureCity());
        flightCreationDto.setDepartureLocale(flight.getDepartureLocale());
        flightCreationDto.setArrivalAirportCode(flight.getArrivalAirportCode());
        flightCreationDto.setArrivalAirportName(flight.getArrivalAirportName());
        flightCreationDto.setArrivalCity(flight.getArrivalCity());
        flightCreationDto.setArrivalLocale(flight.getArrivalLocale());
        flightCreationDto.setArrivalDate(flight.getArrivalDate());
        flightCreationDto.setTicketPrice(flight.getTicketPrice());
        flightCreationDto.setTicketCurrency(flight.getTicketCurrency());
        flightCreationDto.setFlightNumber(flight.getFlightNumber());
        flightCreationDto.setSeatCapacity(flight.getSeatCapacity());

        return flightCreationDto;
    }

    public Flight toDetailsEntity(FlightDetailsDto flightDetailsDto){

        Flight flight = new Flight();
        //BeanUtils.copyProperties(flightDetailsDto, flight);
        flight.setDepartureDate(flightDetailsDto.getDepartureDate());
        flight.setDepartureAirportCode(flightDetailsDto.getDepartureAirportCode());
        flight.setDepartureAirportName(flightDetailsDto.getDepartureAirportName());
        flight.setDepartureCity(flightDetailsDto.getDepartureCity());
        flight.setDepartureLocale(flightDetailsDto.getDepartureLocale());
        flight.setArrivalAirportCode(flightDetailsDto.getArrivalAirportCode());
        flight.setArrivalAirportName(flightDetailsDto.getArrivalAirportName());
        flight.setArrivalCity(flightDetailsDto.getArrivalCity());
        flight.setArrivalLocale(flightDetailsDto.getArrivalLocale());
        flight.setArrivalDate(flightDetailsDto.getArrivalDate());
        flight.setTicketPrice(flightDetailsDto.getTicketPrice());
        flight.setTicketCurrency(flightDetailsDto.getTicketCurrency());
        flight.setFlightNumber(flightDetailsDto.getFlightNumber());
        flight.setSeatCapacity(flightDetailsDto.getSeatCapacity());

        return flight;
    }

    public FlightDetailsDto toDetailsDto(Flight flight){

        FlightDetailsDto flightDetailsDto = new FlightDetailsDto();
        //BeanUtils.copyProperties(flightDetailsDto, flight);
        flightDetailsDto.setDepartureDate(flight.getDepartureDate());
        flightDetailsDto.setDepartureAirportCode(flight.getDepartureAirportCode());
        flightDetailsDto.setDepartureAirportName(flight.getDepartureAirportName());
        flightDetailsDto.setDepartureCity(flight.getDepartureCity());
        flightDetailsDto.setDepartureLocale(flight.getDepartureLocale());
        flightDetailsDto.setArrivalAirportCode(flight.getArrivalAirportCode());
        flightDetailsDto.setArrivalAirportName(flight.getArrivalAirportName());
        flightDetailsDto.setArrivalCity(flight.getArrivalCity());
        flightDetailsDto.setArrivalLocale(flight.getArrivalLocale());
        flightDetailsDto.setArrivalDate(flight.getArrivalDate());
        flightDetailsDto.setTicketPrice(flight.getTicketPrice());
        flightDetailsDto.setTicketCurrency(flight.getTicketCurrency());
        flightDetailsDto.setFlightNumber(flight.getFlightNumber());
        flightDetailsDto.setSeatCapacity(flight.getSeatCapacity());

        return flightDetailsDto;
    }

    public Flight toUpdateEntity(FlightUpdateDto flightUpdateDto){

        Flight flight = new Flight();
        flight.setDepartureDate(flightUpdateDto.getDepartureDate());
        flight.setArrivalAirportCode(flightUpdateDto.getArrivalAirportCode());
        flight.setArrivalAirportCode(flightUpdateDto.getArrivalAirportCode());
        flight.setArrivalDate(flightUpdateDto.getArrivalDate());
        flight.setTicketPrice(flightUpdateDto.getTicketPrice());
        flight.setTicketCurrency(flightUpdateDto.getTicketCurrency());

        return flight;
    }

    public FlightUpdateDto toUpdateDto(Flight flight){

        FlightUpdateDto flightUpdateDto = new FlightUpdateDto();
        flightUpdateDto.setDepartureDate(flight.getDepartureDate());
        flightUpdateDto.setArrivalAirportCode(flight.getArrivalAirportCode());
        flightUpdateDto.setArrivalAirportCode(flight.getArrivalAirportCode());
        flightUpdateDto.setArrivalDate(flight.getArrivalDate());
        flightUpdateDto.setTicketPrice(flight.getTicketPrice());
        flightUpdateDto.setTicketCurrency(flight.getTicketCurrency());

        return flightUpdateDto;
    }
}
