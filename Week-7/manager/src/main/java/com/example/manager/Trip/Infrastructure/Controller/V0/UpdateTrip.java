package com.example.manager.Trip.Infrastructure.Controller.V0;

import com.example.manager.Trip.Application.TripService;
import com.example.manager.Trip.Infrastructure.Controller.Dto.Input.TripInputDto;
import com.example.manager.Trip.Infrastructure.Controller.Dto.Output.TripOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("v0/trip")
@RestController
public class UpdateTrip {
    @Autowired
    TripService tripService;

    @PutMapping("{id}")
    public TripOutputDto updateTrip(@PathVariable UUID id, @RequestBody TripInputDto tripInputDto) {
        return tripService.updateTrip(id, tripInputDto);
    }
}
