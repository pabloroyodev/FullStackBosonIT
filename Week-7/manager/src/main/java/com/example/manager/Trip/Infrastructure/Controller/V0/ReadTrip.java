package com.example.manager.Trip.Infrastructure.Controller.V0;

import com.example.manager.Trip.Application.TripService;
import com.example.manager.Trip.Infrastructure.Controller.Dto.Output.TripOutputDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequestMapping("v0/trip")
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
}
