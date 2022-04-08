package com.backweb.Trip.Infrastructure.Controller.V0;

import com.backweb.Trip.Application.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("v0/trip")
@RestController
public class DeleteTrip {
    @Autowired
    TripService tripService;

    @DeleteMapping("{id}")
    public void deleteTrip(@PathVariable UUID id) {
        tripService.deleteTrip(id);
    }
}
