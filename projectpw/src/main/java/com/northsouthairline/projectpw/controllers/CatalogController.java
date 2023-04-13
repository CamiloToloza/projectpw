package com.northsouthairline.projectpw.controllers;

import com.northsouthairline.projectpw.controllers.dto.FlightCreationDto;
import com.northsouthairline.projectpw.controllers.dto.FlightDetailsDto;
import com.northsouthairline.projectpw.controllers.dto.FlightUpdateDto;
import com.northsouthairline.projectpw.controllers.dto.mapping.FlightMapper;
import com.northsouthairline.projectpw.entities.Flight;
import com.northsouthairline.projectpw.exceptions.EntityAlreadyExistsException;
import com.northsouthairline.projectpw.services.FlightServices;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/northsouthairline/flights")
public class CatalogController {
    private final FlightServices flightServices;
    private final FlightMapper flightMapper;

    public CatalogController(FlightMapper flightMapper, FlightServices flightServices) {
        this.flightMapper = flightMapper;
        this.flightServices = flightServices;
    }


    @PostMapping("/catalog/")
    public ResponseEntity<FlightCreationDto> create(@RequestBody FlightCreationDto flightDto){
        Flight flightCreated = null;
        try {
            flightCreated = flightServices.create(flightDto);
        } catch (Exception exception) {
            throw new EntityAlreadyExistsException();
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(flightCreated.getId())
                .toUri();

        return ResponseEntity.created(location).body(flightDto);
    }

    @PutMapping("/catalog/{id}")
    public ResponseEntity<Flight> update(@PathVariable("id")Long id,
                                                  @RequestBody FlightUpdateDto flightUpdateDto){
        return flightServices.update(id, flightUpdateDto)
                .map(flightUpdated -> ResponseEntity.ok().body(flightUpdated))
                .orElseGet(() -> {
                    Flight flightCreation = new Flight();
                    BeanUtils.copyProperties(flightUpdateDto, flightCreation);

                    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(flightCreation.getId())
                            .toUri();
                    return ResponseEntity.created(location).body(flightCreation);
                });
    }

    @GetMapping("/catalog/")
    public ResponseEntity<List<FlightDetailsDto>> searchByDepartureAndArrivalAirportAndDate(
            @RequestParam("departureAirportCode") String departureAirportCode,
            @RequestParam("arrivalAirportCode") String arrivalAirportCode,
            @RequestParam("departureDate") LocalDate departureDate){

        Optional<List<FlightDetailsDto>> flightsFound = flightServices.findByDepartureAndArrivalAirportAndDate(
                departureAirportCode, arrivalAirportCode, departureDate);
        return flightsFound.map(flights -> ResponseEntity.status(HttpStatus.OK).body(flights))
                .orElseThrow(() -> new RuntimeException("No flights were found with the previous properties"));
    }

    @GetMapping("/catalog/{airportCode}")
    public ResponseEntity<List<FlightDetailsDto>> findByAirportCode(@PathVariable("airportCode") String airportCode){
        Optional<List<FlightDetailsDto>> flightFound = flightServices.findByAirportCode(airportCode);

        return flightFound.map(flightDetailsDtos -> ResponseEntity.status(HttpStatus.OK).body(flightDetailsDtos))
                .orElseThrow(() -> new RuntimeException("No flights were found with the previous properties"));
     }
    @DeleteMapping("/catalog/{id}")
    public ResponseEntity<String> deleteByid(@PathVariable Long id) {
        flightServices.delete(id);
        return ResponseEntity.ok(String.format("The flight have been deleted: %d", id));
    }

}
