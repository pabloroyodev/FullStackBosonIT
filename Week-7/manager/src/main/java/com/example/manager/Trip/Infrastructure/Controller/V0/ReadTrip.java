package com.example.manager.Trip.Infrastructure.Controller.V0;

import com.example.manager.Trip.Application.TripService;
import com.example.manager.Trip.Infrastructure.Controller.Dto.Output.TripOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public TripOutputDto filterTripById(@PathVariable Integer id) {
        return tripService.filterTripById(id);
    }
}
