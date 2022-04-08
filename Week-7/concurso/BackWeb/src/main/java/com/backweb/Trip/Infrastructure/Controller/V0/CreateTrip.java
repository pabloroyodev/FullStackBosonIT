package com.backweb.Trip.Infrastructure.Controller.V0;

import com.backweb.Trip.Application.TripService;
import com.backweb.Trip.Infrastructure.Controller.Dto.Input.TripInputDto;
import com.backweb.Trip.Infrastructure.Controller.Dto.Output.TripOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("v0/trip")
@RestController
public class CreateTrip {
    @Autowired
    TripService tripService;

    @PostMapping
    public TripOutputDto addTrip(@RequestBody TripInputDto tripInputDto) {
        return tripService.addTrip(tripInputDto);
    }
}
