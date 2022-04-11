package com.backempresa.Trip.Infrastructure.Controller.V0;

import com.backempresa.Trip.Application.TripService;
import com.backempresa.Trip.Infrastructure.Controller.Dto.Output.TripCensoredOutputDto;
import com.backempresa.Trip.Infrastructure.Controller.Dto.Output.TripOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequestMapping("v0-empresa/trip")
@RestController
public class ReadTrip {
    @Autowired
    TripService tripService;

    @GetMapping
    public List<TripOutputDto> findAll(){
        return tripService.getAllTrip();
    }

    @GetMapping("{id}")
    public TripOutputDto filterTripById(@PathVariable UUID id) {
        return tripService.filterTripById(id);
    }

    @GetMapping("/details")
    public List<TripOutputDto> findByDepartureAndArrivalAndDate(@RequestParam String departure, @RequestParam String arrival, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date) {
        return tripService.findByDepartureAndArrivalAndDate(departure, arrival, date);
    }

    @GetMapping("/detailsLocalDate")
    public List<TripCensoredOutputDto> findByDepartureAndArrivalAndLocalDate(@RequestParam String departure, @RequestParam String arrival, @RequestParam String localDate) {
        return tripService.findByDepartureAndArrivalAndLocalDate(departure, arrival, localDate);
    }
}
